package Stratego;

import javax.swing.*;
import java.util.ArrayList;

public class Bomb extends Piece{

    public Bomb(int row, int col,Player player){
        super(row,col,player);
        String name = toString();
        image = new ImageIcon(Piece.class.getResource("\\Pieces\\"+name+".png")).getImage();
        rank = 100; // in baray inke joz bomber, baghie age attack konn hazf shan injuri set shode
        index = 10;
    }
    @Override
    public boolean addToPieceList(ArrayList<Piece> piecesList, int[] pieceCounter) {
        if(pieceCounter[10] < 6){
            piecesList.add(this);
            pieceCounter[10] += 1;
            System.out.println("Done");
            return true;
        }
        else {
            System.out.println("You already have 6 bombs");
            return false;
        }
    }
    @Override
    public String toString() {
        return "Bomb";
    }

    @Override
    public boolean move(Player player,int row1,int col1,int row2,int col2) {
        // textArea --> you cant move bomb
        return false;
    }

    @Override
    public boolean move(Player player1, int row1, int col1, Player player2, int row2, int col2) {
        // textArea --> you cant move bomb
        return false;
    }
}
