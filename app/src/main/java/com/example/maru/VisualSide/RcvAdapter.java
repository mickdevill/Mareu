package com.example.maru.VisualSide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maru.R;
import com.example.maru.model.LaRéunion;

import java.util.List;


public class RcvAdapter extends RecyclerView.Adapter<RcvAdapter.holder> {

    List<LaRéunion> reuList;

    public RcvAdapter(List<LaRéunion> reuList) {
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
        final LaRéunion reunion = reuList.get(position);

        holder.sujet.setText(reunion.getSujet() + " - " + minutsAndHours(reunion.getHeur()) + ":" + minutsAndHours(reunion.getMinut()) + " - " + reunion.getJour() +
                "/" + coolMonth(reunion.getMois()) + "/" + reunion.getAné() + " - " + "salle n°" + reunion.getRoomNum());
        holder.members.setText(reunion.getMembersMail());

        holder.deliteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reuList.remove(reunion);
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

    //pour le bon affichaage de l'heure
    private String minutsAndHours(int time) {
        return time <= 9 ? "0" + time : String.valueOf(time);
    }

    //pour le bon affichage du mois
    private int coolMonth(int month) {
        return month += 1;
    }

}
