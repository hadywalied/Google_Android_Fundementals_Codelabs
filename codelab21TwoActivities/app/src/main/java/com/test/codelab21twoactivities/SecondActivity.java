package com.test.codelab21twoactivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
//    views
    TextView tv_recievedmsg;
    Button btn_reply;
    TextInputEditText tied_Reply;
//    variables
    Intent intentMessage , intentReply;
    Bundle bundleMessage, bundleReply;
    public static final String Extra_Reply= "com.test.codelab21twoactivities.SecondActivity.Reply";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        findingViewsById
        tv_recievedmsg = findViewById(R.id.tv_recievedmsg);
        tied_Reply = findViewById(R.id.tiet_reply);
        btn_reply = findViewById(R.id.btn_reply);
//        Recieve The Message
        intentMessage = getIntent();
        bundleMessage= (Bundle) intentMessage.getBundleExtra(MainActivity.Extra_message);

        if(bundleMessage!=null){
            tv_recievedmsg.setText(bundleMessage.getString(MainActivity.Extra_message));
        }

//        Reply

        btn_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reply = tied_Reply.getText().toString();
//                if(!TextUtils.isEmpty(reply)){
                    bundleReply =new Bundle();
                    bundleReply.putString(Extra_Reply , reply);
                    intentReply = new Intent();
                    intentReply.putExtra(Extra_Reply , bundleReply);
                    setResult(RESULT_OK, intentReply);
                    finish();
//                }
            }
        });

    }
}
