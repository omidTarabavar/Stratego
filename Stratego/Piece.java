package Stratego;

import java.util.ArrayList;

public abstract class Piece {
    int rank;
    protected int[] position = new int[2];
    public Piece(int row,int col){
        position[0] = row;
        position[1] = col;
    }
    public void move(Piece piece,String direction){

    }
    public void attack(Piece attacker,Piece defender){

    }
    public abstract boolean addToPieceList(ArrayList<Piece> piecesList,int[] pieceCounter);

}
