package com.example.skiline11.pierwszaaplikacja;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class PlanNewRunning extends AppCompatActivity {

    int year_x, month_x, day_x;
    int hours_x, minutes_x;
    static final int DATA_ID = 0, TIME_ID = 1;
    public Button chooseDateButton, chooseTimeButton;
    public Button button100m, button800m, button1500m, button5000m, button10000m, buttonMarathon;
    public TextView textDate, textTime;
    Drawable orgBackground;
    public String choosenDistance = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_new_running);

        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        hours_x = cal.get(Calendar.HOUR);
        minutes_x = cal.get(Calendar.MINUTE);
        showCalendarOnDialogCLick();
        showClockOnDialogClick();
        distanceClick();
//        planItClick();
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

    public void showClockOnDialogClick()
    {
        chooseTimeButton = (Button)findViewById(R.id.chooseTime);
        chooseTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TIME_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        switch(id)
        {
            case DATA_ID:
                return new DatePickerDialog(this, dpickerListener, year_x, month_x, day_x);
            case TIME_ID:
                return new TimePickerDialog(this, tpickerListener, hours_x, minutes_x, true);
        }
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
            Toast.makeText(PlanNewRunning.this, year_x + "/" + month_x + "/" + day_x, Toast.LENGTH_LONG).show();
        }
    };

    private TimePickerDialog.OnTimeSetListener tpickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String zero1, zero2;
            hours_x = hourOfDay;
            minutes_x = minute;
            textTime = (TextView)findViewById(R.id.time);
            if(hours_x < 10) zero1 = "0";
            else zero1 = "";
            if(minutes_x < 10) zero2 = "0";
            else zero2 = "";
            textTime.setText(zero1 + hours_x + " : " + zero2 + minutes_x);
            chooseTimeButton.setBackgroundColor(Color.CYAN);
            Toast.makeText(PlanNewRunning.this, hours_x + " : " + minutes_x, Toast.LENGTH_LONG).show();
        }
    };

    public void clearColorOfDistanceButton()
    {
        switch(choosenDistance)
        {
            case "100m" :
                button100m.setBackground(orgBackground);
                break;
            case "800m" :
                button800m.setBackground(orgBackground);
                break;
            case "1500m" :
                button1500m.setBackground(orgBackground);
                break;
            case "5000m" :
                button5000m.setBackground(orgBackground);
                break;
            case "10000m" :
                button10000m.setBackground(orgBackground);
                break;
            case "Marathon" :
                buttonMarathon.setBackground(orgBackground);
                break;
            default:
                break;
        }
    }

    public void setChoosenDistance(String distance)
    {
        clearColorOfDistanceButton();
        choosenDistance = distance;
        switch(distance)
        {
            case "100m" :
                button100m.setBackgroundColor(Color.CYAN);
                break;
            case "800m" :
                button800m.setBackgroundColor(Color.CYAN);
                break;
            case "1500m" :
                button1500m.setBackgroundColor(Color.CYAN);
                break;
            case "5000m" :
                button5000m.setBackgroundColor(Color.CYAN);
                break;
            case "10000m" :
                button10000m.setBackgroundColor(Color.CYAN);
                break;
            default:
                break;
        }
    }

    public void distanceClick() {
        button100m = (Button) findViewById(R.id.button100m);
        orgBackground = button100m.getBackground();
        button100m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button100m.setBackgroundColor(Color.CYAN);
                setChoosenDistance("100m");
            }
        });
        button800m = (Button) findViewById(R.id.button800m);
        button800m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button800m.setBackgroundColor(Color.CYAN);
                setChoosenDistance("800m");
            }
        });
        button1500m = (Button) findViewById(R.id.button1500m);
        button1500m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1500m.setBackgroundColor(Color.CYAN);
                setChoosenDistance("1500m");
            }
        });
        button5000m = (Button) findViewById(R.id.button5000m);
        button5000m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button5000m.setBackgroundColor(Color.CYAN);
                setChoosenDistance("5000m");
            }
        });
        button10000m = (Button) findViewById(R.id.button10000m);
        button10000m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button10000m.setBackgroundColor(Color.CYAN);
                setChoosenDistance("10000m");
            }
        });
        buttonMarathon = (Button) findViewById(R.id.buttonMaraton);
        buttonMarathon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonMarathon.setBackgroundColor(Color.CYAN);
                setChoosenDistance("Marathon");
            }
        });
    }
}
