package com.example.neelimapradhan.ourstory;

import android.graphics.Bitmap;
import android.location.Location;
import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Created by Chris on 3/25/2017.
 *
 * This class represents events as a collection of data
 */

public class Event {
    protected int userid;
    protected String title, description;
    protected List hashtags;
    protected Date startDate, endDate;
    protected Location location;
    protected Bitmap image;

    Event(int userid, String title, String description, List<String> hashtags,
          Date startDate, Date endDate, Location location, Bitmap image) {

        this.userid = userid;
        this.title = title;
        this.description = description;
        this.hashtags = hashtags;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.image = image;
    }
}
