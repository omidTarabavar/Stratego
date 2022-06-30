package Stratego;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class SGUI {
    static boolean activeMouse = false;
    static boolean start = false;
    static Player player1 = new Player();
    static Player aI = new Player();
    static int selectedRow1;
    static int selectedCol1;
    static int selectedRow2;
    static int selectedCol2;
    public static void main(String[] args) {
        Game.initialBoard(player1);
        JFrame frame = new JFrame("Stratego");
        frame.setBounds(0,0,1200,1200);

//        frame.setUndecorated(true);
        JPanel jPanel = new JPanel(){
            @Override
            public void paint(Graphics g) {
                boolean color = true; // true = white - false = blue
                for(int row = 0 ; row < 10 ;row++){
                    for(int col = 0 ;col < 10 ; col++){
                        if((row ==4 || row == 5)  && (col == 2 || col == 3 || col ==6 || col==7) ){
                            g.setColor(Color.BLUE);
                        }else if(color){
                            g.setColor(Color.getHSBColor(190,37,89));
                        }else {
                            g.setColor(Color.getHSBColor(150,227,210));
                        }
                        g.fillRect(col*64,row*64,64,64);
                        color = !color;
                    }
                    color = !color;
                }
                for(int i = 0 ; i < Game.board.length;i++){
                    for(int j = 0 ; j < Game.board[0].length;j++){
                        Piece piece = Game.board[i][j];
                        if(piece != null) {
                            g.drawImage(piece.image, piece.x, piece.y, this);
                        }


                    }
                }
            }
        };
        frame.setLayout(null);
        jPanel.setBounds(10,10,640,640);
        JButton button = new JButton("Start");
        button.setBounds(850,500,70,25);
        JLabel label = new JLabel("");
        label.setBounds(847,530,80,30);
        frame.add(jPanel);
        frame.add(button);
        frame.add(label);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start = true;
                label.setText("Game started");
            }
        });
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                int col = (e.getX()-10)/64;
                int row = (e.getY()-32)/64;
                if(!start){
                    if(row > 5){
                        selectedRow1 = row;
                        selectedCol1 = col;
                        System.out.println("col:"+col+" row: "+row);
                    }
                }if(start){
                    selectedRow1 = row;
                    selectedCol1 = col;
                    Piece piece = Piece.findPieceInBoard(row,col);
                    System.out.println(piece);
                    System.out.println("col:"+col+" row: "+row);
                }


            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int col = (e.getX()-10)/64;
                int row = (e.getY()-32)/64;
                Piece selectedPiece2=null;
                Piece selectedPiece1=null;
                if(!start){
                    if(row > 5){
                        selectedRow2 = row;
                        selectedCol2 = col;
                        selectedPiece2 = Piece.findPieceInBoard(selectedRow2,selectedCol2);
                        selectedPiece1 = Piece.findPieceInBoard(selectedRow1,selectedCol1);
                    }
                    if(selectedPiece1 != null && selectedPiece2 != null){
                        replacePieces(selectedPiece1,selectedPiece2);
                        frame.repaint();
                    }
                }if(start){
                    selectedRow2 = row;
                    selectedCol2 = col;
                    Game.move(selectedRow1, selectedCol1,selectedRow2,selectedCol2,player1,aI);
                    frame.repaint();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
//                if(firstPiece != null){
//                    activeMouse = true;
//                    firstPiece.x = (e.getX()-32);
//                    firstPiece.y = (e.getY()-64);
//                    frame.repaint();
//                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }
    public static void replacePieces(Piece piece1 , Piece piece2){
        int temp = piece1.x;
        piece1.x = piece2.x;
        piece2.x = temp;
        temp = piece1.y;
        piece1.y = piece2.y;
        piece2.y=temp;
        Piece tempPiece = Game.board[piece1.y/64][piece1.x/64];
        Game.board[piece1.y/64][piece1.x/64] = Game.board[piece2.y/64][piece2.x/64];
        Game.board[piece2.y/64][piece2.x/64] = tempPiece;

        activeMouse = false;

    }

}
