package pdfviewer;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import org.icepdf.ri.common.*;
import org.icepdf.ri.common.views.*;

public class Controller extends SwingController implements ListSelectionListener, ActionListener {

    DefaultListModel<String> model;
    JFileChooser chooser;
    PDFFilter filter;
    
    public void initialise(DefaultListModel<String> m) {

	this.model = m;
	this.chooser = new JFileChooser();
	this.filter = new PDFFilter();

	this.chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    public void valueChanged(ListSelectionEvent e) {

	if (e.getValueIsAdjusting() == false) {

	    JList<String> l = (JList<String>) e.getSource();
	    String s = (String) l.getSelectedValue();

	    openDocument(s);

	    // CAUTION! This should be done AFTER openDocument
	    // as it has code that can change the view mode,
	    // if specified by the file.
	    setPageViewMode(DocumentViewControllerImpl.ONE_PAGE_VIEW, false);
	}
    }

    @Override
    public void actionPerformed(ActionEvent e) {

	// CAUTION! This super call is important since otherwise,
	// the toolbar icons of the pdf viewer panel won't work.
	super.actionPerformed(e);

	String c = e.getActionCommand();

	if (c.equals("open")) {

	    int r = this.chooser.showOpenDialog(null);

	    if (r == JFileChooser.APPROVE_OPTION) {
		
		File d = this.chooser.getSelectedFile();
		File[] l = d.listFiles(this.filter);

		this.model.clear();

		for (File f : l) {
		    
		    try {

			this.model.addElement(f.getCanonicalPath());
			
		    } catch (IOException ex) {

			ex.printStackTrace();
		    }
		}
	    }
	}
    }
}
