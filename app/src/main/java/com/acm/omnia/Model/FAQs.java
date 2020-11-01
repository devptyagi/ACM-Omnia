package com.acm.omnia.Model;

import java.util.ArrayList;

public class FAQs {
    public static ArrayList<Question> general = new ArrayList<>();
    public static ArrayList<Question> graphicDesigning = new ArrayList<>();
    public static ArrayList<Question> webDevelopment = new ArrayList<>();
    public static ArrayList<Question> competitiveProgramming = new ArrayList<>();
    public static ArrayList<Question> artificialIntelligence = new ArrayList<>();
    public static ArrayList<Question> appDevelopment = new ArrayList<>();
    public static ArrayList<Question> dataScience = new ArrayList<>();
    public static ArrayList<Question> gameDevelopment = new ArrayList<>();
    public static ArrayList<Question> cyberSecurity = new ArrayList<>();


    static {
        general.add(
                new Question(
                "What is ACM JUIT?",
                "ACM Student Chapter JUIT is a student branch of ACM International. We look to create a community focused towards the technical development of individuals all around the college through sessions, collaborative projects and events."
                )
        );

        general.add(
                new Question(
                        "How to become an ACM JUIT Member?",
                        "To become a member, individuals need to go through a two stage process involving an aptitude and general ability test followed by a personal interaction round to know them better."
                )
        );

        general.add(
                new Question(
                        "I missed the selection process the first time. Is there a way to become a part of the ACM JUIT Community?",
                        "At ACM, we do not say no to anyone who wants to learn more and contribute to the community. In case you have missed out due to unavoidable reasons, feel free to contact third year members from ACM JUIT."
                )
        );

        general.add(
                new Question(
                        "Is any prior knowledge in technical fields required to become a member?",
                        "No. We look for people who want to learn and are willing to put in that extra mile to develop their technical skills and can learn from seniors and peers alike."
                )
        );

        graphicDesigning.add(
                new Question(
                        "What is done at the Graphic Designing Team?",
                        "The Design Team is for developing everything you see on screen. From posters and videos for event promos to UI UX designs of Web and Phone Apps - including the one you see in your hands right now, Omnia."
                )
        );

        graphicDesigning.add(
                new Question(
                        "Do we need any tools to join the Graphic Design Team?",
                        "The tools you require will be your creativity and some softwares depending on which field of design interests you. For image editing - Adobe Photoshop and Corel Draw, while for UI UX Designing - Adobe XD and Figma. Don’t worry if you do not have them right now, we’ll help you get them - free of cost."
                )
        );

        webDevelopment.add(
                new Question(
                        "What is done at the Web Development Team?",
                        "Everything from the frontend to the backend of a well looking and well functioning website is what we do. Whether it is basics of HTML CSS or the powerful Laravel or the intricacies of the MERN/MEAN Stack - we have people working, learning and developing in all of them."
                )
        );

        webDevelopment.add(
                new Question(
                        "What is the scope and future of Web Development in the industry?",
                        "Web Development - full stack, is one of the most sought after jobs in the industry of today. With the transition to a complete online world every startup, business or individual needs to make an online presence and a good website is what makes that possible."
                )
        );

        competitiveProgramming.add(
                new Question(
                        "What is done at the Competitive Programming Team?",
                        "Problem solving and then translating that solution to code is that one quality each developer programmer should have. At this team, we aim to inculcate that within members - subjecting them to various coding contests within short intervals, introducing new concepts - Greedy Algos, Dynamic Programming, Data Structures and Platforms to test their skills."
                )
        );

        competitiveProgramming.add(
                new Question(
                        "Should I have a preference among the various languages to use for Programming?",
                        "The most used programming languages in Competitive Coding are C++ and JAVA, which make them a preference of both experts and amateurs. They provide exponentially shorter runtime as compared to “easy to learn” Python and help get your basics of programming strong. " +
                                "For platforms, we encourage using - Hackerrank and LeetCode for self practice), CodeChef, Codeforces and Hackerearth for Contests. However, you are free to explore more."
                )
        );

        artificialIntelligence.add(
                new Question(
                        "What is done at the Artificial Intelligence Team?",
                        "For starters, we remove the myths and misconceptions regarding the term ‘Artificial Intelligence’. We draw a line between what is AI as movies and pop culture makes you think and what AI in the 21st Century is. Starting from data handling to algorithms of Machine Learning and gradually moving into the depths of Deep Learning."
                )
        );

        artificialIntelligence.add(
                new Question(
                        "How do we test our skills in Machine Learning?",
                        "The team is active on Kaggle - a Data Science and ML platform, participating in contests and maintaining notebooks on varied datasets."
                )
        );

        appDevelopment.add(
                new Question(
                        "What is done at the App Dev Team?",
                        "We work on developing mobile and web apps using all the possible tech stacks in demand right now. Omnia - is a brainchild of the members of the app development team as well."
                )
        );

        appDevelopment.add(
                new Question(
                        "What languages and frameworks are used to develop mobile apps?",
                        "For languages, JAVA, Dart and Swift are the most primary Mobile only app development languages. For cross platform apps - React Native and Dart (Flutter) can be used."
                )
        );

        dataScience.add(
                new Question(
                        "What is done at the Data Science Team?",
                        "Data is considered as the most valuable commodity in the world right now. Data Science is where we learn how to procure data, clean it, handle it and analyse it to obtain desired predictions. It has become one of the most essential tools to have in the world."
                )
        );

        dataScience.add(
                new Question(
                        "How is Data Science related to AI?",
                        "Data Science is the first step to building any AI model. Every Machine Learning algorithm or Neural Network Architecture is based on data. A good dataset ensures a good prediction by our model and therefore, data fetching and cleaning become essential steps in your AI Model Pipeline."
                )
        );

        gameDevelopment.add(
                new Question(
                        "What is the Game Development Team about?",
                        "Game Development brings to life that dream which almost everyone has of developing a game. Here, we focus on learning languages like C# which are crucial for game development - concepts like Object Oriented Programming and slowly working our way to Game Engines such as Unity and Unreal to develop anything from 2D Platform Games to 3D World Exploration ones."
                )
        );

        cyberSecurity.add(
                new Question(
                        "What is the Cyber Security Team?",
                        "Keeping a secure server, penetration testings and the basics of securing your privacy over the internet are what we focus on in the Cyber Security Team. It starts with spreading general awareness about these terms from actually working on securing systems and finding vulnerabilities among others."
                )
        );

        cyberSecurity.add(
                new Question(
                        "What contests do you participate in?",
                        "The team tests their skills on contests like CTFs and other cryptographic events over platforms like Hack The Box. These contests require an out of the box thinking and team efforts using almost every internet tool to solve encrypted messages and codes."
                )
        );

    }
}
