package com.example.dblab_2;

import androidx.appcompat.app.AppCompatActivity;
import android.opengl.GLDebugHelper;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MainActivity extends Activity {

    final String LOG_TAG = "myLogs";

    // Данные для таблицы должностей
    int[] position_id = {1, 2, 3, 4};
    int[] position_salary = {15000, 13000, 10000, 8000};
    String[] position_name = {"Директор", "Программер", "Бухгалтер", "Охранник"};

    // Данные для таблицы людей
    String[] people_name = {"Иван", "Марья", "Петр", "Антон", "Даша", "Борис", "Костя", "Игорь"};
    int[] people_posid = {2, 3, 2, 2, 3, 1, 2, 4};

    /**
     * Called when the activity is first created
     **/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Подключаемся к БД
        DBHelper dbh = new DBHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();

        // Описание курсора
        Cursor c;

        // Вывож в лог данных по должностям
        Log.d(LOG_TAG, "--- Table position ---");
        c = db.query("position", null, null, null, null, null, null);
        logCursor(c);
        c.close();
        Log.d(LOG_TAG, "--- ---");

        // Вывод в лог данных по людям
        Log.d(LOG_TAG, "--- Table people ---");
        c = db.query("people", null, null, null, null, null, null);
        logCursor(c);
        c.close();
        Log.d(LOG_TAG, "--- ---");

        // Вывод результата объединения
        // rawQuery
        Log.d(LOG_TAG, "--- INNER JOIN with rawQuery ---");
        String sqlQuery = "select PL.name as Name, PS.name as Position, salary as Salary "
                + "from people as PL "
                + "inner join position as PS "
                + "on PL.posid = PS.id "
                + "where salary > ?";
        c = db.rawQuery(sqlQuery, new String[]{"12000"});
        logCursor(c);
        c.close();
        Log.d(LOG_TAG, "--- ---");

        // Вывод результата объединения
        // query
        Log.d(LOG_TAG, "--- INNER JOIN with query ---");
        String table = "people as PL inner join position as PS on PL.posid = PS.id";
        String columns[] = {"PL.name as Name, PS.name as Position, salary as Salary"};
        String selection = "salary < ?";
        String[] selectionArgs = {"12000"};
        c = db.query(table, columns, selection, selectionArgs, null, null, null);
        logCursor(c);
        c.close();
        Log.d(LOG_TAG, "--- ---");

        dbh.close();
    }

    // Вывод данных из курсора в лог
    void logCursor(Cursor c) {
        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String cn : c.getColumnNames()) {
                        str = str.concat(cn + " = " + c.getString(c.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(LOG_TAG, str);
                }
                while (c.moveToNext());
            } else
                Log.d(LOG_TAG, "Cursor is null");
        }
    }

    // класс для работы с БД
    class DBHelper extends SQLiteOpenHelper
    {
        public DBHelper(Context context)
        {
            super(context, "myBD", null, 1);
        }

        public void onCreate(SQLiteDatabase db)
        {
            Log.d(LOG_TAG, "--- onCreate db ---");
            ContentValues cv = new ContentValues();

            // создание таблицы должностей
            db.execSQL("create table position ("
            + "id integer primary key,"
            + "name text," + "salary integer"
            + ");");

            // заполнение
            for (int i = 0; i < position_id.length; i++)
            {
                cv.clear();
                cv.put("id", position_id[i]);
                cv.put("name", position_name[i]);
                cv.put("salary", position_salary[i]);
                db.insert("position", null, cv);
            }

            // создание таблицы людей
            db.execSQL("create table people ("
            + "id integer primary key autoincrement,"
            + "name text,"
            + "posid integer"
            + ");");

            // заполнение
            for (int i = 0; i < people_name.length; i++)
            {
                cv.clear();
                cv.put("name", people_name[i]);
                cv.put("posid", people_posid[i]);
                db.insert("people", null, cv);
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {

        }
    }
}
