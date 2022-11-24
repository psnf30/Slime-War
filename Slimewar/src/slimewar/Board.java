package slimewar;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Board extends JPanel{
    
    int score;
    
    ImageIcon titleScreen = new ImageIcon(this.getClass().getResource("titleScreen.gif"));
    ImageIcon Gameover = new ImageIcon(this.getClass().getResource("gameover.gif"));
    ImageIcon Win = new ImageIcon(this.getClass().getResource("win.gif"));
    ImageIcon red_slime_right = new ImageIcon(this.getClass().getResource("slime4-1.png"));
    ImageIcon red_slime_left = new ImageIcon(this.getClass().getResource("slime4-2.png"));
    ImageIcon [] G_red = {red_slime_left,red_slime_right};

    ImageIcon yellow_slime_right = new ImageIcon(this.getClass().getResource("slime3-2.png"));
    ImageIcon yellow_slime_left = new ImageIcon(this.getClass().getResource("slime3-1.png"));
    ImageIcon[] G_yellow = {yellow_slime_left,yellow_slime_right};
    ImageIcon blue_slime_right = new ImageIcon(this.getClass().getResource("slime2-1.png"));
    ImageIcon blue_slime_left = new ImageIcon(this.getClass().getResource("slime2-2.png"));
    ImageIcon[] G_blue = {blue_slime_left,blue_slime_right};

    ImageIcon pink_slime_right = new ImageIcon(this.getClass().getResource("slime1-1.png"));
    ImageIcon pink_slime_left = new ImageIcon(this.getClass().getResource("slime1-2.png"));
    ImageIcon[] G_pink = {pink_slime_left,pink_slime_right};
    
    //Pacman
    ImageIcon Army_left = new ImageIcon(this.getClass().getResource("amryleft.png"));
    ImageIcon Army_right = new ImageIcon(this.getClass().getResource("armyright.png"));
    ImageIcon Army_up = new ImageIcon(this.getClass().getResource("armyright.png"));
    ImageIcon Army_down = new ImageIcon(this.getClass().getResource("armyright.png"));
    ImageIcon [] Army_images  = {Army_left,Army_right,Army_up,Army_down};
    
    ImageIcon blood = new ImageIcon(this.getClass().getResource("blood.png"));

    Army army = new  Army(10*Component.cellSize,15*Component.cellSize);
    
    //slimes
    Slime slime1 = new Slime(10*Component.cellSize,8*Component.cellSize);
    Slime slime2 = new Slime(10*Component.cellSize,9*Component.cellSize);
    Slime slime3 = new Slime(11*Component.cellSize,9*Component.cellSize);
    Slime slime4 = new Slime(9*Component.cellSize,9*Component.cellSize);
            
    boolean title ;
    boolean balls[][];
    boolean states[][];
    int lives = 10;
        
    public Board() {
        title = true;
        balls = new boolean[Component.cellSize][Component.cellSize];
        states = new boolean[Component.cellSize][Component.cellSize];
        unit();
    }
    public void unit(){
        for (int i = 0; i < Component.cellSize; i++) {
            for (int j = 0; j < Component.cellSize; j++) {
                balls[i][j]=true;
                states[i][j]=true;
            }
            //เว้นช่องว่างไว้ spawn ผีกับตัวเหลือง
            balls[10][8]= false;
            balls[10][9]= false;
            balls[11][9]= false;
            balls[9][9]= false;
            balls[10][15]= false;
            
        }
    }
    
    //กำหนดให้โชว์เลือดเป็นรูป Pacman
    public void drawLives(Graphics g){
        
        g.setColor(Color.yellow);
            for (int i = 0; i < lives; i++) {
                g.drawImage(blood.getImage(),(Component.cellSize+5) * i+15, Component.max+10, Component.cellSize, Component.cellSize,null);
                
            }
    }
    // สร้างลูกบอลถ้าเจอตำแหน่งที่บล็อคสีฟ้าอยู่จะลบทิ้ง
    public void update(Graphics g, int x , int y, int width, int height){
        g.fillRect(x, y, width, height);
        
        for (int i = x/20; i < x/20 + width/20; i++) {
            for (int j = y/20; j < y/20 + height/20; j++) {
                balls[i][j] = false;
                states[i-1][j-1] = false;
            }
        }
    }
    //สร้างลูกบอลและกำหนดที่ที่ลูกบอลอยู่
    public void drawBalls(Graphics g){
        g.setColor(Color.green);
        for (int i = 1; i < Component.cellSize; i++) {
            for (int j = 1; j < Component.cellSize; j++) {
                if(balls[i][j]){
                    g.fillOval(i*20+8, j*20+8, 4, 4);
                }
            }
        }
        
    }
    
    public void reset(){
        if(lives > 0){
            lives--;
            slime1.x=10*Component.cellSize;
            slime1.y=8*Component.cellSize;
            
            slime2.x=10*Component.cellSize;
            slime2.y=9*Component.cellSize;
            
            slime3.x=11*Component.cellSize;
            slime3.y=9*Component.cellSize;
            
            slime4.x=9*Component.cellSize;
            slime4.y=9*Component.cellSize;
               
            army.x =10*Component.cellSize;
            army.y = 15*Component.cellSize;
            
            Game.flag = true;
        }
        
    }
    
    //วาดรูปตารางที่ให้ตัว Pacman เดิน
    public void drawBoard(Graphics g){
        
        g.setColor(Color.white);
        g.drawRect(19, 19, 382, 382);
        
        //สร้างบล็อคสีฟ้า
        g.setColor(Color.pink);
        update(g,40,40,60,20);
        update(g,120,40,60,20);
        update(g,200,20,20,40);
        update(g,240,40,60,20);
        update(g,320,40,60,20);
        update(g,40,80,60,20);
        update(g,160,80,100,20);
        update(g,200,80,20,60);
        update(g,320,80,60,20);
        update(g,20,120,80,60);
        update(g,320,120,80,60);
        update(g,20,200,80,60);
        update(g,320,200,80,60);
        update(g,160,160,40,20);
        update(g,220,160,40,20);
        update(g,160,180,20,20);
        update(g,160,200,100,20);
        update(g,240,180,20,20);
        update(g,120,120,60,20);
        update(g,120,80,20,100);
        update(g,280,80,20,100);
        update(g,240,120,60,20);
        update(g,280,200,20,60);
        update(g,120,200,20,60);
        update(g,160,240,100,20);
        update(g,200,260,20,40);
        update(g,120,280,60,20);
        update(g,240,280,60,20);
        update(g,40,280,60,20);
        update(g,80,280,20,60);
        update(g,320,280,60,20);
        update(g,320,280,20,60);
        update(g,360,320,40,20);
        update(g,20,320,40,20); 
        update(g,160,320,100,20);
        update(g,200,320,20,60);
        update(g,40,360,140,20);
        update(g,240,360,140,20);
        update(g,280,320,20,60);
        update(g,120,320,20,60);
        
        repaint();
        
        
    }
    
    /* (non-Javadoc)
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics g){  
        g.setColor(Color.black);
        g.fillRect(0,0,420,500);
        
        drawBoard(g);
        drawBalls(g);
        drawLives(g);
        //ปริ้นสกอร์
        Font f=new Font("Cursive",Font.BOLD,30);
        g.setFont(f);
        g.drawString("score : "+score, Component.max/2+50,Component.max+30);
        //สร้างผีไปไว้ำตำแหน่งที่กำหนด
         g.drawImage(G_red[slime1.index].getImage(), slime1.x,slime1.y, null); 
         g.drawImage(G_yellow[slime2.index].getImage(), slime2.x,slime2.y, null);
         g.drawImage(G_pink[slime3.index].getImage(), slime3.x,slime3.y, null);
         g.drawImage(G_blue[slime4.index].getImage(), slime4.x,slime4.y, null);
        
        g.drawImage(Army_images[army.index].getImage(), army.x,army.y,null);
             
        if(title){
            g.drawImage(titleScreen.getImage(), 0, 0, null);
        }
        if(lives ==0){
            g.drawImage(Gameover.getImage(), 0, 0, null);
        }
        if(check()){
            g.drawImage(Win.getImage(), 0, 0, null);
        }
    }
    public boolean check(){
        for (int i = 1; i < Component.cellSize; i++) {
            for (int j = 1; j < Component.cellSize; j++) {
                if(balls[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}
    

