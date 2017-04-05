package com.example.neelimapradhan.ourstory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    User user = null;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_PREVIEW = 2;
    static final int REQUEST_CREATE_EVENT = 3;
    static final int REQUEST_LOGIN = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Checks for instantiated user*/
        if (user == null) {
            startActivityForResult(new Intent(getApplicationContext(),LoginActivity.class),REQUEST_LOGIN);
        }

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

        /*Post Preview Button Setup*/
        Button postPreview = (Button) findViewById(R.id.post_button); //media button will open menu
        postPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent previewIntent = new Intent(getApplicationContext(),PreviewActivity.class);
                previewIntent.putExtra("text",textBox.getText());
                startActivityForResult(previewIntent,REQUEST_PREVIEW);
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
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            //mImageView.setImageBitmap(imageBitmap);

        } else if (requestCode == REQUEST_PREVIEW && resultCode == RESULT_OK) {
            ((EditText) findViewById(R.id.edit_text)).setText("");
            Toast.makeText(getApplicationContext(), "Post Published!", Toast.LENGTH_SHORT).show();

        } else if (requestCode == REQUEST_CREATE_EVENT) {
            switch (resultCode) {
                case RESULT_OK:
                    Toast.makeText(getApplicationContext(), "New Event Published!", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_FIRST_USER:
                    Toast.makeText(getApplicationContext(), "Draft Saved", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "Draft Discarded", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_LOGIN && resultCode == RESULT_OK) {
            Bundle b = (Bundle) data.getExtras().get("user_info");
            if (b != null)
                user = b.getParcelable("user_info");

            Toast.makeText(getApplicationContext(), "Logged in as " + user.username, Toast.LENGTH_SHORT).show();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.media_menu, menu);
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

        if (id == R.id.nav_share) {
            // Handle the camera action

        }  else if (id == R.id.create_event_nav) {
            Intent createEventIntent = new Intent(this.getApplicationContext(),CreateEventActivity.class);
            startActivity(createEventIntent);
            return true;

        } else if (id == R.id.nav_upcoming1) {

        } else if (id == R.id.nav_invite) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
