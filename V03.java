import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.swing.JPanel;


public class V03 extends JPanel
{
	WholeLane lane1;
	Part p;
	GUILane gl;
	GUIPart gp1;
	GUIPart gp2;
	
	int paintCount = 0;
	
	public V03()
	{
		//panel size
		this.setPreferredSize(new Dimension(800,400));	
		
		//load images
		Painter.loadImages();
		
		//initialize
		lane1 = new WholeLane();
		gl = new GUILane( lane1.getComboLane(), 5, 190,10 );
		
		p = new Part("p", "a random part", 5);
		//gp1 = new GUIPart(p, Painter.ImageEnum.CORNFLAKE, 490, 10);
		//gp1.movement = new Movement(gp1.movement.calcPos(System.currentTimeMillis()), 0, System.currentTimeMillis(), new Point2D.Double(190,10), 10, System.currentTimeMillis()+5000);
	}
	
	public void paint(Graphics gfx)
	{
		Graphics2D g = (Graphics2D)gfx;
		
		if (paintCount % 100 == 0) {
			gp1 = new GUIPart(p, Painter.ImageEnum.CORNFLAKE, 490, 20);
			gp1.movement = new Movement(gp1.movement.calcPos(System.currentTimeMillis()), 0, System.currentTimeMillis(), new Point2D.Double(190,20), 10, System.currentTimeMillis()+5000);
		}
		
		if (paintCount % 100 == 50) {
			gp2 = new GUIPart(p, Painter.ImageEnum.CORNFLAKE, 490, 20);
			gp2.movement = new Movement(gp2.movement.calcPos(System.currentTimeMillis()), 0, System.currentTimeMillis(), new Point2D.Double(190,20), 10, System.currentTimeMillis()+5000);
		}
		
		gl.draw(g, System.currentTimeMillis());
		
		if( gp1 != null )
			gp1.draw(g, System.currentTimeMillis());
		
		if(gp2 != null)
			gp2.draw(g, System.currentTimeMillis());
		
		
//		if (paintCount % 100 == 75)
//			if ( lane1.areLanesOn() )
//				lane1.turnOffLane();
//			else
//				lane1.turnOnLane();
		
		
		paintCount++;
	}
	
}
