package com.example.skiline11.pierwszaaplikacja;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.skiline11.pierwszaaplikacja.database.DataBaseHelper;

import java.util.Calendar;

public class AddNewResult extends AppCompatActivity {

    public static int year_x, month_x, day_x;
    public static int hours_x, minutes_x, seconds_x, miliseconds_x;
    static final int DATA_ID = 0;
    public Button chooseDateButton;
    public Button button100m, button800m, button1500m, button5000m, button10000m, buttonMaraton;
    public Button buttonAdd;
    public TextView textDate;
    public TextView textTime1, textTime2, textTime3;
    public EditText editTextTime1, editTextTime2, editTextTime3;
    Drawable orgBackground;
    public static String choosenDistance = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_result);
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        showCalendarOnDialogCLick();
        myOnClickListener();
        addThisResult();
    }

    public void addThisResult()
    {
        buttonAdd = (Button)findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(choosenDistance)
                {
                    case "BiegNa100m" :
                        hours_x = 0;
                        minutes_x = 0;
                        seconds_x = Integer.parseInt(editTextTime2.getText().toString());
                        miliseconds_x = Integer.parseInt(editTextTime3.getText().toString());
                        break;
                    case "BiegNa800m" :
                    case "BiegNa1500m" :
                    case "BiegNa5000m" :
                        hours_x = 0;
                        minutes_x = Integer.parseInt(editTextTime1.getText().toString());
                        seconds_x = Integer.parseInt(editTextTime2.getText().toString());
                        miliseconds_x = Integer.parseInt(editTextTime3.getText().toString());
                        break;
                    case "BiegNa10000m" :
                    case "Maraton" :
                        hours_x = Integer.parseInt(editTextTime1.getText().toString());
                        minutes_x = Integer.parseInt(editTextTime2.getText().toString());
                        seconds_x = Integer.parseInt(editTextTime3.getText().toString());
                        miliseconds_x = 0;
                        break;
                    default:
                        break;
                }
                DataBaseHelper.addNewResult();
            }
        });
    }

    public void showCalendarOnDialogCLick()
    {
        chooseDateButton = (Button)findViewById(R.id.chooseDate);
        chooseDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATA_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        if(id == DATA_ID) return new DatePickerDialog(this, dpickerListener, year_x, month_x, day_x);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x = year;
            month_x = month + 1;
            day_x = dayOfMonth;
            textDate = (TextView)findViewById(R.id.date);
            textDate.setText(day_x + "/" + month_x + "/" + year_x);
            chooseDateButton.setBackgroundColor(Color.CYAN);
            Toast.makeText(AddNewResult.this, year_x + "/" + month_x + "/" + day_x, Toast.LENGTH_LONG).show();
        }
    };

    public void clearColorOfDistanceButton()
    {
        switch(choosenDistance)
        {
            case "BiegNa100m" :
                button100m.setBackground(orgBackground);
                break;
            case "BiegNa800m" :
                button800m.setBackground(orgBackground);
                break;
            case "BiegNa1500m" :
                button1500m.setBackground(orgBackground);
                break;
            case "BiegNa5000m" :
                button5000m.setBackground(orgBackground);
                break;
            case "BiegNa10000m" :
                button10000m.setBackground(orgBackground);
                break;
            case "Maraton" :
                buttonMaraton.setBackground(orgBackground);
                break;
            default:
                break;
        }
    }

    public void setUnitOfResultTime(String distance)
    {
        textTime1 = (TextView)findViewById(R.id.time1);
        textTime2 = (TextView)findViewById(R.id.time2);
        textTime3 = (TextView)findViewById(R.id.time3);
        editTextTime1 = (EditText)findViewById(R.id.result1);
        editTextTime2 = (EditText)findViewById(R.id.result2);
        editTextTime3 = (EditText)findViewById(R.id.result3);
        editTextTime1.setVisibility(View.VISIBLE);
        editTextTime2.setVisibility(View.VISIBLE);
        editTextTime3.setVisibility(View.VISIBLE);
        textTime1.setVisibility(View.VISIBLE);
        textTime2.setVisibility(View.VISIBLE);
        textTime3.setVisibility(View.VISIBLE);
        if(distance.equals("BiegNa100m")) editTextTime1.setVisibility(View.VISIBLE);
        switch(distance)
        {
            case "BiegNa100m" :
                textTime1.setText("");
                editTextTime1.setVisibility(View.GONE);
                textTime2.setText("sekund");
                textTime3.setText("setnych sek.");
                break;
            case "BiegNa800m" :
            case "BiegNa1500m" :
            case "BiegNa5000m" :
                textTime1.setText("minut");
                textTime2.setText("sekund");
                textTime3.setText("setnych sek");
                break;
            case "BiegNa10000m" :
            case "Maraton" :
                textTime1.setText("godzin");
                textTime2.setText("minut");
                textTime3.setText("sekund");
                break;
            default:
                break;
        }
    }


    public void setChoosenDistance(String distance)
    {
        clearColorOfDistanceButton();
        setUnitOfResultTime(distance);
        choosenDistance = distance;
        switch(distance)
        {
            case "BiegNa100m" :
                button100m.setBackgroundColor(Color.CYAN);
                break;
            case "BiegNa800m" :
                button800m.setBackgroundColor(Color.CYAN);
                break;
            case "BiegNa1500m" :
                button1500m.setBackgroundColor(Color.CYAN);
                break;
            case "BiegNa5000m" :
                button5000m.setBackgroundColor(Color.CYAN);
                break;
            case "BiegNa10000m" :
                button10000m.setBackgroundColor(Color.CYAN);
                break;
            case "Maraton" :
                buttonMaraton.setBackgroundColor(Color.CYAN);
                break;
            default:
                break;
        }
    }

    public void myOnClickListener() {
        button100m = (Button) findViewById(R.id.button100m);
        orgBackground = button100m.getBackground();
        button100m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button100m.setBackgroundColor(Color.CYAN);
                setChoosenDistance("BiegNa100m");
            }
        });
        button800m = (Button) findViewById(R.id.button800m);
        button800m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button800m.setBackgroundColor(Color.CYAN);
                setChoosenDistance("BiegNa800m");
            }
        });
        button1500m = (Button) findViewById(R.id.button1500m);
        button1500m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1500m.setBackgroundColor(Color.CYAN);
                setChoosenDistance("BiegNa1500m");
            }
        });
        button5000m = (Button) findViewById(R.id.button5000m);
        button5000m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button5000m.setBackgroundColor(Color.CYAN);
                setChoosenDistance("BiegNa5000m");
            }
        });
        button10000m = (Button) findViewById(R.id.button10000m);
        button10000m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button10000m.setBackgroundColor(Color.CYAN);
                setChoosenDistance("BiegNa10000m");
            }
        });
        buttonMaraton = (Button) findViewById(R.id.buttonMaraton);
        buttonMaraton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonMaraton.setBackgroundColor(Color.CYAN);
                setChoosenDistance("Maraton");
            }
        });
    }
}
