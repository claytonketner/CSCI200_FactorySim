import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.swing.JPanel;


public class V01 extends JPanel
{
	GUIPart gp;
	
	public V01()
	{
		this.setPreferredSize(new Dimension(400,600));
		Part p = new Part("p1", "a random part", 5);
		gp = new GUIPart(p, GUIPart.RAISIN, 100, 100);
		gp.movement = new Movement(new Point2D.Double(100,100), 0, System.currentTimeMillis(), new Point2D.Double(200,200), 10, System.currentTimeMillis()+5000);
	}
	
	public void paint(Graphics g)
	{
		gp.draw((Graphics2D)g, System.currentTimeMillis());
	}
}
