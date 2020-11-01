package com.acm.omnia.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class EventsFragment extends Fragment {

    public EventsFragment() {
        // Required empty public constructor
    }

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Event> eventList = new ArrayList<>();
    ArrayList<PastEvent> pastEventList = new ArrayList<>();
    EventAdapter eventRecyclerAdapter;
    PastEventAdapter pastEventRecyclerAdapter;
    RecyclerView recyclerUpcomingEvents, recyclerPastEvents;
    RecyclerView.LayoutManager eventsLayoutManager, pastEventsLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout);
        toolbar = view.findViewById(R.id.toolBar);
        setupToolbar();
        recyclerUpcomingEvents = view.findViewById(R.id.recycler_events);
        recyclerPastEvents = view.findViewById(R.id.recycler_past_events);
        eventsLayoutManager = new LinearLayoutManager(getActivity());
        pastEventsLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        fetchEventData();
        fetchPastEventsData();
        return view;
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
                            recyclerPastEvents.setAdapter(pastEventRecyclerAdapter);
                            recyclerPastEvents.setLayoutManager(pastEventsLayoutManager);
                        }
                    }
                });
    }

    private void setupToolbar() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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
                            eventRecyclerAdapter = new EventAdapter(eventList);
                            recyclerUpcomingEvents.setAdapter(eventRecyclerAdapter);
                            recyclerUpcomingEvents.setLayoutManager(eventsLayoutManager);
                        }
                    }
                });
    }
}