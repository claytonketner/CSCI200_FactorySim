import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;


public class Painter 
{
	static void draw(Graphics2D g, ImageIcon image, long currentTime, Movement movement)
	{
		// Convert the ImageIcon to BufferedImage to rotate and scale
		int w = image.getIconWidth();
		int h = image.getIconHeight();
		BufferedImage buffImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D)buffImg.getGraphics();
		image.paintIcon(null, g2, 0, 0);
		g2.dispose();

		int cardWidth = buffImg.getWidth();
		int cardHeight = buffImg.getHeight();

		AffineTransform tx = new AffineTransform();

		tx.translate(cardWidth, cardHeight);
		tx.rotate(movement.calcRot(currentTime));
		tx.translate(-cardWidth/2, -cardHeight/2);

		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		try {
			buffImg = op.filter(buffImg, null);
			g.drawImage(buffImg, null, (int)(movement.calcPos(currentTime).x - cardWidth/2), (int)(movement.calcPos(currentTime).y - cardHeight/2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
