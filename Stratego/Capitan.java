package Stratego;

import javax.swing.*;
import java.util.ArrayList;

public class Capitan extends Piece{
    public Capitan(int row, int col, Players player){
        super(row,col,player);
        if(player.toString().equals("Person")) {
            image = new ImageIcon(Piece.class.getResource("\\Pieces\\" + toString() + ".png")).getImage();
        }else {
            image = new ImageIcon(Piece.class.getResource("\\Pieces\\questionMark" + player.color + ".png")).getImage();
        }
        rank = 6;
        index = 5;
    }

    @Override
    public String toString() {
        return "Capitan";
    }
}
