package com.example.lsahi.mytest;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by lsahi on 2018/3/30.
 */

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.ViewHolder>{

    private Context mContext;
    private List<School> mSchoolList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView schoolImage;
        TextView schoolName;
        TextView schoolActivityName;

        public ViewHolder(View view){
            super(view);
            cardView=(CardView) view;
            schoolImage=(ImageView) view.findViewById(R.id.schoolImage);
            schoolName=(TextView) view.findViewById(R.id.schoolUserName);
            schoolActivityName=(TextView) view.findViewById(R.id.schoolActivityName);
        }
    }

    public SchoolAdapter(List<School> schoolList){
        mSchoolList=schoolList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if(mContext==null){
            mContext=parent.getContext();
        }
        View view=LayoutInflater.from(mContext).inflate(R.layout.school_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        School school=mSchoolList.get(position);
        holder.schoolName.setText(school.getUserName());
        holder.schoolActivityName.setText(school.getActivityName());
        Glide.with(mContext).load(school.getActivityImageId()).into(holder.schoolImage);
    }

    @Override
    public int getItemCount(){
        return mSchoolList.size();
    }


}
