package Stratego;

import javax.swing.*;
import java.util.ArrayList;

public class Sergeant extends Piece{
    public Sergeant(int row, int col,Player player){
        super(row,col,player);
        image = new ImageIcon(Piece.class.getResource("\\Pieces\\"+toString()+".png")).getImage();
        index = 3;
        rank = 4;
    }

    @Override
    public boolean addToPieceList(ArrayList<Piece> piecesList, int[] pieceCounter) {
        if(pieceCounter[3] < 4){
            piecesList.add(this);
            pieceCounter[3] += 1;
            System.out.println("Done");
            return true;
        }
        else {
            System.out.println("You already have 4 sergeants");
            return false;
        }
    }
    @Override
    public String toString() {
        return "Sergeant";
    }
}
