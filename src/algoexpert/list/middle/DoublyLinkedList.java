package algoexpert.list.middle;

class Program {

    public static void main(String[] args) {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();

        //setHead
        doublyLinkedList.setHead(new Node(5));
        doublyLinkedList.setHead(new Node(3));
        doublyLinkedList.setHead(new Node(2));
        doublyLinkedList.setHead(new Node(1));
        doublyLinkedList.setHead(new Node(4));

        //setTail
        doublyLinkedList.setTail(new Node(6));

        //insertBefore
        doublyLinkedList.insertBefore(new Node(6), new Node(3));

        //doublyLinkedList.removeNodesWithValue(3);


        Node current = doublyLinkedList.head;

        while (current != null) {

            System.out.print(current.value + " -> ");

            current = current.next;
        }


    }

    static class DoublyLinkedList {
        public Node head;
        public Node tail;

        public void setHead(Node node) {
            // Write your code here.
            if (this.head != null) {
                Node current = head;
                current.prev = new Node(node.value);

                this.head = node;
                head.next = current;


            } else {
                setTail(node);
                head = node;
            }
        }

        public void setTail(Node node) {
            // Write your code here.
            if (head == null) {
                tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = new Node(node.value);
            }

        }

        //node and nodeToInsert already exist
        //just mode nodeToInsert before node
        public void insertBefore(Node node, Node nodeToInsert) { // nodeToInsert before
            //input  4 1 2 3 5 6
            //       4 1 2   5   6
            //output 4 1 2 5 3 6

            // Write your code here.
//            nodeToInsert.prev.next = nodeToInsert.next;
//            nodeToInsert.next.prev = nodeToInsert.prev;


            Node current = this.head;

            while (current != null) {

                if (current.value == nodeToInsert.value) {
                    //remove(current);

                    current.prev.next = current.next;
                    current.next.prev = current.prev;


                    /*
                    //5
                    if (node.prev == null) node.prev = nodeToInsert;
                    node.prev.next = nodeToInsert; //npe
                    nodeToInsert.prev = node.prev;

                    //6
                    node.prev = nodeToInsert;
                    nodeToInsert.next = node;

                    */

                    //
                    break;

                }
                current = current.next;
            }


        }

        public void insertAfter(Node node, Node nodeToInsert) {
            // Write your code here.

            remove(nodeToInsert);
            if (node.next == null) node.next = nodeToInsert;

            nodeToInsert.next = node.next;
            node.next.prev = nodeToInsert;

            node.next = nodeToInsert;
            nodeToInsert.prev = node;


        }

        public void insertAtPosition(int position, Node nodeToInsert) {
            // Write your code here.
            while (position == 0) {

                Node current = head.next;

                //5-6-7
                current.prev.next = nodeToInsert;
                nodeToInsert.prev = current.prev;
                nodeToInsert.next = current;
                current.prev = nodeToInsert;

                position--;
            }

        }

        public void removeNodesWithValue(int value) {
            // Write your code here.
            Node current = head;

            while (current.next != null) {
                if (current.value == value) {
                    remove(current);
                    break;
                }
                current = current.next;
            }

        }

        public void remove(Node node) {
            // Write your code here.
            if (head == node) head = head.next;
            if (tail == node) tail = tail.prev;

            if (node.prev != null) node.prev.next = node.next;
            if (node.next != null) node.next.prev = node.prev;

            node.next = null;
            node.prev = null;

        }

        public boolean containsNodeWithValue(int value) {
            // Write your code here.
            Node current = head;

            while (current.next != null) {
                if (current.value == value) {
                    return true;

                }
                current = current.next;
            }
            return false;
        }

    }

    // Do not edit the class below.
    static class Node {
        public int value;
        public Node prev;
        public Node next;

        public Node(int value) {
            this.value = value;
        }


    }
}
