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
import com.acm.omnia.databinding.FragmentCouncilBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class CouncilFragment extends Fragment {

    public CouncilFragment() {
        // Required empty public constructor
    }

    FragmentCouncilBinding binding;

    DrawerLayout drawerLayout;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCouncilBinding.inflate(inflater, container, false);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout);

        setupToolbar();
        setupCouncilImages();
        return binding.getRoot();
    }

    private void setupCouncilImages() {
        db.collection("council").document("pictures")
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()) {
                        Picasso.get().load(document.getString("chairperson")).into(binding.imgChair);
                        Picasso.get().load(document.getString("viceChair")).into(binding.imgViceChair);
                        Picasso.get().load(document.getString("memberChair")).into(binding.imgMemberChair);
                        Picasso.get().load(document.getString("treasurer")).into(binding.imgTreasurer);
                        Picasso.get().load(document.getString("webmaster")).into(binding.imgWebmaster);
                        Picasso.get().load(document.getString("secretary")).into(binding.imgSecretary);
                    }
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

}