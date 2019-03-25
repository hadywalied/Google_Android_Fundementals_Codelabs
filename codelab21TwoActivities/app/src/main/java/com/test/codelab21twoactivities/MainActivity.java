package com.test.codelab21twoactivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //    declaration of views
    Button btn_send;
    TextInputEditText tied_message;
    TextView tv_header, tv_reply;

    //    variables declarations
    public static String message;
    Bundle bundle = new Bundle();
    public static final String Extra_message = "com.test.codelab21twoactivities.MainActivity.message";
    public static final int TEXT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        findingViewsById
        btn_send = findViewById(R.id.btn_send);
        tied_message = findViewById(R.id.tiet_message);
        tv_header = findViewById(R.id.Reply_header);
        tv_reply = findViewById(R.id.tv_reply);
// Restore the state.
        if (savedInstanceState != null) {
            boolean isVisible =
                    savedInstanceState.getBoolean("reply_visible");
            if (isVisible) {
                tv_header.setVisibility(View.VISIBLE);
                tv_reply.setText(savedInstanceState
                        .getString("reply_text"));
                tv_reply.setVisibility(View.VISIBLE);
            }

        }

//                HandlingClicks
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                //getting the message
                message = tied_message.getText().toString();
//                if (TextUtils.isEmpty(message)) {
//                    Toast.makeText(MainActivity.this, "please enter the message", Toast.LENGTH_LONG).show();
//                } else {
                    bundle.putString(Extra_message, message);
                    intent.putExtra(Extra_message, bundle);
                    startActivityForResult(intent, TEXT_REQUEST);
//                }
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // If the heading is visible, message needs to be saved.
        // Otherwise we're still using default layout.
        if (tv_header.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text", tv_reply.getText().toString());
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getBundleExtra(SecondActivity.Extra_Reply);
                tv_header.setVisibility(View.VISIBLE);
                tv_reply.setVisibility(View.VISIBLE);
                tv_reply.setText(bundle.getString(SecondActivity.Extra_Reply));
            }
        }

    }
}
