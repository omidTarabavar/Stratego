package Stratego;

import java.util.ArrayList;

public class Person extends Players{
    public ArrayList<Piece> pieces = new ArrayList<>();
    // index = power piece-1 --> marshal = 9 - spy = 0 -- bomb = 10 , flag = 11;
    public Person(){
        color = "Red";
    }
    @Override
    public String toString() {
        return "Person";
    }


}
