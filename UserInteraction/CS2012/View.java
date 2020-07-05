package cs12;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class View extends JPanel implements Observer {
	Model model;

	public void initialise(Model m, Controller c) {
		this.model = m;
		this.addMouseListener(c);
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		for (Target t : this.model.targets) {
			Color color = new Color(0, 0, 0);
			g2d.setColor(color);
			Shape s = new Ellipse2D.Double(t.position.getX() - t.size / 2, t.position.getY() - t.size / 2, t.size,
					t.size);
			g2d.setColor(t.fadeColour(t.age));
			g2d.fill(s);
		}
		g.dispose();
	}
}
