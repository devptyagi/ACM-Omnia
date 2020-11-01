package com.acm.omnia.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.acm.omnia.R;
import com.acm.omnia.util.ConnectionManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    ConstraintLayout constraintLayout;
    ImageView imgLogo;
    TextView txtOmnia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ConnectionManager connectionManager = new ConnectionManager();
        if(!connectionManager.checkConnectivity(SplashActivity.this)) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(SplashActivity.this);
            dialog.setTitle("Error");
            dialog.setMessage("No Internet Connection");
            dialog.setPositiveButton("Open Settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent settingsIntent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                    startActivity(settingsIntent);
                    finish();
                }
            });
            dialog.setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.finishAffinity(SplashActivity.this);
                }
            });
            dialog.create();
            dialog.show();
        } else {
            constraintLayout = findViewById(R.id.layour_splash);
            constraintLayout.setBackgroundResource(R.drawable.loader_1);
            imgLogo = findViewById(R.id.imgLogo);
            txtOmnia = findViewById(R.id.txtOmnia);
            imgLogo.setVisibility(View.GONE);
            txtOmnia.setVisibility(View.GONE);
            mAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = mAuth.getCurrentUser();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    constraintLayout.setBackgroundResource(R.drawable.loader_2);
                }
            }, 500);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    constraintLayout.setBackgroundResource(R.drawable.loader_3);
                }
            }, 1000);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    constraintLayout.setBackgroundResource(R.drawable.loader_4);
                    imgLogo.setVisibility(View.VISIBLE);
                    txtOmnia.setVisibility(View.VISIBLE);
                }
            }, 1600);

            if(currentUser != null) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }, 2200);
            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                }, 2200);
            }
        }
    }
}