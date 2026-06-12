package algoexpert.leetcode.recursion;

public class MergeTwoSortedLists2 {
	//21. Merge Two Sorted Lists
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1, new ListNode(2, new ListNode(4, new ListNode(5, null))));
		ListNode node2 = new ListNode(1, new ListNode(3, new ListNode(4, null)));
		System.out.println(mergeTwoLists(node1, node2));
	}

	public static ListNode mergeTwoLists(ListNode list1, ListNode list2){
		// Base Case: If either list is empty, return the other list
		if (list1 == null) return list2;
		if (list2 == null) return list1;

		// Recursive Step: Compare values and link the smaller node
		if (list1.val <= list2.val) {
			System.out.println("before recursion if list list1.val <= list2");
			System.out.println("list list1.val <= list2 is: " + list1);

			ListNode head = mergeTwoLists(list1.next, list2);

			System.out.println("\n ----- after recursion if list list1.val <= list2");
			System.out.println("list1 " + list1 + " - list2 " + list2);
			System.out.println("list1.next = head is " + head);

			list1.next = head;
			System.out.println("return list 1: " + list1);
			return list1;
		} else {
			System.out.println("before recursion if list1.val > list2");
			System.out.println("list 2 is: " + list1);

			ListNode head = mergeTwoLists(list1, list2.next);

			System.out.println("\n ----- after recursion if  list1.val > list2");
			System.out.println("list1 " + list1 + " - list2 " + list2);
			System.out.println("list2.next = head is " + head);

			list2.next = head;

			System.out.println("return list 2: " + list2);
			return list2;
		}
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



