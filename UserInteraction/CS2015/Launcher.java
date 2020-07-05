package administration;

import java.util.*;
import javax.swing.*;

public class Launcher {

    public static void main(String[] args) {

	// Examples:
        // Locale l = new Locale("en", "GB");
        // Locale g = new Locale("de", "DE");
        Locale l = new Locale(args[0], args[1]);

        // The string "resource/labels" contains
        // the package name at first, followed by
        // the prefix name of all properties files.
        ResourceBundle r = ResourceBundle.getBundle("resource/labels", l);
	DefaultListModel m = new DefaultListModel();
	MasterView v = new MasterView();
	Controller c = new Controller();

	v.initialise(m, c, r);
	c.initialise(m, v);
    }
}
