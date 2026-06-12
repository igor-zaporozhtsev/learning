package algoexpert.leetcode.recursion;

import static algoexpert.leetcode.recursion.RemoveLinkedListElements.remove;

public class RemoveLinkedListElements {
	public static void main(String[] args) {
		ListNode list =
			new ListNode(1,
				new ListNode(2,
					new ListNode(6,
						new ListNode(3,
							new ListNode(4,
								new ListNode(5,
									new ListNode(6,
										null
									)
								)
							)
						)
					)
				)
			);

		ListNode list2 =
			new ListNode(7,
				new ListNode(7,
					null
				)
			);

		ListNode list3 =
			new ListNode(1,
				new ListNode(2,
					null
				)
			);
		System.out.println(removeElements(list3, 1));
	}

	public static ListNode removeElements(ListNode head, int val) {
		if (head == null) return head;
		ListNode node = remove(head, val);
		if(node.next == null && node.val == val){
			return null;
		}
		if (node.val == val){
			return node.next;
		}

		return node;


	}

	public static ListNode remove(ListNode head, int val){
		if (head.next == null) return head;

		ListNode returnedNode = remove(head.next, val);

		if (returnedNode.val == val){
			head.next = returnedNode.next;
		}

		return head;
	}

	static class ListNode {
		int val;
		ListNode next;

		ListNode() {

		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}

		@Override
		public String toString() {
			return "ListNode{" +
				"val=" + val +
				", next=" + next +
				'}';
		}
	}
}
