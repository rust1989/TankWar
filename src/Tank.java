import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.naming.directory.DirContext;


public class Tank {
   private  int x;
   private  int y;
   private static final int speed=5;
   private static final int Width=50;
   private static final int Height=50;
   
   public enum Direction{l,r,u,d,lu,ld,ru,rd,stop};
   private boolean left=false,down=false,up=false,right=false;
   
   Direction dir=Direction.stop;
   
   TankClient tc;
   
   public Tank(int x, int y) {
	this.x = x;
	this.y = y;
   }
   public Tank(int x,int y,TankClient tc){
	   this(x,y);
	   this.tc=tc;
   }
   public void paint(Graphics g) {
		Color c=g.getColor();
		g.setColor(Color.red);
		g.fillOval(x, y, Width, Height);
		g.setColor(Color.BLUE);
		switch(dir){
		case l:
			g.drawLine(x, Height/2, 0, Height/2);
		break;
		case r:
			g.drawLine(x, Height/2, Width+5, Height/2);
			break;
		case ld:
			g.drawLine(x, Height/2, 0,Height);
			break;
		case lu:
			g.drawLine(x,Height/2,0,0);
			break;
		case rd:
			g.drawLine(x,Height/2,Width,Height);
			break;
		case ru:
			g.drawLine(Width/2, Height/2, Width, 0);
			break;
		case d:
			g.drawLine(Width/2,Height/2,Width/2,Height);
			break;
		case u:
			g.drawLine(Width/2, Height/2, Width/2,0);
			break;
				
		}
		g.setColor(c);
		
	}
   public void move(){
	   System.out.println(dir);
	   switch(dir){
	   case d:
		   y+=speed;
	   break;
	   case u:
		  y-=speed;   
	   break;
	   case l:
		x-=speed;   
	   break;
	   case r:
	     x+=speed;
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
	   case stop:
	  break;
	   }
	   
   }
   public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
		left=true;
		break;
		case KeyEvent.VK_RIGHT:
		 right=true;
		 break;
		case KeyEvent.VK_UP:
		 up=true;
		 break;
		case KeyEvent.VK_DOWN:
		  down=true;
		 break;
		}
		direction();
		move();
	}
   public void keyReleased(KeyEvent e){
	   switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
		left=false;
		break;
		case KeyEvent.VK_RIGHT:
		 right=false;
		 break;
		case KeyEvent.VK_UP:
		 up=false;
		 break;
		case KeyEvent.VK_DOWN:
		  down=false;
		 break;
		}
	   direction();
   }
   public void direction(){
	  if(!up&&!down&&!left&&!right) dir=Direction.stop ;
	  if(up&&!down&&!left&&!right) dir=Direction.u ;
	  if(!up&&down&&!left&&!right) dir=Direction.d;
	  if(!up&&!down&&left&&!right) dir=Direction.l;
	  if(!up&&!down&&!left&&right) dir=Direction.r;
	  if(up&&!down&&left&&!right) dir=Direction.lu;
	  if(up&&!down&&!left&&right) dir=Direction.ru;
	  if(!up&&down&&left&&!right) dir=Direction.ld;
	  if(!up&&down&&!left&&right) dir=Direction.rd; 
   }
}
