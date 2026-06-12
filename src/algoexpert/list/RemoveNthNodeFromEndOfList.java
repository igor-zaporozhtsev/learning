package algoexpert.list;

public class RemoveNthNodeFromEndOfList {

	public static void main(String[] args) {
		ListNodeR list = new ListNodeR(1, new ListNodeR(2, new ListNodeR(3, new ListNodeR(4, new ListNodeR(5)))));
//		ListNodeR list = new ListNodeR(1, new ListNodeR(2, null));
//		ListNodeR list = new ListNodeR(1, null);

		System.out.println(removeNthFromEnd(list, 2));
	}

	public static ListNodeR removeNthFromEnd(ListNodeR head, int n) {
		ListNodeR left = head;
		ListNodeR right = head;

		if (head.next == null && n == 1){
			return null;
		}

		//set the right pointer to n
		for (int i = 0; i < n; i++) {
			right = right.next;
		}

		if (right == null) {
			return head.next;
		}

		while (right.next != null) {
			System.out.println("left: " + left.val + " - " + "right: " + right.val);
			right = right.next;
			left = left.next;
		}

		left.next = left.next.next;
		return head;
	}
}


class ListNodeR {
      int val;
      ListNodeR next;

	  ListNodeR() {

      }

	  ListNodeR(
		  int val
      ) {
		  this.val = val;
	  }

	  ListNodeR(
		  int val,
		  ListNodeR next
      ) {
		  this.val = val;
		  this.next = next;
	  }

	@Override
	public String toString() {
		return "ListNodeR{" +
			"val=" + val +
			", next=" + next +
			'}';
	}
}