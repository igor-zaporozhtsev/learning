package algoexpert.list.easy;

public class RemoveDuplicates {
    public static void main(String[] args) {
        LinkedList list1 = new LinkedList(1);
        LinkedList list2 = new LinkedList(1);
        LinkedList list3 = new LinkedList(3);
        LinkedList list4 = new LinkedList(4);
        LinkedList list5 = new LinkedList(4);
        LinkedList list6 = new LinkedList(4);
        LinkedList list7 = new LinkedList(5);
        LinkedList list8 = new LinkedList(6);
        LinkedList list9 = new LinkedList(6);
        list1.next = list2;
        list2.next = list3;
        list3.next = list4;
        list4.next = list5;
        list5.next = list6;
        list6.next = list7;
        list7.next = list8;
        list8.next = list9;

        //  removeDuplicatesFromLinkedList(list1);
        System.out.println("removeDuplicatesFromLinkedList -- " + removeDuplicatesFromLinkedList(list1));

    }

    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }

        @Override
        public String toString() {
            return "LinkedList{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    public static LinkedList removeDuplicatesFromLinkedList(LinkedList linkedList) {
        LinkedList currentNode = linkedList;
        while (currentNode != null) {
            while (currentNode.next != null && currentNode.next.value == currentNode.value) {
                currentNode.next = currentNode.next.next;
            }
            currentNode = currentNode.next;
        }
        return linkedList;
    }
}
