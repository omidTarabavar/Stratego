package Stratego;

import java.util.ArrayList;

public abstract class Piece {
    int rank;
    protected int[] position = new int[2];
    public Piece(int row,int col){
        position[0] = row;
        position[1] = col;
    }
    public void move(int tiles,char direction){
        int[] nextPos = findNextPosition(this.position,direction,tiles);
        if(Game.board[nextPos[0]][nextPos[1]] == null){
            this.position = nextPos;
        }else {
            attack(nextPos);
        }
    }
    public void attack(int[] nextPos){
        Piece piece2 = findPieceInBoard(nextPos[0],nextPos[1]);
        if(this.rank > piece2.rank){
            Game.board[nextPos[0]][nextPos[1]] = this;
        }else if(this.rank == piece2.rank){
            Game.board[nextPos[0]][nextPos[1]] = null;
            Game.board[this.position[0]][this.position[1]] = null;
        }else {
            Game.board[this.position[0]][this.position[1]] = null;
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


}
