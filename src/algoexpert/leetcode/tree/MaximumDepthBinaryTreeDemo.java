package algoexpert.leetcode.tree;


//104. Maximum Depth of Binary Tree
public class MaximumDepthBinaryTreeDemo {

    public static void main(String[] args) {
	    TreeNode root = new TreeNode(3,
		    new TreeNode(9,
			    new TreeNode(8, null, null),
			    null),
		    new TreeNode(20,
			    new TreeNode(15, null, null),
			    new TreeNode(7, null, null)
		    )
	    );

	    System.out.println(maxDepth(root));

    }

    public static int maxDepth(TreeNode root) {
		if(root == null){
		    return 0;
	    }

	    int l = maxDepth(root.left);
	    int r = maxDepth(root.right);

		l++;
		r++;

	    return (l > r) ? l : r;
    }
}
