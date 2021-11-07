package com.acm.omnia.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import com.acm.omnia.Model.EventsData;
import com.acm.omnia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewAdapter> {

    private Context context;
    private ArrayList<EventsData> list;

    public EventsAdapter(Context context, ArrayList<EventsData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EventViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.delete_event, parent, false);

        return new EventViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewAdapter holder, final int position) {
        final int index = 0;

        EventsData currentItem = list.get(position);
        holder.deleteEventTitle.setText(currentItem.getTitle());
        holder.deleteEventDate.setText(currentItem.getDate());
        holder.deleteEventTime.setText(currentItem.getTime());
        holder.deleteEventMonth.setText(currentItem.getMonth());

        holder.deleteEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to delete this notice");
                builder.setCancelable(true);
                builder.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final FirebaseFirestore reference;
                                reference = FirebaseFirestore.getInstance();
                                reference.collection("events").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(index);
                                            String documentId = documentSnapshot.getId();
                                            reference.collection("events").document(documentId).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    }
                                });

                                notifyItemRemoved(position);
                            }
                        }
                );
                builder.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }
                );
                AlertDialog dialog = null;
                try {
                    dialog = builder.create();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (dialog != null) {
                    dialog.show();
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EventViewAdapter extends RecyclerView.ViewHolder {

        private TextView deleteEvent;
        private TextView deleteEventTitle;
        private TextView deleteEventDate;
        private TextView deleteEventTime;
        private TextView deleteEventMonth;


        public EventViewAdapter(@NonNull View itemView) {
            super(itemView);

            deleteEvent = itemView.findViewById(R.id.deleteEvent);
            deleteEventTitle = itemView.findViewById(R.id.deleteEventTitle);
            deleteEventDate = itemView.findViewById(R.id.deleteEventDate);
            deleteEventTime = itemView.findViewById(R.id.deleteEventTime);
            deleteEventMonth = itemView.findViewById(R.id.deleteEventMonth);


        }
    }
}
