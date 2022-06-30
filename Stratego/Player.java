package Stratego;

import java.util.ArrayList;

public class Player {
    public ArrayList<Piece> pieces = new ArrayList<>();
    int playerNumber;
    // index = power piece-1 --> marshal = 9 - spy = 0 -- bomb = 10 , flag = 11;
    int[] pieceCounter = new int[12];
    String[][] moves = new String[2][2];
    public Player(){
        for(int i = 0 ;i< Game.players.length;i++){
            if(Game.players[i] == null){
                Game.players[i] = this;
                playerNumber = (i+1);
            }
        }
    }
    @Override
    public String toString() {
        return "Player"+playerNumber;
    }
    public void addToMoves(int row1,int col1,int row2,int col2){
        int index = indexFinder();
        if(index < 2){
            moves[index][0] = ""+row1+""+col1;
            moves[index][1] = ""+row2+""+col2;
        }else {
            moves[0][0] = moves[1][0];
            moves[0][1] = moves[1][1];
            moves[1][0] = ""+row1+""+col1;
            moves[1][1] = ""+row2+""+col2;
        }

    }
    public int indexFinder(){
        int index =2;
        for(int i = 0 ; i < moves.length ; i++){
            if(moves[i][0] == null){
                index = i;
                break;
            }
        }
        return index;
    }
    public boolean checkMoves(int row1,int col1,int row2,int col2) {
        int cnt = 0;
        String firstPlace = "" + row1 + "" + col1;
        String secondPlace = "" + row2 + "" + col2;

        if (indexFinder() == 2) {
            for (int i = 0; i < moves.length; i++) {
                if (moves[i][0].equals(firstPlace) && moves[i][1].equals(secondPlace)) {
                    cnt += 1;
                } else if (moves[i][1].equals(firstPlace) && moves[i][0].equals(secondPlace)) {
                    cnt += 1;
                }
            }
            if (cnt == 2) {
                return false;
            }
        }
        return true;
    }
}
