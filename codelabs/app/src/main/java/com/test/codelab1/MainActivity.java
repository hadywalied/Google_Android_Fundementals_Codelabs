package com.test.codelab1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_Count, btn_Toast;
    TextView tv_counter;
    int count =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Main", "onCreate: Main");

//        findViewsByIds
        btn_Count = findViewById(R.id.button3);
        btn_Toast = findViewById(R.id.button2);
        tv_counter = findViewById(R.id.textView);

//        Handling clicks

        btn_Toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),String.valueOf(count-1), Toast.LENGTH_SHORT).show();
            }
        });
        btn_Count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count<101){
                tv_counter.setText(String.valueOf(count));
                count++;}
                else count =0;
            }
        });
    }


}
