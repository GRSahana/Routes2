package com.homeassignment.routes.Adapter;


import android.graphics.Bitmap;

import org.json.JSONArray;

public class ProfileEventDetails {
    private String id;
    private String Profile_name;
    private String Profile_image;
    private String description;
    private String accesebility;
    private String stops;

   public ProfileEventDetails(String id, String Profile_name, String Profile_image, String description, String accesebility, String stops){
        this.id = id;
       this.Profile_name = Profile_name;
       this.Profile_image = Profile_image;
       this.description = description;
       this.accesebility = accesebility;
       this.stops = stops;
    }

    public String getId(){
        return id;
    }

    public String getProfile_name(){
        return Profile_name;
    }

    public String getProfile_image(){
        return Profile_image;
    }

    public String getDescription() {
        return description;
    }

    public String getAccesebility() {
        return accesebility;
    }

    public String getStops() {
        return stops;
    }
}
