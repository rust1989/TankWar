import java.awt.Color;
import java.awt.Graphics;


public class Bullet {
   private int x;
   private int y;
   TankClient tc;
   private static final int width=10;
   private static final int height=10;
   private static final int speed=100;
	public Bullet(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public Bullet(int x,int y,TankClient tc){
		this(x,y);
		this.tc=tc;
	}  
	public void draw(Graphics g){
		Color c=g.getColor();
		g.setColor(Color.BLACK);
		g.fillOval(x,y,width, height);
		g.setColor(c);
	}
   
}
