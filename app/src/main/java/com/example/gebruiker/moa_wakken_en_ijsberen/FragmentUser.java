package com.example.gebruiker.moa_wakken_en_ijsberen;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentUser extends Fragment {

    TextView tvTest;
    SharedPreferences preferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        tvTest = (TextView) view.findViewById(R.id.tvUserName);
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String name = preferences.getString("Name", "");

        return view;
    }
}
