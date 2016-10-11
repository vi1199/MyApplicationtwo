package com.example.vineet.myapplicationone.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vineet.myapplicationone.Models.ListItems;
import com.example.vineet.myapplicationone.R;

import java.util.ArrayList;


public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    private ArrayList<ListItems> orderList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, address, id,time;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.texttitle);
            address = (TextView) view.findViewById(R.id.textsub);
            id =(TextView)view.findViewById(R.id.textbody);
            time = (TextView) view.findViewById(R.id.texttime);
        }
    }


    public RecycleAdapter(ArrayList<ListItems> orderList) {
        this.orderList = orderList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bob_uitextlist, parent, false);
      //  Log.d(" item","view :" +itemView);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ListItems fan;
            fan = orderList.get(position);
            holder.name.setText("Name : " + fan.getFango_name());
            holder.address.setText("Address : " + fan.getFango_location());
            holder.id.setText("Order Id : " + fan.getFango_order_id());
            holder.time.setText(fan.getFango_time());
            Log.d("holder ", " is :" + orderList);

    }

    @Override
    public int getItemCount() {
      //  Log.d("list ","is:"+orderList.size());
        return orderList.size();

    }
}
