import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class KitAssemblyGraphics extends JPanel {
	private FactoryPainter painter;

	/**
	 * @param args
	 */

	public KitAssemblyGraphics() {
		painter = new FactoryPainter();
		add(new JLabel("Graphics"));
	}

	public void paint(Graphics gfx) {
		Graphics2D g = (Graphics2D) gfx;

		BufferedImage factoryImg = painter.drawKitAssembly();
		g.drawImage(factoryImg, 0, 0, null);

	}

	public void setFactoryState(FactoryStateMsg factoryState) {
		painter.setFactoryState(factoryState);
	}

	public void update(FactoryUpdateMsg updateMsg) {
		painter.update(updateMsg);
	}
}
