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

public class LastFaragmentAdapter extends RecyclerView.Adapter<LastFaragmentAdapter.LastHolder> {
    private ArrayList<ListItems> lastFragment;
    ListItems Lt;

    public class LastHolder extends RecyclerView.ViewHolder {
        public TextView name, address, id,time,code;


        public LastHolder(View lastView) {
            super(lastView);
            name = (TextView) lastView.findViewById(R.id.texttitle);
            code = (TextView) lastView.findViewById(R.id.textsub);
            id =(TextView)lastView.findViewById(R.id.textbody);
            time = (TextView) lastView.findViewById(R.id.texttime);
        }
    }
    public LastFaragmentAdapter(ArrayList<ListItems> lastFragment){
        this.lastFragment = lastFragment;

    }

    @Override
    public LastFaragmentAdapter.LastHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View lastview = LayoutInflater.from(parent.getContext()).inflate(R.layout.bob_uitextlist,parent,false);
        return new LastHolder(lastview);
    }

    @Override
    public void onBindViewHolder(LastFaragmentAdapter.LastHolder holder, int position) {
        Lt = lastFragment.get(position);
        holder.name.setText("Name : " + Lt.getFango_name());
        holder.code.setText("Ordr Code : " + Lt.getFango_fango_id());
        holder.id.setText("Order Id : " + Lt.getFango_order_id());
        holder.time.setText(Lt.getFango_time());
        Log.d("holder ", " is :" + lastFragment);

    }

    @Override
    public int getItemCount() {
        return lastFragment.size();
    }
}
