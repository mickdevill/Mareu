package com.example.maru.VisualSide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maru.R;
import com.example.maru.model.laReunion;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class rcvAdapter extends RecyclerView.Adapter<rcvAdapter.holder> {

    List<laReunion> reuList;

    public rcvAdapter(List<laReunion> reuList) {
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
    public void onBindViewHolder(@NonNull holder holder, int position) {
        final laReunion reunion = reuList.get(position);
        holder.sujet.setText(reunion.getSujet() + " - " + reunion.getHeur() + ":" + reunion.getMinut() + " - " + reunion.getJour() +
                "/" + reunion.getMois() + "/" + reunion.getAné() + " - " + "sale n°"+ reunion.getRoomNum() );
        holder.members.setText(reunion.getMembersMail());

        holder.deliteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new deliteReuEvent(reunion));
            }
        });
    }

    @Override
    public int getItemCount() {
        return reuList.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        ImageView preview;
        TextView sujet;
        TextView members;
        ImageButton deliteBtn;

        public holder(@NonNull View itemView) {
            super(itemView);
            preview = itemView.findViewById(R.id.reuPreview);
            sujet = itemView.findViewById(R.id.ReuSujetAndInfo);
            members = itemView.findViewById(R.id.reuMail);
            deliteBtn = itemView.findViewById(R.id.reuDelite);


        }
    }

}
