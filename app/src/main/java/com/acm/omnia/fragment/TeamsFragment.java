package com.acm.omnia.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.acm.omnia.R;
import com.acm.omnia.databinding.FragmentTeamsBinding;

public class TeamsFragment extends Fragment {

    public TeamsFragment() {
        // Required empty public constructor
    }

    FragmentTeamsBinding binding;


    DrawerLayout drawerLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTeamsBinding.inflate(inflater, container, false);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout);

        binding.btnDoubt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ForumFragment()).commit();
            }
        });
        setupToolbar();
        return binding.getRoot();
    }

    private void setupToolbar() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, binding.toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(Color.parseColor("#FFFFFF"));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

}