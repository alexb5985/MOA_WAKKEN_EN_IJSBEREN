package com.example.gebruiker.moa_wakken_en_ijsberen;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

/**
 * Created by J on 12/11/2016.
 */

public class CustomPager extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private int[] imageResId = {
            R.drawable.dice,
            R.drawable.userimage,
            R.drawable.settingsimage,
            R.drawable.help
    };
    private Context context;

    public CustomPager(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentGame tab1 = new FragmentGame();
                return tab1;
            case 1:
                FragmentUser tab2 = new FragmentUser();
                return tab2;
            case 2:
                FragmentSettings tab3 = new FragmentSettings();
                return tab3;
            case 3:
                FragmentHelp tab4 = new FragmentHelp();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable image = ContextCompat.getDrawable(context, imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }
}