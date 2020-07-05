package pdfviewer;

import java.awt.*;
import javax.swing.*;
import org.icepdf.ri.common.*;

public class View extends JFrame {

    JList<String> list;

    public void initialise(DefaultListModel<String> m, Controller c) {

        JSplitPane sp = new JSplitPane();
        JPanel p = new JPanel();
        BorderLayout l = new BorderLayout();
        JScrollPane sc = new JScrollPane();
        this.list = new JList<String>(m);
        JButton b = new JButton();
        SwingViewBuilder vb = new SwingViewBuilder(c);
	JPanel v = vb.buildViewerPanel();

        this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.list.addListSelectionListener(c);

        sc.getViewport().setView(this.list);

        b.setText("Open Directory");
        b.setActionCommand("open");
        b.addActionListener(c);

        p.setLayout(l);
        p.add(sc, BorderLayout.CENTER);
        p.add(b, BorderLayout.SOUTH);

        sp.setOneTouchExpandable(true);
        sp.setDividerLocation(300);
        sp.setLeftComponent(p);
        sp.setRightComponent(v);

        add(sp);
        setTitle("PDF Viewer");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
