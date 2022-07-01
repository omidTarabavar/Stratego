package Stratego;

import javax.swing.*;

public class Flag extends Piece{

    public Flag(int row, int col, Players player){
        super(row,col,player);
        if(player.toString().equals("Person")) {
            image = new ImageIcon(Piece.class.getResource("\\Pieces\\" + toString() + ".png")).getImage();
        }else {
            image = new ImageIcon(Piece.class.getResource("\\Pieces\\questionMark" + player.color + ".png")).getImage();
        }
        index = 11;
        rank = 0; // in baray inke har piece be flag attack dad, betone hazfesh kone
    }

    @Override
    public String toString() {
        return "Flag";
    }

    @Override
    public boolean move(Players player,int row1, int col1, int row2, int col2) {
        // textArea --> you cant move flag
        return false;
    }
}
