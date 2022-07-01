package Stratego;

public class Game {
    public static Piece[][] board = new Piece[10][10];
    static int[] numbers = {1,8,5,4,4,4,3,2,1,1,6,1};

    public static void initialBoard(Person person, Computer computer){
        int type = 0;
        int amount=0;
        for(int i = 0 ; i < 4 ; i++){
            for(int j = 0 ; j < Game.board[i].length;j++){
                if(amount < numbers[type]){
                    board[i][j] = getPiece(type,i,j,computer);
                    amount += 1;
                }else {
                    amount = 1;
                    type += 1;
                    board[i][j] = getPiece(type,i,j,computer);
                }
            }
        }
        type = 0;
        amount = 0;
        for(int i = 9 ; i > 5 ; i--){
            for(int j = 0; j< board.length;j++){
                if(amount < numbers[type]) {
                    board[i][j] = getPiece(type, i, j, person);
                    amount += 1;
                }else {
                    amount = 1;
                    type += 1;
                    board[i][j] = getPiece(type, i, j, person);
                }
            }
        }
    }
    public static Piece getPiece(int type, int row , int col, Players player){
        switch (type){
            case 0:{
                return new Spy(row,col,player);
            }case 1:{
                return new Scout(row,col,player);
            }case 2:{
                return new Miner(row,col,player);
            }case 3:{
                return new Sergeant(row,col,player);
            }case 4:{
                return new Lieutenant(row,col,player);
            }case 5:{
                return new Capitan(row,col,player);
            }case 6:{
                return new Major(row,col,player);
            }case 7:{
                return new Colonel(row,col,player);
            }case 8:{
                return new General(row,col,player);
            }case 9:{
                return new Marshal(row,col,player);
            }case 10:{
                return new Bomb(row,col,player);
            }case 11:{
                return new Flag(row,col,player);
            }
        }
        return null;
    }

}
