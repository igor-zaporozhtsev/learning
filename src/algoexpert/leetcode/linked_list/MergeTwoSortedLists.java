package algoexpert.leetcode.linked_list;

import algoexpert.leetcode.recursion.ReverseLinkedList.ListNode;

//21. Merge Two Sorted Lists
public class MergeTwoSortedLists {

	public static void main(String[] args) {
		ListNode node1 = new ListNode(1, new ListNode(2, new ListNode(4, new ListNode(5,  new ListNode(6)))));
		ListNode node2 = new ListNode(1, new ListNode(3, new ListNode(4, null)));
		ListNode merged = mergeTwoLists(node1, node2);
		System.out.println(merged);
	}

	//1-2-4
	//1-3-4

	public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		if (list1 == null) {
			return list2;
		}
		if (list2 == null) {
			return list1;
		}

		if (list1.val <= list2.val){
			ListNode head = mergeTwoLists(list1.next, list2);
			list1.next = head;
			return list1;
		} else {
			ListNode head = mergeTwoLists(list1, list2.next);
			list2.next = head; //4.next -> 5 -> 6 null //чому list2 тут томущо хочемо побудувати list від початку тобто мати  head на початку тому будуємо від великих значень до меленьких
			return list2;
		}
	}
}
