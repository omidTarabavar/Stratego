package Stratego;

import java.util.ArrayList;

public class Colonel extends Piece{
    public Colonel(int row, int col){
        super(row,col);
        rank = 8;
    }

    @Override
    public void addToPieceList(Piece piece, ArrayList<Piece> piecesList, int[] peaceCounter) {
        if(peaceCounter[7] < 2){
            piecesList.add(piece);
        }
        else {
            System.out.println("You already have 2 colonels");
        }
    }
}
