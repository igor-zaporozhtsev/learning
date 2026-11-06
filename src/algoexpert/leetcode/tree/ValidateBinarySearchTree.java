package algoexpert.leetcode.tree;

//98. Validate Binary Search Tree
public class ValidateBinarySearchTree {

	void main(String[] args) {
		TreeNode root = new TreeNode(5,
			new TreeNode(1,
				new TreeNode(4, null, null),
				new TreeNode(3, null, null)),
			new TreeNode(6, null, null));

		System.out.println(isValidBST(root));
	}

	public boolean isValidBST(TreeNode root) {
		return valid(root, null, null);
	}

	// min and max are exclusive bounds; null means "no bound on this side"
	private boolean valid(TreeNode node, Integer min, Integer max) {
		if (node == null) {
			return true;
		}
		if ((min != null && node.val <= min) ||
			(max != null && node.val >= max)) {
			return false;
		}
		return valid(node.left, min, node.val)
			&& valid(node.right, node.val, max);
	}
}
