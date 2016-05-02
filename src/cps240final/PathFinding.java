/*
 * @author: Brian Bauman and Michael Ostrander
 * 
 * PathFinding
 * 
 * Messy implementation of A* pathfinding.
 * 
 * TODO:
 *  -- Create subgrid instead of 512 x 512 grid
 * 
 *  Modified version from here:
 *  http://www.codebytes.in/2015/02/a-shortest-path-finding-algorithm.html
 * Not currently used.
 *
 *DO NOT RUN, THIS WILL USE ALL THE MEMORY
 */

package cps240final;

import java.util.PriorityQueue;
import java.util.Stack;

public class PathFinding {
	public static final int DIAGONAL_COST = 14;
	public static final int V_H_COST = 10;

	static class Cell {
		int heuristicCost = 0; // Heuristic cost
		int finalCost = 0; // G+H
		int i, j;
		Cell parent;

		Cell(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "[" + this.i + ", " + this.j + "]";
		}
	}

	// Blocked cells are just null Cell values in grid
	static Cell[][] grid = new Cell[512][512];

	static PriorityQueue<Cell> open;

	static boolean closed[][];
	static int startI, startJ;
	static int endI, endJ;

	public static void setBlocked() {
		for (int i = 0; i < 512; i++)
			for (int j = 0; j < 512; j++)
				for (MapObject m : Main.map) {
					if (m.getBoundary().contains(i, j))
						grid[i][j] = null;
				}
	}

	public static void setStartCell(int i, int j) {
		startI = i;
		startJ = j;
	}

	public static void setEndCell(int i, int j) {
		endI = i;
		endJ = j;
	}

	public static void checkAndUpdateCost(Cell current, Cell t, int cost) {
		if (t == null || closed[t.i][t.j])
			return;
		int t_final_cost = t.heuristicCost + cost;

		boolean inOpen = open.contains(t);
		if (!inOpen || t_final_cost < t.finalCost) {
			t.finalCost = t_final_cost;
			t.parent = current;
			if (!inOpen)
				open.add(t);
		}
	}

	public static void AStar() {

		// add the start location to open list.
		open.add(grid[startI][startJ]);

		Cell current;

		while (true) {
			current = open.poll();
			if (current == null)
				break;
			closed[current.i][current.j] = true;

			if (current.equals(grid[endI][endJ])) {
				return;
			}

			Cell t;
			if (current.i - 1 >= 0) {
				t = grid[current.i - 1][current.j];
				checkAndUpdateCost(current, t, current.finalCost + V_H_COST);

				if (current.j - 1 >= 0) {
					t = grid[current.i - 1][current.j - 1];
					checkAndUpdateCost(current, t, current.finalCost + DIAGONAL_COST);
				}

				if (current.j + 1 < grid[0].length) {
					t = grid[current.i - 1][current.j + 1];
					checkAndUpdateCost(current, t, current.finalCost + DIAGONAL_COST);
				}
			}

			if (current.j - 1 >= 0) {
				t = grid[current.i][current.j - 1];
				checkAndUpdateCost(current, t, current.finalCost + V_H_COST);
			}

			if (current.j + 1 < grid[0].length) {
				t = grid[current.i][current.j + 1];
				checkAndUpdateCost(current, t, current.finalCost + V_H_COST);
			}

			if (current.i + 1 < grid.length) {
				t = grid[current.i + 1][current.j];
				checkAndUpdateCost(current, t, current.finalCost + V_H_COST);

				if (current.j - 1 >= 0) {
					t = grid[current.i + 1][current.j - 1];
					checkAndUpdateCost(current, t, current.finalCost + DIAGONAL_COST);
				}

				if (current.j + 1 < grid[0].length) {
					t = grid[current.i + 1][current.j + 1];
					checkAndUpdateCost(current, t, current.finalCost + DIAGONAL_COST);
				}
			}
		}
	}

	/*
	 * Params : tCase = test case No. x, y = Board's dimensions si, sj = start
	 * location's x and y coordinates ei, ej = end location's x and y
	 * coordinates int[][] blocked = array containing inaccessible cell
	 * coordinates
	 */
	public static Stack<Cell> pathRun(int si, int sj, int ei, int ej) {
		int x = 512;
		int y = 512;
		Stack<Cell> path = new Stack<Cell>();
		// Reset
		grid = new Cell[x][y];
		closed = new boolean[x][y];
		open = new PriorityQueue<>((Object o1, Object o2) -> {
			Cell c1 = (Cell) o1;
			Cell c2 = (Cell) o2;

			return c1.finalCost < c2.finalCost ? -1 : c1.finalCost > c2.finalCost ? 1 : 0;
		});
		// Set start position
		setStartCell(si, sj); // Setting to 0,0 by default. Will be useful for
								// the UI part

		// Set End Location
		setEndCell(ei, ej);

		for (int i = 0; i < x; ++i) {
			for (int j = 0; j < y; ++j) {
				grid[i][j] = new Cell(i, j);
				grid[i][j].heuristicCost = Math.abs(i - endI) + Math.abs(j - endJ);
			}
		}
		grid[si][sj].finalCost = 0;

		/*
		 * Set blocked cells. Simply set the cell values to null for blocked
		 * cells.
		 */

		AStar();

		if (closed[endI][endJ]) {
			// Trace back the path
			Cell current = grid[endI][endJ];
			path.push(current);
			while (current.parent != null) {
				path.push(current.parent);
			}
		}
		return path;
	}
}
