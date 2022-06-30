package Stratego;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SGUI {
    static boolean activeMouse = false;
    static Players currentPlayer;
    static boolean start = false;
    static Person person = new Person();
    static Computer computer = new Computer();
    static int row1;
    static int col1;
    static int row2;
    static int col2;
    static boolean playerTurn = true;
    public static void main(String[] args) {
        Game.initialBoard(person,computer);
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
        JRadioButton redRadio = new JRadioButton("Red");
        JRadioButton blueRadtio = new JRadioButton("Blue");
        redRadio.setBounds(850,200,50,30);
        blueRadtio.setBounds(850,230,50,30);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(redRadio);
        buttonGroup.add(blueRadtio);
        frame.add(jPanel);
        frame.add(button);
        frame.add(label);
        frame.add(redRadio);
        frame.add(blueRadtio);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start = true;
                label.setText("Game started");
                button.setBorder(BorderFactory.createLoweredBevelBorder());
                button.setEnabled(false);
                redRadio.setEnabled(false);
                blueRadtio.setEnabled(false);
                if(redRadio.isSelected()){
                    currentPlayer = person;
                    person.color = "Red";
                    computer.color="Blue";
                    playerTurn = true;
                    computer.updateColor();
                    frame.repaint();

                }else {
                    person.color = "Blue";
                    computer.color="Red";
                    currentPlayer = computer;
                    playerTurn = false;
                    computer.updateColor();
                    frame.repaint();

                }
            }
        });
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(playerTurn) {
                     int col = (e.getX() - 10) / 64;
                     int row = (e.getY() - 32) / 64;
                    if (!start) {
                        if (row > 5) {
                            row1 = row;
                            col1 = col;
                            System.out.println("col:" + col + " row: " + row);
                        }
                    }
                    if (start) {
                        row1 = row;
                        col1 = col;
                        Piece piece = Piece.findPieceInBoard(row, col);
                        System.out.println(piece);
                        System.out.println("col:" + col + " row: " + row);
                    }

                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(playerTurn) {
                    col2 = (e.getX() - 10) / 64;
                    row2 = (e.getY() - 32) / 64;
                    Piece piece1 = Piece.findPieceInBoard(row1, col1);
                    Piece piece2 = Piece.findPieceInBoard(row2, col2);
                    if (!start && (piece1 != null && piece2 != null) &&  (row2 > 5) && (row1 > 5)) {
                        replacePieces(piece1, piece2);
                        frame.repaint();
                    }
                    if (start) {
                        boolean moved = piece1.move(currentPlayer,row1, col1, row2, col2);
                        if(moved)
                            playerTurn = false;
                        frame.repaint();
                    }
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
