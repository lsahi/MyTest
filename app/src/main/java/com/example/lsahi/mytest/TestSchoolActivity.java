package com.example.lsahi.mytest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lsahi.mytest.com.example.lsahi.tools.School;
import com.example.lsahi.mytest.com.example.lsahi.tools.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestSchoolActivity extends AppCompatActivity implements View.OnClickListener{

    private String loggedName;
    private TextView myname;

    private SwipeRefreshLayout swipeRefresh;
    private DrawerLayout mDrawerLayout;


    private School[] schools={
           /* new School("akagi","Kancolle",R.drawable.akagi),
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
            new School("bismarck","Kancolle",R.drawable.bismarck),*/
            new School("lsahi","software",R.drawable.lsahi_temp),

            new School("lsahi1","computer",R.drawable.lsahi1),
            new School("lsahi1","steam",R.drawable.lsahi2),
            new School("lsahi1","ubisoft",R.drawable.lsahi3),
            new School("lsahi5","google",R.drawable.lsahi4),
            new School("lsahi2","android",R.drawable.lsahi5),
            new School("lsahi3","iphone",R.drawable.lsahi6),
            new School("lsahi4","windows",R.drawable.lsahi7)
    };
    private List<School> schoolList=new ArrayList<>();

    private Student.SchoolAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_school);
        initSchools();

        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView=(NavigationView) findViewById(R.id.nav_view);
        View headerLayout = navView.inflateHeaderView(R.layout.nav_header);
        myname = (TextView) headerLayout.findViewById(R.id.username);
        navHeaderChange();

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ls_menu);
        }
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem item){
                switch(item.getItemId()){
                    case R.id.nav_login:
                        if(loginStatusCheck()==1){
                            Toast.makeText(TestSchoolActivity.this,"您已登陆，无需再次登录",Toast.LENGTH_SHORT).show();
                        }else{
                            Intent intentLogin=new Intent(TestSchoolActivity.this,LoginActivity.class);
                            startActivity(intentLogin);
                        }
                        /*
                        Intent intentLogin=new Intent(TestSchoolActivity.this,LoginActivity.class);
                        startActivity(intentLogin);
                        */
                        break;
                    case R.id.nav_profile:
                        Intent intentProfile=new Intent(TestSchoolActivity.this,ProfileActivity.class);
                        startActivity(intentProfile);
                        break;
                    case R.id.nav_usercheck:
                        Intent intentCheck=new Intent(TestSchoolActivity.this,UploadActivity.class);
                        startActivity(intentCheck);
                        break;
                    case R.id.nav_logout:
                        //pref.
                        break;
                    default:
                        mDrawerLayout.closeDrawers();
                }
                return true;
            }
        });

        FloatingActionButton add=(FloatingActionButton)findViewById(R.id.add);
        add.setOnClickListener(this);

        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.school_recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Student.SchoolAdapter(schoolList);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                Intent intent2=new Intent(TestSchoolActivity.this, AddActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
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

/*
    private void setCardIamge(){
        String url = "http://img.my.csdn.net/uploads/201407/26/1406383291_8239.jpg";
        OkHttpUtils.get().url(url).tag(this)
                .build()
                .connTimeOut(20000).readTimeOut(20000).writeTimeOut(20000)
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int id) {
                        imageView.setImageBitmap(bitmap);
                    }
                });
    }
    */

// this loginChack also offers username
    public int loginStatusCheck(){
        SharedPreferences pref=getSharedPreferences("localLogin",MODE_PRIVATE);
        String name=pref.getString("name","");
        int status=pref.getInt("status",0);
        //status =1
        loggedName=name;
        return status;
    }

    //在handler中更新UI
    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg) {
            if(loggedName!=null) {
                myname.setText(loggedName);
            }else{
                myname.setText("游客，请登录");
            }
        }
    };

    //change UI
    private void navHeaderChange(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message=new Message();
                message.what=1;
                mHandler.sendMessage(message);
            }
        }).start();
    }


}
