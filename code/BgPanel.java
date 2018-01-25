

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

/**
 * 
 * @ClassName: BgPanel
 * @Description: Add a background to the panel.
 * @Author: Group44
 * @Version:
 * 
 */

public class BgPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	Image im;

	public BgPanel() {
		im = Toolkit.getDefaultToolkit().getImage("bg.jpg");
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int imWidth = im.getWidth(this);
		int imHeight = im.getHeight(this);
		int FWidth = getWidth();
		int FHeight = getHeight();
		int x = (FWidth - imWidth) / 2;
		int y = (FHeight - imHeight) / 2;
		g.drawImage(im, x, y, null);
	}
}
