import org.junit.Test;

import java.io.Console;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ProblemTest {

    public static class TwoSumProblem {

        @Test
        public void twoSumShouldGiveTwoSums() {
            int[] parts = Solution.twoSum(new int[]{6, 3, 2, 1, 4, 11}, 12);
            assertTrue(IntStream.of(parts).anyMatch(part -> part == 3));
            assertTrue(IntStream.of(parts).anyMatch(part -> part == 5));
        }

        @Test
        public void twoSumShouldGiveTwoSumsWithSameNumber() {
            int[] parts = Solution.twoSum(new int[]{6, 6, 2, 1, 4, 11}, 12);
            assertTrue(IntStream.of(parts).anyMatch(part -> part == 0));
            assertTrue(IntStream.of(parts).anyMatch(part -> part == 1));
        }
    }

    public static class AtoiProblem {
        @Test
        public void atoiShouldConvertNumberToNumber() {
            assertThat(Solution.myAtoi("1"), is(1));
        }

        @Test
        public void atoiShouldThrowExceptionForNonNumbers() {
            try {
                Solution.myAtoi("Hodor");
                assertFalse("Should Throw Illegal Argument Exception", true);
            } catch (IllegalArgumentException ex) {
            }
        }
    }

    public static class ComputeArraySubsetMatch {
        @Test
        public void computeIntersectionForSimilarArrays() {
            assertThat(Solution.computeArraySubsetMatch(new int[]{1, 1}, new int[]{1, 1}), is(new int[]{1, 1}));
        }

        @Test
        public void computeIntersectionForArrayWhichIsASubsetOfLargerArray() {
            assertThat(Solution.computeArraySubsetMatch(new int[]{2, 1, 3}, new int[]{1, 3}), is(new int[]{1, 3}));
        }
    }

    public static class ComputeIntersectionOfArray {

        @Test
        public void intersectShouldReturnAllMatchingElements() {
            assertThat(Solution.computeMatchingArrayElements(new int[]{1, 1}, new int[]{1}), is(new int[]{1}));
        }

        @Test
        public void intersectShouldReturnAllMatchingElementsOfSubset() {
            assertThat(Solution.computeMatchingArrayElements(new int[]{2, 1, 3}, new int[]{1, 3}), is(new int[]{1, 3}));
        }

    }

    public static class ComputeSimpleIntersectionOfArray {

        @Test
        public void shouldReturnNonRepeatedArrayIntersection() {
            assertThat(Solution.computeSimpleIntersection(new int[]{1, 2, 3}, new int[]{2, 2}), is(new int[]{2}));
        }

    }

    public static class RemoveDuplicatesInPlace {

        @Test
        public void shouldRemoveDuplicatesInPlace() {
            assertThat(Solution.removeDuplicates(new int[]{1, 1, 2, 2, 3, 4, 4, 5}), is(5));
        }

    }

    public static class NimGame {

        @Test
        public void shouldPredictVictory() {
            assertThat(Solution.canWinNim(4), is(false));
            assertThat(Solution.canWinNim(5), is(true));
            assertThat(Solution.canWinNim(6), is(true));
            assertThat(Solution.canWinNim(7), is(true));
            assertThat(Solution.canWinNim(8), is(false));
        }

    }

    public static class IntegerCubeRoot {

        @Test
        public void shouldReturnTrueFor1() {
            assertThat(Solution.hasIntegerCubeRoot(1), is(true));
        }

        @Test
        public void shouldReturnFalseFor2() {
            assertThat(Solution.hasIntegerCubeRoot(2), is(false));
        }

        @Test
        public void shouldReturnTrueFor8() {
            assertThat(Solution.hasIntegerCubeRoot(8), is(true));
        }

    }

    public static class PowerOfThree {

        @Test
        public void shouldReturnTrueFor1() {
            assertThat(Solution.isPowerOfThree(1), is(true));
        }

        @Test
        public void shouldReturnTrueFor3And9() {
            assertThat(Solution.isPowerOfThree(3), is(true));
            assertThat(Solution.isPowerOfThree(9), is(true));
        }

        @Test
        public void shouldReturnTrueFor8() {
            assertThat(Solution.isPowerOfThree(8), is(false));
        }


        @Test
        public void testIsPowerOfThreeIteration() {
            assertThat(Solution.isPowerOfThreeIteration(0), is(false));
            assertThat(Solution.isPowerOfThreeIteration(1), is(true));
            assertThat(Solution.isPowerOfThreeIteration(8), is(false));
            assertThat(Solution.isPowerOfThreeIteration(9), is(true));
        }
    }

    public static class ValidAnagram {

        @Test
        public void shouldReturnTrueForAnagramWordPairs() {
            assertThat(Solution.isAnagram("anagram", "nagaram"), is(true));
            assertThat(Solution.isAnagram("Anagram", "Nagaram"), is(true));
            assertThat(Solution.isAnagram("ab", "a"), is(false));
            assertThat(Solution.isAnagram("rat", "car"), is(false));
        }

    }

    public static class ThreeSumProblem {

        @Test
        public void shouldReturnAllThreeSums() {
            List<List<Integer>> threeSums = Solution.getThreeSums(new int[]{-2, -1, 0, 1, 34, 9});
            assertThat(threeSums.size(), is(1));
            assertThat(threeSums.get(0).get(0), is(-1));
            assertThat(threeSums.get(0).get(1), is(0));
            assertThat(threeSums.get(0).get(2), is(1));

        }

        @Test
        public void shouldReturnAllThreeSumsForLargeImput() {
            List<List<Integer>> threeSums = Solution.getThreeSums(new int[]{7, -1, 14, -12, -8, 7, 2, -15, 8, 8, -8, -14, -4, -5, 7, 9, 11, -4, -15, -6, 1, -14, 4, 3, 10, -5, 2, 1, 6, 11, 2, -2, -5, -7, -6, 2, -15, 11, -6, 8, -4, 2, 1, -1, 4, -6, -15, 1, 5, -15, 10, 14, 9, -8, -6, 4, -6, 11, 12, -15, 7, -1, -9, 9, -1, 0, -4, -1, -12, -2, 14, -9, 7, 0, -3, -4, 1, -2, 12, 14, -10, 0, 5, 14, -1, 14, 3, 8, 10, -8, 8, -5, -2, 6, -11, 12, 13, -7, -12, 8, 6, -13, 14, -2, -5, -11, 1, 3, -6});
            assertThat(threeSums.size(), is(1));
            assertThat(threeSums.get(0).get(0), is(-1));
            assertThat(threeSums.get(0).get(1), is(0));
            assertThat(threeSums.get(0).get(2), is(1));

        }

    }

    public static class ContainsDuplicate {

        @Test
        public void shouldReturnTrueWhenArrayContainsDuplicate() {
            assertThat(Solution.containsDuplicate(new int[]{1, 1, 2, 4}), is(true));
            assertThat(Solution.containsDuplicate(new int[]{1, 2, 4}), is(false));
        }

    }

    public static class MoveZeroes {

        @Test
        public void shouldMoveZeroes() {
            int[] nums = {1, 1, 0, 0, 4, 0};
            Solution.moveZeroes(nums);
            assertThat(nums, is(new int[]{1, 1, 4, 0, 0, 0}));
        }

    }

    public static class HammingWeight {

        @Test
        public void shouldPrintNumberOfOneBits() {

            System.out.println(Integer.toBinaryString(-1));
            System.out.println(Integer.toBinaryString(-2147483647));
            int i = 1 | -2147483647;

            System.out.println(i);
            System.out.println(Integer.toBinaryString(i));

            int x = -1;
            System.out.println(x >>= 1);
            System.out.println(x <<= 1);
            System.out.println(x >>= 1);
            System.out.println(x >>= 1);
//          I'm too lazy to right hammingWeight test, :yawn !
//          will cook some Biryani instead :D
        }

    }

    public static class MaxProfit {

        @Test
        public void shouldGetMaxProfit() {
            assertThat(Solution.maxProfit(new int[]{0, 1, 5, 2, -1, 13}), is(14));
        }

    }

    public static class GetIntersectionLinkedList {

        @Test
        public void shouldFindIntersectingNode() {
            ListNode someHead = new ListNode(1);
            assertThat(Solution.getIntersectionNode(someHead, someHead), is(someHead));
        }

    }

    public static class LeverOrderTraversal {

        @Test
        public void shouldTraverseTreeInLevelOrder() {

            TreeNode root = new TreeNode(1);
            TreeNode level1Left = new TreeNode(2);
            root.left = level1Left;
            root.right = new TreeNode(3);
            level1Left.left = new TreeNode(4);
            level1Left.right = new TreeNode(5);
            assertThat(Solution.leverOrderTraverse(root), is(new int[]{1, 2, 3, 4, 5}));

        }

    }

    public static class IsPalindrome {

        @Test
        public void shouldReturnTrueForSingleDigitNumbers() {
            assertThat(Solution.isPalindromeOneVariable(3), is(true));
            assertThat(Solution.isPalindromeOneVariable(0), is(true));
            assertThat(Solution.isPalindromeOneVariable(-3), is(false));
            assertThat(Solution.isPalindromeOneVariable(10), is(false));
            assertThat(Solution.isPalindromeOneVariable(89), is(false));
            assertThat(Solution.isPalindromeOneVariable(100), is(false));
            assertThat(Solution.isPalindromeOneVariable(1001), is(true));
            assertThat(Solution.isPalindromeOneVariable(1000021), is(false));
        }

    }

    public static class NumArrayTest {

        @Test
        public void shouldReturnSumBetweenIndices() {
            NumArray numArray = new NumArray(new int[]{1, 2, 1, 0, 1});

            assertThat(numArray.sumRange(0, 0), is(1));
            assertThat(numArray.sumRange(0, 1), is(3));
            assertThat(numArray.sumRange(0, 2), is(4));
            assertThat(numArray.sumRange(1, 1), is(2));
            assertThat(numArray.sumRange(1, 2), is(3));

            NumArray numArray2 = new NumArray(new int[]{});
        }

    }

    public static class ReverseString {

        @Test
        public void shouldReverseString() {
            char[] chars = new char[]{'a', 'b'};
            System.out.print(new String(chars));
            System.out.print(String.valueOf(chars));
        }

    }

    public static class AddUsingLogicalOperators {

        @Test
        public void shouldAddNumber() {
            assertThat(Solution.getSum(1, 1), is(2));
            assertThat(Solution.getSum(1, 3), is(4));
            assertThat(Solution.getSum(1, 2), is(3));
            assertThat(Solution.getSum(-9, 1), is(-8));
            assertThat(Solution.getSum(-1, -1), is(-2));
        }
    }

    public static class UglyNumber {

        @Test
        public void shouldTestUglyNumber() {
            assertThat(Solution.isUgly(-2147483648), is(false));
            assertThat(Solution.isUgly(-51799), is(false));
        }

    }

    public static class HappyNumber {

        @Test
        public void shouldTestHappyNumber() {
            assertThat(Solution.isHappy(7), is(true));
        }

    }

    public static class RomanToInt {

        @Test
        public void shouldReturnIntegerForRomanNumeral() {
            assertThat(Solution.romanToInt("XIV"), is(14));
        }

    }

    public static class IntToRoman {

        @Test
        public void shouldReturnRomanRomanNumeralOfInteger() {
            assertThat(Solution.intToRoman(14), is("XIV"));
        }

    }

    public static class ReverseInt {

        @Test
        public void shouldReverseInteger() {
            assertThat(Solution.reverseInteger(-123), is(-321));
            assertThat(Solution.reverseInteger(-100), is(-1));
        }

    }

    public static class IsomorphicIntegers {

        @Test
        public void shouldIdentifyIsomorphicIntegers() {
            assertThat(Solution.isIsomorphic("ab", "aa"), is(false));
        }

    }

    public static class CompareVersion {

        @Test
        public void shouldCompareVersion() {
            assertThat(Solution.compareVersion("1", "0"), is(1));
        }

    }

    public static class PascalsTraingle2 {

        @Test
        public void shouldWhatever() {
            ArrayList<Integer> integers = new ArrayList<>(10);
            Solution.getRow(2);
        }

    }

    public static class ConvertToTile {

        @Test
        public void shouldConvert26toZ() {
            assertThat(Solution.convertToTitle(26), is("Z"));
        }

    }

    public static class RotateArrayInPlace {

        @Test
        public void shouldRotateArray() {
            int[] nums = {1, 2, 3};
            Solution.rotateInPlace(nums, 2);
            assertThat(nums, is(new int[]{2, 3, 1}));
        }

    }

    public static class RectangleArea {

        @Test
        public void shouldComputeIntersectingRectangleArea() {
            assertThat(Solution.computeArea(0, 0, 1, 1, 10, 10, 11, 11), is(2));
            assertThat(Solution.computeArea(-3, 0, 3, 4, 0, -1, 9, 2), is(45));
        }

    }

    public static class MergeSortedArray {

        @Test
        public void shouldmergeArray() {
            Solution.merge(new int[]{1, 0}, 1, new int[]{2}, 1);
        }

    }

    public static class PalindromeLinkedList {

        @Test
        public void shouldTestPalindromeLinkedList() {
            ListNode l1 = new ListNode(1);
            ListNode l2 = new ListNode(0);
            ListNode l3 = new ListNode(3);
            ListNode l4 = new ListNode(4);
            ListNode l5 = new ListNode(0);
            ListNode l6 = new ListNode(1);

            l1.next = l2;
            l2.next = l3;
            l3.next = l4;
            l4.next = l5;
            l5.next = l6;

            boolean isPalindrome = Solution.isPalindrome(l1);
            assertTrue(!isPalindrome);
        }

    }

    public static class LongestSubstringNonRepeatingCharacters {

        @Test
        public void shouldTest() {
            assertThat(Solution.lengthOfLongestSubstring(null), is(0));
            assertThat(Solution.lengthOfLongestSubstring(""), is(0));
            assertThat(Solution.lengthOfLongestSubstring("pwwkew"), is(3));
            assertThat(Solution.lengthOfLongestSubstring("a"), is(1));
            assertThat(Solution.lengthOfLongestSubstring("aa"), is(1));
            assertThat(Solution.lengthOfLongestSubstring("aabb"), is(2));
            assertThat(Solution.lengthOfLongestSubstring("abba"), is(2));
            assertThat(Solution.lengthOfLongestSubstring("dvdf"), is(3));
        }

    }

    public static class FourSum {

        @Test
        public void shouldTest() {
            int[] nums = {1, 0, -1, 0, -2, 2};
            List<List<Integer>> fourSum = Solution.fourSum(nums, 0);
            assertThat(fourSum.size(), is(3));
            System.out.print(fourSum);
        }

    }

    public static class CountAndSay {

        @Test
        public void should() {
            assertThat(Solution.countAndSay(1), is("1"));
            assertThat(Solution.countAndSay(2), is("11"));
            assertThat(Solution.countAndSay(3), is("21"));
            assertThat(Solution.countAndSay(4), is("1211"));
            assertThat(Solution.countAndSay(5), is("111221"));
        }

        @Test
        public void shouldTestStringWaala() {
            assertThat(Solution.countAndSayStrings(1), is("1"));
            assertThat(Solution.countAndSayStrings(2), is("11"));
            assertThat(Solution.countAndSayStrings(3), is("21"));
            assertThat(Solution.countAndSayStrings(4), is("1211"));
            assertThat(Solution.countAndSayStrings(5), is("111221"));
        }

    }

    public static class ZeroMatrix {

        @Test
        public void shouldSetZeroes() {
            int[][] matrix = new int[][]{{0, 0, 0, 5}, {4, 3, 1, 4}, {1, 2, 1, 3}, {0, 0, 1, 1}};
            Solution.setZeroesNoExtraSpace(matrix);
        }

    }

    public static class ValidSudoku {

        @Test
        public void shouldTest() {
            char[][] board = createBoard();
            board[0][0] = '1';
            board[0][8] = '1';
            char[][] board2 = createBoard();
            board2[0][0] = '1';
            board2[8][0] = '1';
            char[][] board3 = createBoard();
            board3[0][0] = '1';
            board3[2][2] = '1';
            char[][] board4 = createBoard();


            assertFalse(Solution.isValidSudoku(board));
            assertFalse(Solution.isValidSudoku(board2));
            assertFalse(Solution.isValidSudoku(board3));
            assertTrue(Solution.isValidSudoku(board4));
        }

        private char[][] createBoard() {
            char[][] board = new char[9][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    board[i][j] = '.';
                }
            }
            return board;
        }

    }

    public static class ZigZag {

        @Test
        public void shouldZigZag() {
            assertThat(Solution.convertZigZag("A", 1), is("A"));
            assertThat(Solution.convertZigZag("PAYPALISHIRING", 3), is("PAHNAPLSIIGYIR"));
            assertThat(Solution.convertZigZag("PAYPALISHIRING", 4), is("PINALSIGYAHRPI"));
        }

    }

    public static class BullsAndCows {

        @Test
        public void should() {
            assertThat(Solution.getHint("1123", "0111"),is("1A1B"));
        }

    }

    public static class AddBinary {

        @Test
        public void should() {
            assertThat(Solution.addBinary("1", "11"), is("100"));
            assertThat(Solution.addBinary("1", "111"), is("1000"));
        }

    }

    public static class ReverseBits {

        @Test
        public void shouldReverseBits() {
            assertThat(Solution.reverseBits(43261596), is(964176192));
        }

    }

    public static class StrStr {

        @Test
        public void shouldStrStr() {
            assertThat(Solution.strStrKmp("a", "a"), is(0));
            assertThat(Solution.strStrKmp("abc", "bc"), is(1));
        }

        @Test
        public void shouldKmpPrefix() {
            assertThat(Solution.getNext("abcaby"), is(new int[]{0,0,0,1,2,0}));
            assertThat(Solution.getNext("abcdabca"), is(new int[]{0,0,0,0,1,2,3,1}));
            assertThat(Solution.getNext("aabaabaaa"), is(new int[]{0,1,0,1,2,3,4,5,2}));
            assertThat(Solution.getNext("acacabacacabacacac"), is(new int[]{0,0,1,2,3,0,1,2,3,4,5,6,7,8,9,10,11,4}));
        }


    }

    public static class LongestPalindromicSubstring {

        @Test
        public void shouldReturnLongestPalindrome() {
            assertThat(Solution.longestPalindrome("abcdcaa"), is("cdc"));
            assertThat(Solution.longestPalindrome("abcddcaa"), is("cddc"));
        }

        @Test
        public void shouldReturnLongestPalindromeManachers() {
            assertThat(Solution.longestPalindromeManacher("abcdcaa"), is("cdc"));
            assertThat(Solution.longestPalindromeManacher("abaxabaxabb"), is("baxabaxab"));
            assertThat(Solution.longestPalindromeManacher("abcddcaa"), is("cddc"));
        }

    }

    public static class AddLinkedListNumbers {

        @Test
        public void should() {
            ListNode l1 = new ListNode(9);
            l1.next = new ListNode(8);

            ListNode l2 = new ListNode(1);

            ListNode result = Solution.addTwoNumbers(l1, l2);
            assertThat(result.val,is(0));
            assertThat(result.next.val,is(9));
        }

    }

    public static class NextPermutation {

        @Test
        public void should() {
            int[] nums = {1, 3, 2};
            Solution.nextPermutationEfficient(nums);
            assertThat(nums, is(new int[]{2,1,3}));

//            int[] nums2 = {3, 2, 1};
//            Solution.nextPermutation(nums2);
//            assertThat(nums2, is(new int[]{1,2,3}));

        }

    }

    public static class Permutation {

        @Test
        public void should() {
            Solution.permuteEfficient(new int[]{1, 2, 3, 4});
        }

    }

    public static class SingleNumber2 {

        @Test
        public void should() {
            assertThat(Solution.singleNumber2(new int[]{1,1,1,2}), is(2));
        }

    }

    public static class LongestSequence {

        @Test
        public void shouldReturn0ForNull() {
            assertThat(Solution.getLongestSequence(null), is(0));
        }

        @Test
        public void shouldReturn0ForEmptyString() {
            assertThat(Solution.getLongestSequence(""), is(0));
        }


        @Test
        public void shouldReturn3Forabcabc() {
            assertThat(Solution.getLongestSequence("ababab"), is(2));
            assertThat(Solution.getLongestSequence("abcabc"), is(3));
            assertThat(Solution.getLongestSequence("abcadc"), is(4));
        }

    }

    public static class BeautifulAranta {

        @Test
        public void shouldPass() {
            assertThat(Solution.reduce(new int[]{3,1,2,3}), is(9));
            assertThat(Solution.reduce(new int[]{4,1,2,3,4}), is(19));
        }

    }
    
}
