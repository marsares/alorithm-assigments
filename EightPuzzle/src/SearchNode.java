public class SearchNode {

	Board board;
	// record how many times the initial board moves
	public int moves;

	public SearchNode(Board board) {
		this.board = board;
		moves = 0;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Iterable<SearchNode> neighbors() {
		Stack snstack = new Stack();
		for (Board neighbors : board.neighbors()) {
			SearchNode sn = new SearchNode(neighbors);
			sn.moves = moves + 1;
			snstack.push(sn);
		}
		return snstack;
	}
}
