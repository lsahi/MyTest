package com.example.lsahi.mytest;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestSchoolActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private School[] schools={
            new School("akagi","Kancolle",R.drawable.akagi),
            new School("kaga","Kancolle",R.drawable.kaga),
            new School("shogaku","Kancolle",R.drawable.shogaku),
            new School("zuikaku","Kancolle",R.drawable.zuigaku),
            new School("akatsuki","Kancolle",R.drawable.akatsuki),
            new School("hibiki","Kancolle",R.drawable.hibiki),
            new School("ikanari","Kancolle",R.drawable.ikanari),
            new School("inazuma","Kancolle",R.drawable.inazuma),
            new School("kongo","Kancolle",R.drawable.kongo),
            new School("hie","Kancolle",R.drawable.hie),
            new School("haruna","Kancolle",R.drawable.haruna),
            new School("kirishima","Kancolle",R.drawable.kirishima),
            new School("yuudachi","Kancolle",R.drawable.yuudachi),
            new School("shigure","Kancolle",R.drawable.shigure),
            new School("eugen","Kancolle",R.drawable.eugen),
            new School("bismarck","Kancolle",R.drawable.bismarck)
    };
    private List<School> schoolList=new ArrayList<>();

    private SchoolAdapter adapter;
    /*
    School akagi=new School(("akagi"),R.drawable.akagi);
            SchoolList.add(akagi);
    School kaga=new School("kaga",R.drawable.kaga);
            SchoolList.add(kaga);
    School shogaku=new School(("shogaku"),R.drawable.shogaku);
            SchoolList.add(shogaku);
    School zuikaku=new School("zuikaku",R.drawable.zuigaku);
            SchoolList.add(zuikaku);
    School akatsuki=new School("akatsuki",R.drawable.akatsuki);
            SchoolList.add(akatsuki);
    School hibiki=new School("hibiki",R.drawable.hibiki);
            SchoolList.add(hibiki);
    School ikanari=new School("ikanari",R.drawable.ikanari);
            SchoolList.add(ikanari);
    School inazuma=new School("inazuma",R.drawable.inazuma);
            SchoolList.add(inazuma);
    School kongo=new School(("kongo"),R.drawable.kongo);
            SchoolList.add(kongo);
    School hie=new School("hie",R.drawable.hie);
            SchoolList.add(hie);
    School haruna=new School("haruna",R.drawable.haruna);
            SchoolList.add(haruna);
    School kirishima=new School("kirishima",R.drawable.kirishima);
            SchoolList.add(kirishima);
    School yuudachi=new School("yuudachi",R.drawable.yuudachi);
            SchoolList.add(yuudachi);
    School shigure=new School("shigure",R.drawable.shigure);
            SchoolList.add(shigure);
    School eugen=new School("eugen",R.drawable.eugen);
            SchoolList.add(eugen);
    School bismarck=new School("bismarck",R.drawable.bismarck);
            SchoolList.add(bismarck);
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_school);
        initSchools();
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.school_recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SchoolAdapter(schoolList);
        recyclerView.setAdapter(adapter);
    }

    private void initSchools(){
        schoolList.clear();
        for(int i=0;i<50;i++){
            Random random=new Random();
            int index=random.nextInt(schools.length);
            schoolList.add(schools[index]);
        }
    }
}
