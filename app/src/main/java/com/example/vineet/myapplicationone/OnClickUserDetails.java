package com.example.vineet.myapplicationone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.vineet.myapplicationone.Models.ListItems;

public class OnClickUserDetails extends AppCompatActivity {

    TextView name;
    TextView no_of_items;
    TextView order_id;
    TextView address;
    TextView amount;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details);
        name = (TextView) findViewById(R.id.textName);
        no_of_items = (TextView) findViewById(R.id.textNoOfItems);
        order_id = (TextView) findViewById(R.id.textOrderCode);
        address = (TextView) findViewById(R.id.textAddress);
        amount = (TextView) findViewById(R.id.textTotalMoney);



        fetchparselData();
    }
    private void fetchparselData() {
        ListItems lisenew = (ListItems) getIntent().getParcelableExtra(MainActivity.KEY_ME);
        name.setText("Name : " +lisenew.getFango_name() );
        no_of_items.setText("Order Id : "+lisenew.getFango_no_of_items());
        order_id.setText("No Of Items : " +lisenew.getFango_order_id());
        address.setText("Address : " +lisenew.getFango_location());
        amount.setText("Total Amount :" +lisenew.getFango_total_amount());
    }
}
