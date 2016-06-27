import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ProblemTest {

    public static class TwoSumProblem{

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

    public static class AtoiProblem{
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

    public static class ComputeArraySubsetMatch{
        @Test
        public void computeIntersectionForSimilarArrays(){
            assertThat(Solution.computeArraySubsetMatch(new int[]{1,1}, new int[]{1,1}), is(new int[]{1, 1}));
        }

        @Test
        public void computeIntersectionForArrayWhichIsASubsetOfLargerArray(){
            assertThat(Solution.computeArraySubsetMatch(new int[]{2,1,3}, new int[]{1,3}), is(new int[]{1, 3}));
        }
    }

    public static class ComputeIntersectionOfArray{

        @Test
        public void intersectShouldReturnAllMatchingElements(){
            assertThat(Solution.computeMatchingArrayElements(new int[]{1,1}, new int[]{1}), is(new int[]{1}));
        }

        @Test
        public void intersectShouldReturnAllMatchingElementsOfSubset(){
            assertThat(Solution.computeMatchingArrayElements(new int[]{2,1,3}, new int[]{1,3}), is(new int[]{1, 3}));
        }

    }

    public static class ComputeSimpleIntersectionOfArray{

        @Test
        public void shouldReturnNonRepeatedArrayIntersection() {
            assertThat(Solution.computeSimpleIntersection(new int[]{1,2,3}, new int[]{2,2}), is(new int[]{2}));
        }

    }

    public static class RemoveDuplicatesInPlace{

        @Test
        public void shouldRemoveDuplicatesInPlace() {
            assertThat(Solution.removeDuplicates(new int[]{1,1,2,2,3,4,4,5}), is(5));
        }
        
    }

    public static class NimGame{

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

    public static class PowerOfThree{

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
    
}
