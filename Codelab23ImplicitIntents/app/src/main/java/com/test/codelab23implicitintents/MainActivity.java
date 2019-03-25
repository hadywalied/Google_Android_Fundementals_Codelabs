package com.test.codelab23implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText et_web, et_geo, et_share;
    Button btn_web, btn_geo, btn_share;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getting views by ids
        et_geo = findViewById(R.id.et_geo);
        et_share = findViewById(R.id.et_share);
        et_web = findViewById(R.id.et_web);
        btn_geo = findViewById(R.id.btn_geo);
        btn_share = findViewById(R.id.btn_share);
        btn_web = findViewById(R.id.btn_web);

//        handling events
        btn_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = et_web.getText().toString();
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Log.d("ImplicitIntents", "Can't handle this!");
                }
            }
        });

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = et_share.getText().toString();
                String mimeType = "text/plain";
                ShareCompat.IntentBuilder
                        .from(MainActivity.this)
                        .setType(mimeType)
                        .setChooserTitle("Share this text with: ")
                        .setText(txt)
                        .startChooser();

            }
        });
        btn_geo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String loc = et_geo.getText().toString();
                Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
                Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Log.d("ImplicitIntents", "Can't handle this intent!");
                }
            }
        });
    }
}
