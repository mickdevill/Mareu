package com.example.maru.UI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maru.R;
import com.example.maru.model.MeetUp;

import java.util.List;

//the class of recyclerView adapter
public class RcvAdapter extends RecyclerView.Adapter<RcvAdapter.holder> {
    //list for constructor and bindViewHolder
    private List<MeetUp> reuList;

    public RcvAdapter(List<MeetUp> reuList) {
        this.reuList = reuList;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rcv_element, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final holder holder, int position) {
        final MeetUp meetUp = reuList.get(position);
//details about meetup
        holder.sujet.setText(meetUp.getSujet() + " - " + minutsAndHours(meetUp.getHour()) + ":" + minutsAndHours(meetUp.getMinut()) +
                " - " + "room: " + meetUp.getRoom());

        holder.date.setText(meetUp.getDay() +
                "/" + coolMonth(meetUp.getMonth()) + "/" + meetUp.getYear());
        holder.members.setText(meetUp.getMembersMail());

        holder.deliteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reuList.remove(meetUp);
                MainActivity.initList();
            }
        });
    }

    @Override
    public int getItemCount() {
        return reuList.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        com.example.maru.customStaf.Circle preview;
        TextView sujet;
        TextView date;
        TextView members;
        ImageButton deliteBtn;

        public holder(@NonNull View itemView) {
            super(itemView);
            preview = itemView.findViewById(R.id.colorCircle);
            sujet = itemView.findViewById(R.id.meetupInfo);
            date = itemView.findViewById(R.id.DateInfo);
            members = itemView.findViewById(R.id.mailsInfo);
            deliteBtn = itemView.findViewById(R.id.remouve);


        }
    }

    //the methods for right info about time and date
    private String minutsAndHours(int time) {
        return time <= 9 ? "0" + time : String.valueOf(time);
    }


    private int coolMonth(int month) {
        return month += 1;
    }

}
