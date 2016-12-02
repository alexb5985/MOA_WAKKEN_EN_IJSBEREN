package com.example.gebruiker.moa_wakken_en_ijsberen;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentMenu.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TEST TEST
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
