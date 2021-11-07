package com.acm.omnia.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.acm.omnia.R;
import com.acm.omnia.databinding.FragmentContactBinding;

public class ContactFragment extends Fragment {

    public ContactFragment() {
        // Required empty public constructor
    }

    FragmentContactBinding binding;
    DrawerLayout drawerLayout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       binding = FragmentContactBinding.inflate(inflater, container, false);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout);

        setupLinks();
        setupToolbar();
        return binding.getRoot();
    }

    private void setupLinks() {
        binding.contactInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInsta();
            }
        });
        binding.contactFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFacebook();
            }
        });
        binding.contactLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLinkedin();
            }
        });
        binding.contactYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openYoutube();
            }
        });
        binding.contactGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGithub();
            }
        });
        binding.contactWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatsapp();
            }
        });
        binding.txtWebsiteLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAcmWebsite();
            }
        });
    }

    private void openWhatsapp() {
        String contact = "+91 7017428546";
        String url = "https://api.whatsapp.com/send?phone=" + contact;
        try {
            PackageManager pm = getContext().getPackageManager();
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(getActivity(), "Whatsapp app not installed in your phone", Toast.LENGTH_SHORT).show();
        }
    }

    private void openAcmWebsite() {
        Uri uri = Uri.parse("http://juit.acm.org/");
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
    }

    private void openGithub() {
        Uri uri = Uri.parse("https://github.com/ACM-JUIT");
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
    }

    private void openYoutube() {
        Uri uri = Uri.parse("https://www.youtube.com/channel/UCxKqCP0amDp1ocxa7fC3TCg");
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        i.setPackage("com.google.android.youtube");
        try {
            startActivity(i);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCxKqCP0amDp1ocxa7fC3TCg")));
        }
    }

    private void openLinkedin() {
        Uri uri = Uri.parse("https://www.linkedin.com/company/acmjuit/");
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        i.setPackage("com.linkedin.android");
        try {
            startActivity(i);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/company/acmjuit/")));
        }
    }

    private void openInsta() {
        Uri uri = Uri.parse("http://instagram.com/_u/acmjuit");
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        i.setPackage("com.instagram.android");
        try {
            startActivity(i);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/acmjuit")));
        }
    }

    private void openFacebook() {
        Uri uri = Uri.parse("fb://facewebmodal/f?href=https://www.facebook.com/acmwolves");
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        i.setPackage("com.facebook.katana");
        try {
            startActivity(i);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/acmwolves")));
        }
    }

    private void setupToolbar() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, binding.toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(Color.parseColor("#FFFFFF"));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
}