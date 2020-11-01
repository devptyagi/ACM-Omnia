package com.acm.omnia.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.acm.omnia.Model.Event;
import com.acm.omnia.R;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    ArrayList<Event> eventList;

    public EventAdapter(ArrayList<Event> evenyList) {
        this.eventList = evenyList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_single, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.txtTitle.setText(event.getTitle());
        holder.txtComingSoon.setVisibility(View.GONE);
        holder.txtDate.setText(event.getDate());
        holder.txtMonth.setText(event.getMonth());
        holder.txtTime.setText(event.getTime());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder{

        TextView txtTitle, txtTime, txtDate, txtMonth, txtComingSoon;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtEventTitle);
            txtTime = itemView.findViewById(R.id.txtEventTime);
            txtDate = itemView.findViewById(R.id.txtEventDate);
            txtMonth = itemView.findViewById(R.id.txtEventMonth);
            txtComingSoon = itemView.findViewById(R.id.txtComingSoon);
        }
    }

}
