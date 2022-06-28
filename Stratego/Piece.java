package Stratego;

import java.util.ArrayList;

public abstract class Piece {
    int rank;
    String team;
    protected int[] position = new int[2];
    public Piece(int row,int col,Player player){
        position[0] = row;
        position[1] = col;
        team = player.toString();
    }
    public void move(int tiles,char direction,Player player1,Player player2){
        int[] nextPos= new int[2];
        boolean ok = findNextPosition(this.position,direction,tiles,nextPos);
        if(ok) {
            if (((nextPos[0] == 4 || nextPos[0] == 5) && (nextPos[1] == 2 || nextPos[1] == 3 || nextPos[1] == 6 || nextPos[1] == 7))) {
                System.out.println("You cant move pieces into Sea");
            }
            else if (isValidMove(player1,nextPos)) {
                if (Game.board[nextPos[0]][nextPos[1]] == null) {
                    this.position = nextPos;
                    Game.board[position[0]][position[1]] = this;
                } else {
                    attack(nextPos, player1, player2);
                }
            }
        }
    }
    public void attack(int[] nextPos,Player player1,Player player2){
        Piece piece2 = findPieceInBoard(nextPos[0],nextPos[1]);
        if(player1.toString().equals(player2.toString())){
            System.out.println("You cant attack your own piece!");
        }else if(piece2.rank == 0){
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
