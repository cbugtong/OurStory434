package com.example.neelimapradhan.ourstory;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class LinkAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_account);

        /*Link Media Button Setup*/
        ImageButton fbButton = (ImageButton) findViewById(R.id.facebook);
        fbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Connected to Facebook!", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton twButton = (ImageButton) findViewById(R.id.twitter);
        twButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Connected to Twitter!", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton igButton = (ImageButton) findViewById(R.id.instagram);
        igButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Connected to Instagram!", Toast.LENGTH_SHORT).show();
            }
        });

        /*Post Preview Button Setup*/
        Button toMainButton = (Button) findViewById(R.id.next); //media button will open menu
        toMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });
    }
}
