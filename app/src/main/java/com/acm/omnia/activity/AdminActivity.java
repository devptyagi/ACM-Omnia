package com.acm.omnia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.acm.omnia.databinding.ActivityAdminBinding;


public class AdminActivity extends AppCompatActivity {

    ActivityAdminBinding binding;
    String userName = "";
    String userPassword = "";
    boolean isValid = false;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       // getSupportActionBar().hide();

        binding.progressBar.setVisibility(View.GONE);

        binding.etLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.progressBar.setVisibility(View.VISIBLE);


                userName = binding.etName.getText().toString();
                userPassword = binding.etPassword.getText().toString();


                if (userName.isEmpty() || userPassword.isEmpty()) {
                    binding.progressBar.setVisibility(View.GONE);

                    Toast.makeText(AdminActivity.this, "Please enter name and password!", Toast.LENGTH_LONG).show();

                } else {


                    isValid = validate(userName, userPassword);


                    if (!isValid) {


                        counter--;


                        binding.progressBar.setVisibility(View.GONE);
                        binding.tyAttemptsinfo.setText("Attempts Remaining: " + String.valueOf(counter));


                        if (counter == 0) {
                            binding.etLogin.setEnabled(false);
                            binding.progressBar.setVisibility(View.GONE);
                            Toast.makeText(AdminActivity.this, "You have used all your attempts try again later!", Toast.LENGTH_LONG).show();
                        } else {
                            binding.progressBar.setVisibility(View.GONE);
                            Toast.makeText(AdminActivity.this, "Incorrect credentials, please try again!", Toast.LENGTH_LONG).show();
                        }
                    } else {

                        binding.progressBar.setVisibility(View.GONE);

                        Toast.makeText(AdminActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AdminActivity.this, AdminMainActivity.class));
                    }

                }
            }
        });
    }

    private boolean validate(String userName, String userPassword) {

        Credentials credentials = new Credentials();


        if (userName.equals(credentials.name) && userPassword.equals(credentials.password)) {
            return true;
        }

        return false;
    }

    class Credentials {
        String name = "Admin";
        String password = "@DmIn";
    }


}