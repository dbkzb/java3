package timedisplay;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
// Do NOT import all classes (*) here since otherwise,
// java.util.Timer would conflict with javax.swing.Timer.
import java.util.Date;
import java.util.TimeZone;
import javax.swing.border.*;
import javax.swing.*;

public class Display extends JPanel implements Serializable, ActionListener {

    transient private SimpleDateFormat formatter;
    private String date;
    private JLabel label;
    transient private Timer timer;

    public Display() {

	this.formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	BorderLayout b = new BorderLayout();
	this.label = new JLabel();
	Font f = new Font("SansSerif", Font.BOLD, 48);
	String[] items = { "Asia/Bangkok", "Asia/Beirut", "Asia/Damascus", "Asia/Istanbul", "Asia/Jakarta",
		"Asia/Shanghai", "Asia/Tokyo", "Europe/Berlin", "Europe/Lisbon", "Europe/Moscow" };
	JComboBox<String> cb = new JComboBox<String>(items);
	this.timer = new Timer(1000, this);

	this.label.setForeground(Color.red);
	this.label.setFont(f);
	this.label.setText(this.date);

	cb.addActionListener(this);
	cb.setSelectedItem("Europe/Berlin");

	setPreferredSize(new Dimension(600, 120));
	setBackground(Color.LIGHT_GRAY);
	setBorder(new MatteBorder(5, 5, 5, 5, Color.GREEN));
	setLayout(b);

	add(this.label, BorderLayout.CENTER);
	add(cb, BorderLayout.SOUTH);

	this.timer.start();
    }

    public String getTimeZone() {

	TimeZone z = this.formatter.getTimeZone();

	return z.getDisplayName();
    }

    public void setTimeZone(String s) {

	TimeZone z = TimeZone.getTimeZone(s);

	this.formatter.setTimeZone(z);
    }

    public Date getDate() {

	Date d = null;
	DateFormat f = DateFormat.getDateInstance();

	try {

	    d = f.parse(this.date);

	} catch (ParseException e) {

	    e.printStackTrace();
	}

	return d;
    }

    public void setDate(Date d) {

	this.date = this.formatter.format(d);

	this.label.setText(this.date);
    }

    public void actionPerformed(ActionEvent e) {

	Object o = e.getSource();

	if (o instanceof Timer) {

	    Date d = new Date();

	    setDate(d);

	} else if (o instanceof JComboBox<?>) {

	    JComboBox<String> cb = (JComboBox<String>) o;
	    String s = (String) cb.getSelectedItem();

	    setTimeZone(s);

	} else {

	    JOptionPane.showMessageDialog(null, "Error: The command is unknown.");
	}
    }
}
