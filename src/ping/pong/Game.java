/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ping.pong;
import javax.swing.*;

/**
 * @author Mahdiyar
 */
public class Game extends JFrame {
     static Graphics g=new Graphics();
     static Menu menu;
     static Welcome welcome=new Welcome();
     static JPanel centerPanel=new JPanel();
     static Welcome welcome01;
     ImageIcon Icon;
public void GamePanel(){
   }
    Game(){
        centerPanel.add(welcome);
        this.add(centerPanel);
        this.setTitle("Ping Pong");
        this.setUndecorated(true);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
          }
    static void showGame(){
        g=new Graphics();
              centerPanel.add(g);

    }
    static void showMenu(){
        menu=new Menu();
              centerPanel.add(menu);
              System.out.println("Show MENU");


    }
    static void showWelcome(){
        welcome01=new Welcome();
        centerPanel.add(welcome01);
System.out.println("showWelcome");
    }

   
  
    
}
