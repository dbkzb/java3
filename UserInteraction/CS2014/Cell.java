package cs14;

public class Cell {
	private int column;
	private int row;
	private Player player;

	public Cell(int column, int row) {
		super();
		this.column = column;
		this.row = row;
		this.player = null;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		if (this.player == null) {
			this.player = player;
		}

	}

	@Override
	public String toString() {
		return "Zeile: " + this.row + " Spalte: " + this.column + this.player.toString();
	}

}
