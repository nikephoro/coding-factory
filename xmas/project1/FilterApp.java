package gr.aueb.cf.xmas.project1;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Arrays;

public class FilterApp {

    public static void main(String[] args)  {
        try (Scanner in = new Scanner(new File("C:/tmp/project01in.txt"));
             PrintStream ps = new PrintStream("C:/tmp/project01out.txt", StandardCharsets.UTF_8)) {

            final int COMBO = 6;
            int[] input = new int[49];
            int num;
            int pivot = 0;
            int[] output = new int[COMBO];
            int window;


            while ((num = in.nextInt()) != -1 && pivot <= 48) {
                input[pivot++] = num;
            }

            int[] inputSorted = Arrays.copyOfRange(input, 0, pivot);
            Arrays.sort(inputSorted);

            window = pivot - COMBO;
            for (int i = 0; i <= window; i++) {
                for (int j = i + 1; j <= window + 1; j++) {
                    for (int k = j + 1; k <= window + 2; k++) {
                        for (int l = k + 1; l <= window + 3; l++) {
                            for (int m = l + 1; m <= window + 4; m++) {
                                for (int n = m + 1; n <= window + 5; n++) {
                                    output[0] = inputSorted[i];
                                    output[1] = inputSorted[j];
                                    output[2] = inputSorted[k];
                                    output[3] = inputSorted[l];
                                    output[4] = inputSorted[m];
                                    output[5] = inputSorted[n];

                                    if (isEvenLE(output, 4) && isOddLE(output, 4) && sameTen(output, 3)
                                            && sameEnding(output, 3) && consecutive(output)) {
                                        ps.printf("%d %d %d %d %d %d\n",
                                                output[0], output[1], output[2], output[3], output[4], output[5]);
                                        System.out.printf("%d %d %d %d %d %d\n",
                                                output[0], output[1], output[2], output[3], output[4], output[5]);
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } catch ( IOException e) {
            e.printStackTrace();
            System.out.println("Input File Not Found or Unable to Create Output File.");
        }

    }


        /**
         * Returns true if the number of evens is lesser than or
         * equal to a threshold limit.
         *
         * @param arr
         *          the input array.
         * @param threshold
         *          the upper limit of the constraint.
         * @return
         *          true, if the number of evens is lesser
         *          than or equal to a threshold limit, false
         *          otherwise.
         */
        public static boolean isEvenLE(int[] arr, int threshold) {
            long evenCount = 0;

            evenCount = Arrays.stream(arr)
                    .filter(num -> num % 2 == 0)
                    .count();

            return evenCount <= threshold;
        }

        /**
         * Returns true if the number of odds is greater than or
         * equal to a threshold limit.
         *
         * @param arr
         *          the input array.
         * @param threshold
         *          the upper limit of the constraint.
         * @return
         *          true, if the number of odds is lesser
         *          than or equal to a threshold limit, false
         *          otherwise.
         */
        public static boolean isOddLE(int[] arr, int threshold) {
            long oddsCount = 0;

            oddsCount = Arrays.stream(arr)
                    .filter(num -> num % 2 != 0)
                    .count();

            return oddsCount <= threshold;
        }

        /**
         * Returns true if threshold or less numbers are in the
         * same ten.
         *
         * @param arr
         *      the input array.
         * @param threshold
         *      the threshold.
         * @return
         *      true, if LE (Lesser or Equal) to threshold numbers
         *      are in the same ten.
         */
        public static boolean sameTen(int[] arr, int threshold) {
            int[] ten = new int[5];

            for (int num : arr) {
                ten[num/10]++;
            }

            return Arrays.stream(ten).anyMatch(t -> t <= threshold);
        }

        /**
         * Returns true if less than three numbers are consecutive.
         *
         * @param arr
         *      the input array.
         * @return
         *      true, if less than three numbers are consecutive.
         */
        public static boolean consecutive(int[] arr) {
            int consecutiveCount = 0;

            for (int i = 0; i <= 3; i++) {
                if (arr[i] == arr[i + 1] - 1 && arr[i] == arr[i + 2] - 2) {
                    consecutiveCount++;
                    break;
                }
            }

            return consecutiveCount <= 2;
        }

        /**
         * Returns true if threshold or more numbers have the same ending.
         *
         * @param arr
         *      the input array.
         * @param threshold
         *      the threshold.
         * @return
         *      true, if LE (Lesser or Equal) to threshold numbers
         *      have the same ending.
         */
        public static boolean sameEnding(int[] arr, int threshold) {
            int[] endings = new int[10];

            for (int num : arr) {
                endings[num % 10]++;
            }

            return Arrays.stream(endings).anyMatch(e -> e <= threshold);
        }
    }

