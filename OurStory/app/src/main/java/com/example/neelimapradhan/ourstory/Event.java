package com.example.neelimapradhan.ourstory;

import android.graphics.Bitmap;
import android.location.Location;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Chris on 3/25/2017.
 *
 * This class represents events as a collection of data
 */

public class Event {
    protected String title, description, location;
    protected String[] hashtags;
    protected Calendar date;
    protected Bitmap image;

    Event(String title, String description, String[] hashtags, Calendar date,
          String location, Bitmap image) {
        this.title = title;
        this.description = description;
        this.hashtags = hashtags;
        this.date = date;
        this.location = location;
        this.image = image;
    }
}
