package com.example.gebruiker.moa_wakken_en_ijsberen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Loek on 2-12-2016.
 */

public class Dobbelstenen {

    public ArrayList<Integer> Stenen;
    int Aantalstenen;
    Random r;
//construct
    public Dobbelstenen(int aantalstenen) {

        Aantalstenen = aantalstenen;
        Stenen = new ArrayList<Integer>();
        r = new Random();
        for(int i=1; i<aantalstenen +1; i++){

            int steen = r.nextInt(7 - 1) ;
            Stenen.add(steen);
        }

    }

    public void Rolldice(){
      Stenen.clear();
        for(int i=1; i< Aantalstenen +1; i++){

            int steen = r.nextInt(7 - 1) ;
            Stenen.add(steen);
        }
    }

}
