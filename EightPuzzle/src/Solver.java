import java.util.Comparator;

/**
 * <p>
 * use A*algorithm to solve eight puzzle
 * </p>
 * 
 * @author YuanBo
 * @version 1.0
 */
public class Solver {
	private SearchNode sn;
	@SuppressWarnings("rawtypes")
	// store sequence of boards in a shortest solution
	private Queue boardqueue;
	// priority queue
	@SuppressWarnings("rawtypes")
	private MinPQ mpq;
	private Comparator<SearchNode> ManhattanOrder = new ManhattanOrder();

	/**
	 * find a solution to the initial board (using the A* algorithm)
	 * 
	 * @param initial
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Solver(Board initial) {
		Board board = initial;
		Board board2 = board.twin();
		sn = new SearchNode(board);
		SearchNode sn2 = new SearchNode(board2);
		mpq = new MinPQ(ManhattanOrder);
		MinPQ mpq2 = new MinPQ(ManhattanOrder);
		boardqueue = new Queue();
		while (!sn.board.isGoal() && !sn2.board.isGoal()) {
			boardqueue.enqueue(sn.board);
			for (SearchNode searchnode : sn.neighbors()) {
				mpq.insert(searchnode);
			}
			for (SearchNode searchnode2 : sn2.neighbors()) {
				mpq2.insert(searchnode2);
			}
			sn = (SearchNode) mpq.delMin();
			sn2 = (SearchNode) mpq2.delMin();
		}
		boardqueue.enqueue(sn.board);
	}

	/**
	 * is the initial board solvable?
	 * 
	 * @return check result
	 */
	public boolean isSolvable() {
		return sn.board.isGoal();
	}

	/**
	 * min number of moves to solve initial board; -1 if no solution
	 * 
	 * @return min number of moves to solve initial board; -1 if no solution
	 */
	public int moves() {
		if (isSolvable()) {
			return sn.moves;
		} else {
			return -1;
		}
	}

	/**
	 * sequence of boards in a shortest solution; null if no solution
	 * 
	 * @return sequence of boards in a shortest solution; null if no solution
	 */
	@SuppressWarnings("unchecked")
	public Iterable<Board> solution() {
		if (isSolvable()) {
			return boardqueue;
		} else {
			return null;
		}

	}

	public static void main(String[] args) {
		// create initial board from file
		In in = new In(args[0]);
		int N = in.readInt();
		int[][] blocks = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				blocks[i][j] = in.readInt();
		Board initial = new Board(blocks);

		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
		}
	}

	private class ManhattanOrder implements Comparator<SearchNode> {
		public int compare(SearchNode v, SearchNode w) {
			if (v.board.manhattan() + v.moves < w.board.manhattan() + w.moves)
				return -1;
			else if (v.board.manhattan() + v.moves == w.board.manhattan()
					+ w.moves)
				return 0;
			else
				return 1;
		}
	}
}
