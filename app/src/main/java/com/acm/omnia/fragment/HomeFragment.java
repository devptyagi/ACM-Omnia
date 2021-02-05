package com.acm.omnia.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.acm.omnia.Model.Blog;
import com.acm.omnia.R;
import com.acm.omnia.adapter.BlogAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.takusemba.multisnaprecyclerview.MultiSnapHelper;
import com.takusemba.multisnaprecyclerview.SnapGravity;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements BlogAdapter.OnBlogClickedListener {


    public HomeFragment() {
        // Required empty public constructor
    }

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecyclerView.LayoutManager layoutManager;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    BlogAdapter recyclerAdapter;
    RecyclerView recyclerView;
    ArrayList<Blog> blogList = new ArrayList<>();
    RelativeLayout progressLayout;
    LinearLayout shareAppCard;
    String shareLink;
    String shareMsg;
    TextView oneName, oneUserName, oneRating, twoName, twoUserName, twoRating, threeName, threeUserName, threeRating;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recycler_blogs);
        layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout);
        toolbar = view.findViewById(R.id.toolBar);
        oneName = view.findViewById(R.id.oneName);
        twoName = view.findViewById(R.id.twoName);
        threeName = view.findViewById(R.id.threeName);
        oneUserName = view.findViewById(R.id.oneUserName);
        twoUserName = view.findViewById(R.id.twoUserName);
        threeUserName = view.findViewById(R.id.threeUserName);
        oneRating = view.findViewById(R.id.oneRating);
        twoRating = view.findViewById(R.id.twoRating);
        threeRating = view.findViewById(R.id.threeRating);
        progressLayout = view.findViewById(R.id.progressLayout);
        progressLayout.setVisibility(View.VISIBLE);
        shareAppCard = view.findViewById(R.id.shareAppCard);
        shareAppCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareApp();
            }
        });
        MultiSnapHelper multiSnapHelper = new MultiSnapHelper(SnapGravity.START, 1, 50);
        multiSnapHelper.attachToRecyclerView(recyclerView);
        setupToolbar();
        fetchData();
        setupLeaderBoard();
        fetchShareUrl();
        return view;
    }

    private void fetchShareUrl() {
        db.collection("extras").document("shareLink")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            shareMsg = task.getResult().getString("shareTitle");
                           shareLink = task.getResult().getString("url");
                        }
                    }
                });
    }

    private void shareApp() {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
            String shareMessage= shareMsg + "\n";
            shareMessage = shareMessage + shareLink;
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "Share app: "));
        } catch(Exception e) {

        }
    }

    private void setupLeaderBoard() {
        db.collection("codechef")
                .document("leaderboard")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                oneName.setText(document.getString("one"));
                                twoName.setText(document.getString("two"));
                                threeName.setText(document.getString("three"));
                                oneUserName.setText(document.getString("oneUserName"));
                                twoUserName.setText(document.getString("twoUserName"));
                                threeUserName.setText(document.getString("threeUserName"));
                                oneRating.setText(document.getString("oneRating"));
                                twoRating.setText(document.getString("twoRating"));
                                threeRating.setText(document.getString("threeRating"));
                                progressLayout.setVisibility(View.GONE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Some error occurred", Toast.LENGTH_SHORT).show();
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

    private void fetchData() {
        db.collection("blogs")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Blog blog = document.toObject(Blog.class);
                                blogList.add(blog);
                            }
                            recyclerAdapter = new BlogAdapter(blogList, HomeFragment.this);
                            recyclerView.setAdapter(recyclerAdapter);
                            recyclerView.setLayoutManager(layoutManager);
                        } else {
                            Toast.makeText(getActivity(), "Some Error Occurred!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onBlogClick(int position) {
        Log.i("HomeFragment", "OnBlogClicked");
        Blog clickedBlog = blogList.get(position);
        Uri uri = Uri.parse(clickedBlog.getBlogLink());
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
    }
}