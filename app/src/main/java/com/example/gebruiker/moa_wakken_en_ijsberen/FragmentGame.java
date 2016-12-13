package com.example.gebruiker.moa_wakken_en_ijsberen;


import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentGame extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    int seconds = 10, dices = 2, secondsTemp;
    Boolean boolPenguins = true;
    Dobbelstenen dice;
    TextView tvTimer, tvPinguins;
    EditText edPolar, edHole, edPenguin;
    Button btnCheck;
    CountDownTimer timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        tvTimer = (TextView) view.findViewById(R.id.tvTimer);
        tvPinguins = (TextView) view.findViewById(R.id.tvPenguins);
        btnCheck = (Button) view.findViewById(R.id.btnCheck);
        edPolar = (EditText) view.findViewById(R.id.edPolar);
        edHole = (EditText) view.findViewById(R.id.edHole);
        edPenguin = (EditText) view.findViewById(R.id.edPenguin);
        tvTimer.setText(String.valueOf(seconds));
        NewGameClick();
        return view;
    }

    public void NewGameClick(){
        //Kijkt of penguins aan staat en enabled/disabled vervolgens textboxes
        if(boolPenguins){
            tvPinguins.setVisibility(View.VISIBLE);
            edPenguin.setVisibility(View.VISIBLE);
        }
        else
        {
            tvPinguins.setVisibility(View.INVISIBLE);
            edPenguin.setVisibility(View.INVISIBLE);
        }

        tvTimer.setText(String.valueOf(seconds));

        btnCheck.setText(R.string.Start);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Maakt textboxes leeg
                ClearTextboxes();
                //Zodra er geklikt word, krijgt de button een nieuwe listener voor controle van score
                CheckScore_Click();
                //maakt array aan met ingestelde dobbelstenen en vult vervolgens imageviews
                dice = new Dobbelstenen(dices);
                dice.Rolldice();
                FillImageView(view.getRootView());
                //Start countdowntimer bij de eerste button click event
                GameTimer_Start();
            }
        });
    }

    public void CheckScore_Click(){
        //Zet nieuw onclick listener op button om score te controleren
        btnCheck.setOnClickListener(null);
        btnCheck.setText(R.string.Controleren);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCheck.setOnClickListener(null);
                timer.cancel();
                GetScores(); // Haalt score op
                NewGameClick(); // TODO Vervangen met getscores()
            }
        });
    }

    public void GameTimer_Start(){
        //Gebruikt globale variabele "seconds" en "secondsTemp" voor instellen van tijd
        //"seconds" houd originele waarde om timer instelling te bewaren
        secondsTemp = seconds;
        timer = new CountDownTimer(seconds * 1000, 1000){
            @Override
            public void onTick(long l) {
                //update textview met tijd die over is
                tvTimer.setText(String.valueOf(l /1000));
                secondsTemp--;
            }
            @Override
            public void onFinish() {
                secondsTemp--;
                GetScores();
                NewGameClick();
                tvTimer.setText(String.valueOf(seconds));
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

    public void ClearTextboxes(){
        //Loopt door de Base layout heen en maakt textboxes leeg;
        View root = getView();
        if(root != null){
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

    //Berekening voor het spel zelf
    public void GetScores(){
        Score score = new Score();
        int hole = 0;
        int polar = 0;
        int penguins = 0;
        //Haalt score op
        for(int i = 0; i < dice.stenen.length -1; i ++) {
            //Checken op wakken (oneven getallen)
            int roll = dice.stenen[i];
            if((roll % 2) == 0){ //even getal
                polar = polar + roll;
            }
            else //oneven getal
            {
                hole = hole + 1; // Wak optellen
                polar = polar + (roll - 1); // Ijsberen optellen - het wak
                penguins = penguins + roll;
            }
        }

        //Controle van gebruiker invoer
        checkUserInput(hole, edHole);
        checkUserInput(polar, edPolar);
        if(boolPenguins)
            checkUserInput(penguins, edPenguin);
    }

    //Methode om userscore te controleren
    //Laat vervolgens zien in textbox of goed of fout is
    public void checkUserInput(int game, EditText input){
        int value = tryParseEditText(input);

        if(game == 0){
            input.setText(" (" + game + ")");
            input.setTextColor(Color.GREEN);
        }
        else if(game > 0 && game == value){
            input.setTextColor(Color.GREEN);
        }
        else{
            input.setText(input.getText() +  " (" + game + ")");
            input.setTextColor(Color.RED);
        }
    }

    //Zelf gemaakte tryparse, want java heeft em niet.....
    public int tryParseEditText(EditText editText) {
        try {
            return Integer.parseInt(editText.getText().toString());
        } catch (Exception e) {
            return 0;
        }
    }

    //Haalt settings op zodra deze veranderd zijn, herstart vervolgs de game
    public void UpdateGame(int seconds, int dices, boolean penguins){
        timer.cancel();
        ClearTextboxes();
        this.seconds = seconds;
        this.dices = dices;
        this.boolPenguins = penguins;
        NewGameClick();
    }

}
