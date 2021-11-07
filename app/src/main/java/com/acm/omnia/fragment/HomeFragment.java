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
import com.acm.omnia.databinding.FragmentHomeBinding;
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

    FragmentHomeBinding binding;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecyclerView.LayoutManager layoutManager;
    DrawerLayout drawerLayout;
    BlogAdapter recyclerAdapter;
    ArrayList<Blog> blogList = new ArrayList<>();
    String shareLink;
    String shareMsg;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout);
        binding.shareAppCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareApp();
            }
        });
        MultiSnapHelper multiSnapHelper = new MultiSnapHelper(SnapGravity.START, 1, 50);
        multiSnapHelper.attachToRecyclerView( binding.recyclerBlogs);
        setupToolbar();
        fetchData();
        setupLeaderBoard();
        fetchShareUrl();
        return binding.getRoot();
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
                                binding.oneName.setText(document.getString("one"));
                                binding.twoName.setText(document.getString("two"));
                                binding.threeName.setText(document.getString("three"));
                                binding.oneUserName.setText(document.getString("oneUserName"));
                                binding.twoUserName.setText(document.getString("twoUserName"));
                                binding.threeUserName.setText(document.getString("threeUserName"));
                                binding.oneRating.setText(document.getString("oneRating"));
                                binding.twoRating.setText(document.getString("twoRating"));
                                binding.threeRating.setText(document.getString("threeRating"));
                                binding.progressLayout.setVisibility(View.GONE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Some error occurred", Toast.LENGTH_SHORT).show();
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
                            binding.recyclerBlogs.setAdapter(recyclerAdapter);
                            binding.recyclerBlogs.setLayoutManager(layoutManager);
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