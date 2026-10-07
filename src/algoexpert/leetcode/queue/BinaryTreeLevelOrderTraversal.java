package algoexpert.leetcode.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//102. Binary Tree Level Order Traversal
public class BinaryTreeLevelOrderTraversal {

	public static void main(String[] args) {
//		TreeNode root = new TreeNode(3,
//			new TreeNode(9, null, null),
//			new TreeNode(20,
//				new TreeNode(15, null, null),
//				new TreeNode(7, null, null)));

		TreeNode root = new TreeNode(1,
			new TreeNode(2, null, null), null);

		System.out.println(levelOrder(root));
	}

	//BFS
	public static List<List<Integer>> levelOrder(TreeNode root) {
		if (root == null){
			return List.of();
		}

		List<List<Integer>> res = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();

		queue.add(root);

		while (!queue.isEmpty()){
			int levelSize = queue.size();
			List<Integer> level = new ArrayList<>();

			for (int i = 0; i < levelSize; i++) {
				TreeNode node = queue.poll();

				level.add(node.val);

				if (node.left != null){
					queue.add(node.left);
				}
				if (node.right != null){
					queue.add(node.right);
				}
			}
			res.add(level);
		}

		return res;
	}

}



class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode() {}
  TreeNode(int val) { this.val = val; }
  TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
  }
}
