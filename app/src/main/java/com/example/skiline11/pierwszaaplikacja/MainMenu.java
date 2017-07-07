package com.example.skiline11.pierwszaaplikacja;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.skiline11.pierwszaaplikacja.database.DataBaseHelper;

public class MainMenu extends AppCompatActivity {

    public Button worldRank, friendsRank, nearbyRunning, planNewRunning, yourResults, addNewResult, addNewFriends;
    public static String rankType = "";

    public void init()
    {
        worldRank = (Button)findViewById(R.id.worldRank);
        worldRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rankType = "World Rank";
                Intent newActiv = new Intent(MainMenu.this, ChooseDistance.class);
                startActivity(newActiv);

            }
        });

        friendsRank = (Button)findViewById(R.id.friendRank);
        friendsRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rankType = "Friends Rank";
                Intent newActiv = new Intent(MainMenu.this, ChooseDistance.class);
                startActivity(newActiv);
            }
        });

        planNewRunning = (Button)findViewById(R.id.planNewRunning);
        planNewRunning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActiv = new Intent(MainMenu.this, PlanNewRunning.class);
                startActivity(newActiv);
            }
        });

        yourResults = (Button)findViewById(R.id.yourResults);
        yourResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rankType = "Your Results";
                Intent newActiv = new Intent(MainMenu.this, ChooseDistance.class);
                startActivity(newActiv);

            }
        });

        addNewResult = (Button)findViewById(R.id.addNewResults);
        addNewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActiv = new Intent(MainMenu.this, AddNewResult.class);
                startActivity(newActiv);
            }
        });

        addNewFriends = (Button)findViewById(R.id.addNewFriends);
        addNewFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActiv = new Intent(MainMenu.this, AddNewFriends.class);
                startActivity(newActiv);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        init();
    }



}
