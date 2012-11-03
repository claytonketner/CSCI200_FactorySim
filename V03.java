import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


public class V03 extends JPanel
{
	WholeLane lane1;
	GUILane test;
	
	public V03()
	{
		this.setPreferredSize(new Dimension(800,400));	
		Painter.loadImages();
		
		lane1 = new WholeLane();
		test = new GUILane( lane1.getComboLane(), 5, 190,10 );
	}
	
	public void paint(Graphics gfx)
	{
		Graphics2D g = (Graphics2D)gfx;
		
		test.draw(g, System.currentTimeMillis());
	}
	
}
