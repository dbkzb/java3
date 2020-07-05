package cs12;

import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {
	ArrayList<Target> targets;
	int score;
	int frequency;
	long time;

	public void initialise() {
		this.targets = new ArrayList<Target>();
		this.score = 0;
		this.frequency = 100;
		this.time = System.currentTimeMillis();
	}

	public void markChanged() {
		setChanged();
	}
}
