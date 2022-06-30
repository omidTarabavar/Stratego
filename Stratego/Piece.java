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
    public void move(int row1 , int col1 , int row2 , int col2){
        boolean validMove = validMove(row1,col1,row2,col2);
        // bayad check she 3 bar yek harkat nare
        if(validMove){
            Piece temp = Game.board[row1][col1];
            Game.board[row1][col1] = Game.board[row2][col2];
            Game.board[row2][col2] = temp;
        }else {
            // textArea
            return;
        }
    }
    public void move(Player player1,int row1,int col1,Player player2,int row2,int col2){
        Piece piece1 = findPieceInBoard(row1,col1);
        Piece piece2 = findPieceInBoard(row2,col2);
        if(!piece1.team.equals(piece2.team)) {
            boolean validMove = validMove(row1, col1,row2, col2);
            if(validMove){
                piece1.attack(player1,row1,col1,player2,row2,col2);
            }else {
                // ----> textArea
            }
        }else {
            // ---> textArea cant attack piece with same team
        }
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
    public void attack(Player player1,int row1,int col1,Player player2,int row2,int col2){
        Piece piece1 = findPieceInBoard(row1,col1);
        Piece piece2 = findPieceInBoard(row2,col2);
        if(piece1.rank > piece2.rank){
            Piece temp = Game.board[row1][col1];
            Game.board[row1][col1] = null;
            Game.board[row2][col2] = temp;
        }else if(piece1.rank < piece2.rank){
            Piece temp = Game.board[row2][col2];
            Game.board[row2][col2] = null;
            Game.board[row1][col1] = temp;
        }else {
            Game.board[row1][col1] = null;
            Game.board[row2][col2] = null;
        }

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
    public boolean isValidMove(Player player,int[] nextPos){
        int rowDif = nextPos[0] - position[0];
        int colDif = nextPos[1] - position[1];
        boolean firstCheck = rowDif ==0 || colDif == 0;
        if(!firstCheck){
            System.out.println("Enter a valid move!");
        }
        boolean secondCheck = checkMoves(player.moves);
        if(!secondCheck){
            System.out.println("You cant do a move 3 times in a row!");
        }
        return firstCheck && secondCheck;
    }
    public boolean checkMoves(String[][] moves){
        for(int i =0 ;i<moves.length-1;i++){
            if(!moves[i][0].equals(moves[i+1][0])){
                return true;
            }else if(!moves[i][1].equals(moves[i+1][1])){
                return true;
            }
        }
        return false;
    }




}
