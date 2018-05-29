package com.example.lsahi.mytest.com.example.lsahi.tools;

/**
 * Created by lsahi on 2018/5/2.
 */

public class School {

    private String activityId;
    private String userName;
    private String activityName;
    private int activityImageId;

    public School(String activityId, String userName, String activityName, int activityImageId){
        this.activityId=activityId;
        this.userName=userName;
        this.activityName=activityName;
        this.activityImageId=activityImageId;
    }

    public String getActivityId(){
        return activityId;
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
