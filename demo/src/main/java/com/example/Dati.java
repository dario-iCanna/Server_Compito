package com.example;

import java.util.ArrayList;

public class Dati {
    ArrayList<String> nomi;
    int[] numBiglietti;

    Dati(){
        nomi = new ArrayList<String>();            
        numBiglietti = new int[3];
        numBiglietti[0] = 50;
        numBiglietti[1] = 100;
        numBiglietti[2] = 150;
    }

    public ArrayList<String> getNomi() {
        return nomi;
    }

    public int[] getNumBiglietti() {
        return numBiglietti;
    }

    public void changeBiglietti(int val, int index){
        numBiglietti[index] += val;
    }

    public boolean addUsername(String name){
        if(nomi.indexOf(name) < 0){
            nomi.add(name);
            return true;
        }
        return false;
    }

    public void removeUsername(String name){
        nomi.remove(name);
    }
}
