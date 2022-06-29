package Stratego;



import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class SGUI {

    public static LinkedList<Piece> pieceLinkedList = new LinkedList<>();
    public static void main(String[] args) {
        Image[] images = new Image[12];
        setImages(images);
        JFrame frame = new JFrame();
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

            }
        };
        jPanel.setBounds(10,10,640,640);
        JPanel jP = new JPanel(){
            @Override
            public void paint(Graphics g) {
                int cnt=0;
                for(int i = 0 ; i< 6;i++){
                    for(int j = 0 ; j < 2;j++){
                        g.drawImage(images[cnt],j*64,i*64,this);
                        cnt += 1;
                    }
                }
            }
        };
        jP.setBounds(750,10,128,384);
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

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

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        frame.add(jP);
        frame.add(jPanel);

        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
    public static Piece getPiece(int x , int y){
        return null;
    }
    public static void setImages(Image[] images){
        images[0] = new ImageIcon(SGUI.class.getResource("\\Pieces\\Bomb.png")).getImage();
        images[1] = new ImageIcon(SGUI.class.getResource("\\Pieces\\Capitan.png")).getImage();
        images[2] = new ImageIcon(SGUI.class.getResource("\\Pieces\\Colonel.png")).getImage();
        images[3] = new ImageIcon(SGUI.class.getResource("\\Pieces\\Flag.png")).getImage();
        images[4] = new ImageIcon(SGUI.class.getResource("\\Pieces\\General.png")).getImage();
        images[5] = new ImageIcon(SGUI.class.getResource("\\Pieces\\Lieutenant.png")).getImage();
        images[6] = new ImageIcon(SGUI.class.getResource("\\Pieces\\Major.png")).getImage();
        images[7] = new ImageIcon(SGUI.class.getResource("\\Pieces\\Marshal.png")).getImage();
        images[8] = new ImageIcon(SGUI.class.getResource("\\Pieces\\Miner.png")).getImage();
        images[9] = new ImageIcon(SGUI.class.getResource("\\Pieces\\Scout.png")).getImage();
        images[10] = new ImageIcon(SGUI.class.getResource("\\Pieces\\Sergeant.png")).getImage();
        images[11] = new ImageIcon(SGUI.class.getResource("\\Pieces\\Spy.png")).getImage();
    }
}
