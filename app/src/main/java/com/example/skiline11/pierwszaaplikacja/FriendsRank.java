package com.example.skiline11.pierwszaaplikacja;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.skiline11.pierwszaaplikacja.adapter.ListOfPeopleAdapter;
import com.example.skiline11.pierwszaaplikacja.model.Person;

import java.util.List;

public class FriendsRank extends AppCompatActivity {

    private ListView lvPeople;
    private ListOfPeopleAdapter adapter;
    private List<Person> peopleList;
    public Button button100m, button800m, button1500m, button5000m, button10000m, buttonMarathon;
    public static String choosenDistance = "0";
    Drawable orgBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_rank);
        myOnClickListener();
    }

    public void setChoosenDistance(String distance)
    {
        choosenDistance = distance;
        Intent newActiv = new Intent(FriendsRank.this, FriendRankList.class);
        startActivity(newActiv);
//        DataBaseHelper.getWorldRankFor(choosenDistance);
    }

    public void myOnClickListener() {
        button100m = (Button) findViewById(R.id.button100m);
        orgBackground = button100m.getBackground();
        button100m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChoosenDistance("100m");
            }
        });
        button800m = (Button) findViewById(R.id.button800m);
        button800m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChoosenDistance("800m");
            }
        });
        button1500m = (Button) findViewById(R.id.button1500m);
        button1500m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChoosenDistance("1500m");
            }
        });
        button5000m = (Button) findViewById(R.id.button5000m);
        button5000m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChoosenDistance("5000m");
            }
        });
        button10000m = (Button) findViewById(R.id.button10000m);
        button10000m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChoosenDistance("10000m");
            }
        });
        buttonMarathon = (Button) findViewById(R.id.buttonMaraton);
        buttonMarathon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChoosenDistance("Maraton");
            }
        });
    }
}
