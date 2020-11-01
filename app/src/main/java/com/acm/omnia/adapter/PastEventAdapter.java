package com.acm.omnia.adapter;

import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.acm.omnia.Model.PastEvent;
import com.acm.omnia.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PastEventAdapter extends RecyclerView.Adapter<PastEventAdapter.PastEventViewHolder> {

    ArrayList<PastEvent> pastEvents;
    float brightness = 8; // change values to suite your need
    float[] colorMatrix = {
            0.33f, 0.33f, 0.33f, 0, brightness,
            0.33f, 0.33f, 0.33f, 0, brightness,
            0.33f, 0.33f, 0.33f, 0, brightness,
            0, 0, 0, 1, 0
    };

    public PastEventAdapter(ArrayList<PastEvent> pastEvents) {
        this.pastEvents = pastEvents;
    }

    @NonNull
    @Override
    public PastEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.past_event_single, parent, false);
        return new PastEventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PastEventViewHolder holder, int position) {
        PastEvent pastEvent = pastEvents.get(position);
        holder.txtEventTitle.setText(pastEvent.getEventTitle());
        Picasso.get().load(pastEvent.getImgUrl()).into(holder.imgPastEvent);
        ColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
        holder.imgPastEvent.setColorFilter(colorFilter);
    }

    @Override
    public int getItemCount() {
        return pastEvents.size();
    }

    class PastEventViewHolder extends RecyclerView.ViewHolder{

        ImageView imgPastEvent;
        TextView txtEventTitle;

        public PastEventViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPastEvent = itemView.findViewById(R.id.imgPastEvent);
            txtEventTitle = itemView.findViewById(R.id.txtPastEventTitle);
        }
    }

}
