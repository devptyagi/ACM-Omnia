package com.acm.omnia.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.acm.omnia.Model.Project;
import com.acm.omnia.R;
import com.acm.omnia.adapter.ProjectAdapter;
import com.acm.omnia.databinding.FragmentProjectsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ProjectsFragment extends Fragment {

    FragmentProjectsBinding binding;
    RecyclerView.LayoutManager layoutManager;
    ProjectAdapter recyclerAdapter;
    ArrayList<Project> projectList = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DrawerLayout drawerLayout;
    public ProjectsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProjectsBinding.inflate(inflater, container, false);

        layoutManager = new LinearLayoutManager(getActivity());
        drawerLayout = getActivity().findViewById(R.id.drawer_layout);

        setupToolbar();
        fetchData();
        return binding.getRoot();
    }

    private void setupToolbar() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, binding.toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(Color.parseColor("#FFFFFF"));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void fetchData() {
        db.collection("projects")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Project project = document.toObject(Project.class);
                                projectList.add(project);
                            }
                            recyclerAdapter = new ProjectAdapter(projectList);
                            binding.recyclerProjects.setAdapter(recyclerAdapter);
                            binding.recyclerProjects.setLayoutManager(layoutManager);
                        } else {
                            Toast.makeText(getActivity(), "Some Error Occurred!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}