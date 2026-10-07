package algoexpert.leetcode.linked_list;

import algoexpert.leetcode.recursion.ReverseLinkedList.ListNode;

//206. Reverse Linked List
public class ReverseLinkedList {

	public static void main(String[] args) {
		ListNode node =
			new ListNode(1,
				new ListNode(2,
					new ListNode(3,
						new ListNode(4))));
		ListNode reversed = reverseList(node);
		System.out.println(reversed);
	}

	//1-2-3-4
	public static ListNode reverseList(ListNode head) {
		if (head.next == null){
			return head;
		}
		ListNode node = reverseList(head.next);
		head.next.next = head;
		head.next = null;
		return node;
	}

}
