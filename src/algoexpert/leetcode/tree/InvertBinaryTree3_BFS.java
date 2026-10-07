package algoexpert.leetcode.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//226. Invert Binary Tree [DFS]
public class InvertBinaryTree3_BFS {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4,
                new TreeNode(2,
                        new TreeNode(1, new TreeNode(-10, null, null), null),
                        new TreeNode(3, null, null)),
                new TreeNode(7,
                        new TreeNode(6, null, null),
                        new TreeNode(9, null, null)));


        TreeNode treeNode = invertTree(root);
        System.out.println(treeNode);
    }

	private static TreeNode invertTree(TreeNode root){
		Queue<TreeNode> queue = new LinkedList<>();

		queue.add(root);

		while (!queue.isEmpty()) {
			//poll node
			TreeNode node = queue.poll();

			//swap
			TreeNode temp = node.right;
			node.right = node.left;
			node.left = temp;

			//add children
			if (node.left !=null) {
				queue.add(node.left);
			}
			if (node.right !=null) {
				queue.add(node.right);
			}
		}

		return root;
	};

}
