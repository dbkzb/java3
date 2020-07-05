package cs14;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Board extends JComponent {
	Model model;
	int sideLength;
	Point upperLeftCorner;

	public void initialise(Model m) {
		this.model = m;
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	public void adjust() {
		Dimension dim = this.getSize();
		int min = Math.min(dim.height, dim.width);
		this.sideLength = min / this.model.size;
		this.upperLeftCorner = this.getLocation();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < this.model.size; i++) {
			for (int j = 0; j < this.model.size; j++) {
				Integer x = this.upperLeftCorner.x + (this.sideLength * i);
				Integer y = this.upperLeftCorner.y + (this.sideLength * j);
				g.drawRect(x, y, this.sideLength, this.sideLength);
				Rectangle rect = new Rectangle(x, y, this.sideLength, this.sideLength);
				// Cell cell = this.model.cells[i][j];
				drawSign(g, rect, this.model.activePlayer);

				this.repaint();
			}
		}
	}

	public void drawSign(Graphics g, Rectangle r, Player p) {
		if (p.equals(Player.CROSS)) {
			drawCross(g, r);
		} else if (p.equals(Player.NAUGHT)) {
			drawNaught(g, r);
		} else {
			System.out.println("Fehler");
		}
	}

	public void drawCross(Graphics g, Rectangle r) {
		int o = 10;
		g.drawLine(r.x + o, r.y + o, r.x + this.sideLength - (2 * o), r.y + this.sideLength - (2 * o));
		g.drawLine(r.x + o, r.y + this.sideLength - (2 * o), r.x + this.sideLength - (2 * o), r.y + o);
	}

	public void drawNaught(Graphics g, Rectangle r) {
		int o = 10;
		g.drawOval(r.x + o, r.y + o, this.sideLength - (2 * o), this.sideLength - (2 * o));
	}
}
