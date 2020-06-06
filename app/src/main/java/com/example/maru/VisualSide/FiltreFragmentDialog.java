package com.example.maru.VisualSide;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.example.maru.R;
import com.example.maru.di.DI;
import com.example.maru.model.LaRéunion;
import com.example.maru.model.ReuService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.lang.Integer.parseInt;

//la classe qui gere touts les dialogue
public class FiltreFragmentDialog extends AppCompatDialogFragment {

    //les variqble de la logic des filtres
    List<LaRéunion> listForFiltr;
    private static ReuService service;
    private filtreListner Listener;
    static final String key = "munuChoice";
    int wm;
    View view;

    //pou filtrer par la salle
    TextInputEditText roomnum;

    //pour le filtrer par la date.
    // oui le Calendar est applé datepicker car le vrai datepicker ne suporte pas api21 et selui de DialogFragment prend le contexte que prend le dialog donc
    //on peut pas avoir la date dans le filterFragmentDiaolg, que dans MainActivity
    private CalendarView datePicker;
    private int yearf2;
    private int mouthf2;
    private int dayf2;

    //pour le filtrer par le temps
    private TimePicker timePicker;
    private int hourfE;
    private int minutfE;

    //pour ajouté une nouvelle réuninon
    private TextInputEditText sujetInAdd;
    private ImageButton imageButtonTime;
    private TextInputEditText roomnumidadd;
    private ImageButton imageButtonDate;
    private TextInputEditText membersinadd;

    //la méthode pour montrer le bon dialogue
    private void createTheDialog(int layres, LayoutInflater inflater) {
        view = inflater.inflate(layres, null);
    }

    //la méthode pour appler le bon dialogue
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
        //pour le dialogue de place

        if (wm == 1) {
            createTheDialog(R.layout.filtre_place_dialog, inflater);
            roomnum = view.findViewById(R.id.inputFiltreP);
        }
        //pour le dialogue de date
        if (wm == 2) {
            createTheDialog(R.layout.date_filter, inflater);
            datePicker = view.findViewById(R.id.DatepikerAdaptedForApi);
            datePicker.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    yearf2 = year;
                    mouthf2 = month;
                    dayf2 = dayOfMonth;
                }
            });

        }
        //pour le dialogue de temps
        if (wm == 3) {
            createTheDialog(R.layout.time_filter, inflater);
            timePicker = view.findViewById(R.id.Timepikerfordialog);
            timePicker.setIs24HourView(true);

            timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                    hourfE = hourOfDay;
                    minutfE = minute;
                }
            });

        }

        builder.setView(view);

        //metre le bon nom au dialogue
        if (wm == 1) {
            builder.setTitle("filtrer Place");
        }
        if (wm == 2) {
            builder.setTitle("filtrer date");
        }
        if (wm == 3) {
            builder.setTitle("filtrer temps");
        }
        if (wm == 4) {
            builder.setTitle("ajouté une réunion");
        }
        //le bouton négative
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
//le bouton positive
        builder.setPositiveButton("FILTRER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //la logique de touts les filtres voir en bas

                if (wm == 1) {
                    if (roomnum.getEditableText().toString().isEmpty()) {
                        Toast.makeText(view.getContext(), "insséré une valeure", Toast.LENGTH_SHORT).show();
                    } else {
                        int RN = parseInt(roomnum.getEditableText().toString());
                        service.filterPlace(RN, listForFiltr);
                        Listener.filterInfo(listForFiltr);
                    }
                }

                if (wm == 2) {
                    if (dayf2 == 0 || mouthf2 == 0 || yearf2 == 0) {
                        Calendar calendar = Calendar.getInstance();
                        dayf2 = calendar.get(Calendar.DAY_OF_MONTH);
                        mouthf2 = calendar.get(Calendar.MONTH);
                        yearf2 = calendar.get(Calendar.YEAR);

                        service.filterDate(dayf2, mouthf2, yearf2, listForFiltr);
                        Listener.filterInfo(listForFiltr);
                    } else {
                        service.filterDate(dayf2, mouthf2, yearf2, listForFiltr);
                        Listener.filterInfo(listForFiltr);
                    }
                }

                if (wm == 3) {
                    if (hourfE == 0 & minutfE == 0) {
                        Calendar c = Calendar.getInstance();
                        hourfE = c.get(Calendar.HOUR_OF_DAY);
                        minutfE = c.get(Calendar.MINUTE);

                        service.filterTime(hourfE, minutfE, listForFiltr);
                        Listener.filterInfo(listForFiltr);
                    } else {
                        service.filterTime(hourfE, minutfE, listForFiltr);
                        Listener.filterInfo(listForFiltr);
                    }
                }
            }
        });
        //l'ajout d'une nouvelle réunion
        if (wm == 4) {
            createTheDialog(R.layout.universal_add_reu, inflater);
            sujetInAdd = view.findViewById(R.id.sujetInAdd);
            imageButtonTime = view.findViewById(R.id.imageButtonTime);
            roomnumidadd = view.findViewById(R.id.roomnumidadd);
            imageButtonDate = view.findViewById(R.id.imageButtonDate);
            membersinadd = view.findViewById(R.id.membersinadd);


            builder.setView(view);
            imageButtonTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment TimePiker = new TimePikerDialogVS();
                    TimePiker.show(MainActivity.mManager, "set the time");
                }
            });


            imageButtonDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment DatePicker = new DatePickerDialogVS();
                    DatePicker.show(MainActivity.mManager, "set the date");
                }
            });


            //Negative Buttont
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            //Positive Button
            builder.setPositiveButton("Add New", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if (sujetInAdd.getEditableText().toString().isEmpty() ||
                            membersinadd.getEditableText().toString().isEmpty() ||
                            roomnumidadd.getEditableText().toString().isEmpty() ||
                            parseInt(roomnumidadd.getEditableText().toString()) > 10) {

                        Toast.makeText(view.getContext(), "veiller coomplété touts les champs CORECTEMENT", Toast.LENGTH_SHORT).show();
                    } else {
                        Listener.foraddnewinfo(parseInt(roomnumidadd.getEditableText().toString()), sujetInAdd.getEditableText().toString(), membersinadd.getEditableText().toString());
                    }


                }
            });

            return builder.create();
        }

        return builder.create();

    }

    //la méthode magique pour rransmétre les valeur
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Listener = (filtreListner) context;
    }


    public interface filtreListner {
        void filterInfo(List<LaRéunion> reunionListff);

        void foraddnewinfo(int roomNum, String sujet, String membersMail);
    }
}
