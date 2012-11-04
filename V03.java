import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.swing.JPanel;
//TODO: fix diverter arm being blocked by diverter platform
//TODO: dumping bin into feeder

@SuppressWarnings("serial")
public class V03 extends JPanel
{
	WholeLane lane1;
	Part p;
	GUILane gl;
	GUIPart gp1;
	GUIPart gp2;
	GUIFeeder gf;
	GUIDiverter gd;
	GUIDiverterArm gda;
	
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
		gf = new GUIFeeder( lane1.getFeeder(), 570, 10 );
		gd = new GUIDiverter( 490, 12 );
		gda = new GUIDiverterArm( 490, 55 );
		gda.movement = new Movement(new Point2D.Double(490,55), 0, System.currentTimeMillis(), new Point2D.Double(490,55), 0.7, System.currentTimeMillis()+1);
		p = new Part("p", "a random part", 5);
	}
	
	public void paint(Graphics gfx)
	{
		Graphics2D g = (Graphics2D)gfx;
		
		
		if( lane1.getLane() == 1 ){ //if top lane
			//top lane moving parts
			if (paintCount % 200 == 0 || paintCount % 200 == 100 ) { //from diverter moving to lane
				gp1 = new GUIPart(p, Painter.ImageEnum.CORNFLAKE, 560, 45);
				gp1.movement = Movement.fromSpeed(gp1.movement.calcPos(System.currentTimeMillis()), 0, System.currentTimeMillis(), new Point2D.Double(490,20), 0, lane1.getSpeed());
			}
			
			if (paintCount % 200 == 20 || paintCount % 200 == 120 ) { //moving on lane
				gp1 = new GUIPart(p, Painter.ImageEnum.CORNFLAKE, 490, 20);
				gp1.movement = Movement.fromSpeed(gp1.movement.calcPos(System.currentTimeMillis()), 0, System.currentTimeMillis(), new Point2D.Double(190,20), 0, lane1.getSpeed());
			}
			
			if (paintCount % 200 == 50 || paintCount % 200 == 150 ) { //from diverter moving to lane
				gp2 = new GUIPart(p, Painter.ImageEnum.CORNFLAKE, 560, 45);
				gp2.movement = Movement.fromSpeed(gp2.movement.calcPos(System.currentTimeMillis()), 0, System.currentTimeMillis(), new Point2D.Double(490,20), 0, lane1.getSpeed());
			}
			
			if (paintCount % 200 == 70 || paintCount % 200 == 170 ) { //moving on lane
				gp2 = new GUIPart(p, Painter.ImageEnum.CORNFLAKE, 490, 20);
				gp2.movement = Movement.fromSpeed(gp2.movement.calcPos(System.currentTimeMillis()), 0, System.currentTimeMillis(), new Point2D.Double(190,20), 0, lane1.getSpeed());
			}
			
		} else { //bottom lane
			//bot lane moving parts
			if (paintCount % 200 == 0 || paintCount % 200 == 100 ) { //from diverter moving to lane
				gp1 = new GUIPart(p, Painter.ImageEnum.CORNFLAKE, 560, 45);
				gp1.movement = Movement.fromSpeed(gp1.movement.calcPos(System.currentTimeMillis()), 0, System.currentTimeMillis(), new Point2D.Double(490,70), 0, lane1.getSpeed());
			}
			
			if (paintCount % 200 == 20 || paintCount % 200 == 120 ) { //moving on lane
				gp1 = new GUIPart(p, Painter.ImageEnum.CORNFLAKE, 490, 70);
				gp1.movement = Movement.fromSpeed(gp1.movement.calcPos(System.currentTimeMillis()), 0, System.currentTimeMillis(), new Point2D.Double(190,70), 0, lane1.getSpeed());
			}
			
			if (paintCount % 200 == 50 || paintCount % 200 == 150 ) { //from diverter moving to lane
				gp2 = new GUIPart(p, Painter.ImageEnum.CORNFLAKE, 560, 45);
				gp2.movement = Movement.fromSpeed(gp2.movement.calcPos(System.currentTimeMillis()), 0, System.currentTimeMillis(), new Point2D.Double(490,70), 0, lane1.getSpeed());
			}
			
			if (paintCount % 200 == 70 || paintCount % 200 == 170 ) { //moving on lane
				gp2 = new GUIPart(p, Painter.ImageEnum.CORNFLAKE, 490, 70);
				gp2.movement = Movement.fromSpeed(gp2.movement.calcPos(System.currentTimeMillis()), 0, System.currentTimeMillis(), new Point2D.Double(190,70), 0, lane1.getSpeed());
			}
		}
		
		//change lane every 85, 185
		if( paintCount % 200 == 85 || paintCount % 200 == 185){
			lane1.divert();
			if(lane1.getLane() == 1) { //arm rotates from bot to top
				gda.movement = new Movement(new Point2D.Double(490,55), -0.7, System.currentTimeMillis(), new Point2D.Double(490,55), 0.7, System.currentTimeMillis()+500);
			} else { //arm rotates from top to bot
				gda.movement = new Movement(new Point2D.Double(490,55), 0.7, System.currentTimeMillis(), new Point2D.Double(490,55), -0.7, System.currentTimeMillis()+500);
			}
		}
		
		gl.draw(g, System.currentTimeMillis());
		
		gf.draw(g, System.currentTimeMillis());
		
		gd.draw(g, System.currentTimeMillis());
		
		gda.draw(g, System.currentTimeMillis());
		
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
