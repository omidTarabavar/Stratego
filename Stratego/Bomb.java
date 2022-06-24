package Stratego;

import java.util.ArrayList;

public class Bomb extends Piece{

    public Bomb(int row, int col){
        super(row,col);
        rank = 100; // in baray inke joz bomber, baghie age attack konn hazf shan injuri set shode
    }
    @Override
    public boolean addToPieceList(ArrayList<Piece> piecesList, int[] pieceCounter) {
        if(pieceCounter[10] < 6){
            piecesList.add(this);
            pieceCounter[10] += 1;
            System.out.println("Done");
            return true;
        }
        else {
            System.out.println("You already have 6 bombs");
            return false;
        }
    }
    @Override
    public String toString() {
        return "Bomb";
    }
}
