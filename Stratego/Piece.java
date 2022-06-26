package Stratego;

import java.util.ArrayList;

public abstract class Piece {
    int rank;
    protected int[] position = new int[2];
    public Piece(int row,int col){
        position[0] = row;
        position[1] = col;
    }
    public void move(int tiles,char direction,Player player1,Player player2){
        int[] nextPos = findNextPosition(this.position,direction,tiles);
        if((nextPos[0] == 4 || nextPos[0] == 5) && (nextPos[1] == 2 || nextPos[1] == 3 || nextPos[1] == 6 || nextPos[1]==7)){
            System.out.println("You cant move pieces into Sea");
        }
        if(Game.board[nextPos[0]][nextPos[1]] == null){
            this.position = nextPos;
            Game.board[position[0]][position[1]] = this;
        }else {
            attack(nextPos,player1,player2);
        }
    }
    public void attack(int[] nextPos,Player player1,Player player2){
        Piece piece2 = findPieceInBoard(nextPos[0],nextPos[1]);
        if(piece2.rank == 0){
            Game.board[nextPos[0]][nextPos[1]] = this;
            player2.pieces.remove(piece2);
            player2.pieceCounter[11] -= 1;
        }
        else {
            normalAttack(this,piece2,player1,player2,nextPos);
        }
    }
    public abstract boolean addToPieceList(ArrayList<Piece> piecesList,int[] pieceCounter);

    public static Piece findPieceInBoard(int row,int col){
        return Game.board[row][col];
    }
    public int[] findNextPosition(int[] position, char dir,int tiles) {
        int[] nextPos = {position[0],position[1]};
        if(dir == 'u'){
            nextPos[0] += tiles;
        }else if(dir =='d'){
            nextPos[0] -= tiles;
        }else if(dir == 'l'){
            nextPos[1] += tiles;
        }else if(dir == 'r'){
            nextPos[1] -= tiles;
        }
        return nextPos;
    }
    public void normalAttack(Piece piece1,Piece piece2,Player player1,Player player2,int[] nextPos){
        if(this.rank > piece2.rank){
            Game.board[nextPos[0]][nextPos[1]] = this;
            player2.pieces.remove(piece2);
            player2.pieceCounter[piece2.rank-1] -= 1;
        }else if(this.rank == piece2.rank){
            Game.board[nextPos[0]][nextPos[1]] = null;
            Game.board[this.position[0]][this.position[1]] = null;
        }else {
            Game.board[this.position[0]][this.position[1]] = null;
        }
    }


}
