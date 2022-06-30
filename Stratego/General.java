package Stratego;

import javax.swing.*;
import java.util.ArrayList;

public class General extends Piece{
    public General(int row, int col, Players player){
        super(row,col,player);
        if(player.toString().equals("Person")) {
            image = new ImageIcon(Piece.class.getResource("\\Pieces\\" + toString() + ".png")).getImage();
        }else {
            image = new ImageIcon(Piece.class.getResource("\\Pieces\\questionMark" + player.color + ".png")).getImage();
        }
        rank = 9;
        index = 8;
    }

    @Override
    public String toString() {
        return "General";
    }
}
