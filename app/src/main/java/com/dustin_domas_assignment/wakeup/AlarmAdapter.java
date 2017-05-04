package com.dustin_domas_assignment.wakeup;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;



public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder>  {

    private  List<AlarmCard> alarmData;
    private Context adContext;

    public AlarmAdapter(){

    }

    public AlarmAdapter(Context c,List<AlarmCard> data) {
        adContext = c;
        alarmData = data;
    }


    // will create the Card view with the alarm attributes
    public static class AlarmViewHolder extends RecyclerView.ViewHolder{

        public  TextView timeDisplay;
        public TextView alaDays;
        public ImageButton removeButton;

        AlarmViewHolder(View view){
            super(view);
        /*
        * add onclick listener for remving card object
        * */
            timeDisplay = (TextView) view.findViewById(R.id.timeDisplay);
            alaDays = (TextView) view.findViewById(R.id.daysTV);
            removeButton = (ImageButton) view.findViewById(R.id.remove_Button);

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
    public void onBindViewHolder(AlarmViewHolder holder, final int position) {

        final AlarmCard al = alarmData.get(position);

        holder.timeDisplay.setText(al.getTime());
        holder.alaDays.setText(al.get_title());

        holder.removeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                alarmData.remove(position);

                notifyItemRemoved(position);

                Toast.makeText(adContext,"Deleted  ",Toast.LENGTH_SHORT).show();
                //removeBroadcast();
            }

        });
    }

    public void add(AlarmCard item, int position) {

        alarmData.add(position,item);
       // notifyItemInserted(position);
    }

/*
    public void remove(AlarmCard item ,int position ) {
        alarmData.remove(position);

        notifyItemRemoved(position);

        Toast.makeText(adContext,"Deleted  ",Toast.LENGTH_SHORT).show();

    }
*/


    @Override
    public int getItemCount() {
        return alarmData.size();
    }
}
