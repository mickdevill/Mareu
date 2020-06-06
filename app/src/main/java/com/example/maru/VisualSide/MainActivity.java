package com.example.maru.VisualSide;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maru.R;
import com.example.maru.di.DI;
import com.example.maru.model.LaRéunion;
import com.example.maru.model.ReuService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FiltreFragmentDialog.filtreListner,
        TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private static ReuService service;

    FloatingActionButton fab;
    static RecyclerView reuList;
    //la liste des réunions
    static List<LaRéunion> listForAdapter;


    //чтобы приложение выдовало коректный фильтр!
    static int curentFilter;
    //le fragment manager pour appeler de DialogFragment
    public static FragmentManager mManager;
    //for add new!!!
    static int dayAdd;
    static int monthAdd;
    static int yearAdd;
    static int hourAdd;
    static int minutAdd;


    public void callTheDialog(int curentFilter) {
        // addNewReunion.start(this);
        FiltreFragmentDialog.newInstance(curentFilter).show(getSupportFragmentManager(), "filterthat");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mManager = getSupportFragmentManager();
        service = DI.getService();


        reuList = findViewById(R.id.rcvList);
        fab = findViewById(R.id.fab);

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        reuList.setLayoutManager(manager);
        reuList.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        curentFilter = 4;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callTheDialog(4);
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


    @Override
    public void filterInfo(List<LaRéunion> reunionListff) {
        reuList.setAdapter(new RcvAdapter(reunionListff));
    }

    //l'ajout de la reunion qui se fait dans la MainActivity
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hourAdd = hourOfDay;
        minutAdd = minute;


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearAdd = year;
        monthAdd = month;
        dayAdd = dayOfMonth;
    }


    @Override
    public void foraddnewinfo(int roomNum, String sujet, String membersMail) {
        if (yearAdd < 0 || monthAdd < 0 || dayAdd < 0 || hourAdd < 0 || minutAdd < 0) {
            Toast.makeText(this, "Il faut indiquer la date et le temps", Toast.LENGTH_LONG).show();

        } else {
            LaRéunion reunion = new LaRéunion(yearAdd, monthAdd, dayAdd, hourAdd, minutAdd, roomNum, sujet, membersMail);
            service.addNew(reunion);
            reuList.setAdapter(new RcvAdapter(listForAdapter));
        }
    }


}
