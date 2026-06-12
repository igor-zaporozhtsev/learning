package algoexpert.leetcode.two_pointers;

public class MiddleOfLinkedList {

	public static void main(String[] args) {
		// 1 -> 2 -> 3 -> 4 -> 5
		ListNodeMiddle head =
			new ListNodeMiddle(
				1,
				new ListNodeMiddle(
					2,
					new ListNodeMiddle(
						3,
						new ListNodeMiddle(
							4,
							new ListNodeMiddle(
								5,
								new ListNodeMiddle(
									6,
									new ListNodeMiddle(
										7,
										new ListNodeMiddle(
											8,
											new ListNodeMiddle(
												9,
												new ListNodeMiddle(
													10,
													null
												)

											)
										)
									)
								)
							)

						)
					)
				)
			);

		ListNodeMiddle middle = middleNode(head);

		System.out.println("Middle node value: " + middle.val);
	}

	/* * *  fast кожну ітерацію додає +1 до свого відриву від slow, тобто не додає скільки проків він робе а збільщується
	дистанція яку він зробив і ми порівнюємо дистанцію яку пройшлт slow та fast.
	тобто накописується дистанція
	slow накопив/зробив 5 кроків
	fast накопив/зробив 10 кроків
	*   приклда забіг спортсменів та відрив відносно дистанції яку вони пройдуть
	* Коля пройде в половину меши дистанцію ніж Вася
	* ми не дивимось на на кроки колі та васі ми дивимось на дистанцію яку воний пройшли роблячі ці кроки
	* * * */

	public static ListNodeMiddle middleNode(ListNodeMiddle head) {
		ListNodeMiddle slow = head;
		ListNodeMiddle fast = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		return slow;
	}

}

// Definition for singly-linked list
class ListNodeMiddle {

	int val;
	ListNodeMiddle next;

	ListNodeMiddle() {
	}

	ListNodeMiddle(int val) {
		this.val = val;
	}

	ListNodeMiddle(
		int val,
		ListNodeMiddle next
	) {
		this.val = val;
		this.next = next;
	}
}