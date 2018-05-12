package com.example.lsahi.mytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShowSchoolActivity extends AppCompatActivity {

    public static final String SCHOOL_NAME="school_name";
    public static final String SCHOOL_IMAGE_ID="school_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_school);
        Intent intent=getIntent();
        String schoolName=intent.getStringExtra(SCHOOL_NAME);
        //
    }
}
