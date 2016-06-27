import java.util.*;
import java.util.stream.IntStream;

public class Solution{

    public static int[] twoSum(int[] nums, int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){

            int compliment = target - nums[i];
            if(map.containsKey(compliment) && i !=  map.get(compliment)){
                return new int[]{i, map.get(compliment)};
            }
            map.put(nums[i],i);
        }
        throw new IllegalArgumentException("No matching sum found");
    }

    public static int myAtoi(String text){
        String trimmedText = text.trim();
        boolean isNegative = false;

        if(trimmedText.startsWith("+")){
            trimmedText = trimmedText.substring(1);
        }
        else if(trimmedText.startsWith("-")){
            trimmedText = trimmedText.substring(1);
            isNegative = true;
        }

        char[] numberCharacters = trimmedText.toCharArray();
        HashMap<Character, Integer> characterIntegerMap = new HashMap<>();
        characterIntegerMap.put('0',0);
        characterIntegerMap.put('1',1);
        characterIntegerMap.put('2',2);
        characterIntegerMap.put('3',3);
        characterIntegerMap.put('4',4);
        characterIntegerMap.put('5',5);
        characterIntegerMap.put('6',6);
        characterIntegerMap.put('7',7);
        characterIntegerMap.put('8',8);
        characterIntegerMap.put('9',9);

        int finalNumber = 0;

        for(int i=0; i<numberCharacters.length; i++){
            boolean isNumber = false;
            for (Character key : characterIntegerMap.keySet()) {
                if (numberCharacters[i] == key){
                    finalNumber = finalNumber*10 + characterIntegerMap.get(key);
                    isNumber = true;
                    break;
                }
            }
            if (!isNumber){
                throw new IllegalArgumentException("Non number characters");
            }
        }

        return isNegative ? finalNumber*-1 : finalNumber;
    }


    public static int[] computeArraySubsetMatch(int[] nums1, int[] nums2) {
        ArrayList<Integer> largestMatchingSubArray = new ArrayList<>();
        for (int i = 0; i< nums1.length; i++){
            for (int j=0; j< nums2.length; j++){
                int ii = i;
                int jj = j;
                ArrayList<Integer> matchingSubArray = new ArrayList<>();
                while(ii<=nums1.length-1 && jj<=nums2.length-1 && nums1[ii] == nums2[jj]){
                    matchingSubArray.add(nums2[jj]);
                    ii++;
                    jj++;
                }
                if(matchingSubArray.size() >= largestMatchingSubArray.size()){
                    largestMatchingSubArray = matchingSubArray;
                }
            }
        }

        return largestMatchingSubArray.stream().mapToInt(i->i).toArray();
    }


    public static int[] computeMatchingArrayElements(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();

        IntStream.of(nums1).forEach(i -> {
            if (map1.containsKey(i)) {
                map1.replace(i, map1.get(i) + 1);
            }
            else map1.put(i, 1);
        });
        IntStream.of(nums2).forEach(i -> {
            if (map2.containsKey(i)) {
                map2.replace(i, map2.get(i) + 1);
            }
            else map2.put(i, 1);
        });

        ArrayList<Integer> interFuckingSection = new ArrayList<>();
        for (Integer map1_key: map1.keySet())  {
            if (map2.containsKey(map1_key)){
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
        if (nums1.length<nums2.length){
            smallerArray = nums1;
            largerArray = nums2;
        }

        HashMap<Integer,Boolean> smallerNumbers = new HashMap<>();
        IntStream.of(smallerArray).forEach(value -> smallerNumbers.put(value,false));
        ArrayList<Integer> intersectionArray = new ArrayList<>();

        for (int i = 0; i < largerArray.length; i++) {
            if (smallerNumbers.containsKey(largerArray[i]) && !smallerNumbers.get(largerArray[i])){
                smallerNumbers.replace(largerArray[i],true);
                intersectionArray.add(largerArray[i]);
            }
        }
        return intersectionArray.stream().mapToInt(value -> value).toArray();
    }

    public static int removeDuplicates(int[] nums) {

        int duplicatePosition = 0;
        int differentPosition = 1;


        while (differentPosition < nums.length){
            if (nums[duplicatePosition] != nums[differentPosition]){
                //swap
                int temp = nums[duplicatePosition+1];
                nums[duplicatePosition+1] = nums[differentPosition];
                nums[differentPosition] = temp;
                duplicatePosition++;
            }

            differentPosition++;
        }

        return duplicatePosition +1;
    }

    public static boolean canWinNim(int i) {
        return i%4 == 0 ? false: true;
    }

}
