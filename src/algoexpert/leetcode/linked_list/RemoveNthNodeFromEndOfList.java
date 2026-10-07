package algoexpert.leetcode.linked_list;

import java.util.List;

//19. Remove Nth Node From End of List
public class RemoveNthNodeFromEndOfList {

	public static void main(String[] args) {
//		ListNode node =
//			new ListNode(1,
//				new ListNode(2,
//					new ListNode(3,
//						new ListNode(4,
//							new ListNode(5
//							)))));

		ListNode node = new ListNode(1, new ListNode(2));
		ListNode reversed = removeNthFromEnd(node, 2);
		System.out.println(reversed);
	}

	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;

		ListNode first = dummy;
		ListNode second = dummy;

		//define step fo second
		for (int i = 0; i < n; i++) {
			second = second.next;
		}

		while (second.next != null){
			first = first.next;
			second = second.next;
		}
		first.next = first.next.next;

		return dummy.next;
	}
}
