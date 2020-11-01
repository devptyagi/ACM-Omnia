package com.acm.omnia.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.acm.omnia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class CouncilFragment extends Fragment {

    public CouncilFragment() {
        // Required empty public constructor
    }

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ImageView imgChair, imgViceChair, imgSecretary, imgTreasurer, imgWebmaster, imgMemberChair;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_council, container, false);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout);
        toolbar = view.findViewById(R.id.toolBar);
        imgChair = view.findViewById(R.id.imgChair);
        imgViceChair = view.findViewById(R.id.imgViceChair);
        imgSecretary = view.findViewById(R.id.imgSecretary);
        imgTreasurer = view.findViewById(R.id.imgTreasurer);
        imgWebmaster = view.findViewById(R.id.imgWebmaster);
        imgMemberChair = view.findViewById(R.id.imgMemberChair);
        setupToolbar();
        setupCouncilImages();
        return view;
    }

    private void setupCouncilImages() {
        db.collection("council").document("pictures")
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()) {
                        Picasso.get().load(document.getString("chairperson")).into(imgChair);
                        Picasso.get().load(document.getString("viceChair")).into(imgViceChair);
                        Picasso.get().load(document.getString("memberChair")).into(imgMemberChair);
                        Picasso.get().load(document.getString("treasurer")).into(imgTreasurer);
                        Picasso.get().load(document.getString("webmaster")).into(imgWebmaster);
                        Picasso.get().load(document.getString("secretary")).into(imgSecretary);
                    }
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

}