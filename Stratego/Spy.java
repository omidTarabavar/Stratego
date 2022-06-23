package Stratego;

import java.util.ArrayList;

public class Spy extends Piece{
    public Spy(int row, int col){
        super(row,col);
        rank = 1;
    }

    @Override
    public void addToPieceList(Piece piece, ArrayList<Piece> piecesList, int[] peaceCounter) {
        if(peaceCounter[9] < 1){
            piecesList.add(piece);
        }
        else {
            System.out.println("You already have a spy");
        }
    }
}
