package administration;

import java.awt.event.*;
import javax.swing.*;

public class Controller implements ActionListener {
    
    public static final String ADD = "add";
    public static final String EDIT = "edit";
    public static final String DELETE = "delete";
    public static final String SAVE = "save";
    public static final String ABORT = "abort";
    
    DefaultListModel model;
    MasterView view;
    
    void initialise(DefaultListModel m, MasterView v) {
	
	this.model = m;
	this.view = v;
    }

    public void actionPerformed(ActionEvent e) {

	String c = e.getActionCommand();
	
	if (c.equals(Controller.ADD)) {

	    Student s = new Student();

	    s.initialise();

	    // CAUTION! The student has to be added
	    // yet BEFORE opening the dialog.
	    // Otherwise, the "ok" action command
	    // of the dialog won't work,
	    // since it determines the index
	    // of the student within the list.
	    this.model.addElement(s);
	    this.view.list.setSelectedIndex(this.model.getSize() - 1);
	    this.view.details.open(s);

	} else if (c.equals(Controller.EDIT)) {

	    int i = this.view.list.getSelectedIndex();
	    
	    if (i >= 0) {
		
	        Student s = (Student) this.model.get(i);

	        this.view.details.open(s);
	    
	    } else {
		
		System.out.println("Error: Could not edit. No student selected.");
	    }

	} else if (c.equals(Controller.DELETE)) {
	    
	    int i = this.view.list.getSelectedIndex();

	    if (i >= 0) {
		
		this.model.remove(i);
	    
	    } else {
		
		System.out.println("Error: Could not delete. No student selected.");
	    }
	    
	} else if (c.equals(Controller.SAVE)) {
	    
	    int i = this.view.list.getSelectedIndex();

	    if (i >= 0) {
		
		Student s = (Student) this.model.get(i);

		this.view.details.save(s);
		this.view.repaint();
	    
	    } else {
		
		System.out.println("Error: Could not save. No student selected.");
	    }

	} else if (c.equals(Controller.ABORT)) {

	    this.view.details.abort();

	} else {

	    System.out.println("Error: Unknown action command.");
	}
    }
}
