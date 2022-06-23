package Stratego;

import java.util.ArrayList;

public class General extends Piece{
    public General(int row, int col){
        super(row,col);
        rank = 9;
    }

    @Override
    public void addToPieceList(Piece piece, ArrayList<Piece> piecesList, int[] peaceCounter) {
        if(peaceCounter[8] < 0){
            piecesList.add(piece);
        }
        else {
            System.out.println("You already have a general");
        }
    }
}
