package algoritms.cache;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class LRUCacheByteByte {

	public static void main(String[] args) {
		LRUCache cache = new LRUCache(10);
		cache.put(1, 2);
		Integer i = cache.get(1);
	}
}

class DoublyLinkedListNode {
	int key;
	int val;
	DoublyLinkedListNode prev;
	DoublyLinkedListNode next;

	public DoublyLinkedListNode(int key, int val) {
		this.key = key;
		this.val = val;
		this.prev = null;
		this.next = null;
	}
}

class LRUCache {
	private int capacity;
	// A hash map that maps keys to nodes.
	private HashMap<Integer, DoublyLinkedListNode> hashmap;
	// Initialize the head and tail dummy nodes and connect them to
	// each other to establish a basic two-node doubly linked list.
	private DoublyLinkedListNode head;
	private DoublyLinkedListNode tail;

	public LRUCache(Integer capacity) {
		this.capacity = capacity;
		this.hashmap = new HashMap<>();
		this.head = new DoublyLinkedListNode(-1, -1);
		this.tail = new DoublyLinkedListNode(-1, -1);
		this.head.next = this.tail;
		this.tail.prev = this.head;
	}

	public Integer get(Integer key) {
		if (!hashmap.containsKey(key)) {
			return -1;
		}
		// To make this key the most recently used, remove its node and
		// re-add it to the tail of the linked list.
		DoublyLinkedListNode node = hashmap.get(key);
		removeNode(node);
		addToTail(node);
		return node.val;
	}

	public void put(Integer key, Integer value) {
		// If a node with this key already exists, remove it from the
		// linked list.
		if (hashmap.containsKey(key)) {
			removeNode(hashmap.get(key));
		}
		DoublyLinkedListNode node = new DoublyLinkedListNode(key, value);
		hashmap.put(key, node);
		// Remove the least recently used node from the cache if adding
		// this new node will result in an overflow.
		if (hashmap.size() > capacity) {
			DoublyLinkedListNode lru = head.next;
			hashmap.remove(lru.key);
			removeNode(lru);
		}
		addToTail(node);
	}

	private void addToTail(DoublyLinkedListNode node) {
		DoublyLinkedListNode prevNode = tail.prev;
		node.prev = prevNode;
		node.next = tail;
		prevNode.next = node;
		tail.prev = node;
	}

	private void removeNode(DoublyLinkedListNode node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}
}
