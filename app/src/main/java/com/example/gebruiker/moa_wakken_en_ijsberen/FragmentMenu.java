package com.example.gebruiker.moa_wakken_en_ijsberen;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentMenu extends Fragment {
    public FragmentMenu() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_menu, container, false);
        Typeface font = Typeface.createFromAsset( getContext().getAssets(), "fontawesome-webfont.ttf" );
        Button button = (Button) v.findViewById(R.id.btnPersonal);
        button.setTypeface(font);
        // Inflate the layout for this fragment
        return v;
    }
}
