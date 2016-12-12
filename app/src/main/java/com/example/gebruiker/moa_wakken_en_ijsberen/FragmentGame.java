package com.example.gebruiker.moa_wakken_en_ijsberen;


import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class FragmentGame extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    LinearLayout diceLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        diceLayout = (LinearLayout) view.findViewById(R.id.Dicelayout);
        Dobbelstenen dice = new Dobbelstenen(5);
        dice.Rolldice();
        //ImageView Setup

        for(int i = 1; i < dice.Stenen.size(); i++){
            String name = ("dice" + dice.Stenen.get(i));
            int id = view.getResources().getIdentifier(name, "drawable", getContext().getPackageName());
            ImageView imageView = (ImageView) view.findViewById(getResources().getIdentifier("imageView" + i, "id", getContext().getPackageName()));
            imageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), id, null));
        }
        return view;
    }
}
