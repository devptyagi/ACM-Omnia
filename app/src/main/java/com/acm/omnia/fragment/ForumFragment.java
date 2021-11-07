package com.acm.omnia.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acm.omnia.Model.FAQs;
import com.acm.omnia.Model.Question;
import com.acm.omnia.R;
import com.acm.omnia.adapter.FaqAdapter;
import com.acm.omnia.databinding.FragmentForumBinding;

import java.util.ArrayList;

public class ForumFragment extends Fragment {

    public ForumFragment() {
        // Required empty public constructor
    }

    FragmentForumBinding binding;


    DrawerLayout drawerLayout;
    RecyclerView.LayoutManager faqLayoutManager;
    FaqAdapter faqRecyclerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentForumBinding.inflate(inflater, container, false);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout);
        faqLayoutManager = new LinearLayoutManager(getActivity());
        setupFaqs(FAQs.general);
        setupToolbar();
        setupFaqClicks();
        return binding.getRoot();
    }

    private void setupFaqs(ArrayList<Question> questionList) {
        faqRecyclerAdapter = new FaqAdapter(questionList);
        binding.recyclerFaqs.setAdapter(faqRecyclerAdapter);
        binding.recyclerFaqs.setLayoutManager(faqLayoutManager);
    }

    private void toggleColors() {
        binding.one.setCardBackgroundColor(Color.parseColor("#261b52"));
        binding.two.setCardBackgroundColor(Color.parseColor("#261b52"));
        binding.three.setCardBackgroundColor(Color.parseColor("#261b52"));
        binding.four.setCardBackgroundColor(Color.parseColor("#261b52"));
        binding.five.setCardBackgroundColor(Color.parseColor("#261b52"));
        binding.six.setCardBackgroundColor(Color.parseColor("#261b52"));
        binding.seven.setCardBackgroundColor(Color.parseColor("#261b52"));
        binding.eight.setCardBackgroundColor(Color.parseColor("#261b52"));
        binding.nine.setCardBackgroundColor(Color.parseColor("#261b52"));
    }

    private void setupFaqClicks() {
        binding.one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleColors();
                binding.one.setCardBackgroundColor(Color.parseColor("#463a77"));
                binding.txtCurrentList.setText("General");
                setupFaqs(FAQs.general);
            }
        });

        binding.two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleColors();
                binding.two.setCardBackgroundColor(Color.parseColor("#463a77"));
                binding.txtCurrentList.setText("Graphic Designing");
                setupFaqs(FAQs.graphicDesigning);
            }
        });

        binding.three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleColors();
                binding.three.setCardBackgroundColor(Color.parseColor("#463a77"));
                binding.txtCurrentList.setText("Competitive Coding");
                setupFaqs(FAQs.competitiveProgramming);
            }
        });

        binding.four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleColors();
                binding.four.setCardBackgroundColor(Color.parseColor("#463a77"));
                binding.txtCurrentList.setText("Web Development");
                setupFaqs(FAQs.webDevelopment);
            }
        });

        binding.five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleColors();
                binding.five.setCardBackgroundColor(Color.parseColor("#463a77"));
                binding.txtCurrentList.setText("App Development");
                setupFaqs(FAQs.appDevelopment);
            }
        });

        binding.six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleColors();
                binding.six.setCardBackgroundColor(Color.parseColor("#463a77"));
                binding.txtCurrentList.setText("Artificial Intelligence");
                setupFaqs(FAQs.artificialIntelligence);
            }
        });

        binding.seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleColors();
                binding.seven.setCardBackgroundColor(Color.parseColor("#463a77"));
                binding.txtCurrentList.setText("Game Development");
                setupFaqs(FAQs.gameDevelopment);
            }
        });

        binding.eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleColors();
                binding.eight.setCardBackgroundColor(Color.parseColor("#463a77"));
                binding.txtCurrentList.setText("Data Science");
                setupFaqs(FAQs.dataScience);
            }
        });

        binding.nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleColors();
                binding.nine.setCardBackgroundColor(Color.parseColor("#463a77"));
                binding.txtCurrentList.setText("Cyber Security");
                setupFaqs(FAQs.cyberSecurity);
            }
        });

    }

    private void setupToolbar() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, binding.toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(Color.parseColor("#FFFFFF"));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

}