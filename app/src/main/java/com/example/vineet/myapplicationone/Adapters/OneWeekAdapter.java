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

/**
 * Created by Vineet on 10/20/2016.
 */

public class OneWeekAdapter  extends RecyclerView.Adapter<OneWeekAdapter.WeekHolder> {
    private ArrayList<ListItems> WeekFragment;
    ListItems Lt;

    public class WeekHolder extends RecyclerView.ViewHolder {
        public TextView name, address, id,time,code;


        public WeekHolder(View WeekView) {
            super(WeekView);
            name = (TextView) WeekView.findViewById(R.id.texttitle);
            code = (TextView) WeekView.findViewById(R.id.textsub);
            id =(TextView)WeekView.findViewById(R.id.textbody);
            time = (TextView) WeekView.findViewById(R.id.texttime);
        }
    }
    public OneWeekAdapter(ArrayList<ListItems> WeekFragment){
        this.WeekFragment = WeekFragment;

    }

    @Override
    public OneWeekAdapter.WeekHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View lastview = LayoutInflater.from(parent.getContext()).inflate(R.layout.bob_uitextlist,parent,false);
        return new OneWeekAdapter.WeekHolder(lastview);
    }

    @Override
    public void onBindViewHolder(OneWeekAdapter.WeekHolder holder, int position) {
        Lt = WeekFragment.get(position);
        holder.name.setText("Name : " + Lt.getFango_name());
        holder.code.setText("Order Code : " + Lt.getFango_fango_id());
        holder.id.setText("Order Id : " + Lt.getFango_order_id());
        holder.time.setText(Lt.getFango_time());
        Log.d("holder ", " is :" + WeekFragment);

    }

    @Override
    public int getItemCount() {
        return WeekFragment.size();
    }
}
