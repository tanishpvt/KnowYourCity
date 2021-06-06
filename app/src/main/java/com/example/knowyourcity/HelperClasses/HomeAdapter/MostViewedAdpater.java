package com.example.knowyourcity.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.knowyourcity.R;

import java.util.ArrayList;

public class MostViewedAdpater extends RecyclerView.Adapter<MostViewedAdpater.MostViewedHolder> {

    ArrayList<MostViewHelperClass> mostViewedLocation;

    public MostViewedAdpater(ArrayList<MostViewHelperClass> mostViewedLocation) {
        this.mostViewedLocation = mostViewedLocation;
    }

    @NonNull
    @Override
    public MostViewedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_viewed_card_design,parent,false);
        MostViewedHolder mostViewedHolder = new MostViewedHolder(view);
        return mostViewedHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MostViewedHolder holder, int position) {
       MostViewHelperClass mostViewHelperClass = mostViewedLocation.get(position);

       holder.image.setImageResource(mostViewHelperClass.getImage());
       holder.title.setText(mostViewHelperClass.getTitle());
       holder.desc.setText(mostViewHelperClass.getDescription());
    }

    @Override
    public int getItemCount() {
        return mostViewedLocation.size();
    }

    public static class MostViewedHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, desc;

        public MostViewedHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.mv_image);
            title = itemView.findViewById(R.id.mv_title);
            desc = itemView.findViewById(R.id.mv_desc);
        }
    }
}
