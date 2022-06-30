package Stratego;

import javax.swing.*;
import java.util.ArrayList;

public class Capitan extends Piece{
    public Capitan(int row, int col,Player player){
        super(row,col,player);
        image = new ImageIcon(Piece.class.getResource("\\Pieces\\"+toString()+".png")).getImage();
        rank = 6;
        index = 5;
    }

    @Override
    public boolean addToPieceList(ArrayList<Piece> piecesList, int[] pieceCounter) {
        if(pieceCounter[5] < 4){
            piecesList.add(this);
            pieceCounter[5] += 1;
            System.out.println("Done");
            return true;
        }
        else {
            System.out.println("You already have 4 capitans");
            return false;
        }
    }
    @Override
    public String toString() {
        return "Capitan";
    }
}
