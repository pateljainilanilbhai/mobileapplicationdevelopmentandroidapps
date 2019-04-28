package com.example.jainil.firebase;

public class enrollment {
    String username;
    String companyid;
    String round1result;
    String round2result;
    String round3result;
    String round1attendance;
    String round2attendance;
    String round3attendance;

    public enrollment() {

    }

    public enrollment(String username, String companyid, String round1result, String round2result, String round3result, String round1attendance, String round2attendance, String round3attendance) {
        this.username = username;
        this.companyid = companyid;
        this.round1result = round1result;
        this.round2result = round2result;
        this.round3result = round3result;
        this.round1attendance = round1attendance;
        this.round2attendance = round2attendance;
        this.round3attendance = round3attendance;
    }

    public String getCompanyid() {
        return companyid;
    }

    public String getUsername() {
        return username;
    }

    public String getRound1result() {
        return round1result;
    }

    public String getRound2result() {
        return round2result;
    }

    public String getRound3result() {
        return round3result;
    }

    public String getRound1attendance() {
        return round1attendance;
    }

    public String getRound2attendance() {
        return round2attendance;
    }

    public String getRound3attendance() {
        return round3attendance;
    }
}
