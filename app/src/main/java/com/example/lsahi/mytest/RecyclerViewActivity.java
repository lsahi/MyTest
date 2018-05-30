package com.example.lsahi.mytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lsahi.mytest.po.Fruit;
import com.example.lsahi.mytest.com.example.lsahi.tools.FruitAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyclerViewActivity extends AppCompatActivity {

    private List<Fruit> fruitList=new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View view){
            super(view);
            fruitImage=(ImageView) view.findViewById(R.id.fruit_image);
            fruitName=(TextView) view.findViewById(R.id.fruit_name);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initFruits();
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter=new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
    }

    private void initFruits(){
        for(int i=0;i<2;i++){
            Fruit akagi=new Fruit(getRandomLengthName("akagi"),R.drawable.akagi);
            fruitList.add(akagi);
            Fruit kaga=new Fruit("kaga",R.drawable.kaga);
            fruitList.add(kaga);
            Fruit shogaku=new Fruit(getRandomLengthName("shogaku"),R.drawable.shogaku);
            fruitList.add(shogaku);
            Fruit zuikaku=new Fruit("zuikaku",R.drawable.zuigaku);
            fruitList.add(zuikaku);
            Fruit akatsuki=new Fruit("akatsuki",R.drawable.akatsuki);
            fruitList.add(akatsuki);
            Fruit hibiki=new Fruit("hibiki",R.drawable.hibiki);
            fruitList.add(hibiki);
            Fruit ikanari=new Fruit("ikanari",R.drawable.ikanari);
            fruitList.add(ikanari);
            Fruit inazuma=new Fruit("inazuma",R.drawable.inazuma);
            fruitList.add(inazuma);
            Fruit kongo=new Fruit(getRandomLengthName("kongo"),R.drawable.kongo);
            fruitList.add(kongo);
            Fruit hie=new Fruit("hie",R.drawable.hie);
            fruitList.add(hie);
            Fruit haruna=new Fruit("haruna",R.drawable.haruna);
            fruitList.add(haruna);
            Fruit kirishima=new Fruit("kirishima",R.drawable.kirishima);
            fruitList.add(kirishima);
            Fruit yuudachi=new Fruit("yuudachi",R.drawable.yuudachi);
            fruitList.add(yuudachi);
            Fruit shigure=new Fruit("shigure",R.drawable.shigure);
            fruitList.add(shigure);
            Fruit eugen=new Fruit("eugen",R.drawable.eugen);
            fruitList.add(eugen);
            Fruit bismarck=new Fruit("bismarck",R.drawable.bismarck);
            fruitList.add(bismarck);
        }
    }
    private String getRandomLengthName(String name){
        Random random=new Random();
        int length=random.nextInt(20)+1;
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<length;i++){
            builder.append(name);
        }
        return builder.toString();
    }
}
