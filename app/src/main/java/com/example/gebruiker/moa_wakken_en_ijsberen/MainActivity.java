package com.example.gebruiker.moa_wakken_en_ijsberen;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPager(getSupportFragmentManager(),MainActivity.this));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
//
//        Score sc = new Score("Naam", 1, 1);
//        DBHandler db = new DBHandler(this);
//
//        db.addScore(sc);
//
//        List<Score> list = db.getAllScores();
//
//
//        Dobbelstenen s = new Dobbelstenen(5);
//        s.Rolldice();
    }
}
