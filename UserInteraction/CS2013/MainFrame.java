package mosaic;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {

    private JLabel[] labels = null;
    public JPopupMenu menu;
    public Model model = null;
    public Controller controller = null;

    private void buildMenu() {
    
        this.menu = new JPopupMenu();
        
        JMenuItem m = new JMenuItem(Controller.MIX);
        JMenuItem r = new JMenuItem(Controller.RESET);
        JMenuItem c = new JMenuItem(Controller.COPY);
        JMenuItem p = new JMenuItem(Controller.PASTE);

        m.setActionCommand("mix");
        r.setActionCommand("reset");
        c.setActionCommand("copy");
        p.setActionCommand("paste");

        m.addActionListener(this.controller);
        r.addActionListener(this.controller);
        c.addActionListener(this.controller);
        p.addActionListener(this.controller);
        
        this.menu.add(m);
        this.menu.add(r);
        this.menu.add(c);
        this.menu.add(p);
    }

    public void initialise() {

        // The colour panel.
        JPanel cp = new JPanel();
        // The button panel.
        JPanel bp = new JPanel();
        GridLayout l = new GridLayout(7, 7, 1, 1);
        this.labels = new JLabel[49];
        JButton mix = new JButton("Mix");
        JButton reset = new JButton("Reset");

        cp.setPreferredSize(new Dimension(400, 400));
        cp.setBackground(Color.lightGray);
        cp.setLayout(l);

        for (int i = 0; i < 49; i++) {
        
            this.labels[i] = new JLabel();

            // The JLabel background is transparent by default,
            // so changing the colour doesn't seem to do anything.
            // Therefore, set the background opaque [undurchlässig].
            this.labels[i].setOpaque(true);
            this.labels[i].setText(String.valueOf(i));
            this.labels[i].addMouseListener(this.controller);

            cp.add(this.labels[i]);
        }

        mix.setActionCommand(Controller.MIX);
        mix.addActionListener(this.controller);
        reset.setActionCommand(Controller.RESET);
        reset.addActionListener(this.controller);

        bp.add(mix);
        bp.add(reset);

        add(cp, BorderLayout.CENTER);
        add(bp, BorderLayout.SOUTH);

        buildMenu();
        setTitle("Mosaic");
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void paint(Graphics g) {
    
        super.paint(g);

        Color[] c = this.model.getColours();
    
        for (int i = 0; i < 49; i++) {

            this.labels[i].setBackground(c[i]);
        }
    }
}
