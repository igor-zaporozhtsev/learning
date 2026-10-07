package algoexpert.leetcode.tree;

class TreeNode {
    protected int val;
    protected TreeNode left;
    protected TreeNode right;

    TreeNode() {

    }

    TreeNode(int val) {
        this.val = val;
    }

    static TreeNode valueOf(int value) {
        return new TreeNode(value);
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


	@Override
	public String toString() {
		return print(this, 0);
	}

	private String print(TreeNode node, int level) {
		if (node == null) return "";

		String result = "";

		result += print(node.right, level + 1);

		result += "    ".repeat(level) + node.val + "\n";

		result += print(node.left, level + 1);

		return result;
	}


}
