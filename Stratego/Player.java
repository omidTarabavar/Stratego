package Stratego;

import java.util.ArrayList;

public class Player {
    public ArrayList<Piece> pieces = new ArrayList<>();
    int playerNumber;
    // index = power piece-1 --> marshal = 9 - spy = 0 -- bomb = 10 , flag = 11;
    int[] pieceCounter = new int[12];
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
}
