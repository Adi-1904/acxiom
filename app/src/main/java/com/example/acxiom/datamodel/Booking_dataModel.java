package com.example.acxiom.datamodel;

public class Booking_dataModel {
    String date,reason,description,email,sms,topic,contactno;

//    public Booking_dataModel(String moviename, String date, String hallname, String row, String time, ArrayList<String> uniqueArrayList) {
//        Moviename = moviename;
//        this.date = date;
//        this.hallname = hallname;
//        this.row = row;
//        this.time = time;
//        this.uniqueArrayList = uniqueArrayList;


//    }


    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }
}
