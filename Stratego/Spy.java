package Stratego;

import javax.swing.*;
import java.util.ArrayList;

public class Spy extends Piece{
    public Spy(int row, int col, Players player){
        super(row,col,player);
        if(player.toString().equals("Person")) {
            image = new ImageIcon(Piece.class.getResource("\\Pieces\\" + toString() + ".png")).getImage();
        }else {
            image = new ImageIcon(Piece.class.getResource("\\Pieces\\questionMark" + player.color + ".png")).getImage();
        }
        index = 0;
        rank = 1;
    }

    @Override
    public String toString() {
        return "Spy";
    }

    @Override
    public boolean attack(Players player,int row1, int col1, int row2, int col2) {
        Piece piece1 = findPieceInBoard(row1,col1);
        Piece piece2 = findPieceInBoard(row2,col2);
        if(piece2.rank == 10 || piece1.rank > piece2.rank){
            Piece temp = Game.board[row1][col1];
            Game.board[row1][col1] = null;
            Game.board[row2][col2] = temp;
            player.addToMoves(row1, col1, row2, col2);
        }else if(piece2.rank > piece1.rank){
            Piece temp = piece2;
            Game.board[row2][col2] = null;
            Game.board[row1][col1] = temp;
        }else {
            Game.board[row1][col1] = null;
            Game.board[row2][col2] = null;
        }
        return true;
    }
}
