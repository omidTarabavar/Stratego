package Stratego;

import java.util.ArrayList;

public class Scout extends Piece{
    public Scout(int row, int col,Player player){
        super(row,col,player);
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
        int[] nextPos = new int[2];
        boolean ok  = findNextPosition(this.position, direction, tiles,nextPos);
        if (isValidMove(nextPos)) {
            if (Game.board[nextPos[0]][nextPos[1]] == null) {
                this.position = nextPos;
            } else if (tiles == 1) {
                attack(nextPos, player1, player2);
            } else {
                System.out.println("Your scout cant move and attack at the same time!");
            }
        }else
            System.out.println("You cant go there");
    }
    @Override
    public boolean isValidMove(int[] nextPos){
        int rowDif = nextPos[0] - position[0];
        int colDif = nextPos[1] - position[1];
        if(rowDif == 0){
            for(int i = position[1]; i <= nextPos[1];i++){
                if(Game.board[position[0]][i] != null){
                    return false;
                }
            }
        }else if(colDif == 0){
            for(int i = position[0]; i <= nextPos[0]; i++){
                if(Game.board[i][position[1]] != null){
                    return false;
                }
            }
        }else {
            return false;
        }
        return true;
    }



}
