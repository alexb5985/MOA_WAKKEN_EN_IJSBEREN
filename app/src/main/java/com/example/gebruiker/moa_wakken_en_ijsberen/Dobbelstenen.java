package com.example.gebruiker.moa_wakken_en_ijsberen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Loek on 2-12-2016.
 */

public class Dobbelstenen {
    public int[] stenen;
    public int Aantalstenen;
    private Random r;

    public Dobbelstenen(int aantalstenen) {
        Aantalstenen = aantalstenen;
    }

    public void Rolldice(){
        //Vult dice[]
        r = new Random();
        int AantalTemp = Aantalstenen;
        stenen = new int[AantalTemp +1];
        while(AantalTemp > 0){
            stenen[AantalTemp -1] = r.nextInt(6) + 1;
            AantalTemp--;
        }
        checkDice();
    }
    //Controleert of er niet alleen even getallen zin
    private void checkDice(){
        int evenNumbers = 0;
        for(int i = 0; i < stenen.length -1; i ++){
            if((stenen[i] % 2) == 0)
                evenNumbers++;
        }
        //Als er alleen even getallen zijn gegooit, rolt die opnieuw
        //Hierdoor heb je altijd minstens 1 wak etc.
        if(evenNumbers == Aantalstenen)
            Rolldice();
    }

}
