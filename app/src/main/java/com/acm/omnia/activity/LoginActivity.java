package com.acm.omnia.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.acm.omnia.R;
import com.acm.omnia.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                overridePendingTransition(android.R.anim.accelerate_decelerate_interpolator, android.R.anim.accelerate_decelerate_interpolator);
                startActivity(i);
            }
        });

        binding.progressBar.setVisibility(View.GONE);
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.btnLogin.setClickable(false);
                login();
            }
        });

        binding.txtAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, AdminActivity.class));
            }
        });
    }

    private void login() {
        String email = binding.txtEmail.getText().toString();
        String pass = binding.txtPass.getText().toString();

        if(email.isEmpty() || pass.isEmpty()) {
            binding.btnLogin.setClickable(true);
            Toast.makeText(LoginActivity.this, "Please enter email/password", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            binding.progressBar.setVisibility(View.GONE);
                            binding.btnLogin.setClickable(true);
                            Toast.makeText(LoginActivity.this, "Email / Password Incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}