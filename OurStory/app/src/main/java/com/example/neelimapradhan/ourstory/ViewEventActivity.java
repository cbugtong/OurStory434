package com.example.neelimapradhan.ourstory;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.GregorianCalendar;

/**
 * Created by Chris on 4/5/2017.
 */

public class ViewEventActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /*Neelima's Birthday Party*/
    String [] h2 = {"#feeling21","#PartyWithPradhan"};
    Event event2 = new Event("Neelima's Birthday Party!",
            "Yay it's my birthday! Come get some Chik-fil-a",
            h2,
            new GregorianCalendar(2017,11,23,20,0),
            "Chik-fil-a",
            null);

    /*HCI Conference @ Stamp*/
    String [] h3 = {"#HCI","#HumanCentered"};
    Event event3 = new Event("HCI Conference @ Stamp",
            "Human Computer Interaction is an increasingly important field. As computers become" +
                    " more and more ubiquitous in our everyday lives, we must continually develop" +
                    " our understanding through a study of technology, psychology, and design",
            h3,
            new GregorianCalendar(2017,10,25,12,0),
            "Stamp Student Union",
            null);

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_PREVIEW = 2;
    static final int REQUEST_CREATE_EVENT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);

        /*Toolbar Setup*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (id == R.id.nav_share) {
            // Handle the camera action

        }  else if (id == R.id.create_event_nav) {
            Intent createEventIntent = new Intent(this.getApplicationContext(),CreateEventActivity.class);
            startActivityForResult(createEventIntent,REQUEST_CREATE_EVENT);

        } else if (id == R.id.nav_upcoming1) {
            Intent viewEventIntent = new Intent(this.getApplicationContext(),ViewEventActivity.class);
            startActivity(viewEventIntent);

        } else if (id == R.id.nav_invite) {


        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
