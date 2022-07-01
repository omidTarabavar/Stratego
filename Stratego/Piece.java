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
    int row;
    int col;
    public Piece(int row, int col, Players player){
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
    public boolean move(Players player,int row1, int col1, int row2, int col2){
        boolean moved = false;
        Piece piece1 = findPieceInBoard(row1,col1);
        Piece piece2 = findPieceInBoard(row2,col2);
        if(piece1 != null) {
            if ((row2 == 4 || row2 == 5) && ((col2 == 2 || col2 == 3 || col2 == 6 || col2 == 7))) {
                // textArea ---> you cant move to the sea
                return false;
            } else {
                boolean validMove = piece1.validMove(row1, col1, row2, col2);
                boolean duplicateCheck = player.checkMoves(row1, col1, row2, col2);
                boolean jumpChecker =true;
                if(piece1.toString().equals("Scout")) {
                    jumpChecker = Scout.jumpChecker(row1, col1, row2, col2);
                }
                if (piece2 == null) {
                    if (validMove && jumpChecker &&duplicateCheck) {
                        Piece temp = Game.board[row1][col1];
                        Game.board[row1][col1] = Game.board[row2][col2];
                        Game.board[row2][col2] = temp;
                        player.addToMoves(row1, col1, row2, col2);
                        moved = true;
                    } else {
                        // textArea
                    }
                } else {
                    if (!piece1.team.equals(piece2.team)) {
                        if (validMove && jumpChecker && duplicateCheck) {
                            moved = piece1.attack(player, row1, col1, row2, col2);
                        } else {
                            // ----> textArea
                        }
                    } else {
                        // ---> textArea cant attack piece with same team
                    }
                }
                if(piece1 != null)
                    piece1.updatePieceStatus();
                if (piece2 != null)
                    piece2.updatePieceStatus();
            }
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
    public boolean attack(Players player,int row1, int col1, int row2, int col2){
        Piece piece1 = findPieceInBoard(row1,col1);
        Piece piece2 = findPieceInBoard(row2,col2);
        if(piece1.rank > piece2.rank){
            Piece temp = Game.board[row1][col1];
            Game.board[row1][col1] = null;
            Game.board[row2][col2] = temp;
            player.addToMoves(row1, col1, row2, col2);
        }else if(piece1.rank < piece2.rank){
            Game.board[row1][col1] = null;
        }else {
            Game.board[row1][col1] = null;
            Game.board[row2][col2] = null;
        }
        return true;
    }

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
