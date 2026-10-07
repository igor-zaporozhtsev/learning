package algoexpert.leetcode.linked_list;

//143.Reorder List
public class ReorderList {

	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);

		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;

		reorderList(node1);

		/*
			Input: head = [1,2,3,4,5]
			Output: [1,5,2,4,3]
		* */

	}

	public static void reorderList(ListNode head) {
		//Fast and Slow pointer to define middle of the list
		if(head == null || head.next == null){
			return;
		}

		ListNode slow = head;
		ListNode fast = head.next;
		while (fast != null && fast.next != null){
			slow = slow.next;
			fast = fast.next.next;
		}
		//reverse second half
		ListNode sec = slow.next;
		slow.next = null;
		ListNode prev = null;
		while (sec != null){
			ListNode temp = sec.next;
			sec.next = prev;
			prev = sec;
			sec = temp;
		}
		//merge to half
		ListNode first = head;
		ListNode second = prev;
		while (second != null){
			ListNode temp1 = first.next;
			ListNode temp2 = second.next;
			first.next = second;
			second.next = temp1;
			first = temp1;
			second = temp2;
		}

		System.out.println();
	}

}