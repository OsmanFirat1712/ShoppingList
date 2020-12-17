package com.example.shoppinglistof;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.widget.GridLayout;

import java.util.concurrent.Callable;
import java.util.zip.Inflater;

import room.DataBase;
import room.TaskRunner;

public class MainActivity extends AppCompatActivity {

    private GridLayoutManager gridLayoutManager;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DataBase dataBase;

    public MainActivity() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= findViewById(R.id.my_recycler_view);
        layoutManager = new GridLayoutManager(this, 2);
/*
        dataBase.getInstance(this).shoppingDao().getAll();
*/

        Toolbar toolbar = findViewById(R.id.toolbarmain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(R.string.Einkaufsliste);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, new MainFragment());
        transaction.commit();
/*

        TaskRunner runner = new TaskRunner();
        runner.executeAsync(() -> {
            dataBase.shoppingDao().getAll();
            return null;
        }, (result) -> {
            closeContextMenu();
            Callable<Object> callable = new Callable<Object>() {
                public Object call() throws Exception {


                }
            };
        });
*/


    }
}