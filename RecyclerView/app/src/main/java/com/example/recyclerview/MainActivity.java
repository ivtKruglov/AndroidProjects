package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
    }

    private void initRecyclerView()
    {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new GrumpyCatAdapter(generateCats());
        recyclerView.setAdapter(adapter);
    }

    private static List<GrumpyCat> generateCats()
    {
        List<GrumpyCat> grumpyCats = new ArrayList<>();
        grumpyCats.add(new GrumpyCat("Grumpy_Cat", "https://i.imgur.com/vvBNLTg.jpg"));
        grumpyCats.add(new GrumpyCat("1", "https://i.imgur.com/Bkg5wek.jpg"));
        grumpyCats.add(new GrumpyCat("456789", "https://i.imgur.com/IqE14jF.jpg"));
        grumpyCats.add(new GrumpyCat("Grumpy", "https://i.imgur.com/wYTCtRu.jpg"));
        grumpyCats.add(new GrumpyCat("recycler_app", "https://i.imgur.com/0XEanXwg.jpg"));
        grumpyCats.add(new GrumpyCat("huif", "https://i.imgur.com/ajbm4IW.jpg"));
        grumpyCats.add(new GrumpyCat("asadv", "https://i.imgur.com/7XX4NTh.jpg"));
        grumpyCats.add(new GrumpyCat("gdb", "https://i.imgur.com/C8uoUMm.jpg"));
        grumpyCats.add(new GrumpyCat("sdbsgt", "https://i.imgur.com/CkXnahk.jpg"));
        grumpyCats.add(new GrumpyCat("avsbrw", "https://i.imgur.com/pqggrK0.jpg"));
        grumpyCats.add(new GrumpyCat("sdbsnet", "https://i.imgur.com/9if0pJQ.jpg"));
        grumpyCats.add(new GrumpyCat("rsfmrtnrt", "https://i.imgur.com/zt7smR4.jpg"));
        grumpyCats.add(new GrumpyCat("qwerty", "https://i.imgur.com/8PDnHK2.jpg"));
        return grumpyCats;
    }
}
