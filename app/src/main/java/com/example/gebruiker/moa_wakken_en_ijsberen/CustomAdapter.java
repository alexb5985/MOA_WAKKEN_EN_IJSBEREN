package com.example.gebruiker.moa_wakken_en_ijsberen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loek on 30-9-2016.
 */
public class CustomAdapter extends ArrayAdapter<Score> implements View.OnClickListener {

    private List<Score> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView tvName;
        TextView tvGood;
        TextView tvFalse;
        TextView tvTime;
        TextView tvDices;

    }

    public CustomAdapter(List<Score> data, Context context) {
        super(context, R.layout.list_layout, data);
        this.dataSet = data;
        this.mContext = context;

    }

    @Override
    public void onClick(View v) {

        int position = (Integer) v.getTag();
        Object object = getItem(position);
        Score dataModel = (Score) object;
    }



    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Score dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_layout, parent, false);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvGood = (TextView) convertView.findViewById(R.id.tvGood);
            viewHolder.tvFalse = (TextView) convertView.findViewById(R.id.tvFalse);
            viewHolder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);
            viewHolder.tvDices = (TextView) convertView.findViewById(R.id.tvDices);




            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        //  Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        //   result.startAnimation(animation);
        lastPosition = position;

        viewHolder.tvName.setText(getContext().getString(R.string.nameList)+ " " + dataModel.getName() );
        viewHolder.tvGood.setText(getContext().getString(R.string.goodList) + " " + dataModel.getGoodGuesses());
        viewHolder.tvFalse.setText(getContext().getString(R.string.wrongList)+ " " + dataModel.getWrongGuesses());
        viewHolder.tvTime.setText(getContext().getString(R.string.timeList)+ " " + dataModel.getTimeTaken() + " sec");
        viewHolder.tvDices.setText(getContext().getString(R.string.diceList)+ " " + dataModel.getNumberDice());
        //viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;


    }
}