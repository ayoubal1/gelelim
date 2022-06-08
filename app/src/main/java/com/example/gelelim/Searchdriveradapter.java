package com.example.gelelim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gelelim.Database.Driver;

import java.util.ArrayList;
import java.util.List;

public class Searchdriveradapter extends RecyclerView.Adapter<Searchdriveradapter.PostHolder>{
    ArrayList<Driver> driverArrayList=new ArrayList<>();


    LayoutInflater layoutInflater;
    Context context;

    public Searchdriveradapter(ArrayList<Driver> driverArrayList, Context context) {
        this.driverArrayList = driverArrayList;
        this.context = context;
    }

    public Searchdriveradapter(List<Driver> drivers) {
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        layoutInflater =LayoutInflater.from(context);
        View p= layoutInflater.inflate(R.layout.searchdriver,parent,false);
        PostHolder ph=new PostHolder(p);
        return ph;
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.Namesearch.setText(driverArrayList.get(position).getName());
        holder.Mobilesearch.setText(driverArrayList.get(position).getPhone());
        holder.Addressearch.setText(driverArrayList.get(position).getAdress());
        holder.linearLayout.setTag(holder);

    }

    @Override
    public int getItemCount() {
        return driverArrayList.size();
    }


    public class PostHolder extends RecyclerView.ViewHolder {
        TextView Namesearch, Mobilesearch, Addressearch;
        LinearLayout linearLayout;
        public PostHolder(@NonNull View itemView) {
            super(itemView);
            Namesearch=itemView.findViewById(R.id.namesearch);
            Mobilesearch=itemView.findViewById(R.id.mobilesearch);
            Addressearch=itemView.findViewById(R.id.ddressearch);
            linearLayout=itemView.findViewById(R.id.linearsearch);
        }
    }
}
