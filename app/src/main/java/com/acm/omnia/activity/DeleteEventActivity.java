package com.acm.omnia.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.acm.omnia.Model.EventsData;
import com.acm.omnia.adapter.EventsAdapter;
import com.acm.omnia.databinding.ActivityDeleteEventBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DeleteEventActivity extends AppCompatActivity {

    ActivityDeleteEventBinding binding;
    private ArrayList<EventsData> list;
    private EventsAdapter adapter;
    private FirebaseFirestore reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeleteEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       // getSupportActionBar().hide();
        reference = FirebaseFirestore.getInstance();
        binding.deleteEventRv.setLayoutManager(new LinearLayoutManager(this));
        binding.deleteEventRv.setHasFixedSize(true);

        getEvent();
    }

    private void getEvent() {


        reference.collection("events").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {


                if (task.isSuccessful()) {
                    list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        EventsData eventsData = document.toObject(EventsData.class);
                        list.add(eventsData);

                    }

                    adapter = new EventsAdapter(DeleteEventActivity.this, list);

                    binding.progressBar.setVisibility(View.GONE);
                    binding.deleteEventRv.setAdapter(adapter);


                }
                adapter.notifyDataSetChanged();
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DeleteEventActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
            }
        });


    }


}