package com.rust;

import java.awt.Color;
import java.awt.Graphics;


public class Explode {
    private int x;
    private int y;
    private  boolean isLive=true;
    
    Integer wid[]={1,5,20,25,20,5,1};
    int step=0;
    TankClient tc;
	public Explode(int x, int y,TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc=tc;
	}
	public void paint(Graphics g){
		if(!isLive) return;
		
		if(step==wid.length){
			isLive=false;
			step=0;
			return ;
		}
		
		Color c=g.getColor();
		g.setColor(Color.YELLOW);
		g.fillOval(x, y, wid[step], wid[step]);
		g.setColor(c);
		step++;
	}
	
    
}
