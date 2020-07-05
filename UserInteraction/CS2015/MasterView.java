package administration;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class MasterView extends JFrame {

    JList list = new JList();
    Details details;

    void initialisePanel(JPanel p, Controller c, ResourceBundle r) {

	JButton add = new JButton();
	JButton edit = new JButton();
	JButton delete = new JButton();

	add.setText(r.getString("add"));
	edit.setText(r.getString("edit"));
	delete.setText(r.getString("delete"));

	add.setActionCommand(Controller.ADD);
	edit.setActionCommand(Controller.EDIT);
	delete.setActionCommand(Controller.DELETE);
	
	add.addActionListener(c);
	edit.addActionListener(c);
	delete.addActionListener(c);

	p.add(add);
	p.add(edit);
	p.add(delete);
    }

    void initialise(DefaultListModel m, Controller c, ResourceBundle r) {

	this.list = new JList();
	JScrollPane s = new JScrollPane(this.list);
	JPanel p = new JPanel();
	this.details = new Details();

	this.list.setModel(m);
	initialisePanel(p, c, r);
	this.details.initialise(c, r);

	add(s, BorderLayout.CENTER);
	add(p, BorderLayout.SOUTH);

        setTitle("Student Administration Master View");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
