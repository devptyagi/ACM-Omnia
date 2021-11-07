package com.acm.omnia.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acm.omnia.Model.Event;
import com.acm.omnia.Model.PastEvent;
import com.acm.omnia.R;
import com.acm.omnia.adapter.EventAdapter;
import com.acm.omnia.adapter.PastEventAdapter;
import com.acm.omnia.databinding.FragmentEventsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.takusemba.multisnaprecyclerview.MultiSnapHelper;
import com.takusemba.multisnaprecyclerview.SnapGravity;

import java.util.ArrayList;

public class EventsFragment extends Fragment {

    public EventsFragment() {
        // Required empty public constructor
    }

    FragmentEventsBinding binding;


    DrawerLayout drawerLayout;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Event> eventList = new ArrayList<>();
    EventAdapter eventRecyclerAdapter;
    PastEventAdapter pastEventRecyclerAdapter;
    ArrayList<PastEvent> pastEventList = new ArrayList<>();
    RecyclerView.LayoutManager eventsLayoutManager, pastEventsLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEventsBinding.inflate(inflater, container, false);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout);
        binding.cardNoUpcomingEvent.setVisibility(View.GONE);
        setupToolbar();

        eventsLayoutManager = new LinearLayoutManager(getActivity());
        pastEventsLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        MultiSnapHelper multiSnapHelper = new MultiSnapHelper(SnapGravity.START, 1, 50);
        multiSnapHelper.attachToRecyclerView(binding.recyclerPastEvents);
        fetchEventData();
        fetchPastEventsData();
        return binding.getRoot();
    }

    private void fetchPastEventsData() {
        db.collection("pastEvents")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PastEvent e = document.toObject(PastEvent.class);
                                pastEventList.add(e);
                            }
                            pastEventRecyclerAdapter = new PastEventAdapter(pastEventList);
                            binding.recyclerPastEvents.setAdapter(pastEventRecyclerAdapter);
                            binding.recyclerPastEvents.setLayoutManager(pastEventsLayoutManager);
                        }
                    }
                });
    }

    private void setupToolbar() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, binding.toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(Color.parseColor("#FFFFFF"));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void fetchEventData() {
        db.collection("events")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        eventList.clear();
                        for (QueryDocumentSnapshot document : value) {
                            Event e = document.toObject(Event.class);
                            eventList.add(e);
                        }
                        if(eventList.isEmpty()) {
                            binding.cardNoUpcomingEvent.setVisibility(View.VISIBLE);
                        } else {
                            binding.cardNoUpcomingEvent.setVisibility(View.GONE);
                        }
                        eventRecyclerAdapter = new EventAdapter(eventList);
                        binding.recyclerEvents.setAdapter(eventRecyclerAdapter);
                        binding.recyclerEvents.setLayoutManager(eventsLayoutManager);
                    }
                });
    }
}