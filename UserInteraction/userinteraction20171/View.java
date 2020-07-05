package unitconverter;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class View extends JFrame {

    JTextField input;
    JComboBox<String> choice;
    JTextField meter;
    JTextField inch;
    JTextField foot;
    JTextField yard;
    Model model;

    private void addButton(JPanel panel, String s, Controller c) {

	JButton b = new JButton(s);

	b.addActionListener(c);

	panel.add(b);
    }

    private JTextField addField(JPanel panel, String s) {

	JTextField tf = new JTextField(20);
	JPanel p = new JPanel();

	tf.setFont(new Font("Monospaced", Font.PLAIN, 12));
	tf.setHorizontalAlignment(JTextField.RIGHT);
	tf.setEditable(false);

	p.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
	p.add(tf);
	p.add(new JLabel(s));

	panel.add(p);

	return tf;
    }

    public void initialise(Model m, Controller c) {

	this.model = m;

	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	String[] items = { "m", "inch", "foot", "yard" };
	this.input = new JTextField(20);
	this.choice = new JComboBox<String>(items);

	this.input.setHorizontalAlignment(JTextField.RIGHT);
	this.input.setFont(new Font("Monospaced", Font.PLAIN, 18));

	this.choice.setActionCommand("choose");
	this.choice.addActionListener(c);

	p1.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));
	p1.add(this.input);
	p1.add(this.choice);

	p2.setBorder(new EmptyBorder(0, 20, 0, 0));
	p2.setLayout(new GridLayout(4, 3, 5, 5));

	addButton(p2, "7", c);
	addButton(p2, "8", c);
	addButton(p2, "9", c);
	addButton(p2, "4", c);
	addButton(p2, "5", c);
	addButton(p2, "6", c);
	addButton(p2, "1", c);
	addButton(p2, "2", c);
	addButton(p2, "3", c);
	addButton(p2, "0", c);
	addButton(p2, ".", c);
	addButton(p2, "C", c);

	p3.setLayout(new GridLayout(4, 2, 10, 5));

	this.meter = addField(p3, "m");
	this.inch = addField(p3, "inch");
	this.foot = addField(p3, "foot");
	this.yard = addField(p3, "yard");

	addButton(p4, "Calculate", c);

	setLayout(new BorderLayout(10, 10));

	add(p1, BorderLayout.NORTH);
	add(p2, BorderLayout.CENTER);
	add(p3, BorderLayout.EAST);
	add(p4, BorderLayout.SOUTH);

	setTitle("Unit Converter");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	pack();
	setVisible(true);
    }

    public void reset() {

	this.meter.setText("");
	this.inch.setText("");
	this.foot.setText("");
	this.yard.setText("");
    }

    @Override
    public void paint(Graphics g) {

	super.paint(g);

	this.meter.setText(Model.format(this.model.getMeter()));
	this.inch.setText(Model.format(this.model.getInch()));
	this.foot.setText(Model.format(this.model.getFoot()));
	this.yard.setText(Model.format(this.model.getYard()));
    }
}
