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
    protected Calendar startDate, endDate;
    protected Bitmap image;

    Event(String title, String description, String[] hashtags, Calendar startDate,
          String location, Bitmap image) {
        this.title = title;
        this.description = description;
        this.hashtags = hashtags;
        this.startDate = startDate;
        this.location = location;
        this.image = image;
    }
}
