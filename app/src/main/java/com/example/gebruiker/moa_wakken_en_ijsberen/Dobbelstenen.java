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
        r = new Random();
        stenen = new int[Aantalstenen +1];
        while(Aantalstenen > 0){
            stenen[Aantalstenen -1] = r.nextInt(6) + 1;
            Aantalstenen--;
        }
    }

}
