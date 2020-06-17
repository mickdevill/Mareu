package com.example.maru.UI;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.maru.R;
import com.example.maru.di.DI;
import com.example.maru.model.MeetUp;
import com.example.maru.service.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.lang.Integer.parseInt;

//the class responsible for all dialogs in the app
public class FiltreFragmentDialog extends AppCompatDialogFragment implements AdapterView.OnItemSelectedListener {


    List<MeetUp> listForFiltr;
    private static Service service;
    private filtreListener Listener;
    static final String key = "munuChoice";
    int wm;
    View view;

    //for room filter
    Spinner room;
    String roomTittle;
    //for date filter
    private CalendarView datePicker;
    private int year;
    private int mouth;
    private int day;

    //for time filter
    private TimePicker timePicker;
    private int hour;
    private int minut;

    //the method to don't write inflater for all dialogs, and create dialog
    private void createTheDialog(int layout, LayoutInflater inflater) {
        view = inflater.inflate(layout, null);
    }

    //method to get to get info what usr click on menu
    static FiltreFragmentDialog newInstance(int curentFilter) {
        FiltreFragmentDialog fragment = new FiltreFragmentDialog();
        Bundle arguments = new Bundle();
        arguments.putInt(key, curentFilter);
        fragment.setArguments(arguments);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        wm = getArguments().getInt(key);
        service = DI.getService();
        listForFiltr = new ArrayList<>(service.getAll());


        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();

        //for room/place filter
        if (wm == 1) {
            createTheDialog(R.layout.room_filter, inflater);
            room = view.findViewById(R.id.roomFilter);
            ArrayAdapter<CharSequence> roomAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.room, android.R.layout.simple_spinner_item);
            roomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            room.setAdapter(roomAdapter);
            room.setOnItemSelectedListener(this);
        }
        //for date filter
        if (wm == 2) {
            createTheDialog(R.layout.date_filter, inflater);
            datePicker = view.findViewById(R.id.DatepikerAdaptedForApi);
            datePicker.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    FiltreFragmentDialog.this.year = year;
                    mouth = month;
                    day = dayOfMonth;
                }
            });

        }
        //for time filter
        if (wm == 3) {
            createTheDialog(R.layout.time_filter, inflater);
            timePicker = view.findViewById(R.id.Timepikerfordialog);
            timePicker.setIs24HourView(true);

            timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                    hour = hourOfDay;
                    minut = minute;
                }
            });

        }
        if (wm == 4) {
            createTheDialog(R.layout.remouve_dialog, inflater);
        }


        builder.setView(view);

        //set the right tittle to dialog window
        if (wm == 1) {
            builder.setTitle(R.string.filter_by_place);
        }
        if (wm == 2) {
            builder.setTitle(R.string.filter_by_date);
        }
        if (wm == 3) {
            builder.setTitle(R.string.filter_by_time);
        }


        //the negative button
        builder.setNegativeButton(R.string.negative_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
//the positive button used for all filters
        if (wm != 4) {
            builder.setPositiveButton(R.string.positive_button, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    if (wm == 1) {
                        service.filterPlace(roomTittle, listForFiltr);
                        Listener.filterInfo(listForFiltr);
                    }

                    if (wm == 2) {
                        if (day == 0 || mouth == 0 || year == 0) {
                            Calendar calendar = Calendar.getInstance();
                            day = calendar.get(Calendar.DAY_OF_MONTH);
                            mouth = calendar.get(Calendar.MONTH);
                            year = calendar.get(Calendar.YEAR);

                            service.filterDate(day, mouth, year, listForFiltr);
                            Listener.filterInfo(listForFiltr);
                        } else {
                            service.filterDate(day, mouth, year, listForFiltr);
                            Listener.filterInfo(listForFiltr);
                        }
                    }

                    if (wm == 3) {
                        if (hour == 0 & minut == 0) {
                            Calendar c = Calendar.getInstance();
                            hour = c.get(Calendar.HOUR_OF_DAY);
                            minut = c.get(Calendar.MINUTE);

                            service.filterTime(hour, minut, listForFiltr);
                            Listener.filterInfo(listForFiltr);

                        } else {
                            service.filterTime(hour, minut, listForFiltr);
                            Listener.filterInfo(listForFiltr);
                        }
                    }


                }
            });

        }

        return builder.create();

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Listener = (filtreListener) context;
    }


    public interface filtreListener {
        void filterInfo(List<MeetUp> listForFilter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        roomTittle = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        roomTittle = "A";
    }
}
