import java.util.*;
///////////////////////////////////////////////////////////////////////////////
//Title:            Torus 8-Puzzle
//Files:            Torus.java
//Semester:         (540) Fall 2017
//
//Author:           Jingyao Wei
//Email:            jwei44@wisc.edu
//CS Login:         jwei
//Lecturer's Name:  Collin Engstrom, Dan Griffin, Ross Kleiman
////////////////////////////80 columns wide //////////////////////////////////

/**
 * This class is a state of 8 puzzle game
 * 
 * @author Jingyao
 *
 */
class State {
	int[] board;
	State parentPt;
	int depth;

	/**
	 * this method is a constructor to initialize the parent of root
	 */
	public State() {
		this.board = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		this.parentPt = null;
		this.depth = 0;
	}

	/**
	 * this method is a constructor to initialize root
	 * 
	 * @param arr
	 */
	public State(int[] arr) {
		this.board = Arrays.copyOf(arr, arr.length);
		this.parentPt = new State();
		this.depth = 0;
	}

	/**
	 * this method is a constructor to initialize a state
	 * 
	 * @param board
	 * @param parentPt
	 * @param depth
	 */
	public State(int[] board, State parentPt, int depth) {
		this.board = board;
		this.parentPt = parentPt;
		this.depth = depth;
	}

	/**
	 *this method is to get four successors and return in order
	 * 
	 * @return
	 */
	public State[] getSuccessors() {
		State[] successors = new State[4];
		int i = new String(this.getBoard()).indexOf("0") / 2;
		// right move
		int[] newBoard = Arrays.copyOf(board, board.length);
		if (i == 0 || i == 1 || i == 3 || i == 4 || i == 6 || i == 7) {
			int temp = newBoard[i];
			newBoard[i] = newBoard[i + 1];
			newBoard[i + 1] = temp;
			successors[0] = new State(newBoard, this, this.depth + 1);
		} else if (i == 2 || i == 5 || i == 8) {
			int temp = newBoard[i];
			newBoard[i] = newBoard[i - 2];
			newBoard[i - 2] = temp;
			successors[0] = new State(newBoard, this, this.depth + 1);
		}
		// left move
		newBoard = Arrays.copyOf(board, board.length);
		if (i == 1 || i == 2 || i == 4 || i == 5 || i == 7 || i == 8) {
			int temp = newBoard[i];
			newBoard[i] = newBoard[i - 1];
			newBoard[i - 1] = temp;
			successors[1] = new State(newBoard, this, this.depth + 1);
		} else if (i == 0 || i == 3 || i == 6) {
			int temp = newBoard[i];
			newBoard[i] = newBoard[i + 2];
			newBoard[i + 2] = temp;
			successors[1] = new State(newBoard, this, this.depth + 1);
		}
		// up move
		newBoard = Arrays.copyOf(board, board.length);
		if (i == 0 || i == 1 || i == 2) {
			int temp = newBoard[i];
			newBoard[i] = newBoard[i + 6];
			newBoard[i + 6] = temp;
			successors[2] = new State(newBoard, this, this.depth + 1);
		} else if (i == 3 || i == 4 || i == 5 || i == 6 || i == 7 || i == 8) {
			int temp = newBoard[i];
			newBoard[i] = newBoard[i - 3];
			newBoard[i - 3] = temp;
			successors[2] = new State(newBoard, this, this.depth + 1);
		}
		// down move
		newBoard = Arrays.copyOf(board, board.length);
		if (i == 6 || i == 7 || i == 8) {
			int temp = newBoard[i];
			newBoard[i] = newBoard[i - 6];
			newBoard[i - 6] = temp;
			successors[3] = new State(newBoard, this, this.depth + 1);
		} else if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5) {
			int temp = newBoard[i];
			newBoard[i] = newBoard[i + 3];
			newBoard[i + 3] = temp;
			successors[3] = new State(newBoard, this, this.depth + 1);
		}
		successors = order(successors);
		return successors;
	}

	/**
	 * this method is to sort all successors in ascending order
	 * 
	 * @param successors
	 * @return
	 */
	private State[] order(State[] successors) {
		State tmp;
		boolean swap = true;
		while (swap) {
			swap = false;
			for (int i = 0; i < successors.length - 1; i++) {
				if (compareTo(successors[i], successors[i + 1]) > 0) {
					tmp = successors[i];
					successors[i] = successors[i + 1];
					successors[i + 1] = tmp;
					swap = true;
				}
			}
		}
		return successors;
	}

	/**
	 * This method is to compare two states in order
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	private int compareTo(State s1, State s2) {
		return s1.getBoard().compareTo(s2.getBoard());
	}

	public void printState(int option) {
		if (option == 1 || option == 2) {
			System.out.println(this.getBoard());
			return;
		} else if (option == 3) {
			System.out.println(this.getBoard() + " parent " + this.parentPt.getBoard());
			return;
		} else if (option == 5) {
			printIterative(this);
			return;
		}
	}

	/**
	 * This method is to print reverse order with state from initial to goal 
	 * 
	 * @param node
	 */
	private void printIterative(State node) {
		if (!node.equals(new State())) {
			printIterative(node.parentPt);
			node.printState(1);
		}
		return;
	}

	public String getBoard() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			builder.append(this.board[i]).append(" ");
		}
		return builder.toString().trim();
	}

	public boolean isGoalState() {
		for (int i = 0; i < 9; i++) {
			if (this.board[i] != (i + 1) % 9)
				return false;
		}
		return true;
	}

	public boolean equals(State src) {
		for (int i = 0; i < 9; i++) {
			if (this.board[i] != src.board[i])
				return false;
		}
		return true;
	}
}

/**
 * This class is the main class to implement iterative deepening DFS
 * 
 * @author Jingyao
 *
 */
public class Torus {
	/**
	 * this method is main method to do five options
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		if (args.length < 10) {
			System.out.println("Invalid Input");
			return;
		}
		int flag = Integer.valueOf(args[0]);
		int[] board = new int[9];
		for (int i = 0; i < 9; i++) {
			board[i] = Integer.valueOf(args[i + 1]);
		}
		int option = flag / 100;
		int cutoff = flag % 100;
		if (option == 1) {
			State init = new State(board);
			State[] successors = init.getSuccessors();
			for (State successor : successors) {
				successor.printState(option);
			}
		} else {
			State init = new State(board);
			Stack<State> stack = new Stack<>();
			List<State> prefix = new ArrayList<>();
			int goalChecked = 0;
			int maxStackSize = Integer.MIN_VALUE;
			while (true) {
				stack.push(init);
				prefix.add(init);
				while (!stack.isEmpty()) {
					//perform iterative deepening; implement prefix list
					State curr = stack.pop();
					if (!curr.equals(init)) {
						if (prefix.indexOf(curr.parentPt) + 1 <= prefix.size()) {
							prefix.subList(prefix.indexOf(curr.parentPt) + 1, prefix.size()).clear();
						}
						prefix.add(curr);
					}
					curr.printState(option);
					if (curr.isGoalState() == true) {
						break;
					} else if (curr.depth < cutoff) {
						for (int i = 0; i < curr.getSuccessors().length; i++) {
							State currSuccessors = curr.getSuccessors()[i];
							if (!containNode(prefix, currSuccessors)) {
								stack.push(currSuccessors);
							}
						}
					} else if (option == 4) {
						break;
					}
				}
				if (option == 4) {
					for (int i = 0; i < prefix.size(); i++) {
						State tmp = prefix.get(i);
						tmp.printState(1);
					}
				}
				if (option != 5)
					break;
				//perform the necessary steps to start a new iteration
				if (option == 5) {
					int numCutoff = 0;
					while (true) {
						stack.push(init);
						prefix.add(init);
						while (!stack.isEmpty()) {
							State curr = stack.pop();
							maxStackSize = Math.max(maxStackSize, stack.size());
							if (!curr.equals(init)) {
								if (prefix.indexOf(curr.parentPt) + 1 <= prefix.size()) {
									prefix.subList(prefix.indexOf(curr.parentPt) + 1, prefix.size()).clear();
								}
								prefix.add(curr);
							}
							goalChecked++;
							if (curr.isGoalState() == true) {
								curr.printState(option);
								System.out.println("Goal-check " + goalChecked);
								System.out.println("Max-stack-size " + maxStackSize);
								return;
							} else if (curr.depth < numCutoff) {
								for (int i = 0; i < curr.getSuccessors().length; i++) {
									State currSuccessors = curr.getSuccessors()[i];
									if (!containNode(prefix, currSuccessors)) {
										stack.push(currSuccessors);
										maxStackSize = Math.max(maxStackSize, stack.size());
									}
								}
							}
						}
						numCutoff++;
						prefix.clear();
					}
				}
			}
		}
	}

	/**
	 * this method is to make if prefix list contains the successor
	 * 
	 * @param prefix
	 * @param successor
	 * @return
	 */
	private static boolean containNode(List<State> prefix, State successor) {
		for (State tmp : prefix) {
			if (successor.equals(tmp)) {
				return true;
			}
		}
		return false;
	}
}
