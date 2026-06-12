package algoexpert.list;

public class DeleteNodeInALinkedList {
	public static ListNode list = new ListNode(4);

	public static void main(String[] args) {
		list.next = new ListNode(5).next = new ListNode(1).next = new ListNode(9);
		deleteNode(new ListNode(5));
	}

	public static void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
	}
}

class ListNode {
      int val;
      ListNode next;

      public ListNode(int x) {
		  val = x;
	  }
 }
