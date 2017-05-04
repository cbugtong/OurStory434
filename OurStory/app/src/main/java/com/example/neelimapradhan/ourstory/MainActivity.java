package com.example.neelimapradhan.ourstory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    /*Maryland Day @ The Stamp Gallery*/
    String [] h1 = {"#MarylandDay2017","#MIDPOINT2017"};
    Event event = new Event("MD Day @ The Stamp Gallery",
            "The Stamp Student Union's own art exhibition space, The Stamp Gallery, welcomes " +
                    "visitors on Maryland Day 2017 to experience our current exhibition MIDPOINT 2017, " +
                    "which showcases three Masters of Fine Arts candidates in the UMD fine arts program." +
                    " See work by Beki Basch, Hugh Condrey Bryant, and Jessica Van Brakle and see how " +
                    "their site-specific works display within the gallery's walls.",
            h1,
            new GregorianCalendar(2017,4,29,20,0),
            "The Stamp Gallery",
            null);

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_PREVIEW = 2;
    static final int REQUEST_CREATE_EVENT = 3;

    Bitmap mBitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Event details setup */
        ((TextView) findViewById(R.id.view_title)).setText(event.title);
        ((CheckBox) findViewById(R.id.checkBox1)).setText(event.hashtags[0]);
        ((CheckBox) findViewById(R.id.checkBox2)).setText(event.hashtags[1]);

        /*EditText Setup*/
        final EditText textBox = (EditText) findViewById(R.id.edit_text);

        /*Toolbar Setup*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*Add Media Button Setup*/
        Button addMedia = (Button) findViewById(R.id.media_button); //media button will open menu
        addMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(takePictureIntent);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        /*CheckBox Setup*/
        final CheckBox check1 = (CheckBox) findViewById(R.id.checkBox1);
        check1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check1.isChecked())
                    textBox.setText(textBox.getText() + " " + check1.getText());
            }
        });

        final CheckBox check2 = (CheckBox) findViewById(R.id.checkBox2);
        check2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check2.isChecked())
                    textBox.setText(textBox.getText() + " " + check2.getText());
            }
        });

        /*Post Preview Button Setup*/
        Button postPreview = (Button) findViewById(R.id.post_button); //media button will open menu
        postPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check1.setChecked(false);
                check2.setChecked(false);
                Intent previewIntent = new Intent(getApplicationContext(),PreviewActivity.class);
                previewIntent.putExtra("text",textBox.getText());
                previewIntent.putExtra("image",mBitmap);
                startActivityForResult(previewIntent,REQUEST_PREVIEW);
            }
        });

        /*Drawer Sidebar Setup*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            mBitmap = (Bitmap) data.getExtras().get("data");

        } else if (requestCode == REQUEST_PREVIEW && resultCode == RESULT_OK) {
            ((EditText) findViewById(R.id.edit_text)).setText("");
            Toast.makeText(getApplicationContext(), "Post Published!", Toast.LENGTH_SHORT).show();
            mBitmap = null;

        } else if (requestCode == REQUEST_CREATE_EVENT) {
            switch (resultCode) {
                case RESULT_OK:
                    Toast.makeText(getApplicationContext(), "New Event Published!", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_FIRST_USER:
                    Toast.makeText(getApplicationContext(), "Draft Saved [TO BE IMPLEMENTED]", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "Draft Discarded", Toast.LENGTH_SHORT).show();
            }
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (id == R.id.create_event_nav) {
            Intent createEventIntent = new Intent(this.getApplicationContext(),CreateEventActivity.class);
            startActivityForResult(createEventIntent,REQUEST_CREATE_EVENT);

        } else if (id == R.id.nav_upcoming1) {
            Intent viewEventIntent = new Intent(this.getApplicationContext(),ViewEventActivity.class);
            viewEventIntent.putExtra("event",1);
            startActivity(viewEventIntent);
            finish();

        } else if (id == R.id.nav_upcoming2) {
            Intent viewEventIntent = new Intent(this.getApplicationContext(),ViewEventActivity.class);
            viewEventIntent.putExtra("event",2);
            startActivity(viewEventIntent);
            finish();

        } else if (id == R.id.nav_invite) {
            Toast.makeText(getApplicationContext(), "TO BE IMPLEMENTED", Toast.LENGTH_SHORT).show();

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
