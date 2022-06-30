package Stratego;

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
}
