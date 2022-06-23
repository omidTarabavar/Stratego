package Stratego;

import java.util.ArrayList;

public class Major extends Piece{
    public Major(int row, int col){
        super(row,col);
        rank = 7;
    }

    @Override
    public void addToPieceList(Piece piece, ArrayList<Piece> piecesList, int[] peaceCounter) {
        if(peaceCounter[6] <3){
            piecesList.add(piece);
        }
        else {
            System.out.println("You already have 3 majors");
        }
    }
}
