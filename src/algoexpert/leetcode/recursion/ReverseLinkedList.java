package algoexpert.leetcode.recursion;

import java.util.List;

public class ReverseLinkedList {
    //206. Reverse Linked List
    //https://leetcode.com/problems/reverse-linked-list/description/

    public static void main(String[] args) {
        ListNode node =
                new ListNode(1,
                        new ListNode(2,
                                new ListNode(3,
                                        new ListNode(4))));
        ListNode reversed = reverseList2(node);
        System.out.println(reversed);
    }

    private static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode current = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return current;
    }

    private static ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        while (head != null){
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static class ListNode {
	    public int val;
	    public ListNode next;
        public ListNode() {}
        public ListNode(int val) {
            this.val = val;
        }
        public ListNode(
	        int val,
	        ListNode next
        ) {
            this.val = val;
            this.next = next;
        }
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode current = this;
            while (current != null) {
                sb.append(current.val).append(" -> ");
                current = current.next;
            }
            sb.append("null");
            return sb.toString();
        }
    }

}
