package com.example.maru.UI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maru.R;
import com.example.maru.di.DI;
import com.example.maru.model.MeetUp;
import com.example.maru.service.Service;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FiltreFragmentDialog.filtreListener {

    private static Service service;

    FloatingActionButton fab;
    static RecyclerView reuList;
    static List<MeetUp> listForAdapter;
    public static FragmentManager forRemoveManager;

    //method to essy call dialog and don't writ extra same code
    public void callTheDialog(int curentFilter) {
        FiltreFragmentDialog.newInstance(curentFilter).show(getSupportFragmentManager(), "filterthat");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        service = DI.getService();
        forRemoveManager = getSupportFragmentManager();
        reuList = findViewById(R.id.rcvList);
        fab = findViewById(R.id.fab);

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        reuList.setLayoutManager(manager);
        reuList.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewMeetup.start(MainActivity.this);

            }
        });
    }

    //all menu actions(4), used to call right filter
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.normalFILTR) {
            listForAdapter = service.getAll();
            reuList.setAdapter(new RcvAdapter(listForAdapter));

        }

        if (id == R.id.PlaceFILTR) {
            callTheDialog(1);
        }

        if (id == R.id.DateFILTR) {
            callTheDialog(2);
        }

        if (id == R.id.TimeFILTR) {
            callTheDialog(3);
        }

        return super.onOptionsItemSelected(item);
    }


    public static void initList() {
        listForAdapter = service.getAll();
        reuList.setAdapter(new RcvAdapter(listForAdapter));
    }


    @Override
    protected void onResume() {
        super.onResume();
        initList();
    }

    //the method to get filtered list from FiltreFragmentDialog
    @Override
    public void filterInfo(List<MeetUp> listForFilter) {
        reuList.setAdapter(new RcvAdapter(listForFilter));
    }

}
