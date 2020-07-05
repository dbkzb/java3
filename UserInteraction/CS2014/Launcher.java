package cs14;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Launcher {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Image icon = new ImageIcon("icon.png").getImage();
		Model model = new Model();
		Board board = new Board();

		int size = Integer.parseInt(args[0]);
		Player starter = Player.CROSS;

		if (args[1].equals("naught")) {
			starter = Player.NAUGHT;
		}

		model.initialise(size, starter);
		board.initialise(model);

		frame.add(board);

		frame.setTitle("Tic Tac Toe");
		frame.setIconImage(icon);

		frame.setPreferredSize(new Dimension(800, 800));
		frame.pack();

		frame.setResizable(false);
		frame.setLocation(20, 20);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
		board.adjust();
		//Aufgabe 5 ist noch nicht umgesetzt
	}
}
