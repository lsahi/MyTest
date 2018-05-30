package com.example.lsahi.mytest;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ShowSchoolActivity extends AppCompatActivity {

    public static final String SCHOOL_ID="school_id";
    public static final String SCHOOL_NAME="school_name";
    public static final String SCHOOL_IMAGE_ID="school_image_id";
    public static final String SCHOOL_DETAILS="school_details";
    public static final String SCHOOL_HOST="school_host";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_school);

        Intent intent=getIntent();
        String schoolId=intent.getStringExtra(SCHOOL_ID);
        String schoolName=intent.getStringExtra(SCHOOL_NAME);
        String schoolDetails=intent.getStringExtra(SCHOOL_DETAILS);
        int schoolImageId=intent.getIntExtra(SCHOOL_IMAGE_ID,0);

        Toolbar toolbar=(Toolbar) findViewById(R.id.ShowSchoolToolbar);
        CollapsingToolbarLayout collapsingToolbar=(CollapsingToolbarLayout)findViewById(R.id.ShowSchoolCollapsingToolbar);
        ImageView schoolImageView=(ImageView) findViewById(R.id.ShowSchoolImageView);
        TextView schoolContentText=(TextView) findViewById(R.id.show_school_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        collapsingToolbar.setTitle(schoolName);
        Glide.with(this).load(schoolImageId).into(schoolImageView);
        String schoolContent=generateSchoolContent(schoolDetails);
        schoolContentText.setText(schoolContent);
        //
    }

    private String generateSchoolContent(String schoolDetails){
        StringBuilder schoolContent=new StringBuilder();
        schoolContent.append(schoolDetails);
        return schoolContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
