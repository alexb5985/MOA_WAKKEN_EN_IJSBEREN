package com.example.gebruiker.moa_wakken_en_ijsberen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentMenu extends Fragment {
    Button btnUser, btnSettings, btnHelp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);
        btnUser = (Button) v.findViewById(R.id.btnUser);
        btnSettings = (Button) v.findViewById(R.id.btnSettings);
        btnHelp = (Button) v.findViewById(R.id.btnHelp);

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.FrameMain, new FragmentUser());
                ft.commit();
            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.FrameMain, new FragmentSettings());
                ft.commit();
            }
        });
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.FrameMain, new FragmentHelp());
                ft.commit();
            }
        });
        return v;
    }
}
