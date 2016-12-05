package com.example.gebruiker.moa_wakken_en_ijsberen;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity{

    FragmentManager fm;
    FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TEST TEST
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.FrameFragment, new FragmentMenu());
        ft.commit();

        Dobbelstenen s = new Dobbelstenen(5);
        s.Rolldice();

    }

}
