package com.msiddhu.chatbot;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.msiddhu.chatbot.Pojo.NotifyService;
import com.msiddhu.chatbot.R;

import java.util.Calendar;

public class profile extends AppCompatActivity {
    ListView listView1,listView2;
Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] conditiondata = {"Anxiety ", "Heart Attack", "Over weight", "Headache"};
        String[] prehealthdata = {" fever 1 week ago ", "asthama at 6 years old", "skin infection - Scabies"};
        bt=(Button)findViewById(R.id.notibutton);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        listView1=(ListView)findViewById(R.id.conditionlist);
        listView2=(ListView)findViewById(R.id.preHealthlist);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        listView1.setAdapter(new ArrayAdapter<String>(this,R.layout.list_view,R.id.textView,conditiondata));
        listView2.setAdapter(new ArrayAdapter<String>(this,R.layout.list_view,R.id.textView,prehealthdata));
    }
    public void createNotification (View view) {
        Log.v("notify","the NOtification class called");
        Intent myIntent = new Intent(getApplicationContext() , NotifyService. class ) ;
        AlarmManager alarmManager = (AlarmManager) getSystemService( ALARM_SERVICE ) ;
        PendingIntent pendingIntent = PendingIntent. getService ( this, 0 , myIntent , 0 ) ;
        Calendar calendar = Calendar. getInstance () ;
        calendar.set(Calendar. SECOND , 0 ) ;
        calendar.set(Calendar. MINUTE , 0 ) ;
        calendar.set(Calendar. HOUR , 0 ) ;
        calendar.set(Calendar. AM_PM , Calendar. AM ) ;
        calendar.add(Calendar. DAY_OF_MONTH , 1 ) ;
        alarmManager.setRepeating(AlarmManager. RTC_WAKEUP , calendar.getTimeInMillis() , 1000 * 60 * 60 * 24 , pendingIntent) ;
        Log.v("notify","the NOtification class end");
    }
}
