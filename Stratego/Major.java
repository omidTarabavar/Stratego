package Stratego;

import javax.swing.*;
import java.util.ArrayList;

public class Major extends Piece{
    public Major(int row, int col, Players player){
        super(row,col,player);
        if(player.toString().equals("Person")) {
            image = new ImageIcon(Piece.class.getResource("\\Pieces\\" + toString() + ".png")).getImage();
        }else {
            image = new ImageIcon(Piece.class.getResource("\\Pieces\\questionMark" + player.color + ".png")).getImage();
        }
        index = 6;
        rank = 7;
    }

    @Override
    public String toString() {
        return "Major";
    }
}
