package Stratego;

import javax.swing.*;
import java.util.ArrayList;

public class Scout extends Piece{
    public Scout(int row, int col,Player player){
        super(row,col,player);
        image = new ImageIcon(Piece.class.getResource("\\Pieces\\"+toString()+".png")).getImage();
        index = 1;
        rank = 2;
    }

    @Override
    public boolean addToPieceList(ArrayList<Piece> piecesList, int[] pieceCounter) {
        if(pieceCounter[1] < 8){
            piecesList.add(this);
            pieceCounter[1] += 1;
            System.out.println("Done");
            return true;
        }
        else {
            System.out.println("You already have 8 scouts");
            return false;
        }
    }
    @Override
    public String toString() {
        return "Scout";
    }

    @Override
    public void move(int row1,int col1,int row2,int col2) {
        boolean validMove = validMove(row1,col1,row2,col2);
        if(validMove){
            Piece temp = Game.board[row1][col1];
            Game.board[row1][col1] = Game.board[row2][col2];
            Game.board[row2][col2] = temp;
        }else {
            // textArea
            return;
        }
    }

    @Override
    public boolean validMove(int row1, int col1, int row2, int col2) {
        return (Math.abs(row2-row1)==0 || Math.abs(col2-col1)==0);
    }

    @Override
    public boolean isValidMove(Player player,int[] nextPos){
        int rowDif = nextPos[0] - position[0];
        int colDif = nextPos[1] - position[1];
        boolean firstCheck = true;
        boolean secondCheck;
        if(rowDif == 0){
            for(int i = position[1]; i < nextPos[1];i++){
                if(Game.board[position[0]][i] != null){
                    firstCheck = false;
                    break;
                }
            }
        }else if(colDif == 0){
            for(int i = position[0]; i < nextPos[0]; i++){
                if(Game.board[i][position[1]] != null){
                    firstCheck = false;
                    break;
                }
            }
        }else {
            System.out.println("Enter a valid move!");
            return false;
        }
        if(!firstCheck){
            System.out.println("You cant reach there!");
        }
        secondCheck = checkMoves(player.moves);
        if(!secondCheck){
            System.out.println("You cant do a move 3 times in a row!");
        }
        return firstCheck && secondCheck;
    }



}
