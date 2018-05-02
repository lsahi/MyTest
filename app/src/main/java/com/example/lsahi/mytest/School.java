package com.example.lsahi.mytest;

/**
 * Created by lsahi on 2018/5/2.
 */

public class School {
    private String userName;
    private String activityName;
    private int activityId;

    public School(String userName, String activityName, int activityId){
        this.userName=userName;
        this.activityName=activityName;
        this.activityId=activityId;
    }

    public String getUserName(){
        return userName;
    }

    public String getActivityName(){
        return activityName;
    }

}
