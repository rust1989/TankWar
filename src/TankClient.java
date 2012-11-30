import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.sun.corba.se.impl.orbutil.closure.Constant;

public class TankClient extends Frame  {
	
    public   void LocalFrame(){
    	this.setSize(600,400);
    	this.setLocation(200,200);
    	this.setResizable(false);
    	this.setBackground(Color.green);
    	this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
    		
		});
    	this.addKeyListener(new KeyMonitor());
    	this.setVisible(true);
    	new Thread(new PaintThread()).start();
    }
    int x=50;
    int y=50;
	Tank mytank=new Tank(x, y,this);
	Bullet bullets=new Bullet(70, 70,this);
	public void paint(Graphics g) {
		mytank.paint(g);
		bullets.draw(g);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       TankClient tc=new TankClient();
       tc.LocalFrame();
	}
	public  class PaintThread implements Runnable{

		
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
	public class KeyMonitor extends KeyAdapter{

		
		public void keyPressed(KeyEvent e) {
			mytank.keyPressed(e);
			
		}

		
		public void keyReleased(KeyEvent e) {
			mytank.keyReleased(e);
		}
		

	}

}
