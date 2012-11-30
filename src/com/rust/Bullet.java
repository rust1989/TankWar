package com.rust;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Bullet {
  
	private int x,y;
	private static final int speed=10;
	private static final int width=10,height=10;
	private boolean live=true;
	
     Tank.direction dir;
     TankClient tc;
    public Bullet(int x, int y, Tank.direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
    public Bullet(int x,int y,Tank.direction dir,TankClient tc){
    	this(x,y,dir);
    	this.tc=tc;
    }
    
    
    public void paint(Graphics g){
    	if(!live) return;
    	Color c=g.getColor();
    	g.setColor(Color.BLUE);
    	g.fillOval(x, y, width, height);
    	g.setColor(c);
    	move();
    }
    public boolean hitTank(ArrayList<Tank> t){
    	for(int i=0;i<t.size();i++){
	    	if(!t.get(i).isbGood()){
		    	if(this.getRec().intersects(t.get(i).getRec())&& t.get(i).isLive()){
		    	t.get(i).setLive(false);
		    	this.setLive(false);
		        Explode ex=new Explode(x, y,tc);
		        tc.exs.add(ex);
		    	return true ;
		    	}
	    	}
    	}
    	return false;
    }
    public Rectangle getRec(){
    	return new Rectangle(x,y,width,height);
    }
    public void move(){
    	switch(dir){
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
	public void setLive(boolean live) {
		this.live = live;
	}
	public boolean isLive() {
		return live;
	}
    
}
