package com.dustin_domas_assignment.wakeup;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;



public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {

    private  List<AlarmCard> alarmData;
    private Context adContext;

    public AlarmAdapter(Context c,List<AlarmCard> data) {
        adContext = c;
        alarmData = data;
    }

// will create the Card view with the alarm attributes
    public static class AlarmViewHolder extends RecyclerView.ViewHolder{

        TextView timeDisplay;
        TextView alaDays;

        AlarmViewHolder(View view){
            super(view);
        /*
        * add onclick listener for remving card object
        * */
            timeDisplay = (TextView) view.findViewById(R.id.timeDisplay);
            alaDays = (TextView) view.findViewById(R.id.daysTV);

        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView rc){
        super.onAttachedToRecyclerView(rc);
    }

    @Override
    public AlarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view2 = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardveiw,parent,false);

        //Initialize the the view holder with the item layout
        AlarmViewHolder alarmViewHolder = new AlarmViewHolder(view2);
        return alarmViewHolder;
    }

    @Override
    public void onBindViewHolder(AlarmViewHolder holder, int position) {

        AlarmCard al = alarmData.get(position);

        holder.timeDisplay.setText(al.getTime());
        holder.alaDays.setText(al.get_title());
    }

    public void add(AlarmCard item, int position) {

        alarmData.add(position,item);
       // notifyItemInserted(position);
    }


    public void remove(AlarmCard item) {
        int pos = alarmData.indexOf(item);
        alarmData.remove(pos);
        notifyItemRemoved(pos);

    }


    @Override
    public int getItemCount() {
        return alarmData.size();
    }
}
