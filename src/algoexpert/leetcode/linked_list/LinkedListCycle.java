package algoexpert.leetcode.linked_list;

//141. Linked List Cycle
public class LinkedListCycle {

	public static void main(String[] args) {
		// Create:
		// 3 → 2 → 0 → -4
		//     ↑         ↓
		//     └─────────┘

		ListNode
			node1 = new ListNode(1);
		ListNode
			node2 = new ListNode(2);
		ListNode
			node3 = new ListNode(3);
//		ListNode node4 = new ListNode(-4);

		node1.next = node2;
		node2.next = node3;
//		node3.next = node4;

		// This creates the cycle.
		node3.next = node1;

		System.out.println(hasCycle(node1));
	}

	// Time Complexity O(n) Space Complexity O(1)
	public static boolean hasCycle(ListNode head) {
		if (head == null || head.next == null) {
			return false;
		}

		ListNode slow = head;
		ListNode fast = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;

			if (slow == fast) {
				return true;
			}
		}
		return false;
	}

	/* Set approach // Time Complexity O(n) Space Complexity O(n)
	public static boolean hasCycle(ListNode head) {
		if(head == null){
			return false;
		}

		Set<ListNode> set = new HashSet<>();

		while (head.next != null){
			ListNode current = head;
			if (set.contains(current)){
					return true;
			}
			set.add(current);
			head = current.next;
		}
		return false;
	}*/
}
