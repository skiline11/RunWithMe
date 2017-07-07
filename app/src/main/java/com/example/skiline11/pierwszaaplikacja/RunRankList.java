package com.example.skiline11.pierwszaaplikacja;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.skiline11.pierwszaaplikacja.adapter.ListOfPeopleAdapter;
import com.example.skiline11.pierwszaaplikacja.adapter.ListOfRunAdapter;
import com.example.skiline11.pierwszaaplikacja.database.DataBaseHelper;
import com.example.skiline11.pierwszaaplikacja.model.Person;
import com.example.skiline11.pierwszaaplikacja.model.Run;

import java.util.List;

public class RunRankList extends AppCompatActivity {

    private ListView lvRun;
    private ListOfRunAdapter adapter;
    private List<Run> runList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_rank_list);
        lvRun = (ListView)findViewById(R.id.runRank_listView); // skończyłem na 19.20
        runList = DataBaseHelper.getRank();
        adapter = new ListOfRunAdapter(this, runList);
        lvRun.setAdapter(adapter);
    }
}
