package algoexpert.leetcode.two_pointers;

//125. Valid Palindrome
public class ValidPalindrome2 {
    public static void main(String[] args) {
//        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
//        System.out.println(isPalindrome("race a car"));
//        System.out.println(isPalindrome("a."));
        System.out.println(isPalindrome("0P"));

    }

    public static boolean isPalindrome(String s) {
	    //remove all not needed charecters
	    char[] chars = s.toLowerCase().replaceAll("[^a-zA-Z0-9]", "").toCharArray();

		int left = 0;
		int right = chars.length - 1;

	    while (left <= right){
			if (chars[left] != chars[right]){
				return false;
			}
		    left++;
		    right--;
	    }
		return true;
    }
}
