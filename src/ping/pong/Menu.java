/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ping.pong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Mahdiyar
 */
public class Menu extends JPanel implements ActionListener {
    JButton Playbtn;
    JButton Contact;
    JButton Exitbtn;
    JButton Previous;
    boolean ContactFlag;
    int resE;
    BufferedImage Menu;
    BufferedImage ContactImage;
    Menu() {

        this.setPreferredSize(new Dimension(1200, 600));
        CreateBtn();
        showbtn(true);
               try {
            Menu = ImageIO.read(new File("MENU.jpg"));
            ContactImage = ImageIO.read(new File("CONTACT01.jpg"));
        } catch (IOException ex) {
            System.err.print("not found");
        }
    }
    public void CreateBtn() {

        this.setBackground(Color.red);
        Playbtn = new JButton(new ImageIcon("PLAY.jpg"));
        Contact = new JButton(new ImageIcon("Contact.jpg"));
        Exitbtn = new JButton(new ImageIcon("Exit.jpg"));
        Previous = new JButton("Previous");


        Playbtn.setBounds(475, 130, 250, 60);
        Contact.setBounds(475, 255, 250, 60);
        Exitbtn.setBounds(475, 380, 250, 60);
        Previous.setBounds(30, 530, 120, 50);

        Playbtn.addActionListener(this);
        Contact.addActionListener(this);
        Previous.addActionListener(this);
        Exitbtn.addActionListener(this);

        this.add(Playbtn);
        this.add(Contact);
        this.add(Exitbtn);
        this.add(Previous);
        this.setLayout(null);
        this.setVisible(true);

    }

    public void showbtn(boolean bln) {
        Playbtn.setVisible(bln);
        Contact.setVisible(bln);
        Exitbtn.setVisible(bln);
    }

    public void paintComponent(java.awt.Graphics g) {

        requestFocus();
        super.paintComponent(g);
        g.drawImage(Menu, 0, 0, null);
        if (ContactFlag) {
            g.drawImage(ContactImage, 0, 0, null);
            showbtn(false);

        }
           }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Playbtn) {
            this.setVisible(false);
            Game.showGame();
        }
        if (e.getSource() == Contact) {

            ContactFlag = true;
            repaint();

        }
        if (e.getSource() == Previous) {
            if (ContactFlag) {

                ContactFlag = false;
                showbtn(true);
                repaint();
            } else {

                this.setVisible(false);
                Game.showWelcome();
            }

        }
        if (e.getSource() == Exitbtn) {
            resE = JOptionPane.showInternalConfirmDialog(null, "Bist du dir sicher, dass du mit dem Spiel aufhören willst?", "Exit", 0);
            if (resE == JOptionPane.YES_OPTION) {
                System.exit(0);
            } else {
                this.setVisible(false);
                Game.showWelcome();
            }
        }
    }
}
class Welcome extends JPanel implements ActionListener, KeyListener {
    BufferedImage welcome;
    BufferedImage Background;
    boolean wVisible;
    JButton wNextBtn;
    Welcome() {
        wVisible = true;
        wNextBtn = new JButton("Next");
        this.setPreferredSize(new Dimension(1200, 600));
        this.add(wNextBtn);

        wNextBtn.setBounds(1060, 530, 120, 50);
        wNextBtn.setEnabled(true);
        wNextBtn.addActionListener(this);
        this.setLayout(null);
        setVisible(true);
        try {
            welcome = ImageIO.read(new File("Welcome.jpg"));
            Background = ImageIO.read(new File("Background.png"));
        } catch (IOException ex) {
            System.err.print("not found");
        }
    }
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        if (wVisible)
            g.drawImage(welcome, 0, 0, null);
        if (!wVisible) {
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == wNextBtn) {
            this.setVisible(false);
            Game.showMenu();
            wVisible = false;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}