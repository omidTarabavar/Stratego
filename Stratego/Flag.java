package Stratego;

import javax.swing.*;
import java.util.ArrayList;

public class Flag extends Piece{

    public Flag(int row,int col,Player player){
        super(row,col,player);
        image = new ImageIcon(Piece.class.getResource("\\Pieces\\"+toString()+".png")).getImage();
        index = 11;
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
    public void move(int row1,int col1,int row2,int col2) {
        // textArea --> you cant move flag
    }

    @Override
    public void move(Player player1, int row1, int col1, Player player2, int row2, int col2) {
        // textArea --> you cant move flag
    }
}
