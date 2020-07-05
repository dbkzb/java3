package administration;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Details extends JDialog {

    JTextField lastName;
    JTextField firstName;
    JTextField dayOfBirth;
    JTextField skills;

    void initialise(Controller c, ResourceBundle r) {
	
	JLabel l = new JLabel();
	this.lastName = new JTextField();
	JLabel f = new JLabel();
	this.firstName = new JTextField();
	JLabel d = new JLabel();
	this.dayOfBirth = new JTextField();
	JLabel s = new JLabel();
	this.skills = new JTextField();
	JButton o = new JButton();
	JButton a = new JButton();

	l.setText(r.getString("last_name"));
	f.setText(r.getString("first_name"));
	d.setText(r.getString("day_of_birth"));
	s.setText(r.getString("skills"));
	o.setText(r.getString("save"));
	a.setText(r.getString("abort"));

	o.setActionCommand(Controller.SAVE);
	a.setActionCommand(Controller.ABORT);
	
	o.addActionListener(c);
	a.addActionListener(c);

	setLayout(new GridLayout(5, 2));
	add(l);
	add(this.lastName);
	add(f);
	add(this.firstName);
	add(d);
	add(this.dayOfBirth);
	add(s);
	add(this.skills);
	add(o);
	add(a);

	setTitle("Student Details");
	setLocation(400, 200);	
	pack();
	setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
	setModal(true);
    }
    
    void open(Student s) {
	
	this.lastName.setText(s.lastName);
	this.firstName.setText(s.firstName);
	this.dayOfBirth.setText(s.dayOfBirth);
	this.skills.setText(s.skills);

	setVisible(true);
    }
    
    void save(Student s) {
	
	s.lastName = this.lastName.getText();
	s.firstName = this.firstName.getText();
	s.dayOfBirth = this.dayOfBirth.getText();
	s.skills = this.skills.getText();

	setVisible(false);
    }
    
    void abort() {
	
	setVisible(false);
    }
}
