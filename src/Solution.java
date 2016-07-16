import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
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

        while (true){
            int sum = 0;
            int[] digits = getAllDigits(n);
            for (int digit : digits) {
                sum = sum+(digit*digit);
            }
            if (set.contains(sum)) return false;
            if (sum == 1) return true;
            set.add(sum);
            n = sum;
        }

    }

    private static int[] getAllDigits(int n) {
        ArrayList<Integer> digits = new ArrayList<>();
        while (n > 0){
            digits.add(n%10);
            n/=10;
        }
        return digits.stream().mapToInt(i->i).toArray();
    }

    public static int climbStairs(int n){
        if (n == 1) return 1;
        if (n == 2) return 2;
        int nSum = 0;
        int n1Sum = 2;
        int n2Sum = 1;
        for (int i = 3; i <= n; i++) {
            nSum = n1Sum + n2Sum;
            n2Sum = n1Sum;
            n1Sum =nSum;
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
                map.put(key, frequency +1);
                if (frequency+1 > nums.length/2) return key;
            }
            else map.put(key, 1);
        }
        return -1;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) return null;
        if (p == null || q == null) return root;
        TreeNode lca = root;

        while (true){
            if (p.val < lca.val && q.val < lca.val){
                lca = lca.left;
            }
            else if (p.val > lca.val && q.val > lca.val){
                lca = lca.right;
            }else return lca;
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
        for (int i = roman.length()-1; i >=0 ; i--) {
            char currentChar = roman.charAt(i);
            if (map.get(currentChar) >= map.get(largestChar)){
                sum+=map.get(currentChar);
                largestChar = currentChar;
            }
            else {
                sum-=map.get(currentChar);
            }
        }

        return sum;
    }

    public static String intToRoman(int num){
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
        while (num > 0){
            if (num / map.get(subtractiveRomans.peek()) >= 1){
                romanBuilder.append(subtractiveRomans.peek());
                num-=map.get(subtractiveRomans.peek());
            }
            else {
                subtractiveRomans.pop();
            }
        }
        return romanBuilder.toString();
    }

    public static boolean isPowerOfFour(int num) {
        if (num<=0) return false;
        int pow = (int) (Math.log(num)/Math.log(4));
        if (num == Math.pow(4,pow)) return true;
        return false;
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode current = head;
        ListNode previous = null;
        head = current.next;
        while (current!= null && current.next != null){
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
        while (!q.isEmpty()){
            MyPair MyPair = q.poll();
            TreeNode node = MyPair.node;
            if (MyPair.level > curretLevel){
                ArrayList<Integer> list = new ArrayList<>();
                list.add(node.val);
                stack.push(list);
            }else {
                List<Integer> list = stack.peek();
                list.add(node.val);
            }
            curretLevel = MyPair.level;
            if (node.left != null) q.add(new MyPair(node.left, curretLevel+1));
            if (node.right != null) q.add(new MyPair(node.right, curretLevel+1));
        }
        while (!stack.isEmpty()){
            result.add(stack.pop());
        }
        return result;
    }

    public String reverseVowels(String s) {
        if(s == null || s == "")
            return s;
        int start = 0;
        int end = s.length()-1;
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

        while (start<end){
            if(!vowels.contains(s.charAt(start))) {start++; continue;}
            if(!vowels.contains(s.charAt(end))) {end--; continue;}

            char temp = chars[start];
            chars[start] = chars[end];
            chars[end]=temp;
            start++;
            end--;
        }
        return new String(chars);
    }

    public boolean isBalanced(TreeNode root){
        if (root == null) return true;
        int difference = getDepth(root.left) - getDepth(root.right);
        if (difference > 1 || difference <-1) return false;
        return true;
    }

    private int getDepth(TreeNode node) {
        return 0;
    }

    public static int reverseInteger(int x) {
        int newNum = 0;
        while (x!=0){
            newNum=newNum*10+(x%10);
            x/=10;
        }
        return newNum;
    }


    class MyPair{
        MyPair(TreeNode node, int level){
            this.node = node;
            this.level = level;
        }
        public TreeNode node;
        public int level;
    }

}