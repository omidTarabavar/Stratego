package Stratego;

import java.util.Random;

public class Computer extends Players{
    // move - attack

    public Computer(){
        color ="Blue";
    }

    @Override
    public String toString() {
        return "Computer";
    }
    public void updateColor(){
        int type = 0;
        int amount=0;
        for(int i = 0 ; i < 4 ; i++){
            for(int j = 0 ; j < Game.board[i].length;j++){
                if(amount < Game.numbers[type]){
                    Game.board[i][j] = Game.getPiece(type,i,j,this);
                    amount += 1;
                }else {
                    amount = 1;
                    type += 1;
                    Game.board[i][j] = Game.getPiece(type,i,j,this);
                }
            }
        }
    }
    public boolean computerMove(){
        boolean continuable = true;
        Random random = new Random(System.currentTimeMillis()*123456789);
        while (continuable) {
            int randomRow = random.nextInt(10);
            int randomCol = random.nextInt(10);
            if (Game.board[randomRow][randomCol] != null && Game.board[randomRow][randomCol].team.equals("Computer")) {
                for (int k = 0; k < Game.board[randomRow].length; k++) {
                    if (Game.board[randomRow][randomCol].move(this, randomRow, randomCol, randomRow, k)) {
                        return true;
                    }
                }
                for (int m = 0; m < Game.board.length; m++) {
                    if (Game.board[randomRow][randomCol].move(this, randomRow, randomCol, m, randomCol)) {
                        return true;
                    }
                }
            }
            continuable = continuable();
        }
        return false;
    }
    public boolean continuable(){
        for(int i = 0 ; i < Game.board.length;i++){
            for(int j = 0 ; j < Game.board[i].length;j++) {
                if (Game.board[i][j] != null) {
                    boolean checkPlayer = Game.board[i][j].team.equals("Computer");
                    boolean checkPiece = !(Game.board[i][j].toString().equals("Bomb") || Game.board[i][j].toString().equals("Flag"));
                    if (checkPlayer && checkPiece) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
