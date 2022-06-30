package Stratego;

import java.awt.*;
import java.util.ArrayList;

public abstract class Piece {
    int index;
    public Image image;
    int x;
    int y;
    int rank;
    String team;
    protected int[] position = new int[2];
    int row;
    int col;
    public Piece(int row,int col,Player player){
        x = col * 64;
        y = row * 64;
        this.row = row;
        this.col = col;
        Game.board[row][col] = this;
        team = player.toString();
    }
    public void updatePieceStatus(){
        for(int i =0 ; i < Game.board.length;i++){
            for(int j = 0 ; j < Game.board[i].length;j++){
                if(Game.board[i][j] == this){
                    this.row = i;
                    this.col = j;
                    this.x = j*64;
                    this.y = i * 64;
                }
            }
        }
    }
    public boolean move(Player player,int row1 , int col1 , int row2 , int col2){
        boolean moved= false;
        boolean validMove = validMove(row1,col1,row2,col2);
        boolean duplicateCheck = player.checkMoves(row1,col1,row2,col2);
        // bayad check she 3 bar yek harkat nare
        if(validMove && duplicateCheck){
            Piece temp = Game.board[row1][col1];
            Game.board[row1][col1] = Game.board[row2][col2];
            Game.board[row2][col2] = temp;
            player.addToMoves(row1,col1,row2,col2);
            moved = true;


        }else {
            // textArea
            moved = false;
        }
        return moved;
    }
    public boolean move(Player player1,int row1,int col1,Player player2,int row2,int col2){
        boolean moved = false;
        Piece piece1 = findPieceInBoard(row1,col1);
        Piece piece2 = findPieceInBoard(row2,col2);
        if(!piece1.team.equals(piece2.team)) {
            boolean validMove = validMove(row1, col1,row2, col2);
            if(validMove){
                moved = piece1.attack(player1,row1,col1,player2,row2,col2);
            }else {
                // ----> textArea
            }
        }else {
            // ---> textArea cant attack piece with same team
        }
        return moved;
    }
    public boolean validMove(int row1, int col1, int row2, int col2){
        if(Math.abs(row2-row1) == 0){
            if(Math.abs(col2-col1) == 1){
                return true;
            }else {
                return false;
            }
        }else if(Math.abs(col2-col1)==0){
            if(Math.abs(row2-row1)==1){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }
    public boolean attack(Player player1,int row1,int col1,Player player2,int row2,int col2){
        Piece piece1 = findPieceInBoard(row1,col1);
        Piece piece2 = findPieceInBoard(row2,col2);
        if(piece1.rank > piece2.rank){
            Piece temp = Game.board[row1][col1];
            Game.board[row1][col1] = null;
            Game.board[row2][col2] = temp;
            player1.addToMoves(row1, col1, row2, col2);
        }else if(piece1.rank < piece2.rank){
            Piece temp = Game.board[row2][col2];
            Game.board[row2][col2] = null;
            Game.board[row1][col1] = temp;
        }else {
            Game.board[row1][col1] = null;
            Game.board[row2][col2] = null;
        }
        return true;
    }
    public abstract boolean addToPieceList(ArrayList<Piece> piecesList,int[] pieceCounter);

    public static Piece findPieceInBoard(int row,int col){
        return Game.board[row][col];
    }
    public boolean findNextPosition(int[] position, char dir,int tiles,int[] nextPos) {
        boolean ok = false;
        nextPos[0] = position[0];
        nextPos[1] = position[1];
        if(dir == 'u' && (nextPos[0]-tiles)>= 0){
            nextPos[0] -= tiles;
            ok = true;
        }else if(dir =='d' && (nextPos[0]+tiles) <= 9){
            nextPos[0] += tiles;
            ok = true;
        }else if(dir == 'l' && (nextPos[1] + tiles) >= 0){
            nextPos[1] -= tiles;
            ok = true;
        }else if(dir == 'r' && (nextPos[1] + tiles) <= 9){
            nextPos[1] += tiles;
            ok = true;
        }
        return ok;
    }





}
