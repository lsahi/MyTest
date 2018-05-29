package com.example.lsahi.mytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lsahi.mytest.com.example.lsahi.tools.Msg;
import com.example.lsahi.mytest.com.example.lsahi.tools.MsgAdapter;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    private List<Msg> msgList= new ArrayList<>();
    private EditText inputText;

    private Button send;

    private RecyclerView msgRecyclerView;

    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        initMsgs();
        inputText=(EditText) findViewById(R.id.input_text);
        send=(Button) findViewById(R.id.send);
        msgRecyclerView=(RecyclerView) findViewById(R.id.msg_recycler_view);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter=new MsgAdapter(msgList);

        msgRecyclerView.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String content=inputText.getText().toString();
                if(!"".equals(content)){
                    Msg msg=new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size()-1);
                    msgRecyclerView.scrollToPosition(msgList.size()-1);
                    inputText.setText("");
                }
            }
        });


    }

    private void initMsgs(){
        Msg msg1=new Msg("HELLO GUY", Msg.TYPE_RECEIVED);
        msgList.add(msg1);

        Msg msg2=new Msg("WHO IS THAT", Msg.TYPE_SENT);
        msgList.add(msg2);

        Msg msg3=new Msg("TOM HERE", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}
