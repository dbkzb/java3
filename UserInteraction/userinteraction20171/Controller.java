package unitconverter;

import java.awt.event.*;

public class Controller implements ActionListener {

    Model model;
    View view;

    public void initialise(Model m, View v) {

	this.model = m;
	this.view = v;
    }

    public void actionPerformed(ActionEvent e) {

	String cmd = e.getActionCommand();

	this.model.builder.setLength(0);
	this.model.builder.append(this.view.input.getText());

	if (cmd.compareTo("0") >= 0 && cmd.compareTo("9") <= 0) {

	    this.model.builder.append(cmd);
	    this.view.input.setText(this.model.builder.toString());

	} else if (cmd.equals(".")) {

	    if (this.model.builder.toString().indexOf('.') == -1) {

		this.model.builder.append(".");
		this.view.input.setText(this.model.builder.toString());
	    }

	} else if (cmd.equals("C")) {

	    this.model.builder.setLength(0);
	    this.view.input.setText("");

	} else if (cmd.equals("Calculate")) {

	    this.view.reset();

	    try {

		String s = this.model.builder.toString().replace(',', '.');
		double v = Double.parseDouble(s);

		this.model.calculate(v, this.model.unit);

	    } catch (NumberFormatException ex) {

		this.model.builder.setLength(0);
		this.view.input.setText("");
	    }

	    //
        // Achtung! Das "repaint" im Controller darf nur im "if"-Abschnitt
        // "calculate" stehen und NICHT für alle Fälle am Ende der
        // "actionPerformed"-Methode.
        //
	    // Anderenfalls werden die Anzeigefelder im Fenster nicht geleert,
	    // wenn die Einheit (Unit) in der ComboBox umgeschaltet wurde.
	    //
	    this.view.repaint();

	} else if (cmd.equals("choose")) {

	    this.model.unit = (String) this.view.choice.getSelectedItem();

	    this.view.reset();
	}
    }
}
