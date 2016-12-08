package com.example.vineet.myapplicationone.Models;

/**
 * Created by Vineet on 10/15/2016.
 */

public class Details_with_photos implements Comparable<Details_with_photos> {

    String Details_name;
    String Details_no_of_items;
    String Details_product_pic_url;
    String Details_order_code;
    String Details_total_amount;
    String Details_address;
    String Details_time;
    String Details_price;
    String Details_opid_color;
    String Details_size;
    String Details_stage;



    public String getDetails_no_of_items() {
        return Details_no_of_items;
    }

    public void setDetails_no_of_items(String details_no_of_items) {
        Details_no_of_items = details_no_of_items;
    }

    public String getDetails_name() {
        return Details_name;
    }

    public void setDetails_name(String details_name) {
        Details_name = details_name;
    }

    public String getDetails_product_pic_url() {
        return Details_product_pic_url;
    }

    public void setDetails_product_pic_url(String details_product_pic_url) {
        Details_product_pic_url = details_product_pic_url;
    }

    public String getDetails_order_code() {
        return Details_order_code;
    }

    public void setDetails_order_code(String details_order_code) {
        Details_order_code = details_order_code;
    }

    public String getDetails_address() {
        return Details_address;
    }

    public void setDetails_address(String details_address) {
        Details_address = details_address;
    }

    public String getDetails_total_amount() {
        return Details_total_amount;
    }

    public String getDetails_price() {
        return Details_price;
    }

    public void setDetails_price(String details_price) {
        Details_price = details_price;
    }

    public String getDetails_opid_color() {
        return Details_opid_color;
    }

    public void setDetails_opid_color(String details_opid_color) {
        Details_opid_color = details_opid_color;
    }

    public String getDetails_size() {
        return Details_size;
    }

    public void setDetails_size(String details_size) {
        Details_size = details_size;
    }

    public String getDetails_time() {
        return Details_time;
    }

    public void setDetails_time(String details_time) {
        Details_time = details_time;
    }

    public void setDetails_total_amount(String details_total_amount) {
        Details_total_amount = details_total_amount;
    }


    public String getDetails_stage() {
        return Details_stage;
    }

    public void setDetails_stage(String details_stage) {
        Details_stage = details_stage;
    }

    @Override
    public int compareTo(Details_with_photos o) {
        return 0;
    }
}
