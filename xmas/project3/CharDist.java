package gr.aueb.cf.xmas.project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class enables the user to read a file containing text
 * written with latin characters and receive number of iterations
 * for each non-whitespace character that appears in the file.
 */


public class CharDist {

    public static void main(String[] args) {
        /**
         *  Creates an array of 128 lines and 2 rows.
         *  Each line contains one latin character in
         *  the first row and the value zero in the second.
         *
         */

        int[][] latinCodex = new int[128][2];
        for (int i = 0; i < 128; i++) {
            latinCodex[i][0] = i;
            latinCodex[i][1] = 0; // Initialize counts to 0
        }


        File file = new File("C:/tmp/lorem.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (char c : line.toCharArray()) {
                    // Check if the character is within the Latin alphabet range (0 to 127)
                    if (c >= 0 && c < 128) {
                        // Skip whitespaces and non-printable characters
                        if (!Character.isWhitespace(c) && Character.isDefined(c)) {
                            latinCodex[c][1]++; // Increment the count for this character
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }

        // Print the results
        for (int i = 0; i < 128; i++) {
            if (latinCodex[i][1] > 0) { // Only print characters that appeared at least once
                System.out.println("Character: " + (char) latinCodex[i][0] + " (Unicode: " + latinCodex[i][0] + ") | Count: " + latinCodex[i][1]);
            }
        }
    }
}