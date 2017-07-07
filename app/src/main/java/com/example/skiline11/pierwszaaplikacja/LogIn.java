package com.example.skiline11.pierwszaaplikacja;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.skiline11.pierwszaaplikacja.adapter.ListOfPeopleAdapter;
import com.example.skiline11.pierwszaaplikacja.database.DataBaseHelper;
import com.example.skiline11.pierwszaaplikacja.model.Person;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class LogIn extends AppCompatActivity {

    public static String myPesel, myName, mySurname;
    public Button logIn;
    public EditText pesel, name, surname;

    private ListView lvPeople;
    private ListOfPeopleAdapter adapter;
    private List<Person> peopleList;
    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        lvPeople = (ListView)findViewById(R.id.friendRank_listView); // skończyłem na 19.20
        copyDatabase(this);
        init();
    }

    private boolean copyDatabase(Context context)
    {
        try {
            InputStream inputStream = context.getAssets().open(DataBaseHelper.DB_NAME);
            String outFileName =  DataBaseHelper.DB_PATH + DataBaseHelper.DB_NAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while((length = inputStream.read(buff)) > 0)
            {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("Login", "DataBase copied");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

// ------------------------ Tej funkcji nigdzie nie używam
//    public void prepareDataBase()
//    {
//        dbHelper = new DataBaseHelper(this);
//
//        File dataBase = getApplicationContext().getDatabasePath(DataBaseHelper.DB_PATH);
//        if(dataBase.exists() == false)
//        {
//            dbHelper.getReadableDatabase();
//            //kopiujemy baze danych
//            if(copyDatabase(this))
//            {
//                Toast.makeText(this, "Copy database succed", Toast.LENGTH_SHORT).show();
//            }
//            else
//            {
//                Toast.makeText(this, "Copy database error", Toast.LENGTH_SHORT).show();
//                return;
//            }
//        }
//        peopleList = dbHelper.getListOfPeoples();
//        adapter = new ListOfPeopleAdapter(this, peopleList);
//        lvPeople.setAdapter(adapter);
//    }

    public void init() {
        dbHelper = new DataBaseHelper(this);
        pesel = (EditText) findViewById(R.id.pesel);
        name = (EditText) findViewById(R.id.name);
        surname = (EditText) findViewById(R.id.surname);
        logIn = (Button) findViewById(R.id.buttonLogIn);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPesel = pesel.getText().toString();
                myName = name.getText().toString();
                mySurname = surname.getText().toString();
//                System.out.println(myPesel + "," + myName + "," + mySurname);
                if(DataBaseHelper.addPersonIfNotExist() == true)
                {
//                    System.out.println("Udało się");
                    Intent newActiv = new Intent(LogIn.this, MainMenu.class);
                    startActivity(newActiv);
                }
//                else System.out.println("Nie udało się");
            }
        });
    }

}