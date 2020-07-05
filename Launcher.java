package pdfviewer;

import javax.swing.*;

public class Launcher {

    public static void main(String[] args) {
	
	DefaultListModel<String> m = new DefaultListModel<String>();
	View v = new View();
        Controller c = new Controller();

        v.initialise(m, c);
        c.initialise(m);
    }
}
