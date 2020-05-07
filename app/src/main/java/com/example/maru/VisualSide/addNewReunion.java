package com.example.maru.VisualSide;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.maru.di.DI;
import com.example.maru.model.laReunion;
import com.example.maru.model.reuService;
import com.google.android.material.button.MaterialButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.maru.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;


public class addNewReunion extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    reuService service;

    TextInputEditText sujet;
    CalendarView calendar;
    ImageButton setTime;
    TextInputEditText members;
    Button save;
    TextView timeText;
    TextInputEditText roomnum;
    int ané;
    int moi;
    int jour;
    int hour;
    int min;

    public static void start(Activity caller) {
        Intent intent = new Intent(caller, addNewReunion.class);
        caller.startActivity(intent);
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_reu);
        service = DI.getService();
        sujet = findViewById(R.id.sujetInAddMenu);
        calendar = findViewById(R.id.calendarView2);
        setTime = findViewById(R.id.imageButton);
        members = findViewById(R.id.mailInput);
        save = findViewById(R.id.saveBtn);
        timeText = findViewById(R.id.textView2);
        roomnum = findViewById(R.id.roomnum);



        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment TimePiker = new TimePikerFrag();
                TimePiker.show(getSupportFragmentManager(), "set the time");
            }
        });

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, final int year, final int month, final int dayOfMonth) {
                ané = year;
                moi = month;
                jour = dayOfMonth;
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sujet.getEditableText().length() == 0 ||
                        members.getEditableText().length() == 0 ||
                        ané < 0 || moi < 0 || jour < 0 || hour < 0 || min < 0 ||
                        roomnum.getEditableText().length() == 0
                        || parseInt(roomnum.getEditableText().toString()) > 10)
                { Toast.makeText(addNewReunion.this,"hey you motherfucker!!! watch yourself!!! if don't want see NullpointerExeption!!!",Toast.LENGTH_LONG).show();}

                else { laReunion reunion = new laReunion(ané, moi, jour, hour, min,
                        parseInt(roomnum.getEditableText().toString()),
                        sujet.getEditableText().toString(), members.getEditableText().toString());
                service.addNew(reunion);
                finish();

            }      }
        });


    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        timeText.setText(hourOfDay + " : " + minute);
        hour = hourOfDay;
        min = minute;
    }
}
