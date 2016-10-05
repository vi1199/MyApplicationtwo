package com.example.vineet.myapplicationone.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.vineet.myapplicationone.Models.ListItems;
import com.example.vineet.myapplicationone.R;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<ListItems>{

    ArrayList<ListItems> listItemses;
    int Resource;
    Context context;
    LayoutInflater layoutInflater;

    public ListAdapter(Context context, int resource, ArrayList<ListItems> objects) {
        super(context, resource, objects);

        listItemses= objects;
        Resource = resource;
        this.context= context;

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Viewholder holder;
        if(convertView== null) {
            convertView = layoutInflater.inflate(Resource,null);
            holder = new Viewholder();

            holder.fangoname = (TextView) convertView.findViewById(R.id.texttitle);
            holder.fangoid= (TextView) convertView.findViewById(R.id.textsub);
            holder.fangoaddress= (TextView) convertView.findViewById(R.id.textbody);
            holder.fangotime = (TextView) convertView.findViewById(R.id.texttime);

            convertView.setTag(holder);
        }
        else {
            holder=(Viewholder) convertView.getTag();
        }

        holder.fangoname.setText("Name : " +listItemses.get(position).getFango_name());
        holder.fangoid.setText("Order Id : " +listItemses.get(position).getFango_order_id());
        holder.fangoaddress.setText("User Address : " +listItemses.get(position).getFango_location());
        holder.fangotime.setText(""+listItemses.get(position).getFango_time());

        return convertView;
    }

    public class Viewholder {
        public TextView fangoname;
        public TextView fangoid;
        public TextView fangoaddress;
        public TextView fangotime;


    }
}
