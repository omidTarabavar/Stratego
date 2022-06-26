package Stratego;

import java.util.ArrayList;

public class Spy extends Piece{
    public Spy(int row, int col,Player player){
        super(row,col,player);
        rank = 1;
    }

    @Override
    public boolean addToPieceList(ArrayList<Piece> piecesList, int[] pieceCounter) {
        if(pieceCounter[9] < 1){
            piecesList.add(this);
            pieceCounter[9] += 1;
            System.out.println("Done");
            return true;
        }
        else {
            System.out.println("You already have a spy");
            return false;
        }
    }
    @Override
    public String toString() {
        return "Spy";
    }

    @Override
    public void attack(int[] nextPos,Player player1,Player player2) {
        Piece piece2 = findPieceInBoard(nextPos[0],nextPos[1]);
        if(piece2.rank == 10){
            Game.board[nextPos[0]][nextPos[1]] = this;
            player2.pieces.remove(piece2);
            player2.pieceCounter[piece2.rank-1] -= 1;
        }else if(piece2.rank == 0){
            Game.board[nextPos[0]][nextPos[1]] = this;
            player2.pieces.remove(piece2);
            player2.pieceCounter[11] -= 1;
        }
        else {
            normalAttack(this,piece2,player1,player2,nextPos);
        }
    }
}
