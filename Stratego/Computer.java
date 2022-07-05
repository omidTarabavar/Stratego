package Stratego;

import java.util.Random;
/**
 * class player computer ke az class Players ers bari mikone
 * in class amal marbot be player computer bazi ro barrasi mikone
 */
public class Computer extends Players{
    /**
     * constructor
     * color player ro set mikone ( ya red ya blue -- bastegi be radiButton entekhabi player dare )
     */
    public Computer(){
        color ="Blue";
    }

    /**
     * name player ro moshakhas mikone - azash to jahaye mokhtalefi estefade khahim kard
     * @return name player -- Computer to in class
     */
    @Override
    public String toString() {
        return "Computer";
    }

    /**
     * vaghti player team khodesho entekhab kard, team moghabel baraye computer mishe ( ya red ya blue )
     * ba estefade az in method color piece haye computer ro dobare tanzim mikonim ( ba tavajoh be entekhab player toye team )
     * type dar vaghe indexi hastesh ke bahash arraye number dar class piece ro peymayesh mikonim, dar mored type dar class piece tozih dade mishavad
     * amount - tedad piece haye mojod az type mored nazare
     * roye board az row haii be index 0 ta 3 peymayesh mikonim - (0 ta 3 chon dar initial, in row ha baraye computer hastesh)
     */
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

    /**
     * in method baraye move automatic computer hastesh --  random baraye entekhab yek row va col random dar board
     * bad az entekhab, check mikone ke row va coli ke entekhab shode, marbot be pieceii az piece haye computer hastesh ya na
     * age marbot bod, besorat yekbar horizontal yek bar vertical, say mikonim movesh bedim, peymayesh horizontaly va verticaly ba estefade az row va col
     * randomie ke entekhab kardim.
     * dar akhar ham az method cancontinue estefade mikonim ke dar ghesmat khodesh dar moredesh tozih midim.
     * @return yek boolean ke moshakhas konad harkat anjam shod ya na
     */
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
            continuable = SGUI.canContinue(this);
        }
        return false;
    }
}
