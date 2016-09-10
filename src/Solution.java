import com.sun.javaws.exceptions.InvalidArgumentException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {

            int compliment = target - nums[i];
            if (map.containsKey(compliment) && i != map.get(compliment)) {
                return new int[]{i, map.get(compliment)};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No matching sum found");
    }

    public static int myAtoi(String text) {
        String trimmedText = text.trim();
        boolean isNegative = false;

        if (trimmedText.startsWith("+")) {
            trimmedText = trimmedText.substring(1);
        } else if (trimmedText.startsWith("-")) {
            trimmedText = trimmedText.substring(1);
            isNegative = true;
        }

        char[] numberCharacters = trimmedText.toCharArray();
        HashMap<Character, Integer> characterIntegerMap = new HashMap<>();
        characterIntegerMap.put('0', 0);
        characterIntegerMap.put('1', 1);
        characterIntegerMap.put('2', 2);
        characterIntegerMap.put('3', 3);
        characterIntegerMap.put('4', 4);
        characterIntegerMap.put('5', 5);
        characterIntegerMap.put('6', 6);
        characterIntegerMap.put('7', 7);
        characterIntegerMap.put('8', 8);
        characterIntegerMap.put('9', 9);

        int finalNumber = 0;

        for (int i = 0; i < numberCharacters.length; i++) {
            boolean isNumber = false;
            for (Character key : characterIntegerMap.keySet()) {
                if (numberCharacters[i] == key) {
                    finalNumber = finalNumber * 10 + characterIntegerMap.get(key);
                    isNumber = true;
                    break;
                }
            }
            if (!isNumber) {
                throw new IllegalArgumentException("Non number characters");
            }
        }

        return isNegative ? finalNumber * -1 : finalNumber;
    }

    public static int[] computeArraySubsetMatch(int[] nums1, int[] nums2) {
        ArrayList<Integer> largestMatchingSubArray = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int ii = i;
                int jj = j;
                ArrayList<Integer> matchingSubArray = new ArrayList<>();
                while (ii <= nums1.length - 1 && jj <= nums2.length - 1 && nums1[ii] == nums2[jj]) {
                    matchingSubArray.add(nums2[jj]);
                    ii++;
                    jj++;
                }
                if (matchingSubArray.size() >= largestMatchingSubArray.size()) {
                    largestMatchingSubArray = matchingSubArray;
                }
            }
        }

        return largestMatchingSubArray.stream().mapToInt(i -> i).toArray();
    }

    public static int[] computeMatchingArrayElements(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();

        IntStream.of(nums1).forEach(i -> {
            if (map1.containsKey(i)) {
                map1.replace(i, map1.get(i) + 1);
            } else map1.put(i, 1);
        });
        IntStream.of(nums2).forEach(i -> {
            if (map2.containsKey(i)) {
                map2.replace(i, map2.get(i) + 1);
            } else map2.put(i, 1);
        });

        ArrayList<Integer> interFuckingSection = new ArrayList<>();
        for (Integer map1_key : map1.keySet()) {
            if (map2.containsKey(map1_key)) {
                Integer maxOccurence = map1.get(map1_key) < map2.get(map1_key) ? map1.get(map1_key) : map2.get(map1_key);
                for (int i = 0; i < maxOccurence; i++) {
                    interFuckingSection.add(map1_key);
                }
            }
        }
//        http://articles.leetcode.com/here-is-phone-screening-question-from/
        return interFuckingSection.stream().mapToInt(value -> value).toArray();
    }

    public static int[] computeSimpleIntersection(int[] nums1, int[] nums2) {
        int[] smallerArray = nums2;
        int[] largerArray = nums1;
        if (nums1.length < nums2.length) {
            smallerArray = nums1;
            largerArray = nums2;
        }

        HashMap<Integer, Boolean> smallerNumbers = new HashMap<>();
        IntStream.of(smallerArray).forEach(value -> smallerNumbers.put(value, false));
        ArrayList<Integer> intersectionArray = new ArrayList<>();

        for (int i = 0; i < largerArray.length; i++) {
            if (smallerNumbers.containsKey(largerArray[i]) && !smallerNumbers.get(largerArray[i])) {
                smallerNumbers.replace(largerArray[i], true);
                intersectionArray.add(largerArray[i]);
            }
        }
        return intersectionArray.stream().mapToInt(value -> value).toArray();
    }

    public static int removeDuplicates(int[] nums) {

        int duplicatePosition = 0;
        int differentPosition = 1;


        while (differentPosition < nums.length) {
            if (nums[duplicatePosition] != nums[differentPosition]) {
                //swap
                int temp = nums[duplicatePosition + 1];
                nums[duplicatePosition + 1] = nums[differentPosition];
                nums[differentPosition] = temp;
                duplicatePosition++;
            }

            differentPosition++;
        }

        return duplicatePosition + 1;
    }

    public static boolean canWinNim(int i) {
        return i % 4 == 0 ? false : true;
    }

    public static boolean hasIntegerCubeRoot(int n) {

        if (n == 0) {
            return false;
        }

        if (n == 1) {
            return true;
        }

        for (int i = 0; i < n / 2; i++) {
            if (i * i * i == n) return true;
        }
        return false;
    }

    public static boolean isPowerOfThree(int n) {
        if (n == 1) return true;
        if (n == 0) return false;
        if (n % 3 == 0) return isPowerOfThree(n / 3);
        return false;
    }

    public static boolean isPowerOfThreeIteration(int n) {
        if (n < 1) return false;
        while (n > 0) {
            if (n == 1) return true;
            if (n % 3 == 0) n = n / 3;
            else return false;
        }
        return true;
    }

    public static boolean isAnagram(String str1, String str2) {

        HashMap<Integer, Integer> map = new HashMap<>();
        str1.toLowerCase().chars().forEach(key -> map.put(key, map.containsKey(key) ? map.get(key) + 1 : 1));

        for (Integer charNumber : str2.toLowerCase().chars().toArray()) {
            if (!map.containsKey(charNumber)) return false;
            if (map.get(charNumber) == 1) map.remove(charNumber);
            else map.put(charNumber, map.get(charNumber) - 1);
        }
        if (map.size() == 0) return true;
        else return false;
    }

    public static List<List<Integer>> getThreeSums(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> threeSums = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {

            int j = i + 1, k = nums.length - 1;

            while (j < k) {
                int threeSum = nums[i] + nums[j] + nums[k];
                if (threeSum == 0) {
                    ArrayList<Integer> newSum = new ArrayList<>();
                    newSum.add(nums[i]);
                    newSum.add(nums[j]);
                    newSum.add(nums[k]);
                    boolean duplicateExists = false;
                    for (List<Integer> sum : threeSums) {
                        if (sum.get(0) == nums[i] && sum.get(1) == nums[j] && sum.get(2) == nums[k])
                            duplicateExists = true;
                    }
                    if (!duplicateExists) threeSums.add(newSum);
                    while (j < k && nums[j] == nums[j + 1]) j++;
                    while (j < k && nums[k] == nums[k - 1]) k--;

                    j++;
                    k--;
                } else if (threeSum < 0) {
                    j++;
                } else k--;
            }
        }
        return threeSums;
    }

    public static boolean containsDuplicate(int[] nums) {
        HashMap map = new HashMap<>();
        int i = 0;
        while (i < nums.length) {
            if (map.containsKey(nums[i])) return true;
            map.put(nums[i], 1);
            i++;
        }
        return false;
    }

    public static void moveZeroes(int[] nums) {

        int voyager = 0, earthGuy = 0, temp = 0;
        while (voyager < nums.length) {
            if (nums[earthGuy] != 0) {
                earthGuy++;
            } else if (nums[earthGuy] == 0 && nums[voyager] != 0) {
                temp = nums[earthGuy];
                nums[earthGuy] = nums[voyager];
                nums[voyager] = temp;

                earthGuy++;
            }

            voyager++;
        }
    }

    public static void deleteNode(ListNode node) {

        ListNode nextNode = node.next;
        ListNode nextNextNode = nextNode.next;

        node.val = nextNode.val;
        node.next = nextNextNode;

    }

    public static ListNode reverseListIterative(ListNode head) {

        if (head == null) return null;
        ListNode current = head;
        ListNode previousNode = null;

        while (current != null) {
            ListNode nextNode = current.next;
            current.next = previousNode;

            previousNode = current;
            current = nextNode;
        }
        return previousNode;
    }

    public static ListNode reverseListStack(ListNode head) {

        if (head == null) return null;
        Stack<ListNode> nodes = new Stack<>();

        while (head.next != null) {
            nodes.push(head);
            head = head.next;
        }
        ListNode newHead = head;
        while (nodes.size() != 0) {
            head.next = nodes.pop();
            head = head.next;
        }
        head.next = null;
        return newHead;
    }

    public static ListNode reverseListRecursion(ListNode head) {
        ListNode current = head;
        if (current == null || current.next == null) return current;

        ListNode newHead;
        newHead = reverseListRecursion(current.next);
        current.next.next = current;
        current.next = null;

        return newHead;
    }

    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) count++;
            n >>= 1;
        }
        return count;
    }

    public static int maxProfit(int[] prices) {

        if (prices.length == 0 || prices.length == 1) return 0;
        int maxProfit = 0, minValue = prices[0];

        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < minValue) minValue = prices[i];
            if (maxProfit < prices[i + 1] - minValue) maxProfit = prices[i + 1] - minValue;
        }
        return maxProfit;
    }

    public static ListNode deleteDuplicates(ListNode head) {

        if (head == null || head.next == null) return head; // optimize
        ListNode previous = head;
        ListNode current = head.next;
        while (current != null) {
            if (previous.val != current.val) {
                previous.next = current;
                previous = current;
            }
            if (current.next == null) previous.next = null;
            current = current.next;
        }

        return head;
    }

    public static boolean hasCycle(ListNode head) {

        if (head == null || head.next == null) return false;
        ListNode turtleNode = head;
        ListNode rabbitNode = head.next;


        while (turtleNode.next != null && rabbitNode.next != null && rabbitNode.next.next != null) {
            if (turtleNode != rabbitNode) {
                turtleNode = turtleNode.next;
                rabbitNode = rabbitNode.next.next;
            } else return true;
        }
        return false;
    }

    public static int removeElement(int[] nums, int val) {

        int left = 0, right = nums.length - 1, count = 0;

        while (left < right) {
            if (nums[left] == val && nums[right] != val) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
                count++;
            } else if (nums[left] == val && nums[right] == val) {
                right--;
                count++;
            } else if (nums[left] != val && nums[right] != val) left++;
            else {
                left++;
                right--;
                count++;
            }
        }

        if (left == right && nums[left] == val) count++;

        return nums.length - count;
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int aLength = 0, bLength = 0;
        ListNode originalHeadA = headA, originalHeadB = headB;
        while (headA != null) {
            headA = headA.next;
            aLength++;
        }
        while (headB != null) {
            headB = headB.next;
            bLength++;
        }
        int longerLength, shorterLength;
        ListNode longerList, shorterList;
        if (aLength > bLength) {
            longerLength = aLength;
            shorterLength = bLength;
            longerList = originalHeadA;
            shorterList = originalHeadB;
        } else {
            shorterLength = aLength;
            longerLength = bLength;
            shorterList = originalHeadA;
            longerList = originalHeadB;
        }

        int distanceCovered = 0;
        while (longerList != null) {
            if (distanceCovered < (longerLength - shorterLength)) {
                longerList = longerList.next;
                distanceCovered++;
            } else {
                if (longerList == shorterList) return longerList;
                longerList = longerList.next;
                shorterList = shorterList.next;
            }
        }
        return null;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        ListNode newHead = head;
        ListNode futuristicNode = head;
        ListNode previoudNode = head;
        for (int i = 0; i < n - 1; i++) {
            futuristicNode = futuristicNode.next;
        }
        if (futuristicNode.next == null) return head.next;

        while (futuristicNode.next != null) {
            previoudNode = head;
            head = head.next;
            futuristicNode = futuristicNode.next;
        }
        previoudNode.next = head.next;

        return newHead;
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], i);
            } else {
                if ((map.get(nums[i]) - i) <= k && map.get(nums[i]) - i >= -k) return true;
                else map.put(nums[i], i);
            }
        }
        return false;
    }

    public static int[] leverOrderTraverse(TreeNode root) {
        Queue<Pair<TreeNode, Integer>> q = new LinkedList<>();
        ArrayList<Integer> values = new ArrayList<>();
        q.add(new ImmutablePair<>(root, 0));
        while (q.size() != 0) {
            Pair<TreeNode, Integer> pair = q.remove();
            TreeNode node = pair.getKey();
            Integer level = pair.getValue();
            values.add(node.val);
            System.out.println(level);
            if (node.left != null) q.add(new ImmutablePair<>(node.left, level + 1));
            if (node.right != null) q.add(new ImmutablePair<>(node.right, level + 1));
        }
        return values.stream().mapToInt(value -> value).toArray();

    }

    public static boolean isSymmetricTreeUsingPair(TreeNode root) {

        if (root == null) return false;
        Queue<Pair<TreeNode, Character>> q = new LinkedList<>();
        q.add(new ImmutablePair<>(root.left, 'L'));
        q.add(new ImmutablePair<>(root.right, 'R'));

        Stack<TreeNode> stack = new Stack<>();
        while (q.size() != 0) {
            Pair<TreeNode, Character> nodeChar = q.remove();
            TreeNode node = nodeChar.getKey();
            Character race = nodeChar.getValue();
            if (race == 'L') stack.push(node);
            else {
                if (stack.size() == 0 || stack.pop().val != node.val) return false;
            }

            if (node.left != null) q.add(new ImmutablePair<>(node.left, race));
            if (node.right != null) q.add(new ImmutablePair<>(node.right, race));
        }

        return stack.size() == 0 ? true : false;
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        if (isMirrorOf(root.left, root.right)) return true;
        return false;
    }

    private static boolean isMirrorOf(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if ((left == null && right != null) || (right == null && left != null)) return false;
        if (left.val != right.val) return false;
        if (isMirrorOf(left.left, right.right) && isMirrorOf(left.right, right.left)) return true;
        return false;
    }

    public static boolean isSymmetricUsingQueue(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> q = new LinkedList<>();

        TreeNode r1 = root;
        TreeNode r2 = root;

        q.add(r1);
        q.add(r2);

        while (q.size() != 0) {
            TreeNode left = q.remove();
            TreeNode right = q.remove();


        }
        return false;

    }

    public static boolean isPalindrome(int x) {
        Integer num = x;
        String s = num.toString();

        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) return false;
            start += 1;
            end -= 1;
        }
        return true;
    }

    public static boolean isPalindromeOneVariable(int x) {
        if (x < 0) return false;
//        int LSD = x%10;
//        int MSD = x/((int) Math.pow(10,Math.floor(Math.log10(x))));
        int numberOfDigits = (int) Math.floor(Math.log10(x)) + 1;
        int totalDigitsLeft = numberOfDigits;
        for (int i = 0; i < (numberOfDigits) / 2; i++) {
            if (((int) (x / Math.pow(10, i))) % 10 != (x / (int) Math.pow(10, totalDigitsLeft - 1)) % 10) {
                return false;
            }
            totalDigitsLeft--;
        }
        return true;
    }

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    public static TreeNode invertTreeIterative(TreeNode root) {
        if (root == null) return null;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (q.size() > 0) {
            TreeNode node = q.remove();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if (node.left != null) q.add(node.left);
            if (node.right != null) q.add(node.right);
        }
        return root;
    }

    public static int rob(int[] nums) {
        if (nums.length == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        return rob(map, nums, nums.length - 1);
    }

    public static int rob(HashMap<Integer, Integer> map, int[] nums, int n) {
        if (map.containsKey(n)) return map.get(n);
        if (n == 0) {
            map.put(0, nums[0]);
            return nums[0];
        }
        if (n == 1) {
            map.put(1, Math.max(nums[0], nums[1]));
            return Math.max(nums[0], nums[1]);
        }
        int max = Math.max(rob(map, nums, n - 1), rob(map, nums, n - 2) + nums[n]);
        map.put(n, max);
        return max;
    }

    public int firstBadVersion(int n) {
        int min = 1;
        int max = n;
        while (max > min) {
            int mid = min + (max - min) / 2;
            if (isBadVersion(mid)) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        return max;
    }

    public boolean isBadVersion(int x) {
        return false;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";

        int minLength = strs[0].length();
        for (String str :
                strs) {
            minLength = str.length() < minLength ? str.length() : minLength;
        }
        String commonChars = "";

        boolean isCommon = true;
        for (int i = 0; i < minLength; i++) {
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].charAt(i) != strs[0].charAt(i)) {
                    isCommon = false;
                    break;
                }
            }
            if (isCommon == false) break;
            else {
                commonChars += strs[0].charAt(i);
            }
        }
        return commonChars;
    }

    public static String reverseString(String s) {
        char[] chars = new char[s.length()];
        for (int i = s.length(), j = 0; i > 0; i--, j++) {
            chars[j] = s.charAt(i - 1);
        }
        return String.valueOf(chars);
    }

    public static int getSum(int a, int b) {

        int sum = a ^ b;
        int carry = (a & b);

        while (carry != 0) {
            carry = carry << 1;
            int newSum = sum ^ carry;
            carry = sum & carry;
            sum = newSum;
        }

        return sum;
    }

    public static int addDigits(int num) {
        if (num == 0) return 0;
        int mod = num % 9;
        if (mod != 0) return mod;
        return 9;
    }

    public static int maxDepth(TreeNode root) {
        return maxDepth(root, 0, 0);
    }

    private static int maxDepth(TreeNode root, int currentDepth, int maxDepth) {
        if (root == null) return maxDepth;
        currentDepth++;
        maxDepth++;
        int leftDepth = maxDepth(root.left, currentDepth, maxDepth);
        int rightDepth = maxDepth(root.right, currentDepth, maxDepth);
        int maxChildDepth = Math.max(leftDepth, rightDepth);
        return maxDepth > maxChildDepth ? maxDepth : maxChildDepth;
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if ((p == null && q != null) || (p != null && q == null)) return false;
        Stack<TreeNode> nodes = new Stack<>();
        nodes.push(q);
        nodes.push(p);
        while (nodes.size() != 0) {
            TreeNode tree1 = nodes.pop();
            TreeNode tree2 = nodes.pop();

            if (tree1.val != tree2.val) return false;
            if ((tree1.right != null && tree2.right == null) || (tree1.right == null && tree2.right != null))
                return false;
            if ((tree1.left != null && tree2.left == null) || (tree1.left == null && tree2.left != null)) return false;

            if (tree1.right != null && tree2.right != null) {
                nodes.push(tree2.right);
                nodes.push(tree1.right);
            }
            if (tree1.left != null && tree2.left != null) {
                nodes.push(tree2.left);
                nodes.push(tree1.left);
            }
        }
        return true;
    }

    public static int titleToNumber(String s) {

        int totalColumnNumber = 0;
        for (int i = 0; i < s.length(); i++) {
            totalColumnNumber += s.charAt(i) - 'A' + 1 * Math.pow(26, s.length() - i - 1);
        }
        return totalColumnNumber;
    }

    public static boolean isPowerOfTwoMod(int n) {
        if (n < 0) return false;
        return Math.pow(2, 63) % n == 0 ? true : false;
    }

    public static boolean isUgly(int num) {
        if (num == 1) return true;
        if (num == 0) return false;
//        strip 2s, 3s and 5s
        while (num % 2 == 0) num /= 2;
        while (num % 3 == 0) num /= 3;
        while (num % 5 == 0) num /= 5;

        return (num == 1);
    }

    public static boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();

        while (true) {
            int sum = 0;
            int[] digits = getAllDigits(n);
            for (int digit : digits) {
                sum = sum + (digit * digit);
            }
            if (set.contains(sum)) return false;
            if (sum == 1) return true;
            set.add(sum);
            n = sum;
        }

    }

    private static int[] getAllDigits(int n) {
        ArrayList<Integer> digits = new ArrayList<>();
        while (n > 0) {
            digits.add(n % 10);
            n /= 10;
        }
        return digits.stream().mapToInt(i -> i).toArray();
    }

    public static int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        int nSum = 0;
        int n1Sum = 2;
        int n2Sum = 1;
        for (int i = 3; i <= n; i++) {
            nSum = n1Sum + n2Sum;
            n2Sum = n1Sum;
            n1Sum = nSum;
        }
        return nSum;
    }

    public static int majorityElement(int[] nums) {
        if (nums.length == 1) return nums[0];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            if (map.containsKey(key)) {
                Integer frequency = map.get(key);
                map.put(key, frequency + 1);
                if (frequency + 1 > nums.length / 2) return key;
            } else map.put(key, 1);
        }
        return -1;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) return null;
        if (p == null || q == null) return root;
        TreeNode lca = root;

        while (true) {
            if (p.val < lca.val && q.val < lca.val) {
                lca = lca.left;
            } else if (p.val > lca.val && q.val > lca.val) {
                lca = lca.right;
            } else return lca;
        }
    }

    public static int romanToInt(String roman) {

        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        char largestChar = 'I';
        int sum = 0;
        for (int i = roman.length() - 1; i >= 0; i--) {
            char currentChar = roman.charAt(i);
            if (map.get(currentChar) >= map.get(largestChar)) {
                sum += map.get(currentChar);
                largestChar = currentChar;
            } else {
                sum -= map.get(currentChar);
            }
        }

        return sum;
    }

    public static String intToRoman(int num) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);

        Stack<String> subtractiveRomans = new Stack<>();
        subtractiveRomans.push("I");
        subtractiveRomans.push("IV");
        subtractiveRomans.push("V");
        subtractiveRomans.push("IX");
        subtractiveRomans.push("X");
        subtractiveRomans.push("XL");
        subtractiveRomans.push("L");
        subtractiveRomans.push("XC");
        subtractiveRomans.push("C");
        subtractiveRomans.push("CD");
        subtractiveRomans.push("D");
        subtractiveRomans.push("CM");
        subtractiveRomans.push("M");
        StringBuilder romanBuilder = new StringBuilder();
        while (num > 0) {
            if (num / map.get(subtractiveRomans.peek()) >= 1) {
                romanBuilder.append(subtractiveRomans.peek());
                num -= map.get(subtractiveRomans.peek());
            } else {
                subtractiveRomans.pop();
            }
        }
        return romanBuilder.toString();
    }

    public static boolean isPowerOfFour(int num) {
        if (num <= 0) return false;
        int pow = (int) (Math.log(num) / Math.log(4));
        if (num == Math.pow(4, pow)) return true;
        return false;
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode current = head;
        ListNode previous = null;
        head = current.next;
        while (current != null && current.next != null) {
//            swap
            if (previous != null) previous.next = current.next;
            ListNode nextnext = current.next.next;
            current.next.next = current;
            current.next = nextnext;


            previous = current;
            current = nextnext;
        }
        return head;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        Stack<List<Integer>> stack = new Stack<>();
        if (root == null) return result;

        Queue<MyPair> q = new LinkedList<>();
        q.add(new MyPair(root, 1));
        int curretLevel = 0;
        while (!q.isEmpty()) {
            MyPair MyPair = q.poll();
            TreeNode node = MyPair.node;
            if (MyPair.level > curretLevel) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(node.val);
                stack.push(list);
            } else {
                List<Integer> list = stack.peek();
                list.add(node.val);
            }
            curretLevel = MyPair.level;
            if (node.left != null) q.add(new MyPair(node.left, curretLevel + 1));
            if (node.right != null) q.add(new MyPair(node.right, curretLevel + 1));
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    public String reverseVowels(String s) {
        if (s == null || s == "")
            return s;
        int start = 0;
        int end = s.length() - 1;
        List<Character> vowels = new ArrayList<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');
        char[] chars = s.toCharArray();

        while (start < end) {
            if (!vowels.contains(s.charAt(start))) {
                start++;
                continue;
            }
            if (!vowels.contains(s.charAt(end))) {
                end--;
                continue;
            }

            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
        return new String(chars);
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return getHeightIfBalanced(root) == -1 ? false : true;
    }

    private int getHeightIfBalanced(TreeNode node) {
        if (node == null) return 0;
        int left = getHeightIfBalanced(node.left);
        int right = getHeightIfBalanced(node.right);

        if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;

        return Math.max(left, right) + 1;
    }

    public static int reverseInteger(int x) {
        int newNum = 0;
        boolean isNegative = x < 0;
        if (isNegative) x *= -1;
        while (x != 0) {
            if (newNum != 0 && (Integer.MAX_VALUE - x % 10) / newNum < 10) return 0;
            newNum = newNum * 10 + (x % 10);
            x /= 10;
        }
        return isNegative ? newNum * -1 : newNum;
    }

    public static boolean isIsomorphic(String s, String t) {

        HashMap<Character, Character> map = new HashMap<>();
        HashSet<Character> usedChars = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                if (map.get(s.charAt(i)) != t.charAt(i)) return false;
            } else {
                if (usedChars.contains(t.charAt(i))) return false;
                map.put(s.charAt(i), t.charAt(i));
                usedChars.add(t.charAt(i));
            }
        }
        return true;
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false; // what if sum = 0 ? still retun false ?

        if (root.left == null && root.right == null && root.val == sum) return true;
        if (root.left != null && hasPathSum(root.left, sum - root.val)) return true;
        if (root.right != null && hasPathSum(root.right, sum - root.val)) return true;
        return false;
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        //shift nums1 by n;
        for (int i = m - 1; i >= 0; i--) {
            nums1[i + n] = nums1[i];
        }

        int i = n, j = 0, k = 0;
        while (j < n && i < m + n) {
            if (nums1[i] < nums2[j]) {
                nums1[k] = nums1[i];
                i++;
            } else {
                nums1[k] = nums2[j];
                j++;
            }
            k++;
        }
        while (j < n) {
            nums1[k] = nums2[j];
            k++;
            j++;
        }
        while (i < m) {
            nums1[k] = nums1[i];
            k++;
            i++;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode l3;
        if (l1.val < l2.val) {
            l3 = l1;
            l1 = l1.next;
        } else {
            l3 = l2;
            l2 = l2.next;
        }
        ListNode newHead = l3;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                l3.next = l1;
                l1 = l1.next;
            } else {
                l3.next = l2;
                l2 = l2.next;
            }
            l3 = l3.next;
        }
        if (l1 == null) l3.next = l2;
        else l3.next = l1;

        return newHead;
    }

    public int lengthOfLastWord(String s) {
        s = s.trim();
        if (s.length() == 0) return 0;
        int end = s.length() - 1;
        int length = 0;
        while (end >= 0 && s.charAt(end) != ' ') {
            length++;
            end--;
        }
        return length;
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode newHead = head;
        while (newHead != null) {
            if (newHead.val == val) newHead = newHead.next;
            else break;
        }
        if (newHead == null) return null;

        ListNode current = newHead;
        while (current != null && current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;
            } else current = current.next;
        }
        return newHead;
    }

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        return minDepth(root, 0);
    }

    private int minDepth(TreeNode node, int depth) {
        if (node == null) return depth;
        if (node.left == null && node.right == null)
            return depth + 1;

        int left = minDepth(node.left, depth + 1);
        int right = minDepth(node.right, depth + 1);
        if (node.left != null && node.right != null) {
            return Math.min(left, right);
        }
        if (node.left == null) {
            return right;
        }
        return left;
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        ArrayList<Character> pushChars = new ArrayList<>();
        pushChars.add('(');
        pushChars.add('[');
        pushChars.add('{');

        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (pushChars.contains(currentChar)) {
                stack.push(currentChar);
            } else {
                if (stack.isEmpty()) return false;
                if (stack.pop() != map.get(currentChar)) return false;
            }
        }

        if (stack.isEmpty()) return true;
        return false;
    }

    public static int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        for (int i = 0; i < v1.length && i < v2.length; i++) {
            int v1Num = Integer.parseInt(v1[i]);
            int v2Num = Integer.parseInt(v2[i]);
            if (v1Num < v2Num) return -1;
            else if (v1Num > v2Num) return 1;
        }

        if (v1.length > v2.length) {
            for (int i = v2.length; i < v1.length; i++) {
                if (Integer.parseInt(v1[i]) != 0) return 1;
            }
            return 0;
        }
        if (v2.length > v1.length) {
            for (int i = v1.length; i < v2.length; i++) {
                if (Integer.parseInt(v2[i]) != 0) return -1;
            }
            return 0;
        }
        return 0;
    }

    public int trailingZeroes(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n / 5;
            n /= 5;
        }
        return sum;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<MyPair> q = new LinkedList<>();
        q.add(new MyPair(root, 1));
        int curretLevel = 0;
        while (!q.isEmpty()) {
            MyPair MyPair = q.poll();
            TreeNode node = MyPair.node;
            if (MyPair.level > curretLevel) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(node.val);
                result.add(list);
            } else {
                List<Integer> list = result.get(result.size() - 1);
                list.add(node.val);
            }
            curretLevel = MyPair.level;
            if (node.left != null) q.add(new MyPair(node.left, curretLevel + 1));
            if (node.right != null) q.add(new MyPair(node.right, curretLevel + 1));
        }

        return result;
    }

    public int guessNumber(int n) {
        if (n == 1) return 1;
        int start = 1;
        int end = n;

        while (start < end) {
            int mid = start + (end - start) / 2;
            int guessedValue = guess(mid);
            if (guessedValue == 0) return mid;
            if (guessedValue == 1) {
                start = mid + 1;
            } else end = mid - 1;
        }
        return start;
    }

    int guess(int num) {
        return 0;
    }

    public boolean isPalindrome(String s) {
        if (s.length() == 0) return true;
        s = s.toLowerCase();
        int start = 0, end = s.length() - 1;
        while (start < end) {
            //find first alpha numberic char
            while (start < s.length() && !Character.isDigit(s.charAt(start)) && !Character.isLetter(s.charAt(start))) {
                start++;
            }
            while (end > 0 && !Character.isDigit(s.charAt(end)) && !Character.isLetter(s.charAt(end))) {
                end--;
            }
            if (start >= end) return true;
            if (s.charAt(start) != s.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> outer = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            ArrayList<Integer> inner = new ArrayList<>();
            outer.add(inner);
            for (int j = 0; j <= i; j++) {
                inner.add(getPascalNumber(outer, i, j));
            }
        }
        return outer;
    }

    private Integer getPascalNumber(List<List<Integer>> outer, int i, int j) {
        if (i == 0) return 1;
        List<Integer> previous = outer.get(i - 1);
        return getWithPascalCheck(previous, j) + getWithPascalCheck(previous, j - 1);
    }

    private Integer getWithPascalCheck(List<Integer> previous, int j) {
        if (j < 0) return 0;
        if (j >= previous.size()) return 0;
        return previous.get(j);
    }

    public static List<Integer> getRow(int rowIndex) {
        List<Integer> pascalNumbers = new ArrayList<>(rowIndex);

        for (int i = 0; i <= rowIndex; i++) {
            pascalNumbers.add(0);
        }
        ArrayList<Integer> backUp = new ArrayList<>(pascalNumbers);


        for (int i = 0; i <= rowIndex; i++) {
            for (int j = 0; j <= i; j++) {
                pascalNumbers.set(j, getPascalNumber2(backUp, i, j));
            }
            Collections.copy(backUp, pascalNumbers);
        }
        return pascalNumbers;
    }

    private static Integer getPascalNumber2(List<Integer> pascalNumbers, int i, int j) {
        if (i == 0) return 1;
        return getWithPascalCheck2(pascalNumbers, j) + getWithPascalCheck2(pascalNumbers, j - 1);
    }

    private static Integer getWithPascalCheck2(List<Integer> previous, int j) {
        if (j < 0) return 0;
        return previous.get(j);
    }

    public int[] plusOne(int[] digits) {
        if (digits.length == 0) return digits;
        int carry = 0;
        int i = digits.length - 1;
        while (i >= 0) {
            if (digits[i] + 1 > 9) {
                digits[i] = 0;
                carry = 1;
            } else {
                digits[i] += 1;
                carry = 0;
                break;
            }
            i--;
        }
        if (carry == 1) {
            int[] newNum = new int[digits.length + 1];
            newNum[0] = 1;
            for (int j = 1; j < newNum.length; j++) {
                newNum[j] = digits[j - 1];
            }
            return newNum;
        }

        return digits;
    }

    public static String convertToTitle(int n) {
        if (n == 0) return "";
        String column = "";
        HashMap<Integer, String> map = new HashMap<>();
        char startChar = 'A';
        map.put(0, "Z");
        for (int i = 0; i < 26; i++) {
            char c = (char) (startChar + i);
            map.put(i + 1, new String(new char[]{c}));
        }

        while (n > 0) {
            column = map.get(n % 26) + column;
            if (n % 26 == 0) {
                n = n / 26 - 1;
            } else n /= 26;
        }
        return column;
    }

    public void rotate(int[] nums, int k) {
        int[] ints = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ints[i] = nums[(i - (k % nums.length) + nums.length) % nums.length];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = ints[i];
        }
    }

    public static void rotateInPlace(int[] nums, int k) {
        k = k % nums.length;
        if (nums.length == 1) return;
//        rotate 1st part
        rotateSubArray(nums, 0, nums.length - k - 1);
//        rotate 2nd part
        rotateSubArray(nums, nums.length - k, nums.length - 1);
//        rotate whole array
        rotateSubArray(nums, 0, nums.length - 1);
    }

    private static void rotateSubArray(int[] nums, int start, int end) {
        int temp;
        while (start < end) {
            temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }
        String currentPath = String.valueOf(root.val);
        if (root.left == null && root.right == null) {
            paths.add(currentPath);
            return paths;
        }
        collectPaths(root.left, currentPath, paths);
        collectPaths(root.right, currentPath, paths);

        return paths;
    }

    private void collectPaths(TreeNode current, String currentPath, ArrayList<String> paths) {
        if (current == null) return;
        currentPath = currentPath + "->" + String.valueOf(current.val);
        if (current.left == null && current.right == null) {
            paths.add(currentPath);
            return;
        }
        collectPaths(current.left, currentPath, paths);
        collectPaths(current.right, currentPath, paths);
    }

    public int countPrimesOptimal(int n) {

        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i * i < n; i++) {
            //start marking numbers for prime
            for (int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) count++;
        }
        return count;
    }

    public static String addBinary(String a, String b) {
        String max = a.length() > b.length() ? a : b;
        String min = a.length() > b.length() ? b : a;
        int lengthDiff = max.length() - min.length();

        for (int i = 0; i < lengthDiff; i++) {
            min = "0" + min;
        }

        char[] added = new char[max.length()];
        boolean hasCarry = false;
        for (int i = 0; i < min.length(); i++) {
            char a2 = min.charAt(min.length() - 1 - i);
            char a1 = max.charAt(max.length() - 1 - i);
            if (a2 == '0' && a1 == '0' && !hasCarry) {
                added[i] = '0';
            } else if (a2 == '0' && a1 == '0' && hasCarry) {
                added[i] = '1';
                hasCarry = false;
            } else if (a2 == '0' && a1 == '1' && !hasCarry) {
                added[i] = '1';
            } else if (a2 == '0' && a1 == '1' && hasCarry) {
                added[i] = '0';
            } else if (a2 == '1' && a1 == '0' && !hasCarry) {
                added[i] = '1';
            } else if (a2 == '1' && a1 == '0' && hasCarry) {
                added[i] = '0';
            } else if (a2 == '1' && a1 == '1' && !hasCarry) {
                added[i] = '0';
                hasCarry = true;
            } else if (a2 == '1' && a1 == '1' && hasCarry) {
                added[i] = '1';
            }
        }

        StringBuilder builder = new StringBuilder();

        for (int i = added.length - 1; i >= 0; i--) {
            builder.append(added[i]);
        }
        String sum = builder.toString();
        if (hasCarry) return "1" + sum;
        return sum;
    }

    public int countPrimes(int n) {
        if (n == 0 || n == 1 || n == 2) return 0;

        int count = 0;
        for (int i = n - 1; i >= 2; i--) {
            if (i % 2 == 0) continue;
            else if (isPrime(i)) count++;
        }
        return count;
    }

    private boolean isPrime(int num) {
        int i = 2;
        while (i <= Math.sqrt(num)) {
            if (num % i == 0) return false;
            i++;
        }
        return true;
    }

    public static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area = (C - A) * (D - B) + (H - F) * (G - E);
        if (C <= E || A > G || B > H || F > D) {
            return area;
        }
        int left = Math.max(A, E);
        int right = Math.min(C, G);
        int bottom = Math.max(B, F);
        int top = Math.min(H, D);
        return area - (right - left) * (top - bottom);
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        if (head.next == null) return true;
        int length = 1;

        ListNode current = head;
        while (current.next != null) {
            current = current.next;
            length++;
        }
        int start;
        if (length % 2 == 0) start = length / 2;
        else start = length / 2 + 1;

        ListNode previous = head;

        for (int i = 0; i < start - 1; i++) { //chk for length 2
            previous = previous.next;
        }
        current = previous.next;
        ListNode next = current.next;

        current = reverseListIterative(current);

        ListNode dupCurrent = current;

        while (dupCurrent != null) {
            if (head.val != dupCurrent.val) return false;
            dupCurrent = dupCurrent.next;
            head = head.next;
        }

        return true;
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int longestLength = 0, currentLength = 0;
        int start = 0;

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, i);
                currentLength++;
            } else {
                longestLength = Math.max(longestLength, currentLength);
                Integer prev = map.get(c);
                for (int j = start; j <= prev; j++) {
                    map.remove(s.charAt(j));
                }
                start = prev + 1;
                map.put(c, i);
                currentLength = i - prev;
            }
        }
        return Math.max(longestLength, currentLength);
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {

        Arrays.sort(nums);
        List<List<Integer>> fourSums = new ArrayList<>();

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int k = j + 1;
                int l = nums.length - 1;
                while (k < l) {
                    int fourSum = nums[i] + nums[j] + nums[k] + nums[l];
                    if (fourSum == target) {
                        boolean duplicateExists = false;
                        for (List<Integer> ints : fourSums) {
                            if (ints.get(0) == nums[i] && ints.get(1) == nums[j] && ints.get(2) == nums[k] && ints.get(3) == nums[l]) {
                                duplicateExists = true;
                                break;
                            }
                        }
                        if (!duplicateExists) {
                            fourSums.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        }
                        k++;
                        l--;
                    } else {
                        if (fourSum < target) k++;
                        else l--;
                    }
                }
            }
        }
        return fourSums;
    }

    public static boolean isValidSudoku(char[][] board) {

        if (board.length != 9 || board[0].length != 9) return false;

        //check per column
        for (int i = 0; i < 9; i++) {
            HashSet<Character> set = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (set.contains(c)) return false;
                if (c != '.') set.add(c);
            }
        }

//        check per row
        for (int i = 0; i < 9; i++) {
            HashSet<Character> set = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                char c = board[j][i];
                if (set.contains(c)) return false;
                if (c != '.') set.add(c);
            }
        }

//        check per box
        for (int block = 0; block < 9; block++) {
            HashSet<Character> set = new HashSet<>();
            for (int i = block / 3 * 3; i < block / 3 * 3 + 3; i++) {
                for (int j = block % 3 * 3; j < block % 3 * 3 + 3; j++) {
                    char c = board[i][j];
                    if (set.contains(c)) return false;
                    if (c != '.') set.add(c);
                }
            }
        }

        return true;
    }

    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;

        for (int i = 0; i < haystack.length(); i++) {
            int k = i;
            int j = 0;
            if (k + needle.length() > haystack.length()) return -1;
            for (; j < needle.length() && k < haystack.length(); j++, k++) {
                if (haystack.charAt(k) != needle.charAt(j)) {
                    break;
                }
            }
            if (j == needle.length()) return i;
        }

        return -1;
    }

    public static int strStrKmp(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;
        int[] prefix = getNext(needle);
        int i = 0, j = 0;
        while (i < haystack.length()) {
            if (j == needle.length()) return i - needle.length();
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                if (j == 0) {
                    i++;
                } else {
                    j = prefix[j - 1];
                }
            }
        }
        if (j == needle.length()) return i - needle.length();
        return -1;
    }

    public static int[] getNext(String needle) {
        int[] prefix = new int[needle.length()];
        int i = 1, j = 0;
        prefix[0] = 0;
        while (i < needle.length()) {
            if (needle.charAt(i) != needle.charAt(j)) {
                while (j != 0 && needle.charAt(i) != needle.charAt(j)) {
                    j = prefix[j - 1];
                }
                if (j == 0 && needle.charAt(i) != needle.charAt(j)) {
                    prefix[i] = 0;
                    i++;
                }
            } else {
                prefix[i] = j + 1;
                i++;
                j++;
            }
        }
        return prefix;
    }


    public boolean wordPattern(String pattern, String str) {
        HashMap<Character, String> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        String[] strs = str.split(" ");
        if (strs.length != pattern.length()) return false;
        for (int i = 0; i < pattern.length(); i++) {
            Character c = pattern.charAt(i);
            if (map.containsKey(c)) {
                if (!map.get(c).equals(strs[i])) return false;
            } else {
                if (set.contains(strs[i])) return false;
                map.put(c, strs[i]);
                set.add(strs[i]);
            }
        }
        return true;
    }

    public void setZeroes(int[][] matrix) {
        ArrayList<Integer> rows = new ArrayList<>();
        ArrayList<Integer> cols = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        for (int col = 0; col < matrix[0].length; col++) {
            for (Integer row : rows) {
                matrix[row][col] = 0;
            }
        }
        for (int row = 0; row < matrix.length; row++) {
            for (Integer col : cols) {
                matrix[row][col] = 0;
            }
        }

    }

    public static void setZeroesNoExtraSpace(int[][] matrix) {
        int zeroRow = 0;
        int zeroCol = 0;
        boolean isZeroFound = false;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    zeroRow = i;
                    zeroCol = j;
                    isZeroFound = true;
                    break;
                }
            }
            if (isZeroFound) break;
        }

        if (!isZeroFound) return;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[zeroRow][j] = 0;
                    matrix[i][zeroCol] = 0;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[zeroRow][j] == 0 || matrix[i][zeroCol] == 0) {
                    if (i == zeroRow || j == zeroCol) continue;
                    matrix[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            matrix[i][zeroCol] = 0;
        }
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[zeroRow][i] = 0;
        }

    }

    public static String countAndSay(int n) {
        if (n == 1) return "1";
        if (n == 2) return "11";
        int number = 11;

        for (int i = 3; i <= n; i++) {
            //counter
            String newNumber = "";
            int prevDigit = number % 10;
            number = number / 10;
            int count = 1;
            while (number > 0) {
                int currentDigit = number % 10;
                if (prevDigit != currentDigit) {
                    newNumber = Integer.toString(count) + Integer.toString(prevDigit) + newNumber;
                    if (count != 1) {
                        count = 1;
                    }
                } else {
                    count++;
                }
                prevDigit = currentDigit;
                number = number / 10;
            }
            newNumber = Integer.toString(count) + Integer.toString(prevDigit) + newNumber;
            number = Integer.valueOf(newNumber);
        }
        return Integer.toString(number);
    }

    public static String countAndSayStrings(int n) {
        if (n == 1) return "1";
        if (n == 2) return "11";
        String number = "11";

        for (int i = 3; i <= n; i++) {
            String newNumber = "";
            Character prevDigit = number.charAt(number.length() - 1);
            number = number.substring(0, number.length() - 1);
            int count = 1;
            while (!number.equals("")) {
                Character currentDigit = number.charAt(number.length() - 1);
                if (prevDigit != currentDigit) {
                    newNumber = Integer.toString(count) + String.valueOf(prevDigit) + newNumber;
                    if (count != 1) {
                        count = 1;
                    }
                } else {
                    count++;
                }
                prevDigit = currentDigit;
                number = number.substring(0, number.length() - 1);
            }
            newNumber = Integer.toString(count) + String.valueOf(prevDigit) + newNumber;
            number = newNumber;
        }
        return number;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        if ("".equals(ransomNote)) return true;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        for (int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            if (map.containsKey(c)) {
                if (map.get(c) == 1) {
                    map.remove(c);
                } else {
                    map.put(c, map.get(c) - 1);
                }
            }
            if (map.isEmpty()) return true;
        }
        return false;
    }

    public static String convertZigZag(String s, int numRows) {
        if (numRows == 1) return s;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            if (i == 0 || i == numRows - 1) {
                int position = i;
                while (position < s.length()) {
                    builder.append(s.charAt(position));
                    position += 2 * (numRows - 1);
                }
            } else {
                int position = i;
                while (position < s.length()) {
                    builder.append(s.charAt(position));
                    position += 2 * (numRows - 1 - i);
                    if (position >= s.length()) break;
                    builder.append(s.charAt(position));
                    position += 2 * (i);
                }
            }
        }
        return builder.toString();
    }

    public static String getHint(String secret, String guess) {
        HashMap<Character, Integer> map = new HashMap<>();
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < secret.length(); i++) {
            char c = secret.charAt(i);
            if (c != guess.charAt(i)) {
                if (map.containsKey(c)) map.put(c, map.get(c) + 1);
                else map.put(c, 1);
            } else bulls++;
        }
        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);
            if (c != secret.charAt(i) && map.containsKey(c)) {
                cows++;
                if (map.get(c) == 1) map.remove(c);
                else map.put(c, map.get(c) - 1);
            }
        }
        return bulls + "A" + cows + "B";
    }

    public int singleNumber(int[] nums) {
        int x = 0;
        for (int num : nums) {
            x ^= num;
        }
        return x;
    }

    public static int reverseBits(int n) {

        for (int i = 0; i < 16; i++) {
            n = swapBits(n, i, 31 - i);
        }
        return n;

    }

    private static int swapBits(int n, int i, int j) {
        int a = n >> i & 1;
        int b = n >> j & 1;
        if ((a ^ b) == 1) {
            n = n ^ ((1 << i) | (1 << j));
        }
        return n;
    }

    public static String longestPalindrome(String s) {
        int i = 0, j, k;
        String longestSubStr = "";
        while (i < s.length()) {
            //odd palindrome
            j = i - 1;
            k = i + 1;
            longestSubStr = findPali(s, j, k, longestSubStr);
            //even palindrome
            if (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                j = i - 1;
                k = i + 2;
                longestSubStr = findPali(s, j, k, longestSubStr);
            }
            i++;
        }
        return longestSubStr;
    }

    private static String findPali(String s, int j, int k, String longestSubStr) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt((k))) {
            j--;
            k++;
        }
        if (s.substring(j + 1, k).length() > longestSubStr.length()) {
            longestSubStr = s.substring(j + 1, k);
        }
        return longestSubStr;
    }

    public static String longestPalindromeManacher(String s) {

        char[] cha = new char[2 * s.length() + 1];
        for (int i = 0; i < 2 * s.length() + 1; i++) {
            if (i % 2 == 0) cha[i] = '$';
            else cha[i] = s.charAt(i / 2);
        }
        s = String.valueOf(cha);

        int i = 0, start, end;
        int[] arr = new int[s.length()];

        while (i < s.length()) {
            start = i - 1;
            end = i + 1;
            while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
                start--;
                end++;
            }
            arr[i] = end - start - 1;

            if (end == s.length()) break;

            boolean found = false;
            int j;
            for (j = i + 1; j < end; j++) {

                arr[j] = Math.min(arr[i - (j - i)], 2 * (end - j - 1) + 1); //chk this
                if (arr[j] == 2 * (end - j - 1) + 1) {
                    found = true;
                    break;
                }
            }
            if (!found) i = end;
            else i = j;

        }
        int max = 0, maxIndex = 0;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] > max) {
                max = arr[j];
                maxIndex = j;
            }
        }
        return (s.substring(maxIndex - max / 2, maxIndex + max / 2 + 1)).replace("$", "");
    }

    public static int singleNumber2(int[] nums) {

        int singles = 0, doubles = 0, commonBitMask;

        for (int i = 0; i < nums.length; i++) {
            doubles |= singles & nums[i];
            singles = singles ^ nums[i];

            commonBitMask = ~(singles & doubles);
            singles &= commonBitMask;
            doubles &= commonBitMask;
        }

        return 0;
    }

    public static int[] singleNumber3(int[] nums) {
        int xor = 0;
        for (int i = 0; i < nums.length; i++) {
            xor ^= nums[i];
        }
        int count = 0;
        while (xor != 0) {
            xor = xor << 1;
            count++;
        }
        int bla = 1;
        for (int i = 0; i < 31 - count; i++) {
            bla = bla << 1;
        }
        int[] ints = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & bla) == 0) ints[0] ^= nums[i];
            else ints[1] ^= nums[i];
        }
        return ints;
    }

    public char findTheDifference(String s, String t) {
        char xor = 0;
        for (int i = 0; i < s.length(); i++) {
            xor ^= s.charAt(i);
        }
        for (int j = 0; j < t.length(); j++) {
            xor ^= t.charAt(j);
        }
        return xor;
    }

    public int[] twoSumXtra(int[] numbers, int target) {
        int start = 0, end = numbers.length - 1;
        while (start < end) {
            int sum = numbers[start] + numbers[end];
            if (sum == target) return new int[]{start + 1, end + 1};
            else if (sum < target) start++;
            else end--;
        }
        return new int[]{};
    }

    public int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3) throw new RuntimeException("invalid arguments");
        Arrays.sort(nums);
        int start = 0, end = 0;
        Integer closestTarget = null;

        for (int i = 0; i < nums.length; i++) {
            start = i + 1;
            end = nums.length - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                int abs = Math.abs(sum - target);
                if ((closestTarget == null) || abs < Math.abs(target - closestTarget)) {
                    closestTarget = sum;
                }

                if (sum < target)
                    start++;
                else
                    end--;
            }
        }
        return closestTarget;
    }

    public int[] productExceptSelf(int[] nums) {
        if (nums.length == 0) return new int[0];
        if (nums.length == 1) return nums;

        int[] result = new int[nums.length];
        result[nums.length - 1] = 1;

        for (int i = nums.length - 2; i >= 0; i--) {
            result[i] = result[i + 1] * nums[i + 1];
        }

        int leftProduct = 1;
        for (int i = 0; i < nums.length; i++) {
            result[i] = result[i] * leftProduct;
            leftProduct = leftProduct * nums[i];
        }
        return result;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result;
        ListNode resultHead;
        boolean hasCarry = false;
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        int sum = l1.val + l2.val;
        hasCarry = sum > 9;
        resultHead = result = new ListNode(hasCarry ? sum % 10 : sum);
        l1 = l1.next;
        l2 = l2.next;

        while (l1 != null && l2 != null) {
            sum = l1.val + l2.val + (hasCarry ? 1 : 0);
            hasCarry = sum > 9;
            result.next = new ListNode(hasCarry ? sum % 10 : sum);
            l1 = l1.next;
            l2 = l2.next;
            result = result.next;
        }
        ListNode remainingNodes;
        if (l1 != null && l2 == null) remainingNodes = l1;
        else remainingNodes = l2;

        while (remainingNodes != null) {
            sum = remainingNodes.val + (hasCarry ? 1 : 0);
            hasCarry = sum > 9;
            result.next = new ListNode(hasCarry ? sum % 10 : sum);
            remainingNodes = remainingNodes.next;
            result = result.next;
        }
        if (hasCarry){
            result.next = new ListNode(1);
        }

        return resultHead;
    }

    public int maxArea(int[] height) {
        int start=0, end = height.length-1, maximumVolume = 0;
        int currentVolume = 0;

        while (start<end){
            currentVolume = Math.min(height[start], height[end]) * (end-start);
            if (currentVolume > maximumVolume) maximumVolume = currentVolume;

            if (height[start] < height[end]) start++;
            else end--;
        }
        return maximumVolume;
    }

    public List<String> letterCombinations(String digits) {
        HashMap<Character, String> map = new HashMap<>();

        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        if (digits == null || digits.equals(""))
            return new ArrayList<>();
        ArrayList<String> comboList = new ArrayList<>();
        makeComboBitch(digits, comboList, map, "");
        return comboList;
    }

    private void makeComboBitch(String digits, ArrayList<String> comboList, HashMap<Character, String> map, String parent) {

        if (digits.length() == 0) {
            comboList.add(parent);
            return;
        }

        char digit = digits.charAt(0);
        String digitChars = map.get(digit);
        for (Character c: digitChars.toCharArray() ) {
            makeComboBitch(digits.substring(1),comboList, map, parent+String.valueOf(c));
        }
    }

    public List<String> generateParenthesis(int n) {
        ArrayList<String> set = new ArrayList<>();
        int leftCount = 0;
        generateParenthesis(set, n, "", leftCount);
        return set;
    }

    private void generateParenthesis(ArrayList<String> set, int n, String innerParanthesis, int leftCount) {
        if (n == 0 && leftCount == 0) {
            set.add(innerParanthesis);
            return;
        }
        if (n > 0 ){
            generateParenthesis(set, n-1, innerParanthesis + "(" , leftCount+1);
        }
        if (leftCount > 0){
            generateParenthesis(set, n, innerParanthesis + ")", leftCount-1);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < getFactorial(nums.length); i++) {
            nextPermutationEfficient(nums);
            ArrayList<Integer> integers = new ArrayList<>();
            IntStream.of(nums).forEach(value -> integers.add(value));
            list.add(integers);
        }
        return list;
    }

    private int getFactorial(int n) {
        int fact = 1;
        for (int i = n; i >=2 ; i--) {
            fact*= i;
        }
        return fact;
    }

    public static void nextPermutationEfficient(int[] nums) {
        if (nums.length == 0 || nums.length ==1) return;

        int i;
        for (i = nums.length-1; i >0 ; i--) {
            if (nums[i-1] < nums[i]) break;
        }

        if (i==0) {
            reverseArray(nums,0,nums.length);
            return;
        }

        for (int j = nums.length-1; j >= i; j--) {
            if (nums[j] > nums[i-1]) {
                int temp = nums[i-1];
                nums[i-1] = nums[j];
                nums[j] = temp;
                break;
            }
        }

        reverseArray(nums, i, nums.length);
    }

    private static void reverseArray(int[] nums, int startIndex, int lastExclusiveIndex) {
        int start = startIndex, end = lastExclusiveIndex-1, temp;
        while (start<end){
            temp = nums[start];
            nums[start]= nums[end];
            nums[end] = temp;

            start++;
            end--;
        }
    }

    public static void nextPermutation(int[] nums) {
        if (nums.length == 0 || nums.length ==1) return;

        int i;
        for (i = nums.length-1; i >0 ; i--) {
            if (nums[i-1] < nums[i]) break;
        }

        //when permutation is already in descending
        if (i==0) {
            Arrays.sort(nums);
            return;
        }

        //pick higher than i-1
        int nextHighestIndex = i;
        for (int j = i; j < nums.length; j++) {
            if (nums[j] > nums[i-1] && nums[j]< nums[nextHighestIndex]) {
                nextHighestIndex = j;
            }
        }

        int temp = nums[i-1];
        nums[i-1] = nums[nextHighestIndex];
        nums[nextHighestIndex] = temp;

        Arrays.sort(nums,i, nums.length);
    }

    public static List<List<Integer>> permuteEfficient(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();

        List<List<Integer>> growingNumbers = new ArrayList<>();
        ArrayList<Integer> first = new ArrayList<>();
        first.add(nums[0]);
        growingNumbers.add(first);
        for (int i = 1; i < nums.length; i++) {
            List<List<Integer>> newGrowingNumbers = new ArrayList<>();
            for (List<Integer> growingNumber: growingNumbers){
                for (int j = 0; j < growingNumber.size()+1; j++) {
                    ArrayList<Integer> al = new ArrayList<>(growingNumber);
                    al.add(j,nums[i]);
                    newGrowingNumbers.add(al);
                }
            }
            growingNumbers = newGrowingNumbers;
        }
        return growingNumbers;
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();

        Set<List<Integer>> growingNumbers = new HashSet<>();
        ArrayList<Integer> first = new ArrayList<>();
        first.add(nums[0]);
        growingNumbers.add(first);
        for (int i = 1; i < nums.length; i++) {
            Set<List<Integer>> newGrowingNumbers = new HashSet<>();
            for (List<Integer> growingNumber: growingNumbers){
                for (int j = 0; j < growingNumber.size()+1; j++) {
                    ArrayList<Integer> al = new ArrayList<>(growingNumber);
                    al.add(j,nums[i]);
                    newGrowingNumbers.add(al);
                    while (j < growingNumber.size()+1 && nums[i] == growingNumber.get(j)){
                        j++;
                    }
                }
            }
            growingNumbers = newGrowingNumbers;
        }
        return growingNumbers.stream().collect(Collectors.toList());
    }

    public int firstUniqChar(String s) {
        HashMap<Character, Boolean> characterUniqueMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (characterUniqueMap.containsKey(c)){
                characterUniqueMap.put(c, false);
            }else characterUniqueMap.put(c, true);
        }
        for (int i = 0; i < s.length(); i++) {
            if (characterUniqueMap.get(s.charAt(i))){
                return i;
            }
        }
        return -1;
    }

    public static int getLongestSequence(String s) {
        if (s == null || s.isEmpty()) return 0;
        String str = "";
        int maxLength = 0;
        int lastIndex = 0;
        for(int i = 0; i < s.length(); i++) {
            if(str.contains(String.valueOf(s.charAt(i)))) {
                maxLength = str.length();
                lastIndex = s.indexOf(s.charAt(i), lastIndex)+1;
                i = lastIndex;
                str = "";
            }
            str += s.charAt(i);
        }
        return maxLength;
    }

    public static int reduce(int[] num){

        Arrays.sort(num);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i<num.length; i++){
            list.add(num[i]);
        }
        int cost =0;

        for (int i = 0; i < list.size(); i++) {
            int currentTotal = list.get(0) + list.get(1);
            cost = cost + currentTotal;
            list.remove(0);
            list.remove(0);

            int insertingIndex = 0;
            for (int j = 0; j < list.size(); j++) {
                if (currentTotal >= list.get(j)){
                    insertingIndex = j;
                    break;
                }
            }
            list.add(insertingIndex,currentTotal);
        }
        return cost;
    }

    class MyPair {
        MyPair(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }

        public TreeNode node;
        public int level;
    }

}