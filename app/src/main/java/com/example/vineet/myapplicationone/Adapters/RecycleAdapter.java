package com.example.vineet.myapplicationone.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vineet.myapplicationone.Models.ListItems;
import com.example.vineet.myapplicationone.R;

import java.util.ArrayList;
import java.util.List;

//public class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//    ArrayList<ListItems> listItemses;
//    int Resource;
//    Context context;
//    LayoutInflater layoutInflater;
//
//    public class Fango_holder extends RecyclerView.ViewHolder{
//        public TextView name, id, address, time;
//
//        public Fango_holder(View view){
//            super(view);
//            name= (TextView)view.findViewById(R.id.texttitle);
//            address = (TextView)view.findViewById(R.id.textsub);
//            id = (TextView)view.findViewById(R.id.textbody);
//            time= (TextView)view.findViewById(R.id.texttime);
//        }
//    }
//
//    public RecycleAdapter(Context context, int resource , ArrayList<ListItems> objects) {
//        listItemses= objects;
//        Resource = resource;
//        this.context= context;
//        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    @Override
//    public Fango_holder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.uitextlist, parent, false);
//
//        return new Fango_holder(itemView);
//    }
//    @Override
//    public void onBindViewHolder(Fango_holder holder, int position) {
//        ListItems movie = listItemses.get(position);
//        holder.name.setText(movie.getFango_name());
//        holder.address.setText(movie.getFango_location());
//        holder.id.setText(movie.getFango_order_id());
//        holder.time.setText(movie.getFango_time());
//    }
//
//
    //    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.uitextlist,null);
//        RecyclerView.ViewHolder vh= new RecyclerView.ViewHolder(view) {
//            @Override
//            public String toString() {
//                return super.toString();
//            }
//        };
//        return vh;
//
//    }
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//
//        Viewholder  holderone ;
//                holderone = new Viewholder();
//        holderone.fangoname.setText("Name : " +listItemses.get(position).getFango_name());
//
//        Log.d("name","is :" +holder.itemView);
//        holderone.fangoid.setText("Order Id : " +listItemses.get(position).getFango_order_id());
//        holderone.fangoaddress.setText("User Address : " +listItemses.get(position).getFango_location());
//        holderone.fangotime.setText(""+listItemses.get(position).getFango_time());
//        return ;
//
//    }

//    public class Viewholder {
//        public TextView fangoaddress;
//        public TextView fangotime;
//
//    }      public TextView fangoname;
//        public TextView fangoid;


//    @Override
//    public int getItemCount() {
//        return 0;
//
//    }
//}


public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    private ArrayList<ListItems> orderList= new ArrayList<ListItems>();

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
                .inflate(R.layout.uitextlist, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ListItems movie = orderList.get(position);
        holder.name.setText(movie.getFango_name());
        holder.address.setText(movie.getFango_location());
        holder.id.setText(movie.getFango_order_id());
        holder.time.setText(movie.getFango_time());
        Log.d("holder " ," is :"+movie);
    }

    @Override
    public int getItemCount() {
        return hashCode();
    }
}
