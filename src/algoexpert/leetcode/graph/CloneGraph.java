package algoexpert.leetcode.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//133. Clone Graph
public class CloneGraph {

	void main() {
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		node1.neighbors = List.of(node2, node4);
		node2.neighbors = List.of(node1, node3);
		node3.neighbors = List.of(node2, node4);
		node4.neighbors = List.of(node3, node1);

		cloneGraph(node1);

	}

	public Node cloneGraph(Node node) {
		Map<Node, Node> oldToNew = new HashMap<>();
		return dfs(node, oldToNew);
	}

	public Node dfs(Node node, Map<Node, Node> oldToNew){
		if (node == null) {
			return null;
		}

		if (oldToNew.containsKey(node)) {
			return oldToNew.get(node);
		}

		Node newNode = new Node(node.val);
		oldToNew.put(node, newNode);

		for (Node neighbor : node.neighbors) {
			Node dfs = dfs(neighbor, oldToNew);
			newNode.neighbors.add(dfs);
		}

		return newNode;

	}

}
