package com.example.gebruiker.moa_wakken_en_ijsberen;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    TextView tvTimer, tvPinguins, tvHoleRes, tvPolarRes, tvPenguinRes;
    EditText edPolar, edHole, edPenguin;
    Button btnCheck;
    CountDownTimer timer;
    SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        tvTimer = (TextView) view.findViewById(R.id.tvTimer);
        tvPinguins = (TextView) view.findViewById(R.id.tvPenguins);
        tvPolarRes = (TextView) view.findViewById(R.id.tvPolarRes);
        tvHoleRes = (TextView) view.findViewById(R.id.tvHoleRes);
        tvPenguinRes = (TextView) view.findViewById(R.id.tvPenguinRes);
        btnCheck = (Button) view.findViewById(R.id.btnCheck);
        edPolar = (EditText) view.findViewById(R.id.edPolar);
        edHole = (EditText) view.findViewById(R.id.edHole);
        edPenguin = (EditText) view.findViewById(R.id.edPenguin);
        tvTimer.setText(String.valueOf(seconds));
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        dices = preferences.getInt("Dice", 3);
        seconds = preferences.getInt("Seconds", 10);
        boolPenguins = preferences.getBoolean("Penguins", true);
        NewGameClick();
        return view;
    }


    public void NewGameClick(){
        //Kijkt of penguins aan staat en enabled/disabled vervolgens textboxes
        if(boolPenguins){
            tvPinguins.setVisibility(View.VISIBLE);
            tvPenguinRes.setVisibility(View.VISIBLE);
            edPenguin.setVisibility(View.VISIBLE);
        }
        else
        {
            tvPenguinRes.setVisibility(View.INVISIBLE);
            tvPinguins.setVisibility(View.INVISIBLE);
            edPenguin.setVisibility(View.INVISIBLE);
        }

        tvTimer.setText(String.valueOf(seconds));

        btnCheck.setText(R.string.Start);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Maakt textboxes leeg
                ClearText();
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

                //Haalt score op als er iets is ingevuld
                if(EmptyTextCheck())
                    GetScores();
                else
                    Toast.makeText(getContext(), R.string.invoerError, Toast.LENGTH_SHORT).show();

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
                NewGameClick();
                tvTimer.setText(String.valueOf(seconds));
                Toast.makeText(getContext(), R.string.tijdError, Toast.LENGTH_SHORT).show();
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
            if((roll % 2) != 0){ //oneven getal
                hole = hole + 1; // Wak optellen
                polar = polar + (roll - 1); // Ijsberen optellen - het wak
                penguins = penguins + (7 - roll); //Penguins optellen
            }
        }
        //Zet de correcte antwoorden achter de invoer van de gebruiker
        tvPenguinRes.setText(" (" + penguins + ")");
        tvHoleRes.setText(" (" + hole + ")");
        tvPolarRes.setText(" (" + polar + ")");
        //Controle van gebruiker invoer, voegt goede en foute antwoorden toe aan scoreclass
        addUserScore(hole, edHole, score);
        addUserScore(polar, edPolar, score);
        if (boolPenguins)
            addUserScore(penguins, edPenguin, score);
        //Voegt username van sharedpreff toe aan score class
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        score.setName(preferences.getString("Name", ""));

        //Voegt score toe aan database, en voegt extra dobbelsteen toe als alles goed is
        if (score.getWrongGuesses() == 0 && dices < 8) {
            dices = dices + 1;
            DBHandler handler = new DBHandler(getContext());
            handler.addScore(score);
        }
    }

    //Methode om userscore te controleren
    //Laat vervolgens zien in edittext/textview of antwoord goed of fout is
    public void addUserScore(int game, EditText input, Score score){
        int value = tryParseEditText(input);
        if(game == value){
            input.setTextColor(Color.GREEN);
            score.setGoodGuesses(score.getGoodGuesses() + 1);
        }
        else
        {
            input.setTextColor(Color.RED);
            score.setWrongGuesses(score.getWrongGuesses() + 1);
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
        if(timer != null)
            timer.cancel();
        ClearText();
        this.seconds = seconds;
        this.dices = dices;
        this.boolPenguins = penguins;
        NewGameClick();
    }

    public void ClearText(){
        //Loopt door de Base layout heen en maakt textboxes leeg;
        View root = getView();
        if(root != null){
            RelativeLayout rel = (RelativeLayout) root.findViewById(R.id.GameLayout);
            for (int i = 0; i < rel.getChildCount(); i++) {
                View v = rel.getChildAt(i);
                if (v instanceof EditText) {
                    ((EditText) v).setText(null);
                    ((EditText) v).setTextColor(Color.BLACK);
                }
            }
            tvPenguinRes.setText(null);
            tvPolarRes.setText(null);
            tvHoleRes.setText(null);
        }
    }

    //Controle op lege edittexts
    public boolean EmptyTextCheck(){
        View root = getView();
        if(root != null) { // ongeldige view, return false
            RelativeLayout rel = (RelativeLayout) root.findViewById(R.id.GameLayout);
            for (int i = 0; i < rel.getChildCount(); i++) {
                View v = rel.getChildAt(i);
                if (v instanceof EditText) {
                    if(v.getVisibility() == View.VISIBLE){
                        if(TextUtils.isEmpty(((EditText) v).getText()))
                            return false; // lege textbox, return false
                    }
                }
            }
        }
        else
            return false;
        return true;
    }
}
