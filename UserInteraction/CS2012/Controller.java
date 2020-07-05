package cs12;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.Timer;

public class Controller extends MouseAdapter implements ActionListener {
	Model model;
	View view;
	Timer timer;
	Rectangle box;

	public void initialise(Model m, View v, Rectangle r) {
		this.model = m;
		this.view = v;
		this.timer = new Timer(1000, this);
		this.timer.start();
		this.box = r;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// The old time.
		long o = this.model.time;
		this.model.time = System.currentTimeMillis() / this.model.frequency;
		if (this.model.time != o) {
			for (Iterator<Target> i = this.model.targets.iterator(); i.hasNext();) {
				Target t = i.next();
				t.ageTarget(this.model.frequency * this.model.time);
				if (t.age > t.lifespan) {
					i.remove();
				}

			}
			Target target = new Target();
			target.initialise(this.box);
			this.model.targets.add(target);
			this.model.markChanged();
			this.model.notifyObservers();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		if (e.getButton() == MouseEvent.BUTTON1) {
			int x = e.getX();
			int y = e.getY();
			for (int i = 0; i < this.model.targets.size(); i++) {
				Target t = this.model.targets.get(i);
				if (t.containsPoint(new Point(x, y))) {
					this.model.score += t.score;
					this.model.targets.remove(i);
					i--;
					this.model.markChanged();
					this.model.notifyObservers();
				}
			}
		}
	}
}
