package Stratego;

import javax.swing.*;
import java.util.ArrayList;

public class Scout extends Piece{
    public Scout(int row, int col, Players player){
        super(row,col,player);
        if(player.toString().equals("Person")) {
            image = new ImageIcon(Piece.class.getResource("\\Pieces\\" + toString() + ".png")).getImage();
        }else {
            image = new ImageIcon(Piece.class.getResource("\\Pieces\\questionMark" + player.color + ".png")).getImage();
        }
        index = 1;
        rank = 2;
    }

    @Override
    public String toString() {
        return "Scout";
    }

    public static boolean jumpChecker(int row1,int col1,int row2,int col2){
        boolean flag = true;
        int rowDif = Math.abs(row2-row1);
        int colDif = Math.abs(col2-col1);

        if(rowDif == 0){
            int smallerCol = Math.min(col1,col2);
            int biggerCol = Math.max(col1,col2);
            for(int i = smallerCol+1 ; i < biggerCol ; i++){
                if(Game.board[row1][i] != null){
                    flag = false;
                    break;
                }
            }
        }else if(colDif == 0){
            int smallerRow = Math.min(row1,row2);
            int biggerRow = Math.max(row1,row2);
            for(int i = smallerRow+1 ; i< biggerRow ;i++){
                if(Game.board[i][col1] != null){
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }
    @Override
    public boolean validMove(int row1, int col1, int row2, int col2) {
        return (Math.abs(row2-row1)==0 || Math.abs(col2-col1)==0);
    }

    @Override
    public boolean attack(Players player,int row1, int col1, int row2, int col2) {
        int rowDif = Math.abs(row2-row1);
        int colDif = Math.abs(col2-col1);
        if(rowDif == 0){
            if(colDif >1){
                // textArea cant move and attack at same time
                return false;
            }else{
                Piece piece1 = findPieceInBoard(row1,col1);
                Piece piece2 = findPieceInBoard(row2,col2);
                if(piece1.rank > piece2.rank){
                    Piece temp = Game.board[row1][col1];
                    Game.board[row1][col1] = null;
                    Game.board[row2][col2] = temp;
                    player.addToMoves(row1, col1, row2, col2);
                }else if(piece1.rank < piece2.rank){
                    Piece temp = Game.board[row2][col2];
                    Game.board[row2][col2] = null;
                    Game.board[row1][col1] = temp;
                }else {
                    Game.board[row1][col1] = null;
                    Game.board[row2][col2] = null;
                }
            }
        }if(colDif == 0){
            if(rowDif >1){
                // textArea cant move and attack at same time
                return false;
            }else{
                Piece piece1 = findPieceInBoard(row1,col1);
                Piece piece2 = findPieceInBoard(row2,col2);
                if(piece1.rank > piece2.rank){
                    Piece temp = Game.board[row1][col1];
                    Game.board[row1][col1] = null;
                    Game.board[row2][col2] = temp;
                    player.addToMoves(row1, col1, row2, col2);
                }else if(piece1.rank < piece2.rank){
                    Piece temp = Game.board[row2][col2];
                    Game.board[row2][col2] = null;
                    Game.board[row1][col1] = temp;
                }else {
                    Game.board[row1][col1] = null;
                    Game.board[row2][col2] = null;
                }
            }
        }
        return true;
    }
}
