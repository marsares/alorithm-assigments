/**
 * <p>
 * a search node of the game
 * </p>
 * 
 * @author YuanBo
 * @version 1.0
 */

public class Board {
	// record the last board
	private Board lastboard = null;
	private final int[][] blocks;

	/**
	 * construct a board from an N-by-N array of blocks
	 * 
	 * @param blocks
	 */
	public Board(int[][] blocks) {
		int n = blocks.length;
		this.blocks = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				this.blocks[i][j] = blocks[i][j];
			}
		}
	}

	/**
	 * board dimension N
	 * 
	 * @return board dimension N
	 */
	public int dimension() {
		return blocks.length;
	}

	/**
	 * number of blocks out of place
	 * 
	 * @return number of blocks out of place
	 */
	public int hamming() {
		int hamming = 0;
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0; j < dimension(); j++) {
				if (blocks[i][j] != i * dimension() + j + 1) {
					hamming++;
				}
			}
		}
		return hamming - 1;
	}

	/**
	 * sum of Manhattan distances between blocks and goal
	 * 
	 * @return sum of Manhattan distances between blocks and goal
	 */
	public int manhattan() {
		int manhattan = 0;
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0; j < dimension(); j++) {
				if (blocks[i][j] > 0) {
					manhattan = manhattan
							+ abs(i - (blocks[i][j] - 1) / dimension())
							+ abs(j - (blocks[i][j] - 1) % dimension());
				}
			}
		}
		return manhattan;
	}

	/**
	 * is this board the goal board?
	 * 
	 * @return check result
	 */
	public boolean isGoal() {
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0; j < dimension(); j++) {
				if (blocks[i][j] != i * dimension() + j + 1
						&& (i != dimension() - 1 || j != dimension() - 1)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * a board obtained by exchanging two adjacent blocks in the same row
	 * 
	 * @return a board obtained by exchanging two adjacent blocks in the same
	 *         row
	 */
	public Board twin() {
		int[][] newblocks;
		if (blocks[0][0] > 0 && blocks[0][1] > 0) {
			newblocks = buildblocks();
			int swap = newblocks[0][0];
			newblocks[0][0] = newblocks[0][1];
			newblocks[0][1] = swap;
		} else {
			newblocks = buildblocks();
			int swap = newblocks[1][0];
			newblocks[1][0] = newblocks[1][1];
			newblocks[1][1] = swap;
		}
		Board twin = new Board(newblocks);
		return twin;
	}

	/**
	 * does this board equal y?
	 */
	public boolean equals(Object y) {
		if (y == this)
			return true;
		if (y == null)
			return false;
		if (y.getClass() != this.getClass())
			return false;
		Board that = (Board) y;
		if (that.dimension() != dimension())
			return false;
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0; j < dimension(); j++) {
				if (blocks[i][j] != that.blocks[i][j])
					return false;
			}
		}
		return true;
	}

	/**
	 * all neighboring boards
	 * 
	 * @return all neighboring boards
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Iterable<Board> neighbors() {
		Stack boardstack = new Stack();
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0; j < dimension(); j++) {
				if (blocks[i][j] < 1 && isExisted(i - 1, j)) {
					int[][] newblocks = buildblocks();
					newblocks[i][j] = newblocks[i - 1][j];
					newblocks[i - 1][j] = 0;
					Board newboard = new Board(newblocks);
					newboard.lastboard = this;
					if (lastboard == null) {
						boardstack.push(newboard);
					} else if (!newboard.equals(lastboard)) {
						boardstack.push(newboard);
					}
				}
				if (blocks[i][j] < 1 && isExisted(i, j - 1)) {
					int[][] newblocks = buildblocks();
					newblocks[i][j] = newblocks[i][j - 1];
					newblocks[i][j - 1] = 0;
					Board newboard = new Board(newblocks);
					newboard.lastboard = this;
					if (lastboard == null) {
						boardstack.push(newboard);
					} else if (!newboard.equals(lastboard)) {
						boardstack.push(newboard);
					}
				}
				if (blocks[i][j] < 1 && isExisted(i, j + 1)) {
					int[][] newblocks = buildblocks();
					newblocks[i][j] = newblocks[i][j + 1];
					newblocks[i][j + 1] = 0;
					Board newboard = new Board(newblocks);
					newboard.lastboard = this;
					if (lastboard == null) {
						boardstack.push(newboard);
					} else if (!newboard.equals(lastboard)) {
						boardstack.push(newboard);
					}
				}
				if (blocks[i][j] < 1 && isExisted(i + 1, j)) {
					int[][] newblocks = buildblocks();
					newblocks[i][j] = newblocks[i + 1][j];
					newblocks[i + 1][j] = 0;
					Board newboard = new Board(newblocks);
					newboard.lastboard = this;
					if (lastboard == null) {
						boardstack.push(newboard);
					} else if (!newboard.equals(lastboard)) {
						boardstack.push(newboard);
					}
				}
			}
		}
		return boardstack;
	}

	/**
	 * string representation of the board
	 */
	public String toString() {
		String out = dimension() + "\n";
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0; j < dimension(); j++) {
				out = out + " " + blocks[i][j];
			}
			out = out + "\n";
		}
		return out;
	}

	private int abs(int number) {
		if (number < 0) {
			return -number;
		} else {
			return number;
		}
	}

	private boolean isExisted(int i, int j) {
		if (i < dimension() && i >= 0 && j < dimension() && j >= 0)
			return true;
		else
			return false;
	}

	private int[][] buildblocks() {
		int n = dimension();
		int[][] newblocks = new int[n][n];
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0; j < dimension(); j++) {
				newblocks[i][j] = blocks[i][j];
			}
		}
		return newblocks;
	}
}
