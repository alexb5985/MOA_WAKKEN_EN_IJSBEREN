package com.example.gebruiker.moa_wakken_en_ijsberen;

/**
 * Created by gebruiker on 28-11-2016.
 */

public class Score {
    private int id;
    private String name;
    private int goodGuesses;
    private int wrongGuesses;

    public int getWrongGuesses() {
        return wrongGuesses;
    }

    public void setWrongGuesses(int wrongGuesses) {
        this.wrongGuesses = wrongGuesses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoodGuesses() {
        return goodGuesses;
    }

    public void setGoodGuesses(int goodGuesses) {
        this.goodGuesses = goodGuesses;
    }

    public Score(String name, int goodGuesses, int wrongGuesses) {
        this.name = name;
        this.goodGuesses = goodGuesses;
        this.wrongGuesses = wrongGuesses;
    }


    public Score(){

    }

}
