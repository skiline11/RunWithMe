package com.example.skiline11.pierwszaaplikacja;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.skiline11.pierwszaaplikacja.database.DataBaseHelper;

import org.w3c.dom.Text;

public class AddNewFriends extends AppCompatActivity {

    private Button buttonAdd;
    private TextView textPesel, textName, textSurname;
    private String friendPesel, frinedName, friendSurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_friends);
        init();
    }

    private void init()
    {
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        textPesel = (TextView) findViewById(R.id.pesel);
        textName = (TextView) findViewById(R.id.name);
        textSurname = (TextView) findViewById(R.id.surname);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("________________Nacisnąłeś przycisk ADD");
                friendPesel = textPesel.getText().toString();
                frinedName = textName.getText().toString();
                friendSurname = textSurname.getText().toString();
                DataBaseHelper.addNewFriends(friendPesel);
            }
        });
    }


}
