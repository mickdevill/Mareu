package com.example.maru.VisualSide;

import android.content.Intent;
import android.os.Bundle;

import com.example.maru.R;
import com.example.maru.model.laReunion;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maru.di.DI;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.example.maru.model.reuService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements filtreFragmentDialog.filtreListner{

    private reuService service;

    //обект собрания который нужен для фильтрации
laReunion reunion;

FloatingActionButton fab;
    RecyclerView reuList;
    //список для адаптера по дефолту и основной список
    List<laReunion> listForAdapter;
    //список для фильтрации!
    List<laReunion>listForFiltr = Arrays.asList();
//чтобы приложение выдовало коректный фильтр!
int curentFilter;
//числа для того чтобы фильтровать по времени!


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        service = DI.getService();

        reuList = findViewById(R.id.rcvList);
        fab = findViewById(R.id.fab);

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        reuList.setLayoutManager(manager);
        reuList.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewReunion.start(MainActivity.this);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.normalFILTR) {
          initList();
            Toast.makeText(this,"normal FILTR",Toast.LENGTH_SHORT).show();
        }

        if (id == R.id.PlaceFILTR){
            Toast.makeText(this,"Place FILTR",Toast.LENGTH_SHORT).show();
            curentFilter = 1;
            filtreFragmentDialog.newInstance(curentFilter).show(getSupportFragmentManager(),"filterPlace");
        }
             if (id == R.id.TimeFILTR){
                 Toast.makeText(this,"Time FILTR",Toast.LENGTH_SHORT).show();
                curentFilter = 2;
                 filtreFragmentDialog.newInstance(curentFilter).show(getSupportFragmentManager(),"filtertime");
             }
             if (id == R.id.PlaceAndTimeFILTR){
                 Toast.makeText(this,"Place And Time FILTR",Toast.LENGTH_SHORT).show();
                 curentFilter = 3;
                 filtreFragmentDialog.newInstance(curentFilter).show(getSupportFragmentManager(),"PlaceAndTimeFILTR");
             }



             return super.onOptionsItemSelected(item);
    }



    public void initList(){
        listForAdapter = service.getAll();
      reuList.setAdapter(new rcvAdapter(listForAdapter));}




    @Override
    protected void onResume() {
        super.onResume();
    initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(deliteReuEvent event) {
        service.removeReu(event.reunion);
        initList();
    }

    @Override
    public void filterInfo(int roomNum, int year, int mouth, int day, int hour, int minut) {
listForAdapter = service.getAll();
       if (curentFilter == 1) {
           listForFiltr = service.getAll();
           service.filterP(reunion, roomNum, listForFiltr);
           reuList.setAdapter(new rcvAdapter(listForFiltr));
       }if (curentFilter == 2){
            listForFiltr = service.getAll();
         service.filterD(reunion,year,mouth,day,listForFiltr);
            reuList.setAdapter(new rcvAdapter(listForFiltr));
        }

        // Toast.makeText(this,"le filtre passe la valeur " + roomNum,Toast.LENGTH_SHORT).show();
    }
}
