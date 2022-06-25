package Stratego;

import java.util.ArrayList;

public class Scout extends Piece{
    public Scout(int row, int col){
        super(row,col);
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
    public void move(int tiles, char direction,Player player1,Player player2) {
        int[] nextPos = findNextPosition(this.position,direction,tiles);
        if(Game.board[nextPos[0]][nextPos[1]] == null){
            this.position = nextPos;
        }else if(tiles == 1){
            attack(nextPos,player1,player2);
        }
        else {
            System.out.println("Your scout cant move and attack at the same time!");
        }
    }



}
