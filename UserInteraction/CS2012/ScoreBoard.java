package cs12;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ScoreBoard extends JPanel implements Observer {
	JLabel label;
	Model model = new Model();
	JDialog dialog;

	public void initialise(Model m, JDialog d) {
		this.dialog = d;
		this.label = new JLabel();
		this.model = m;

		dialog.add(this);

	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.label.setText(String.valueOf(this.model.score));
	}
}
