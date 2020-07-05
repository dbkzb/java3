package cs14;

public class Model {

	int size;
	Cell[][] cells;
	Cell[] mainDiagonal;
	Cell[] antiDiagonal;
	Player activePlayer;
	Player winner;

	public void initialise(int size, Player starter) {

		this.size = size;
		this.activePlayer = starter;

		createCells();

		this.mainDiagonal = getMainDiagonal();
		this.antiDiagonal = getAntiDiagonal();
	}

	public void take(int c, int r) {

		Cell cell = cells[c][r];

		if (cell.getPlayer() == null) {

			cell.setPlayer(this.activePlayer);
			switchPlayer();
		}
	}

	public void switchPlayer() {

		if (this.activePlayer == null) {

			this.activePlayer = Player.CROSS;

		} else if (this.activePlayer == Player.CROSS) {

			this.activePlayer = Player.NAUGHT;

		} else if (this.activePlayer == Player.NAUGHT) {

			this.activePlayer = Player.CROSS;
		}
	}

	/**
	 * Test if all cells are taken by the same player.
	 */
	private boolean takenByTheSame(Cell[] cells) {

		boolean b = true;
		Player p = cells[0].getPlayer();

		if (p == null) {

			// There is no player.
			b = false;

		} else {

			// Assign first cell's player.
			Player taker = p;

			for (int i = 1; i < cells.length; i++) {

				p = cells[i].getPlayer();

				if (p == null) {

					// There is no player.
					b = false;

				} else if (p != taker) {

					// This is a differnt player.
					b = false;
				}

				if (b == false) {

					break;
				}
			}
		}

		return b;
	}

	/**
	 * Test if all cells are taken.
	 */
	private boolean isFull() {

		boolean b = true;

		for (int i = 0; i < size; i++) {

			for (int j = 0; j < size; j++) {

				if (cells[i][j].getPlayer() == null) {

					b = false;
				}
			}
		}

		return b;
	}

	/**
	 * Test if game is finished.
	 */
	public boolean isFinished() {

		boolean b;

		// Check rows.
		for (int i = 0; i < this.size; i++) {

			b = takenByTheSame(this.cells[i]);

			if (b == true) {

				this.winner = this.cells[i][0].getPlayer();

				return true;
			}
		}

		// Check columns.
		for (int i = 0; i < size; i++) {

			b = takenByTheSame(getColumn(i));

			if (b) {

				this.winner = this.cells[0][i].getPlayer();

				return true;
			}
		}

		// Check main diagonal.
		b = takenByTheSame(mainDiagonal);

		if (b) {

			this.winner = this.cells[0][0].getPlayer();

			return true;
		}

		// Check reciprocal diagonal.
		b = takenByTheSame(this.antiDiagonal);

		if (b) {

			this.winner = this.cells[0][this.size - 1].getPlayer();

			return true;
		}

		// Check if game ended in stalemate (there is no winner).
		return isFull();
	}

	private void createCells() {

		this.cells = new Cell[this.size][];

		for (int c = 0; c < this.size; c++) {

			this.cells[c] = new Cell[this.size];

			for (int r = 0; r < size; r++)

				cells[c][r] = new Cell(c, r);
		}
	}

	/**
	 * Get a one-dimensional array of cells which represent the main diagonal of the
	 * cell matrix.
	 */
	private Cell[] getMainDiagonal() {

		Cell[] diagonal = new Cell[this.size];

		for (int j = 0; j < this.size; j++) {

			diagonal[j] = this.cells[j][j];
		}

		return diagonal;
	}

	/**
	 * Get a one-dimensional array of cells which represent the reciprocal diagonal
	 * of the cell matrix.
	 */
	private Cell[] getAntiDiagonal() {

		Cell[] diagonal = new Cell[size];

		for (int j = 0; j < size; j++) {

			diagonal[j] = cells[j][size - j - 1];
		}

		return diagonal;
	}

	/**
	 * Get a one-dimensional array of cells which represent a column of the cell
	 * matrix.
	 * 
	 * @param c the column
	 */
	private Cell[] getColumn(int c) {

		Cell[] column = new Cell[this.size];

		for (int r = 0; r < this.size; r++) {

			column[r] = this.cells[r][c];
		}

		return column;
	}
}
