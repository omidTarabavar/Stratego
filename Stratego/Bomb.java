package Stratego;

import javax.swing.*;

public class Bomb extends Piece{

    public Bomb(int row, int col, Players player){
        super(row,col,player);
        if(player.toString().equals("Person")) {
            image = new ImageIcon(Piece.class.getResource("\\Pieces\\" + toString() + ".png")).getImage();
        }else {
            image = new ImageIcon(Piece.class.getResource("\\Pieces\\questionMark" + player.color + ".png")).getImage();
        }
        rank = 100; // in baray inke joz bomber, baghie age attack konn hazf shan injuri set shode
        index = 10;
    }
    @Override
    public String toString() {
        return "Bomb";
    }

    @Override
    public boolean move(Players player,int row1, int col1, int row2, int col2) {
        if(player.toString().equals("Person"))
            SGUI.textArea.append("You cant move the bomb\n");
        return false;
    }
}
