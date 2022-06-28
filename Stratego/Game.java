package Stratego;

import java.util.Scanner;

public class Game {
    public static Piece[][] board = new Piece[10][10];
    public static Player[] players = new Player[2];
    public boolean addAPiece(Player player){
        Piece piece;
        boolean ok;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Choose your piece:");
        System.out.println("\n1-Spy");
        System.out.println("2-Scout");
        System.out.println("3-Miners");
        System.out.println("4-Sergeant");
        System.out.println("5-Lieutenant");
        System.out.println("6-Capitan");
        System.out.println("7-Major");
        System.out.println("8-Colonel");
        System.out.println("9-General");
        System.out.println("10-Marshal");
        System.out.println("11-Bomb");
        System.out.println("12-Flag\n");
        System.out.print("-> ");
        int chosenPiece = keyboard.nextInt();
        System.out.print("Choose a place(e.g -> 2,3): ");
        String chosenPlace = keyboard.next();
        int row = Game.board.length-(chosenPlace.charAt(0)-48-1);
        int col = chosenPlace.charAt(2)-48-1;
        if(row == 4 || row == 5){
            System.out.println("You cannot place your piece here!");
            return false;
        }
        else if(board[row][col] == null) {
            switch (chosenPiece) {
                case 1: {
                    piece = new Spy(row, col,player);
                    ok = piece.addToPieceList(player.pieces, player.pieceCounter);
                    if(ok)
                        board[row][col] = piece;
                    break;
                }
                case 2: {
                    piece = new Scout(row, col,player);
                    ok = piece.addToPieceList(player.pieces, player.pieceCounter);
                    if(ok)
                        board[row][col] = piece;
                    break;
                }
                case 3: {
                    piece = new Miner(row, col,player);
                    ok = piece.addToPieceList(player.pieces, player.pieceCounter);
                    if(ok)
                        board[row][col] = piece;
                    break;
                }
                case 4: {
                    piece = new Sergeant(row, col,player);
                    ok = piece.addToPieceList(player.pieces, player.pieceCounter);
                    if(ok)
                        board[row][col] = piece;
                    break;
                }
                case 5: {
                    piece = new Lieutenant(row, col,player);
                    ok = piece.addToPieceList(player.pieces, player.pieceCounter);
                    if(ok)
                        board[row][col] = piece;
                    break;
                }
                case 6: {
                    piece = new Capitan(row, col,player);
                    ok = piece.addToPieceList(player.pieces, player.pieceCounter);
                    if(ok)
                        board[row][col] = piece;
                    break;
                }
                case 7: {
                    piece = new Major(row, col,player);
                    ok = piece.addToPieceList(player.pieces, player.pieceCounter);
                    if(ok)
                        board[row][col] = piece;
                    break;
                }
                case 8: {
                    piece = new Colonel(row, col,player);
                    ok = piece.addToPieceList(player.pieces, player.pieceCounter);
                    if(ok)
                        board[row][col] = piece;
                    break;
                }
                case 9: {
                    piece = new General(row, col,player);
                    ok = piece.addToPieceList(player.pieces, player.pieceCounter);
                    if(ok)
                        board[row][col] = piece;
                    break;
                }
                case 10: {
                    piece = new Marshal(row, col,player);
                    ok = piece.addToPieceList(player.pieces, player.pieceCounter);
                    if(ok)
                        board[row][col] = piece;
                    break;
                }
                case 11:{
                    piece = new Bomb(row,col,player);
                    ok = piece.addToPieceList(player.pieces,player.pieceCounter);
                    if(ok)
                        board[row][col] = piece;
                    break;
                }
                case 12:{
                    piece = new Flag(row,col,player);
                    ok = piece.addToPieceList(player.pieces,player.pieceCounter);
                    if(ok)
                        board[row][col] = piece;
                    break;
                }
                default:{
                    System.out.println("Enter a valid number!");
                    return false;
                }
            }
        }else {
            System.out.println("This position is already taken");
            return false;
        }
        return true;
    }

    public void movePiece(Player player1,Player player2){
        int[] position = new int[2];
        int tiles=1;
        Scanner keyboard =new Scanner(System.in);
        System.out.print("Enter your move(e.g > Major,2,3-u): ");
        String move = keyboard.next();
        findPosition(move,position);
        String pieceName = findPieceName(move);
        Piece piece= Piece.findPieceInBoard(position[0],position[1]);
        if(piece != null) {
            if (pieceName.equals("Scout")) {
                System.out.print("Number of tiles: ");
                tiles = keyboard.nextInt();
            }
            piece.move(tiles,move.charAt(move.length()-1),player1,player2);
        }else {
            System.out.println("There is no piece at this position");
        }
    }
    public String findPieceName(String move){
        int endingIndex = 0;
        for(int i = 0 ; i < move.length();i++){
            if(move.charAt(i) == ','){
                endingIndex = (i-1);
                break;
            }
        }
        return move.substring(0,endingIndex+1);
    }

    public void findPosition(String move,int[] position){
        for(int i = 0 ; i < move.length();i++){
            if(move.charAt(i) == ','){
                position[0] = move.charAt(i+1)-48;
                position[1] = move.charAt(i+3)-48;
                break;
            }
        }
    }

//    public void showBoard

}
