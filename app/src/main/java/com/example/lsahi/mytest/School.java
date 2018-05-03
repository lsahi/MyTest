package com.example.lsahi.mytest;

/**
 * Created by lsahi on 2018/5/2.
 */

public class School {
    private String userName;
    private String activityName;
    private int activityImageId;

    public School(String userName, String activityName, int activityImageId){
        this.userName=userName;
        this.activityName=activityName;
        this.activityImageId=activityImageId;
    }

    public String getUserName(){

        return userName;
    }

    public String getActivityName(){

        return activityName;
    }

    public int getActivityImageId(){

        return activityImageId;
    }

}
