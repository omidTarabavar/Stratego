package Stratego;

import javax.swing.*;

public class Colonel extends Piece{
    public Colonel(int row, int col, Players player){
        super(row,col,player);
        if(player.toString().equals("Person")) {
            image = new ImageIcon(Piece.class.getResource("\\Pieces\\" + toString() + ".png")).getImage();
        }else {
            image = new ImageIcon(Piece.class.getResource("\\Pieces\\questionMark" + player.color + ".png")).getImage();
        }
        index = 7;
        rank = 8;
    }

    @Override
    public String toString() {
        return "Colonel";
    }
}
