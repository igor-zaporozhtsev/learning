package algoexpert.leetcode.tree;

//Lowest Common Ancestor in Binary Search Tree
public class LowestCommonAncestorInBinarySearchTree {

	void main() {
		TreeNode root = new TreeNode(6,
			new TreeNode(2,
				new TreeNode(0, null, null),
				new TreeNode(4,
					new TreeNode(3, null, null),
					new TreeNode(5, null, null))),
			new TreeNode(8,
				new TreeNode(7, null, null),
				new TreeNode(9, null, null)));

//		TreeNode p = new TreeNode(0);
//		TreeNode q = new TreeNode(4);

//		TreeNode p = new TreeNode(4);
//		TreeNode q = new TreeNode(5);

		TreeNode p = new TreeNode(5);
		TreeNode q = new TreeNode(2);

		System.out.println(lowestCommonAncestor(root, p, q).val);
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		TreeNode cur = root;

		while (cur !=  null){
			if (p.val > cur.val && q.val > cur.val){
				//go right
				cur = cur.right;
			} else if (p.val < cur.val && q.val < cur.val) {
				//go left
				cur = cur.left;
			} else {
				return cur;
			}
		}

		return null;

	}
}
