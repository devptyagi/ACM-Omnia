package com.acm.omnia.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.acm.omnia.Model.ProjectsData;
import com.acm.omnia.databinding.ActivityAddProjectsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AddProjectsActivity extends AppCompatActivity {

    private final int REQ = 1;
    ActivityAddProjectsBinding binding;
    String downloadUrl = "";
    private Bitmap bitmap;
    private FirebaseFirestore reference;
    private StorageReference storageReference;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddProjectsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //getSupportActionBar().hide();

        reference = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();


        dialog = new ProgressDialog(this);


        binding.addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        binding.uploadEventsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.projectsTitle.getText().toString().isEmpty()) {
                    binding.projectsTitle.setText("Empty");
                    binding.projectsTitle.requestFocus();
                } else if (bitmap == null) {
                    uploadEvent();
                } else {
                    uploadImage();
                }
            }
        });
    }


    private void uploadImage() {
        dialog.setMessage("Uploading Please Wait");
        dialog.setCancelable(true);
        dialog.show();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);

        byte[] finalimg = baos.toByteArray();
        final StorageReference filePath;
        filePath = storageReference.child("projects").child(finalimg + "jpg");
        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(AddProjectsActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl = String.valueOf(uri);
                                    uploadEvent();
                                }
                            });
                        }
                    });
                } else {
                    dialog.dismiss();
                    Toast.makeText(AddProjectsActivity.this, "Something Went Wrong.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void uploadEvent() {


        String title = binding.projectsTitle.getText().toString();


        ProjectsData projectsData = new ProjectsData(title, downloadUrl);

        reference.collection("projects").add(projectsData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                dialog.dismiss();
                Toast.makeText(AddProjectsActivity.this, "Project Uploaded", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddProjectsActivity.this, MainActivity.class));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(AddProjectsActivity.this, "Please Try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openGallery() {
        Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage, REQ);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            binding.eventImageView.setImageBitmap(bitmap);
        }
    }
}