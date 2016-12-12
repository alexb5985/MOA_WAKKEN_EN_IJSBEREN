package com.example.gebruiker.moa_wakken_en_ijsberen;


import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FragmentGame extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    int time;
    Dobbelstenen dice;
    TextView tvTimer;
    EditText edPolar, edHole, edPinguin;
    Button btnCheck;
    CountDownTimer timer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        tvTimer = (TextView) view.findViewById(R.id.tvTimer);
        btnCheck = (Button) view.findViewById(R.id.btnCheck);
        edPolar = (EditText) view.findViewById(R.id.edPolar);
        edHole = (EditText) view.findViewById(R.id.edHole);
        edPinguin = (EditText) view.findViewById(R.id.edPinguin);
        tvTimer.setText(String.valueOf(time));
        NewGameClick();
        return view;
    }

    public void NewGameClick(){
        time = 10; //seconden
        tvTimer.setText(String.valueOf(time));
        btnCheck.setText(R.string.Start);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Zodra er geklikt word, krijgt de button een nieuwe listener voor controle van score
                CheckScore_Click();
                //maakt array aan met ingestelde dobbelstenen en vult vervolgens imageviews
                dice = new Dobbelstenen(8);
                dice.Rolldice();
                FillImageView(view.getRootView());
                //Start countdowntimer bij de eerste button click event
                GameTimer_Start();
            }
        });
    }

    public void CheckScore_Click(){
        btnCheck.setOnClickListener(null);
        btnCheck.setText(R.string.Controleren);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCheck.setOnClickListener(null);
                timer.cancel();
                NewGameClick();
            }
        });
    }

    public void GameTimer_Start(){
        //Gebruikt globale variabele "time" voor instellen van tijd
        timer = new CountDownTimer(time * 1000, 1000){
            @Override
            public void onTick(long l) {
                //update textview met tijd die over is
                tvTimer.setText(String.valueOf(l /1000));
                time--;
            }
            @Override
            public void onFinish() {
                time--;
                NewGameClick();
                ResetLayout();
                tvTimer.setText(String.valueOf(time));
                Toast.makeText(getContext(), "FINISHED TOAST TEST", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    public void FillImageView(View v){
        //Loop voor vullen van imageviews met waarden uit de dice array
        for(int i = 0; i < dice.stenen.length -1; i ++){
            String name = "dice" + dice.stenen[i];
            int id = v.getResources().getIdentifier(name, "drawable", getContext().getPackageName());
            ImageView imageView = (ImageView) v.findViewById(getResources().getIdentifier("imageView" + (i + 1), "id", getContext().getPackageName()));
            imageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), id, null));
        }
    }

    public void ResetLayout(){
        //Loopt met een loop door de gridlayout met imageviews heen, zet vervolgens de drawable op "null"
        //Loopt vervolgens door de Base layout heen en maakt textboxes leeg;
        View root = getView();
        if(root != null){
            //Reset grid / imageviews
            GridLayout grid = (GridLayout) root.findViewById(R.id.Dicelayout);
            for (int i = 0; i < grid.getChildCount(); i++) {
                View v = grid.getChildAt(i);
                if (v instanceof ImageView) {
                    ((ImageView) v).setImageDrawable(null);
                }
            }
            //Reset relative / edittexts
            RelativeLayout rel = (RelativeLayout) root.findViewById(R.id.GameLayout);
            for (int i = 0; i < rel.getChildCount(); i++) {
                View v = rel.getChildAt(i);
                if (v instanceof EditText) {
                    ((EditText) v).setText(null);
                }
            }
        }
    }
}
