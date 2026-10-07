package algoexpert.leetcode.two_pointers;

public class RemoveDuplicatesFromSortedArray_04_2026 {

	public static void main(String[] args) {
		int arr[] = {1,2,2,2, 3,4,4,5};
		int result = removeDuplicates(arr);
		System.out.println(result);
	}

	/**
	 * Видаляє дублікати з відсортованого масиву без додаткової пам'яті.

	 * Інсайт: інтуїтивно тягнешся до двох окремих вказівників (i та j),
	 * але у відсортованому масиві дублікати завжди стоять поруч —
	 * тому достатньо порівняти поточний елемент із попереднім ({@code nums[i] != nums[i-1]}),
	 * не додумався що можна порівняти не i та j між собою,
	 * а можна порівняти i та i-1.
	 *
	 * @param nums відсортований масив цілих чисел
	 * @return кількість унікальних елементів

	 * See {@link RemoveDuplicatesfromSortedArray#removeDuplicates2(int[])}
	 *
	 * @implNote Час: O(n), Пам'ять: O(1)
	 */

	//two pointers
	public static int removeDuplicates(int[] nums) {
		int j = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[j]){
				continue;
			}
			if (nums[i] > nums[j]){
				nums[j + 1] = nums[i];
				j++;
			}
		}
		return j+1;
	}
}
