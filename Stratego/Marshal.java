package Stratego;

import java.util.ArrayList;

public class Marshal extends Piece{
    public Marshal(int row, int col){
        super(row,col);
        rank = 10;
    }

    @Override
    public void addToPieceList(Piece piece,ArrayList<Piece> piecesList,int[] peaceCounter) {
        if(peaceCounter[9] < 0){
            piecesList.add(piece);
        }
        else {
            System.out.println("You already have a marshal");
        }
    }
}
