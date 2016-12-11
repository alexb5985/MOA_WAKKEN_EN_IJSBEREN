package com.example.gebruiker.moa_wakken_en_ijsberen;

import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;

import java.util.ArrayList;

public class FragmentMenu extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    ArrayList<Fragment> fragment = new ArrayList();
    public FragmentMenu(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Fragment tab1 = new FragmentGame();
                return tab1;
            case 1:
                Fragment tab2 = new FragmentUser();
                return tab2;
            case 2:
                Fragment tab3 = new FragmentSettings();
                return tab3;
            case 3:
                Fragment tab4 = new FragmentHelp();
                return tab4;
            default:
                return null;
        }
    }

    public void addFragment(Fragment f){
        this.fragment.add(f);
    }

    @Override
    public int getCount() {
        return 0;
    }
}
