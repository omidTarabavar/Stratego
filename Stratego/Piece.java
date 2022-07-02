package Stratego;

import java.awt.*;

public abstract class Piece {
    // index = power piece-1 --> marshal = 9 - spy = 0 -- bomb = 10 , flag = 11;
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
        boolean ok = true;
        Piece piece1 = findPieceInBoard(row1,col1);
        Piece piece2 = findPieceInBoard(row2,col2);
        if(piece1 != null) {
            if ((row2 == 4 || row2 == 5) && ((col2 == 2 || col2 == 3 || col2 == 6 || col2 == 7))) {
                if(player.toString().equals("Person"))
                    SGUI.textArea.append("You cant go to sea\n");
                return false;
            } else {
                boolean validMove = piece1.validMove(row1, col1, row2, col2);
                boolean duplicateCheck = player.checkMoves(row1, col1, row2, col2);
                boolean jumpChecker =true;
                if(piece1.toString().equals("Scout")) {
                    jumpChecker = Scout.jumpChecker(row1, col1, row2, col2);
                }
                ok = validMove && duplicateCheck && jumpChecker;
                if(player.toString().equals("Person")){
                    if (!validMove) {
                        SGUI.textArea.append("Do a valid move\n");
                    }else if(!jumpChecker){
                        SGUI.textArea.append("You can not pass over the pieces\n");
                    }else if(!duplicateCheck){
                        SGUI.textArea.append("You can not move more than twice between two tiles\n");
                    }
                }

                if (ok && piece2 == null) {
                    Piece temp = SGUI.board[row1][col1];
                    SGUI.board[row1][col1] = SGUI.board[row2][col2];
                    SGUI.board[row2][col2] = temp;
                    player.addToMoves(row1, col1, row2, col2);
                    moved = true;
                } else if(ok){
                    if (!piece1.team.equals(piece2.team)) {
                        moved = piece1.attack(player, row1, col1, row2, col2);
                    } else {
                        if(player.toString().equals("Person")) {
                            SGUI.textArea.append("You cant attack you own piece\n");
                        }
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
            return Math.abs(col2 - col1) == 1;
        }else if(Math.abs(col2-col1)==0){
            return Math.abs(row2 - row1) == 1;
        }
        return false;
    }
    public boolean attack(Players player,int row1, int col1, int row2, int col2){
        Piece piece1 = findPieceInBoard(row1,col1);
        Piece piece2 = findPieceInBoard(row2,col2);
        if(piece1.rank > piece2.rank){
            SGUI.textArea.append(piece1.team+"'s "+piece1.toString()+" attacked "+piece2.team+"'s "
                    +piece2.toString() +"\n-> "+piece2.toString()+" is removed\n");
            Piece temp = SGUI.board[row1][col1];
            SGUI.board[row1][col1] = null;
            SGUI.board[row2][col2] = temp;
            player.addToMoves(row1, col1, row2, col2);
        }else if(piece1.rank < piece2.rank){
            SGUI.textArea.append(piece1.team+"'s "+piece1.toString()+" attacked "+piece2.team+"'s "
                    +piece2.toString() +"\n-> "+piece1.toString()+" is removed\n");
            SGUI.board[row1][col1] = null;

        }else {
            SGUI.textArea.append(piece1.team+"'s "+piece1.toString()+" attacked "+piece2.team+"'s "
                    +piece2.toString() +"\n-> both got removed\n");
            SGUI.board[row1][col1] = null;
            SGUI.board[row2][col2] = null;
        }
        return true;
    }

    public static Piece findPieceInBoard(int row,int col){
        return SGUI.board[row][col];
    }
}
