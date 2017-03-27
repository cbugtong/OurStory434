package com.example.neelimapradhan.ourstory;

import java.util.List;

/**
 * Created by Chris on 3/27/2017.
 *
 * This class represents users with associated login and event information. Dummy information
 * resides in this class.
 */

public class User {
    protected String username, password;
    protected int userid;
    protected List<Event> admin, member;


    User(String username, String password, int userid, List<Event> admin, List<Event> member) {
        this.username = username;
        this.password = password;
        this.userid = userid;
        this.admin = admin;
        this.member = member;
    }
}
