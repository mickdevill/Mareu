package com.example.maru.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.maru.R;
import com.example.maru.di.DI;
import com.example.maru.model.MeetUp;
import com.example.maru.service.Service;
import com.google.android.material.textfield.TextInputEditText;

public class AddNewMeetup extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {
    //my Views
    private TextInputEditText sujet;
    private TextInputEditText mails;
    private Spinner room;
    private TextView textTime;
    private ImageButton setTime;
    private TextView textDate;
    private ImageButton setDate;
    private Button save;
    //my var for logic and add
    int hourAdd;
    int minutAdd;
    int monthAdd;
    int dayAdd;
    int yearAdd;
    String theRoom;
    //sevice
    Service service;

    public static void start(Activity Caller) {
        Intent intent = new Intent(Caller, AddNewMeetup.class);
        Caller.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //find all by id
        setContentView(R.layout.add_new_meetup);
        sujet = findViewById(R.id.aboutMeetup);
        mails = findViewById(R.id.membersMails);
        room = findViewById(R.id.room);
        textTime = findViewById(R.id.textTime);
        setTime = findViewById(R.id.imageButtonTime);
        textDate = findViewById(R.id.textDate);
        setDate = findViewById(R.id.imageButtonDate);
        save = findViewById(R.id.save);
//init sesrvice
        service = DI.getService();

        //array addapter for the room spiner
        ArrayAdapter<CharSequence> roomAdapter = ArrayAdapter.createFromResource(this, R.array.room, android.R.layout.simple_spinner_item);
        roomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        room.setAdapter(roomAdapter);
        room.setOnItemSelectedListener(this);

        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePikerDialogUi timePikerDialog = new TimePikerDialogUi();
                timePikerDialog.show(getSupportFragmentManager(), "setTheTime");
            }
        });

        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialogUi datePickerDialog = new DatePickerDialogUi();
                datePickerDialog.show(getSupportFragmentManager(), "setTheDate");
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sujet.getEditableText().toString().isEmpty() ||
                        !mails.getEditableText().toString().contains("@") ||
                        !mails.getEditableText().toString().contains(".") ||
                        dayAdd == 0 || monthAdd == 0 || yearAdd == 0 || hourAdd == 0 ||
                        minutAdd == 0) {
                    Toast.makeText(AddNewMeetup.this, R.string.Add_error, Toast.LENGTH_SHORT).show();
                } else {
                    MeetUp reunion = new MeetUp(yearAdd, monthAdd, dayAdd, hourAdd, minutAdd,
                            theRoom, sujet.getEditableText().toString(), mails.getEditableText().toString());
                    service.addNew(reunion);
                    finish();
                }
            }
        });

    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        textDate.setText(dayOfMonth + "/" + coolMonth(month) + "/" + year);
        dayAdd = dayOfMonth;
        monthAdd = month;
        yearAdd = year;

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        textTime.setText(minutsAndHours(hourOfDay) + ":" + minutsAndHours(minute));
        hourAdd = hourOfDay;
        minutAdd = minute;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        theRoom = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        theRoom = "A";
    }

    //methods like in RecyclerView, used to make right text in date&time TextViews
    private String minutsAndHours(int time) {
        return time <= 9 ? "0" + time : String.valueOf(time);
    }


    private int coolMonth(int month) {
        return month += 1;
    }

}


