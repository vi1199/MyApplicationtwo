package com.example.vineet.myapplicationone;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

//import com.example.vineet.myapplicationone.Adapters.RecycleAdapter;

/**
 * Created by Vineet on 10/1/2016.
 */

public class tabFragment extends android.support.v4.app.Fragment{
    int color;
//    RecycleAdapter adapter;
    TextView v;

    public tabFragment() {
    }

    @SuppressLint("ValidFragment")
    public tabFragment(int color) {
        this.color = color;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        ((TextView)view.findViewById(R.id.textfag)).setText("Beach");

//        final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.frag_bg);
//        frameLayout.setBackgroundColor(color);

//        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.frag_layout_scroll);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setHasFixedSize(true);

//        List<String> list = new ArrayList<String>();
//
//        for (int i = 0; i < VersionModel.data.length; i++) {
//            list.add(VersionModel.data[i]);
//        }
//        adapter = new RecycleAdapter(list);
//        recyclerView.setAdapter(adapter);
        return view;
    }
}



