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

public class CatogeriesAdapter extends RecyclerView.Adapter<CatogeriesAdapter.CatogeriesViewHolder> {

    ArrayList<CatogeriesHelperClass> catogeriesLocations;

    public CatogeriesAdapter(ArrayList<CatogeriesHelperClass> catogeriesLocations) {
        this.catogeriesLocations = catogeriesLocations;
    }

    @NonNull
    @Override
    public CatogeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_card_design,parent,false);
        CatogeriesViewHolder catogeriesViewHolder = new CatogeriesViewHolder(view);

        return catogeriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatogeriesViewHolder holder, int position) {
        CatogeriesHelperClass catogeriesHelperClass = catogeriesLocations.get(position);
        holder.image.setImageResource(catogeriesHelperClass.getImage());
        holder.title.setText(catogeriesHelperClass.getTitle());


    }

    @Override
    public int getItemCount() {
        return catogeriesLocations.size();
    }

    public static class CatogeriesViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;

        public CatogeriesViewHolder(@NonNull View itemView) {
            super(itemView);
            //Hooks
            image = itemView.findViewById(R.id.catogeries_image);
            title = itemView.findViewById(R.id.catogeries_title);
        }
    }
}
