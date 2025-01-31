package gr.aueb.cf.xmas.project2;

/**
 * The following class calculates the maximum sum of sequential integers a given
 * array of integers can produce.
 */

public class MaxSum {

    public static void main(String[] args) {
        int[] arr = { 10, -10, -1, -10 , -11, 23};
        System.out.println(maxSum(arr));
    }

    static int maxSum(int[] arr) {
        int globalMax = arr[0];
        int localSum = arr[0];

        for (int i = 1; i < arr.length; i++) {

            if (localSum < 0) {
                localSum = arr[i];}
                else localSum += arr[i] ;
                
            globalMax = Math.max(globalMax, localSum);
        }
        return globalMax;
    }

    
}

