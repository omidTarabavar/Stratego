package Stratego;

import java.awt.*;

public abstract class Piece {
    static int[] numbers = {1,8,5,4,4,4,3,2,1,1,6,1};
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
        SGUI.board[row][col] = this;
        team = player.toString();
    }
    public void updatePieceStatus(){
        for(int i =0 ; i < SGUI.board.length;i++){
            for(int j = 0 ; j < SGUI.board[i].length;j++){
                if(SGUI.board[i][j] == this){
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
                        Piece temp = SGUI.board[row1][col1];
                        SGUI.board[row1][col1] = SGUI.board[row2][col2];
                        SGUI.board[row2][col2] = temp;
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
            Piece temp = SGUI.board[row1][col1];
            SGUI.board[row1][col1] = null;
            SGUI.board[row2][col2] = temp;
            player.addToMoves(row1, col1, row2, col2);
        }else if(piece1.rank < piece2.rank){
            SGUI.board[row1][col1] = null;
        }else {
            SGUI.board[row1][col1] = null;
            SGUI.board[row2][col2] = null;
        }
        return true;
    }

    public static Piece findPieceInBoard(int row,int col){
        return SGUI.board[row][col];
    }
}
