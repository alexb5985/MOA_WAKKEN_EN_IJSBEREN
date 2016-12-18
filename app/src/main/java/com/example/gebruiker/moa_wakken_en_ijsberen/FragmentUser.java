package com.example.gebruiker.moa_wakken_en_ijsberen;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

public class FragmentUser extends Fragment {

    private static CustomAdapter adapter;
    TextView tvTest;
    ListView lv;
    SharedPreferences preferences;
    List<Score> score ;
    DBHandler db;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_user, container, false);
        //tvTest = (TextView) view.findViewById(R.id.tvUserName);
       // preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
       // String name = preferences.getString("Name", "");
        lv = (ListView) view.findViewById(R.id.listView);
        vulListView(view.getContext());
        return view;
    }

    //refresh listview
    @Override
    public void setUserVisibleHint(boolean visible){
        super.setUserVisibleHint(visible);
        if (visible && isResumed()){
            vulListView(view.getContext());
        }
    }

//methode om list view te vullen met data
public void vulListView(Context context){
    db = new DBHandler( context);
    score = db.getAllScores();
    adapter = new CustomAdapter(score, context);



    lv.setAdapter(adapter);
}

}
