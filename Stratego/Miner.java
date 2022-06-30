package Stratego;

import javax.swing.*;
import java.util.ArrayList;

public class Miner extends Piece{
    public Miner(int row, int col,Player player){
        super(row,col,player);
        image = new ImageIcon(Piece.class.getResource("\\Pieces\\"+toString()+".png")).getImage();
        rank = 3;
        index = 2;
    }

    @Override
    public boolean addToPieceList(ArrayList<Piece> piecesList, int[] pieceCounter) {
        if(pieceCounter[2] < 5){
            piecesList.add(this);
            pieceCounter[2] += 1;
            System.out.println("Done");
            return true;
        }
        else {
            System.out.println("You already have 5 miners");
            return false;
        }
    }
    @Override
    public String toString() {
        return "Miner";
    }

    @Override
    public void attack(Player player1, int row1, int col1, Player player2, int row2, int col2) {
        Piece piece1 = findPieceInBoard(row1,col1);
        Piece piece2 = findPieceInBoard(row2,col2);
        if(piece2.rank == 100 || piece1.rank > piece2.rank){
            Piece temp = Game.board[row1][col1];
            Game.board[row1][col1] = null;
            Game.board[row2][col2] = temp;
        }else if(piece2.rank > piece1.rank){
            Piece temp = Game.board[row2][col2];
            Game.board[row2][col2] = null;
            Game.board[row1][col1] = temp;
        }else {
            Game.board[row1][col1] = null;
            Game.board[row2][col2] = null;
        }
    }
}
