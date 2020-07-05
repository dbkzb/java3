package cs14;

public class Player {
	String name;
	static final Player CROSS = new Player("cross");
	static final Player NAUGHT = new Player("naught");

	public Player(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Name: " + this.name;
	}
}
