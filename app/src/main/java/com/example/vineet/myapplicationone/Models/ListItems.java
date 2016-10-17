package com.example.vineet.myapplicationone.Models;
import android.os.Parcel;
import android.os.Parcelable;

public class ListItems implements Comparable<ListItems>
{

    String fango_name;
    String fango_order_id;
    String fango_location;
    String fango_no_of_items;
    String fango_time;
    String fango_total_amount;
    String fango_photo;


    public ListItems (){


    }

    public String getFango_time() {

        return fango_time;
    }

    public void setFango_time(String fango_time) {

        this.fango_time = fango_time;
    }

    public String getFango_name() {
        return fango_name;
    }

    public void setFango_name(String fango_name) {
        this.fango_name = fango_name;
    }

    public String getFango_order_id() {
        return fango_order_id;
    }

    public void setFango_order_id(String fango_order_id) {
        this.fango_order_id = fango_order_id;
    }

    public String getFango_location() {
        return fango_location;
    }

    public void setFango_location(String fango_location) {
        this.fango_location = fango_location;
    }

    public String getFango_no_of_items() {
        return fango_no_of_items;
    }

    public void setFango_no_of_items(String fango_no_of_items) {
        this.fango_no_of_items = fango_no_of_items;
    }

    public String getFango_total_amount() {
        return fango_total_amount;
    }

    public void setFango_total_amount(String fango_total_amount) {
        this.fango_total_amount = fango_total_amount;
    }

    public String getFango_photo() {
        return fango_photo;
    }

    public void setFango_photo(String fango_photo) {
        this.fango_photo = fango_photo;
    }


    @Override
    public int compareTo(ListItems o) {
        return 0;
    }
}
