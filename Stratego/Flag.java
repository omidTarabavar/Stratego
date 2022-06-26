package Stratego;

import java.util.ArrayList;

public class Flag extends Piece{

    public Flag(int row,int col){
        super(row,col);
        rank = 0; // in baray inke har piece be flag attack dad, betone hazfesh kone
    }
    @Override
    public boolean addToPieceList(ArrayList<Piece> piecesList, int[] pieceCounter) {
        if(pieceCounter[11] < 1){
            piecesList.add(this);
            pieceCounter[11] += 1;
            System.out.println("Done");
            return true;
        }
        else {
            System.out.println("You already have a flag");
            return false;
        }
    }

    @Override
    public String toString() {
        return "Flag";
    }

    @Override
    public void move(int tiles, char direction,Player player1,Player player2) {
        System.out.println("You cant move the flag");
    }
}
