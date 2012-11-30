package com.rust;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class TankClient extends Frame {
	
	

	private static final int Game_With=500;
	private static final int Game_Height=300;
	private static final int Game_X=200;
	private static final int Game_Y=200;
	
	
	ArrayList<Bullet> bullets=new ArrayList<Bullet>();
	
	ArrayList<Tank>  tanks=new ArrayList<Tank>();
	
	ArrayList<Explode> exs=new ArrayList<Explode>();
	
	Tank mytank=new Tank(50, 50,true,this);
	
    public void localFrame(){
    	this.setLocation(Game_X, Game_Y);
    	this.setSize(Game_With,Game_Height);
    	this.setBackground(Color.green);
    	this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
    	this.setResizable(false);
    	this.setVisible(true);
    	this.addKeyListener(new KeyMonitor());
    	new Thread(new PaintThread()).start();
    	
    }
    public void paint(Graphics g) {
    	for(int j=0;j<tanks.size();j++){
    	   tanks.get(j).paint(g);	
		}
    	for(int i=0;i<bullets.size();i++){
	        bullets.get(i).paint(g);
		   if(bullets.get(i).hitTank(tanks)){
		    bullets.remove(i);
		    }
		}
    	for(int i=0;i<exs.size();i++){
	      exs.get(i).paint(g);
		}
	}
	public static void main(String[] args) {
		TankClient tc=new TankClient();
		tc.localFrame();
		for(int i=0;i<5;i++){
			tc.tanks.add(new Tank((int)(Game_With*Math.random()), (int)(Game_Height*Math.random()),false,tc));
		}
		tc.tanks.add(tc.mytank);
	}
    class PaintThread implements Runnable {
		public void run() {
			while(true){
				repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}	
    class KeyMonitor extends KeyAdapter{

		public void keyPressed(KeyEvent e) {
			mytank.keyPressed(e);
			
		}

		public void keyReleased(KeyEvent e) {
			mytank.keyReleased(e);
		}
    	
    }
}
