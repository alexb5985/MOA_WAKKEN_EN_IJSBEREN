package com.example.gebruiker.moa_wakken_en_ijsberen;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSettings extends Fragment {
    RadioButton rbPinguins;
    SeekBar skbTime, skbDices;
    TextView tvTime, tvDices;
    Button btnSave;
    int seconds = 10, dices = 1;

    //Callback voor fragment, geeft seconden en aantal dobbelstenen aan main
    setSettingsListener mCallback;
    public interface setSettingsListener {
        void setSettings(int seconds, int dices, boolean pinguins);
    }

    //Attach methode, controleert of context bestaat (mainactivity)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (setSettingsListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        rbPinguins = (RadioButton) view.findViewById(R.id.rbYes);
        skbTime = (SeekBar) view.findViewById(R.id.skbSeconds);
        skbDices = (SeekBar) view.findViewById(R.id.skbDices);
        tvTime = (TextView) view.findViewById(R.id.tvTime);
        tvDices = (TextView) view.findViewById(R.id.tvDices);
        btnSave = (Button) view.findViewById(R.id.btnSaveSettings);
        tvTime.setText(String.format("%1s %2s", seconds, getString(R.string.Seconden)));
        tvDices.setText(String.valueOf(dices));

        //Zet maximale waarde voor seconden seekbar (60 seconden)
        skbTime.setMax(60 -10);
        skbTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //laat gekozen tijd zijn in textview achter de seekbar en zet gekozen tijd weg in "seconds" variabele
                tvTime.setText(String.format("%1s %2s", (seconds = i + 10), getString(R.string.Seconden)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Zet maximale waarde voor dobbelstenen seekbar (8 dobbelstenen)
        skbDices.setMax(8 -1);
        skbDices.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvDices.setText(String.valueOf((dices = i + 1)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //haalt mainactivity context op, en voert methode uit om tab te wisselen
               // MainActivity main = (MainActivity) getContext();
                //main.selectPage(0);
                mCallback.setSettings(seconds, dices, rbPinguins.isChecked());
            }
        });

        return view;
    }

}
