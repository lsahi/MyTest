package com.example.lsahi.mytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SecondActivity extends AppCompatActivity {

    private  String[] data={"apple","banana","1","2","cherry","strawberry","234","564664","orange","pear","wut","space","test","man","ls","what","kancolle",};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                SecondActivity.this, android.R.layout.simple_list_item_1,data
        );
        ListView listView=(ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}
