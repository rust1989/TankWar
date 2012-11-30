package com.rust;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Tank {
	private static final int speed=5;
	private static final int tankwidth=50;
	private static final int tankheight=50;
	private boolean up=false,down=false,left=false,right=false;
    public enum direction {up,down,left,right,lu,ru,ld,rd,stop};
	private int x,y;
	private direction btdir=direction.right;
	
	private int num,bnum;
	
	private boolean live=true;
	
	direction dir=direction.stop;
	TankClient tc;
	private boolean bGood;
	
    public boolean isbGood() {
		return bGood;
	}
	public void setbGood(boolean bGood) {
		this.bGood = bGood;
	}
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}
    public Tank(int x,int y,boolean bGood,TankClient tc){
    	this(x,y);
    	this.tc=tc;
    	this.bGood=bGood;
    }
    public void paint(Graphics g){
    	if(!live) return;
    	Color c=g.getColor();
    	if(bGood==true){
		g.setColor(Color.RED);
    	}else if(bGood==false){
    	g.setColor(Color.BLACK);
    	}
		g.fillOval(x, y, tankwidth, tankheight);
		g.setColor(Color.BLUE);
     switch(btdir){
    	
    	case stop:
    	break;
    	case left:
            g.drawLine(x+tankwidth/2, y+tankheight/2, x-5, y+tankheight/2);
    	break;
    	case right:
    		g.drawLine(x+tankwidth/2, y+tankheight/2, x+tankwidth+5, y+tankheight/2);
        break;
    	case down:
    		g.drawLine(x+tankwidth/2, y+tankheight/2, x+tankwidth/2, y+tankheight+5);
    	break;
    	case up:
    		g.drawLine(x+tankwidth/2, y+tankheight/2, x+tankwidth/2, y-5);
    	break;
    	case lu:
    		g.drawLine(x+tankwidth/2, y+tankheight/2, x, y);
        break;
    	case ld:
    		g.drawLine(x+tankwidth/2, y+tankheight/2, x, y+tankheight);
    	break;
    	case ru:
    		g.drawLine(x+tankwidth/2, y+tankheight/2, x+tankwidth, y);
    	break;
    	case rd:
    		g.drawLine(x+tankwidth/2, y+tankheight/2, x+tankwidth, y+tankheight);
    	break;
    	}
		
		g.setColor(c);
	
		move();
    }
    public void move(){
    	switch(dir){
    	
    	case stop:
    	break;
    	case left:
    	x-=speed;
    	break;
    	case right:
    	x+=speed;
        break;
    	case down:
    	y+=speed;
    	break;
    	case up:
    	y-=speed;
    	break;
    	case lu:
    	x-=speed;
    	y-=speed;
        break;
    	case ld:
    	x-=speed;
    	y+=speed;
    	break;
    	case ru:
    	x+=speed;
    	y-=speed;
    	break;
    	case rd:
    	x+=speed;
    	y+=speed;
    	break;
    	}
    }
    public void fire(){
    	Bullet bl=new Bullet(x+20, y+20, btdir,tc);
    	tc.bullets.add(bl);
    }
    public void keyPressed(KeyEvent e){
    	
    	switch(e.getKeyCode()){
    	
		case KeyEvent.VK_DOWN:
			down=true;
		break;
		case KeyEvent.VK_UP:
			up=true;
		break;
		case KeyEvent.VK_LEFT:
			left=true;
		break;
		case KeyEvent.VK_RIGHT:
			right=true;
		break;		
		}
    	
    	direction();
    	if(dir!=direction.stop)
       	 btdir=dir;
    	
    }
    public void keyReleased(KeyEvent e){
    	switch(e.getKeyCode()){
    	case KeyEvent.VK_DOWN:
			down=false;
		break;
		case KeyEvent.VK_UP:
			up=false;
		break;
		case KeyEvent.VK_LEFT:
			left=false;
		break;
		case KeyEvent.VK_RIGHT:
			right=false;
		break;
    	}
    	direction();
    	fire();
    }
    public void direction(){
    	if(left&&!right&&!down&&!up) dir=direction.left;
    	if(!left&&right&&!down&&!up) dir=direction.right;
    	if(!left&&!right&&!down&&up) dir=direction.up;
    	if(!left&&!right&&down&&!up) dir=direction.down;
    	if(left&&!right&&!down&&up) dir=direction.lu;
    	if(left&&!right&&down&&!up) dir=direction.ld;
    	if(!left&&right&&!down&&up) dir=direction.ru;
    	if(!left&&right&&down&&!up) dir=direction.rd;
    	if(!left&&!right&&!down&&!up) dir=direction.stop;
    	
    }
    public Rectangle getRec(){
    	return new Rectangle(x,y,tankwidth,tankheight);
    }
	public void setLive(boolean live) {
		this.live = live;
	}
	public boolean isLive() {
		return live;
	}
    
}
