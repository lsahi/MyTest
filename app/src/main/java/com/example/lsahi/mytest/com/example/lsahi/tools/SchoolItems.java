package com.example.lsahi.mytest.com.example.lsahi.tools;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lsahi.mytest.R;
import com.example.lsahi.mytest.ShowSchoolActivity;
import com.example.lsahi.mytest.po.School;

import java.util.List;

/**
 * Created by lsahi on 2018/5/29.
 */

public class SchoolItems {

    /**
     * Created by lsahi on 2018/3/30.
     */

    public static class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.ViewHolder> {

        private Context mContext;
        private List<School> mSchoolList;

        static class ViewHolder extends RecyclerView.ViewHolder {
            CardView cardView;
            ImageView schoolImage;
            TextView schoolName;
            TextView schoolActivityName;

            public ViewHolder(View view) {
                super(view);
                cardView = (CardView) view;
                schoolImage = (ImageView) view.findViewById(R.id.schoolImage);
                schoolName = (TextView) view.findViewById(R.id.schoolUserName);
                schoolActivityName = (TextView) view.findViewById(R.id.schoolActivityName);
            }
        }

        public SchoolAdapter(List<School> schoolList) {
            mSchoolList = schoolList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (mContext == null) {
                mContext = parent.getContext();
            }
            View view = LayoutInflater.from(mContext).inflate(R.layout.school_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            holder.cardView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int position=holder.getAdapterPosition();
                    School school=mSchoolList.get(position);
                    Intent intent=new Intent(mContext,ShowSchoolActivity.class);
                    intent.putExtra(ShowSchoolActivity.SCHOOL_ID,school.getId());
                    intent.putExtra(ShowSchoolActivity.SCHOOL_NAME,school.getName());
                    intent.putExtra(ShowSchoolActivity.SCHOOL_DETAILS,school.getDetails());
                    intent.putExtra(ShowSchoolActivity.SCHOOL_HOST,school.getHost());
                    intent.putExtra(ShowSchoolActivity.SCHOOL_IMAGE_ID,school.getActivityImageId());
                    mContext.startActivity(intent);
                }

            });

            return holder;
            //return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            School school = mSchoolList.get(position);
            holder.schoolName.setText(school.getHost());
            holder.schoolActivityName.setText(school.getName());
            Glide.with(mContext).load(school.getActivityImageId()).into(holder.schoolImage);
        }

        @Override
        public int getItemCount() {
            return mSchoolList.size();
        }

    }
}
