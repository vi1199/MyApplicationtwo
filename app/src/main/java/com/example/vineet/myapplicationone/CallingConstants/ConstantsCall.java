package com.example.vineet.myapplicationone.CallingConstants;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Vineet on 12/8/2016.
 */

public class ConstantsCall  extends AppCompatActivity{

   public static final String ip="http://52.66.140.142:8080";
   public static  String url_admin="http://52.66.140.142:8080/fango_admin";
   public static String fango_live ="/fango_live";
   public static String fango_admin ="/fango_admin";
   public static  String mainurl = fango_live ;



public String getAll = ip+mainurl+"/admin/order/get/all";
}
