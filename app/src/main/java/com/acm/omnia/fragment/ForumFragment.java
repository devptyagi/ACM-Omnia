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

import java.util.ArrayList;

public class ForumFragment extends Fragment {

    public ForumFragment() {
        // Required empty public constructor
    }

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    CardView one, two, three, four, five, six, seven, eight, nine;
    RecyclerView questionRecyclerView;
    RecyclerView.LayoutManager faqLayoutManager;
    TextView txtCurrentList;
    FaqAdapter faqRecyclerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forum, container, false);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout);
        toolbar = view.findViewById(R.id.toolBar);
        txtCurrentList = view.findViewById(R.id.txtCurrentList);
        one = view.findViewById(R.id.one);
        two = view.findViewById(R.id.two);
        three = view.findViewById(R.id.three);
        four = view.findViewById(R.id.four);
        five = view.findViewById(R.id.five);
        six = view.findViewById(R.id.six);
        seven = view.findViewById(R.id.seven);
        eight = view.findViewById(R.id.eight);
        nine = view.findViewById(R.id.nine);
        questionRecyclerView = view.findViewById(R.id.recycler_faqs);
        faqLayoutManager = new LinearLayoutManager(getActivity());
        setupFaqs(FAQs.general);
        setupToolbar();
        setupFaqClicks();
        return view;
    }

    private void setupFaqs(ArrayList<Question> questionList) {
        faqRecyclerAdapter = new FaqAdapter(questionList);
        questionRecyclerView.setAdapter(faqRecyclerAdapter);
        questionRecyclerView.setLayoutManager(faqLayoutManager);
    }

    private void toggleColors() {
        one.setCardBackgroundColor(Color.parseColor("#261b52"));
        two.setCardBackgroundColor(Color.parseColor("#261b52"));
        three.setCardBackgroundColor(Color.parseColor("#261b52"));
        four.setCardBackgroundColor(Color.parseColor("#261b52"));
        five.setCardBackgroundColor(Color.parseColor("#261b52"));
        six.setCardBackgroundColor(Color.parseColor("#261b52"));
        seven.setCardBackgroundColor(Color.parseColor("#261b52"));
        eight.setCardBackgroundColor(Color.parseColor("#261b52"));
        nine.setCardBackgroundColor(Color.parseColor("#261b52"));
    }

    private void setupFaqClicks() {
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleColors();
                one.setCardBackgroundColor(Color.parseColor("#463a77"));
                txtCurrentList.setText("General");
                setupFaqs(FAQs.general);
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleColors();
                two.setCardBackgroundColor(Color.parseColor("#463a77"));
                txtCurrentList.setText("Graphic Designing");
                setupFaqs(FAQs.graphicDesigning);
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleColors();
                three.setCardBackgroundColor(Color.parseColor("#463a77"));
                txtCurrentList.setText("Competitive Coding");
                setupFaqs(FAQs.competitiveProgramming);
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleColors();
                four.setCardBackgroundColor(Color.parseColor("#463a77"));
                txtCurrentList.setText("Web Development");
                setupFaqs(FAQs.webDevelopment);
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleColors();
                five.setCardBackgroundColor(Color.parseColor("#463a77"));
                txtCurrentList.setText("App Development");
                setupFaqs(FAQs.appDevelopment);
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleColors();
                six.setCardBackgroundColor(Color.parseColor("#463a77"));
                txtCurrentList.setText("Artificial Intelligence");
                setupFaqs(FAQs.artificialIntelligence);
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleColors();
                seven.setCardBackgroundColor(Color.parseColor("#463a77"));
                txtCurrentList.setText("Game Development");
                setupFaqs(FAQs.gameDevelopment);
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleColors();
                eight.setCardBackgroundColor(Color.parseColor("#463a77"));
                txtCurrentList.setText("Data Science");
                setupFaqs(FAQs.dataScience);
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleColors();
                nine.setCardBackgroundColor(Color.parseColor("#463a77"));
                txtCurrentList.setText("Cyber Security");
                setupFaqs(FAQs.cyberSecurity);
            }
        });

    }

    private void setupToolbar() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(Color.parseColor("#FFFFFF"));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

}