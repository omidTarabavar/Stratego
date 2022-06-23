package Stratego;

import java.util.ArrayList;

public class Scout extends Piece{
    public Scout(int row, int col){
        super(row,col);
        rank = 2;
    }

    @Override
    public void addToPieceList(Piece piece, ArrayList<Piece> piecesList, int[] peaceCounter) {
        if(peaceCounter[1] < 8){
            piecesList.add(piece);
        }
        else {
            System.out.println("You already have 8 scouts");
        }
    }
}
