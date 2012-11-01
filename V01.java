import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class V01 extends JPanel
{
	GUIPart gp;
	
	public V01()
	{
		Part p = new Part("p1", "a random part", 5);
		gp = new GUIPart(p, GUIPart.RAISIN, 100, 100);
	}
	
	public void paint(Graphics g)
	{
		gp.draw((Graphics2D)g, System.currentTimeMillis());
	}
}
