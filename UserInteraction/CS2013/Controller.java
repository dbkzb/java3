package mosaic;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Controller extends MouseAdapter implements ActionListener {

    public static final String MIX = "mix";
    public static final String RESET = "reset";
    public static final String COPY = "copy";
    public static final String PASTE = "paste";

    private int index = 0;
    private Color colour = null;

    public Model model = null;
    public MainFrame view = null;

    public void actionPerformed(ActionEvent e) {

        String ac = e.getActionCommand();
        
        if (ac.equals(Controller.MIX)) {
      
            this.model.mixAllColours();
            
        } else if (ac.equals(Controller.RESET)) {

            this.model.resetAllColours();
            
        } else if (ac.equals(Controller.COPY)) {

            // Store index and colour of event source component in model.
            this.model.storedColour = this.colour;

        } else if (ac.equals(Controller.PASTE)) {

            // Copy previously remembered colour to currently clicked component.
            this.model.copyColour(this.index, this.model.storedColour);
        }

        this.view.repaint();
    }

    public void mousePressed(MouseEvent e) {

        super.mousePressed(e);

        int b = e.getButton();
        Object o = e.getSource();
        int i = 0;
        Color c = null;

        if (o instanceof JLabel) {

            JLabel l = (JLabel) o;
            i = Integer.valueOf(l.getText());
            c = l.getBackground();
        }

        if (b == MouseEvent.BUTTON1) {

            this.model.mixOneColour(i);
            this.view.repaint();

        } else if (b == MouseEvent.BUTTON3) {
        
            // The index and colour of the former event become the source.
            this.index = i;
            this.colour = c;

            if (e.isPopupTrigger()) {

                this.view.menu.show((Component) e.getSource(), e.getX(), e.getY());
            }
        }
    }
}
