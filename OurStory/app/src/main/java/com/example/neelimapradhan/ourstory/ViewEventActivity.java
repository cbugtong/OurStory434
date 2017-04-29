package com.example.neelimapradhan.ourstory;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Created by Chris on 4/5/2017.
 */

public class ViewEventActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        int eventNumber = 0; // null value, no dummy value will assign zero

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
                    " our understanding through a study of technology, psychology, and design.",
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
        int eventValue = (int) getIntent().getExtras().get("event");
        Event event = null;
        Button b = (Button) findViewById(R.id.modal_button);

        /*Toolbar Setup*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        switch (eventValue) {
            case 1:
                event = event2;
                break;
            case 2:
                event = event3;
                break;
            default:
        }
        if (event != null) {
            ((TextView) findViewById(R.id.view_title)).setText(event.title);
            ((TextView) findViewById(R.id.view_location)).setText(event.location);
            ((TextView) findViewById(R.id.view_description)).setText(event.description);
            ((TextView) findViewById(R.id.view_date)).setText(
                    new SimpleDateFormat("MMMM d, y - h:mm a").format(event.date.getTime()));

            String hashtags[] = event.hashtags;
            for (int i = 0; i < hashtags.length; i++) {
                ((TextView) findViewById(R.id.view_hashtags)).append(hashtags[i] + " ");
            }
        }

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

        if (id == R.id.nav_curr) {
            Intent createEventIntent = new Intent(this.getApplicationContext(),MainActivity.class);
            startActivity(createEventIntent);
            finish();

        } else if (id == R.id.create_event_nav) {
            Intent createEventIntent = new Intent(this.getApplicationContext(),CreateEventActivity.class);
            startActivityForResult(createEventIntent,REQUEST_CREATE_EVENT);

        } else if (id == R.id.nav_upcoming1 && eventNumber != 1) {
            Intent viewEventIntent = new Intent(this.getApplicationContext(),ViewEventActivity.class);
            viewEventIntent.putExtra("event",1);
            startActivity(viewEventIntent);
            finish();

        }else if (id == R.id.nav_upcoming2 && eventNumber != 2) {
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
