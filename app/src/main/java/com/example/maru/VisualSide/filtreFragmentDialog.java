package com.example.maru.VisualSide;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.example.maru.R;
import com.example.maru.di.DI;
import com.example.maru.model.laReunion;
import com.example.maru.model.reuService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import static java.lang.Integer.parseInt;

import java.util.Calendar;

public class filtreFragmentDialog extends AppCompatDialogFragment {

private filtreListner Listener;


    TextInputEditText roomnum;
    static final String key = "munuChoice";
    int wm;
    View view;
//pour le filtre par la date
int yearf2;
int mouthf2;
int dayf2;

    static filtreFragmentDialog newInstance(int curentFilter) {
        filtreFragmentDialog fragment = new filtreFragmentDialog();
        Bundle arguments = new Bundle();
        arguments.putInt(key, curentFilter);
        fragment.setArguments(arguments);
        return fragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        wm = getArguments().getInt(key);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        if (wm == 1) {
            view = inflater.inflate(R.layout.filtre_place_dialog, null);
            roomnum = view.findViewById(R.id.inputFiltreP);

        }
        if (wm == 2) {
            view = inflater.inflate(R.layout.time_filter_dialog, null);
            final CalendarView dpf2 = view.findViewById(R.id.dpf2);
            dpf2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    yearf2 = year;
                    mouthf2 = month;
                    dayf2 = dayOfMonth;
                }
            });
        }
        if (wm == 3){

        }


            builder.setView(view);
        builder.setTitle("filter by Place");
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("filter", new DialogInterface.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialog, int which) {
              if (wm == 1){
                  int RN = parseInt(roomnum.getEditableText().toString());
                if (RN>0){
               if (wm == 1){
                Listener.filterInfo(RN,0,0,0,0,0);}}
              }
if (wm == 2){
    Listener.filterInfo(0,yearf2,mouthf2,dayf2,0,0);}
}
        });






return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    Listener = (filtreListner) context;
    }

    public interface filtreListner{
        void filterInfo (int roomNum, int year, int mouth, int day, int hour, int minut);
    }

}
