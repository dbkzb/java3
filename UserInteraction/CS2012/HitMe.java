package cs12;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class HitMe {
	public static void main(String[] args) {
		Model model = new Model();
		View view = new View();
		JFrame frame = new JFrame();
		Controller controller = new Controller();
		ScoreBoard board = new ScoreBoard();
		JDialog dialog = new JDialog();

		model.initialise();
		view.initialise(model, controller);
		board.initialise(model, dialog);

		frame.add(view);
		dialog.add(board);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);
		frame.setTitle("HitMe Game");

		controller.initialise(model, view, frame.getBounds());
		model.addObserver(view);
	//	dialog.addObserver(board);

		frame.setVisible(true);
	}
}
