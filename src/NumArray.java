public class NumArray {
    private int[] nums;
    private int[] sumData;

    public NumArray(int[] nums) {
        this.nums = nums;
        sumData = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i==0) sumData[i] = nums[i];
            else
            sumData[i]= sumData[i-1] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        if (i==0) return sumData[j];
        return sumData[j] - sumData[i-1];
    }

}
