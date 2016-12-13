package com.example.gebruiker.moa_wakken_en_ijsberen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSettings extends Fragment {



    RadioButton rbPinguins;
    SeekBar skbTime;
    TextView tvTime;
    int seconds = 10;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        rbPinguins = (RadioButton) view.findViewById(R.id.rbYes);
        skbTime = (SeekBar) view.findViewById(R.id.skbSeconds);
        tvTime = (TextView) view.findViewById(R.id.tvTime);
        tvTime.setText(String.format("%1s %2s", seconds, getString(R.string.Seconden)));

        //Zet maximale waarde voor seekbar (60 seconden)
        skbTime.setMax(60 -10);
        skbTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //laat gekozen tijd zijn in textview achter de seekbar
                tvTime.setText(String.format("%1s %2s", (seconds = i + 10), getString(R.string.Seconden)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return view;
    }

}
