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
        if(ok) {
            if (((nextPos[0] == 4 || nextPos[0] == 5) && (nextPos[1] == 2 || nextPos[1] == 3 || nextPos[1] == 6 || nextPos[1] == 7))) {
                System.out.println("You cant move pieces into Sea");
            } else if (isValidMove(player1, nextPos)) {
                if (Game.board[nextPos[0]][nextPos[1]] == null) {
                    this.position = nextPos;
                } else if (tiles == 1) {
                    attack(nextPos, player1, player2);
                } else {
                    System.out.println("Your scout cant move and attack at the same time!");
                }
            }
        }
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
