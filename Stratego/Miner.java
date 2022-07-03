package Stratego;

import javax.swing.*;

public class Miner extends Piece{
    public Miner(int row, int col, Players player){
        super(row,col,player);
        if(player.toString().equals("Person")) {
            image = new ImageIcon(Piece.class.getResource("\\Pieces\\" + toString() + ".png")).getImage();
        }else {
            image = new ImageIcon(Piece.class.getResource("\\Pieces\\questionMark" + player.color + ".png")).getImage();
        }
        rank = 3;
        index = 2;
    }

    @Override
    public String toString() {
        return "Miner";
    }

    @Override
    public boolean attack(Players player,int row1, int col1, int row2, int col2) {
        Piece piece1 = findPieceInBoard(row1,col1);
        Piece piece2 = findPieceInBoard(row2,col2);
        if(piece2.rank == 100 || piece1.rank > piece2.rank){
            SGUI.textArea.append(piece1.team+"'s "+piece1.toString()+" attacked "+piece2.team+"'s "
                    +piece2.toString() +"\n-> "+piece2.toString()+" is removed\n");
            Piece temp = SGUI.board[row1][col1];
            SGUI.board[row1][col1] = null;
            SGUI.board[row2][col2] = temp;
            player.addToMoves(row1, col1, row2, col2);
        }else if(piece2.rank > piece1.rank){
            SGUI.textArea.append(piece1.team+"'s "+piece1.toString()+" attacked "+piece2.team+"'s "
                    +piece2.toString() +"\n-> "+piece1.toString()+" is removed\n");
            SGUI.board[row1][col1] = null;
        }else {
            SGUI.textArea.append(piece1.team+"'s "+piece1.toString()+" attacked "+piece2.team+"'s "
                    +piece2.toString() +"\n-> both got removed\n");
            SGUI.board[row1][col1] = null;
            SGUI.board[row2][col2] = null;
        }
        return true;
    }
}
