package gr.aueb.cf.xmas.project5;

public class Theatrale {

    private static final int ROWS = 30;
    private static final int COLS = 12;
    private boolean[][] seats;


    public static void main(String[] args) {
        Theatrale theater = new Theatrale();


        theater.book('A', 1);
        theater.book('B', 2);
        theater.book('C', 3);
        theater.book('A', 1); // Προσπάθεια κράτησης ήδη κρατημένης θέσης


        theater.cancel('B', 2);
        theater.cancel('D', 4); // Προσπάθεια ακύρωσης μη κρατημένης θέσης


        theater.printTheater();
    }

    // Κατασκευαστής για αρχικοποίηση των θέσεων του θεάτρου
    public Theatrale() {
        seats = new boolean[ROWS][COLS]; // Όλες οι θέσεις είναι αρχικά μη κρατημένες (false)
    }


    public void book(char col, int row) {
        // Έλεγχος αν η θέση είναι εντός των επιτρεπτών ορίων
        if (row < 1 || row > ROWS || col < 'A' || col >= 'A' + COLS) {
            System.out.println("Μη έγκυρη θέση: " + col + row);
            return;
        }

        // Έλεγχος αν η θέση είναι ήδη κρατημένη
        if (seats[row - 1][col - 'A']) {
            System.out.println("Η θέση " + col + row + " είναι ήδη κρατημένη.");
        } else {
            seats[row - 1][col - 'A'] = true; // Κράτηση της θέσης
            System.out.println("Η θέση " + col + row + " έχει κρατηθεί.");
        }
    }

    // Μέθοδος για ακύρωση μιας κράτησης
    public void cancel(char col, int row) {
        // Έλεγχος αν η θέση είναι εντός των επιτρεπτών ορίων
        if (row < 1 || row > ROWS || col < 'A' || col >= 'A' + COLS) {
            System.out.println("Μη έγκυρη θέση: " + col + row);
            return;
        }

        // Έλεγχος αν η θέση είναι ήδη κρατημένη
        if (!seats[row - 1][col - 'A']) {
            System.out.println("Η θέση " + col + row + " δεν είναι κρατημένη.");
        } else {
            seats[row - 1][col - 'A'] = false; // Ακύρωση της κράτησης
            System.out.println("Η κράτηση για τη θέση " + col + row + " έχει ακυρωθεί.");
        }
    }

    // Μέθοδος για εκτύπωση της τρέχουσας κατάστασης του θεάτρου
    public void printTheater() {
        System.out.println("Χάρτης Θέσεων Θεάτρου:");
        System.out.print("  ");
        for (char c = 'A'; c < 'A' + COLS; c++) {
            System.out.print(c + " ");
        }
        System.out.println();

        for (int i = 0; i < ROWS; i++) {
            System.out.printf("%2d ", i + 1); // Εκτύπωση του αριθμού της σειράς
            for (int j = 0; j < COLS; j++) {
                System.out.print(seats[i][j] ? "X " : "- "); // X για κρατημένη θέση, - για διαθέσιμη θέση
            }
            System.out.println();
        }
    }
}