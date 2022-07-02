package Stratego;

import java.util.Random;

public class Computer extends Players{

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
            for(int j = 0 ; j < SGUI.board[i].length;j++){
                if(amount < Piece.numbers[type]){
                    SGUI.board[i][j] = SGUI.getPiece(type,i,j,this);
                    amount += 1;
                }else {
                    amount = 1;
                    type += 1;
                    SGUI.board[i][j] = SGUI.getPiece(type,i,j,this);
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
            if (SGUI.board[randomRow][randomCol] != null && SGUI.board[randomRow][randomCol].team.equals("Computer")) {
                for (int k = 0; k < SGUI.board[randomRow].length; k++) {
                    if (SGUI.board[randomRow][randomCol].move(this, randomRow, randomCol, randomRow, k)) {
                        return true;
                    }
                }
                for (int m = 0; m < SGUI.board.length; m++) {
                    if (SGUI.board[randomRow][randomCol].move(this, randomRow, randomCol, m, randomCol)) {
                        return true;
                    }
                }
            }
            continuable = continuable();
        }
        return false;
    }
    public boolean continuable(){
        for(int i = 0 ; i < SGUI.board.length;i++){
            for(int j = 0 ; j < SGUI.board[i].length;j++) {
                if (SGUI.board[i][j] != null) {
                    boolean checkPlayer = SGUI.board[i][j].team.equals("Computer");
                    boolean checkPiece = !(SGUI.board[i][j].toString().equals("Bomb") || SGUI.board[i][j].toString().equals("Flag"));
                    if (checkPlayer && checkPiece) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
