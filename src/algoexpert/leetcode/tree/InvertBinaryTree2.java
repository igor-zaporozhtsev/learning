package algoexpert.leetcode.tree;


//226. Invert Binary Tree [DFS]
public class InvertBinaryTree2 {
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



    static public TreeNode invertTree(TreeNode root) {
	    if (root == null) return root;

		TreeNode left = invertTree(root.left);
	    TreeNode right = invertTree(root.right);

	    System.out.println("base case");

	    root.left = right;
	    root.right = left;

	    return root;
    }
}
