package com.example.lawnwizard.models;
import com.google.firebase.firestore.GeoPoint;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Job {
    private String imageURI;
    private String homeowner;
    private String worker;
    private String homeownerID;
    private String workerID;
    private ArrayList<String> hwBlacklist = new ArrayList<String>();
    private ArrayList<String> workerBlacklist = new ArrayList<String>();
    private String description;
    private int pay;
    private GeoPoint location;
    private boolean accepted = false;
    private boolean completed = false;
    private boolean deleted = false;
    private String createdDate;

    public Job(){}

    public Job(User homeowner, String description, int pay, GeoPoint location, String imageURI){
        Date currentTime = Calendar.getInstance().getTime();
        this.homeownerID = homeowner.getUserID();
        this.hwBlacklist = homeowner.getBlacklist();
        this.homeowner = homeowner.getName();
        this.description = description;
        this.pay = pay;
        this.location = location;
        this.imageURI = imageURI;
        this.createdDate = currentTime.toString();
    }

    public Job(User homeowner, String description, int pay, GeoPoint location){
        Date currentTime = Calendar.getInstance().getTime();
        this.homeownerID = homeowner.getUserID();
        this.hwBlacklist = homeowner.getBlacklist();
        this.homeowner = homeowner.getName();
        this.description = description;
        this.pay = pay;
        this.location = location;
        this.createdDate = currentTime.toString();
    }

    public String getHomeowner() { return this.homeowner; }

    public String getDescription() {
        return description;
    }

    public int getPay() {
        return pay;
    }

    public boolean getDeleted() { return this.deleted; }

    public String getImageURI() { return this.imageURI; }

    public GeoPoint getLocation() {
        return location;
    }

    public String getCreatedDate() { return this.createdDate; }

    public String getHomeownerID() { return this.homeownerID; }

    public String getWorkerID() { return this.workerID; }

    public ArrayList<String> getHwBlacklist() { return this.hwBlacklist; }

    public ArrayList<String> getWorkerBlacklist() { return this.workerBlacklist; }

    public void setWorkerID(String id) { this.workerID = id; }

    public void setWorkerBlacklist(ArrayList<String> list) { this.workerBlacklist = list; }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeleted(){
        this.deleted = true;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted() {
        this.completed = true;
    }

    public void setAccepted() {
        this.accepted = true;
    }

}
