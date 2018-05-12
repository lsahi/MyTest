package com.example.lsahi.mytest;

import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
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

    private SwipeRefreshLayout swipeRefresh;
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
        swipeRefresh=(SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshSchools();
            }
        });
    }

    private void refreshSchools(){
        new Thread(new Runnable(){
            @Override
            public  void run(){
                try{
                    Thread.sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        initSchools();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    // get from the Internet, refresh to get again
    private void initSchools(){
        schoolList.clear();
        for(int i=0;i<50;i++){
            Random random=new Random();
            int index=random.nextInt(schools.length);
            schoolList.add(schools[index]);
        }
    }
}
