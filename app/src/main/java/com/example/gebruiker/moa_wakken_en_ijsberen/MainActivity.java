package com.example.gebruiker.moa_wakken_en_ijsberen;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentSettings.setSettingsListener {
    ViewPager viewPager;
    TabLayout tabLayout;
    CustomPager customPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        customPager = new CustomPager(getSupportFragmentManager(), MainActivity.this);
        //viewPager.setAdapter(new CustomPager(getSupportFragmentManager(),MainActivity.this));
        viewPager.setAdapter(customPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    //Methode voor fragments om van tab te wisselen
    public void selectPage(int pageIndex){
        tabLayout.setScrollPosition(pageIndex,0f,true);
        viewPager.setCurrentItem(pageIndex);
    }

    @Override
    public void setSettings(int seconds, int dices, boolean pinguins) {
        selectPage(0); // Zet actieve fragment in viewpager naar FragmentGame
        int id = viewPager.getCurrentItem();
        //Haalt actieve fragment uit de viewpager (FragmentGame)
        FragmentGame frag = (FragmentGame) getSupportFragmentManager().findFragmentByTag("android:switcher:"+R.id.viewpager+":"+id);
        frag.UpdateGame(seconds, dices, pinguins);
    }
}
