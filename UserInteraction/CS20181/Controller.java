package timedisplay;

import javax.swing.event.*;

public class Controller implements ChangeListener {

    View view;

    public void initialise(View v) {

	this.view = v;
    }

    public void stateChanged(ChangeEvent e) {

	int c = this.view.clocks.getComponentCount();
	int v = (Integer) this.view.counter.getValue();
	int diff = v - c;

	if (diff > 0) {

	    for (int i = 0; i < diff; i++) {

		// Add a new clock.
		Display d = new Display();

		this.view.clocks.add(d);
	    }

	} else if (diff < 0) {

	    int a = Math.abs(diff);
	    
	    for (int i = 0; i < a; i++) {

		// Remove the last clock.
		this.view.clocks.remove(c - 1 - i);
	    }
	}

	this.view.pack();
	this.view.repaint();
    }
}
