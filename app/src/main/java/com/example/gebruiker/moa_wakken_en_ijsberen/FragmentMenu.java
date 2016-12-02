package com.example.gebruiker.moa_wakken_en_ijsberen;

import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentMenu extends Fragment {
    TabItem btnGame, btnUser, btnSettings, btnHelp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);
        btnGame = (TabItem) v.findViewById(R.id.btnGame);
        btnUser = (TabItem) v.findViewById(R.id.btnUser);
        btnSettings = (TabItem) v.findViewById(R.id.btnSettings);
        btnHelp = (TabItem) v.findViewById(R.id.btnHelp);
        
//        btnGame.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.FrameMain, new FragmentGame());
//                ft.commit();
//            }
//        });

        return v;
    }
}
