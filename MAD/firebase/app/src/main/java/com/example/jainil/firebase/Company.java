package com.example.jainil.firebase;

public class Company {
    String id;
    String location;
    String name;
    String website;
    String requirements;
    String date;
    String time;
    String round1location;
    String round2location;
    String round3location;
    String round1time;
    String round2time;
    String round3time;


    public Company()
    {

    }

    public Company(String id, String location, String name, String website, String requirements, String date, String time, String round1location, String round2location, String round3location, String round1time, String round2time, String round3time) {
        this.id = id;
        this.location = location;
        this.name = name;
        this.website = website;
        this.requirements = requirements;
        this.date = date;
        this.time = time;
        this.round1location = round1location;
        this.round2location = round2location;
        this.round3location = round3location;
        this.round1time = round1time;
        this.round2time = round2time;
        this.round3time = round3time;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    public String getRequirements() {
        return requirements;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getRound1location() {
        return round1location;
    }

    public String getRound2location() {
        return round2location;
    }

    public String getRound3location() {
        return round3location;
    }

    public String getRound1time() {
        return round1time;
    }

    public String getRound2time() {
        return round2time;
    }

    public String getRound3time() {
        return round3time;
    }
}
