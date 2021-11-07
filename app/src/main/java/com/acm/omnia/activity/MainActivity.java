package com.acm.omnia.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.acm.omnia.R;
import com.acm.omnia.databinding.ActivityMainBinding;
import com.acm.omnia.fragment.ContactFragment;
import com.acm.omnia.fragment.CouncilFragment;
import com.acm.omnia.fragment.EventsFragment;
import com.acm.omnia.fragment.ForumFragment;
import com.acm.omnia.fragment.HomeFragment;
import com.acm.omnia.fragment.ProjectsFragment;
import com.acm.omnia.fragment.TeamsFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FirebaseMessaging.getInstance().subscribeToTopic("all");
       // setupDrawer();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    @Override
    protected void onResume() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user.getPhotoUrl() != null) {
            Picasso.get().load(user.getPhotoUrl()).into((ImageView) binding.drawerLayout.findViewById(R.id.imgDrawerProfilePicture));
        }
        super.onResume();
    }

    private void setupDrawer() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user.getPhotoUrl() != null) {
            Picasso.get().load(user.getPhotoUrl()).into((ImageView) binding.drawerLayout.findViewById(R.id.imgDrawerProfilePicture));
        }

        binding.drawerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, EditProfileActivity.class);
                startActivity(i);
            }
        });

        binding.drawerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).addToBackStack("Home").commit();
                binding.drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        binding.drawerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ForumFragment()).addToBackStack("Forum").commit();
                binding.drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        binding.drawerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EventsFragment()).addToBackStack("Events").commit();
                binding.drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        binding.drawerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CouncilFragment()).addToBackStack("Council").commit();
                binding.drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        binding.drawerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TeamsFragment()).addToBackStack("Teams").commit();
                binding.drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        binding.drawerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContactFragment()).addToBackStack("Contact").commit();
                binding.drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        binding.drawerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProjectsFragment()).addToBackStack("Projects").commit();
                binding.drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        binding.drawerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmLogout();
                binding.drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
    }




    private void confirmLogout() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseAuth.getInstance().signOut();
                Intent in = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(in);
                finish();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialogBuilder.setTitle("Are you sure?");
        alertDialogBuilder.setCancelable(true);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}