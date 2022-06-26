package Stratego;

import java.util.ArrayList;

public class Miner extends Piece{
    public Miner(int row, int col,Player player){
        super(row,col,player);
        rank = 3;
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
    public void attack(int[] nextPos,Player player1,Player player2) {
        Piece piece2 = findPieceInBoard(nextPos[0],nextPos[1]);
        if(piece2.rank == 100){
            Game.board[nextPos[0]][nextPos[1]] = this;
            player2.pieces.remove(piece2);
            player2.pieceCounter[10] -= 1;
        }else if(piece2.rank == 0){
            Game.board[nextPos[0]][nextPos[1]] = this;
            player2.pieces.remove(piece2);
            player2.pieceCounter[11] -= 1;
        }else {
            normalAttack(this,piece2,player1,player2,nextPos);
        }
    }
}
