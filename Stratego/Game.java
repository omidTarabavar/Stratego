package Stratego;

public class Game {
    public static Piece[][] board = new Piece[10][10];
    public static Player[] players = new Player[2];
    static int[] numbers = {1,8,5,4,4,4,3,2,1,1,6,1};

    public static boolean move(int row1,int col1,int row2,int col2,Player player1,Player player2){
        boolean moved = false;
        Piece piece1 = Piece.findPieceInBoard(row1,col1);
        Piece piece2 = Piece.findPieceInBoard(row2,col2);
        if(piece1 != null){
            if(piece2 == null){
                if((row2 == 4 || row2 ==5) && ((col2 == 2 || col2 == 3 || col2 == 6 || col2 == 7))){
                    // textArea ---> you cant move to the sea
                }else {
                    moved = piece1.move(player1,row1,col1,row2,col2);
                }
            }else {
                moved = piece1.move(player1,row1,col1,player2,row2,col2);
                piece2.updatePieceStatus();
            }
            piece1.updatePieceStatus();
        }
        return moved;
    }
    public static void initialBoard(Player player){
        int type = 0;
        int amount=0;
        for(int i = 9 ; i > 5 ; i--){
            for(int j = 0; j< board.length;j++){
                if(amount < numbers[type]) {
                    board[i][j] = getPiece(type, i, j, player);
                    amount += 1;
                }else {
                    amount = 1;
                    type += 1;
                    board[i][j] = getPiece(type, i, j, player);
                }
            }
        }
    }
    public static Piece getPiece(int type,int row , int col,Player player){
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
