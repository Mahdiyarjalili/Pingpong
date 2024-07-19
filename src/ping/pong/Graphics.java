/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ping.pong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Mahdiyar
 */
public class Graphics extends JPanel implements Runnable, KeyListener, ActionListener{
     Thread t;
     int posXP=0;
     int posYP=100;
     int posXB=100;
     int posYB=280;
     int yp=5;
     int yp0=5;
     int XB;
     int YB;
     int Level;
     int BALL_SPEED_X=5;
     int BALL_SPEED_Y=5;
     int PADDLE_WIDTH=10;
     int PADDLE_HEIGHT=150;
     int BALL_WIDTH=40;
     int BALL_HEIGHT=40;
     int Score=0;
     boolean ThreadRunning;
     boolean GameOver;
     boolean isRunning;
     int PANEL_WIDTH=1200;
     int PANEL_HEIGHT=600;
     BufferedImage Background;
     BufferedImage Ball;
     BufferedImage PingPong;
     JButton erneut;
     JButton Previous;
     int res;
     Font font;
     boolean BallMove;
    Graphics(){
        
        this.setVisible(true);
        ThreadRunning=true;
         erneut=new JButton("Erneut Starten");
         erneut.setBounds(1020, 530, 150, 50);
         this.add(erneut);
         erneut.addActionListener(this);
         erneut.setEnabled(true);
         
         Previous=new JButton("Menu");
         Previous.setBounds(50, 530, 150, 50);
         this.add(Previous);
         Previous.addActionListener(this);
         Previous.setEnabled(true);
      
         
         this.setLayout(null);

        addKeyListener(this );
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        //this.setBackground(Color.black);
        this.setFocusable(true);
        try{
           Background=ImageIO.read(new File("Background.png"));
            Ball=ImageIO.read(new File("Ball.png"));
            PingPong=ImageIO.read(new File("PingPong.png"));
        }
        catch(IOException ex){System.err.print("not found");            
        }
        start();

    }
    public void run() {
        while(ThreadRunning){
            move();
            BallMove();
            ckeckCollision();
            repaint();
                   
            try{
            Thread.sleep(10);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }

    }
     public void paintComponent(java.awt.Graphics g){
if(isRunning)
{       requestFocus();
        super.paintComponent(g);
        g.drawImage(Background, 0, 0,null);
        g.drawImage(Ball, posXB, posYB,null);
        g.drawImage(PingPong, posXP, posYP,null);
        font=new Font("Arial",Font.BOLD,20);
        g.setFont(font);
        g.drawString("Score", 880, 550);
        g.drawString(""+Score, 980, 550);
        
         font=new Font("Arial",Font.BOLD,20);
        g.setFont(font);
        g.drawString("Level", 880, 575);
        g.drawString(""+Level, 980, 575);
        

}

    }
     public void start(){

         isRunning=true;
         BallMove=true;
         yp=0;
         Level=1;

     }
     public void move(){


         if(posYP+yp+PADDLE_HEIGHT>PANEL_HEIGHT){
             yp=0;
         }
         if(posYP+yp<=0){
             yp=0;
         }
                   posYP+=yp;

     }
public void addNotify(){

    t=new Thread(this);
    super.addNotify();
    t.start();

}
    

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(yp==0){
            yp=yp0;
        }

       if(e.getKeyCode()==KeyEvent.VK_UP){
            yp=-yp;
            if(yp>0){
                           yp=-yp;
    }
        }
       if(e.getKeyCode()==KeyEvent.VK_DOWN){
            yp=+yp;
            
        }

    }
    

    @Override
    public void keyReleased(KeyEvent e) {
        yp=0;
//                       if(e.getKeyCode()==KeyEvent.VK_UP){
//                           yp=-yp;
//    }
                      
    }
    public void BallMove(){
      if(BallMove)  {
        posXB+=BALL_SPEED_X;
        posYB+=BALL_SPEED_Y;}
    }
    
    
   public void ckeckCollision(){
      
        if(posXB+BALL_WIDTH>=PANEL_WIDTH ){ // Right Co
            BALL_SPEED_X=-BALL_SPEED_X;
        }
        if(posYB+BALL_HEIGHT>=PANEL_HEIGHT||posYB<=0){ //Up & Down Co
         BALL_SPEED_Y=-BALL_SPEED_Y;
        }
        if((posXB<=PADDLE_WIDTH) && ((posYB-BALL_HEIGHT+40)<=(posYP+PADDLE_HEIGHT)) && (posYB>=posYP-40)){ 
          //  && posYB>=posYP
                        BALL_SPEED_X=-BALL_SPEED_X;
                        Score+=10;
                        if(Score>10){
                         BALL_SPEED_X=8;
                         BALL_SPEED_Y=8;
                         yp=8;
                         Level=2;
                        }
                        if(Score>80){
                         BALL_SPEED_X=9;
                         BALL_SPEED_Y=9;
                         yp=9;
                         Level=3;
                        }
                        if(Score>120){
                         BALL_SPEED_X=10;
                         BALL_SPEED_Y=10;
                         yp=10;
                         Level=4;
                        }
                        if(Score>160){
                         BALL_SPEED_X=11;
                         BALL_SPEED_Y=11;
                         yp=11;
                         Level=5;
                        }
                        if(Score>200){
                         BALL_SPEED_X=12;
                         BALL_SPEED_Y=12;
                         yp=12;
                         Level=6;
                        }

        }
        if(posXB<0){  
            resetGame();             
                                  
        }
   }
        public void resetGame(){
            
            //JOptionPane.showMessageDialog(null, "Hey");
          // res= JOptionPane.showInternalConfirmDialog(null,"END...Score= "+Score,"Ergebnis");
            res= JOptionPane.showInternalConfirmDialog(null, "END...Score= "+Score, "Ergebnis",0 );
            //res= JOptionPane.show
            if(res==JOptionPane.YES_OPTION){
                           Score=0;
        posXB=1000;
        posYB=50;
        BALL_SPEED_X=5;
        BALL_SPEED_Y=5;
        yp=8;
        start();
        //repaint();
            }
        
   }

    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getSource()==erneut){
    BallMove=false;
    posXB=500;
    posYB=300;
resetGame();
}
    if(e.getSource()==Previous){
this.setVisible(false);
    ThreadRunning=false;
Game.showMenu();
}
    }  
   
    
   
}
