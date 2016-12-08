package com.example.vineet.myapplicationone.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vineet.myapplicationone.Bob_order_onclick;
import com.example.vineet.myapplicationone.Models.Details_with_photos;
import com.example.vineet.myapplicationone.R;
import com.squareup.picasso.Picasso;
//import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Vineet on 10/17/2016.
 */

public class DetailsViewAdapter extends RecyclerView.Adapter<DetailsViewAdapter.DetailViewHolder> {
    private ArrayList<Details_with_photos> details_with_photoses;
    Details_with_photos det;



    public class DetailViewHolder extends RecyclerView.ViewHolder {

        public TextView Nname, no_ofitems,orderCode, paise ,addr, times,prize,colors,sizes;
        public ImageView img;

        public DetailViewHolder(View itemView) {

            super(itemView);
//            Nname = (TextView) itemView.findViewById(R.id.bob_details_name);
//           no_ofitems= (TextView)itemView.findViewById(R.id.bob_details_no_of_items);
//            orderCode =(TextView) itemView.findViewById(R.id.bob_details_order_id);
//            paise = (TextView)itemView.findViewById(R.id.bob_details_amount);
//            addr = (TextView)itemView.findViewById(R.id.bob_details_address);
//            times =(TextView)itemView.findViewById(R.id.bob_details_date);
            img = (ImageView) itemView.findViewById(R.id.bob_details_image);
            prize=(TextView)itemView.findViewById(R.id.bob_details_Prize);
//            colors = (TextView)itemView.findViewById(R.id.bob_details_color);
            sizes = (TextView)itemView.findViewById(R.id.bob_details_size);



        }
    }
    public DetailsViewAdapter(ArrayList<Details_with_photos> details_with_photoses) {
        this.details_with_photoses = details_with_photoses;
    }

    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bob_order_details, parent, false);
        Log.d("view holder",":"+itemView);
        return new DetailViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DetailViewHolder holder, int position) {
        det = details_with_photoses.get(position);
//        holder.Nname.setText("Customer Name :"+det.getDetails_name());
//        holder.no_ofitems.setText("No Of Items :"+det.getDetails_no_of_items());
//        holder.orderCode.setText("Order Code :"+det.getDetails_order_code());
//        holder.addr.setText("Address :"+det.getDetails_address());
//        holder.times.setText(""+det.getDetails_time());
        holder.sizes.setText("Size : "+det.getDetails_size());
//        holder.colors.setText("Color : "+det.getDetails_opid_color());
        holder.prize.setText("Prize : Rs"+det.getDetails_price());
//        holder.img.setImageResource(details_with_photoses.get(position));
        Picasso.with(Bob_order_onclick.contextme).load(det.getDetails_product_pic_url()).into(holder.img);
//        Log.d("holder ","is:"+holder);
//        holder.img.setImageResource(Integer.parseInt(""+det.getDetails_product_pic_url()));

    }

    @Override
    public int getItemCount() {
        Log.d("size ","is:"+details_with_photoses);
        return details_with_photoses.size();
    }

}
