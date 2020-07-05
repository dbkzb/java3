package timedisplay;

import java.awt.*;
import javax.swing.*;

public class View extends JFrame {

    JPanel clocks;
    JSpinner counter;

    public void initialise(Controller c) {

	String s = "timedisplay/urania-weltzeituhr_alexanderplatz_berlin.jpeg";
	Icon i = new ImageIcon(s);
	JLabel l = new JLabel(i);
	JPanel p = new JPanel();
	BorderLayout b = new BorderLayout();
	this.clocks = new JPanel();
	BoxLayout bl = new BoxLayout(clocks, BoxLayout.Y_AXIS);
	SpinnerNumberModel n = new SpinnerNumberModel(0, 0, 5, 1);
	this.counter = new JSpinner(n);
	Dimension d = new Dimension(400, 100);
	Font f = new Font("SansSerif", Font.BOLD, 64);

	this.clocks.setLayout(bl);

	this.counter.setPreferredSize(d);
	this.counter.setFont(f);
	this.counter.setToolTipText("Erzeugt oder löscht eine Uhr");
	this.counter.addChangeListener(c);

	p.setLayout(b);
	p.add(this.clocks, BorderLayout.CENTER);
	p.add(this.counter, BorderLayout.SOUTH);

	add(l, BorderLayout.WEST);
	add(p, BorderLayout.CENTER);

	setTitle("Weltzeituhr");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	pack();
	setVisible(true);
    }
}
