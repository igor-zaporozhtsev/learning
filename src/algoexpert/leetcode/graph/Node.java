package algoexpert.leetcode.graph;

import java.util.ArrayList;
import java.util.List;

public class Node {

	public int val;
	public List<Node> neighbors;

	protected Node() {
		val = 0;
		neighbors = new ArrayList<>();
	}

	protected Node(int val) {
		this.val = val;
		neighbors = new ArrayList<>();
	}

	protected Node(
		int val,
		ArrayList<Node> neighbors
	) {
		this.val = val;
		this.neighbors = neighbors;
	}

}
