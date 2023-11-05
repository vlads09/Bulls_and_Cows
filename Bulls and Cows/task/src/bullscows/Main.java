package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length:");
        String getCode = s.nextLine();
        int lCode = -1;
        try {
            lCode = Integer.parseInt(getCode);
        } catch (NumberFormatException e){
            System.out.println("Error: \"" + getCode +"\" isn't a valid number.");
            return;
        }
        if (lCode > 36) {
            System.out.println("Error: can't generate a secret number with a length of " +
                    lCode + " because there aren't enough unique digits.");
        } else if (lCode == 0) {
            System.out.println("Error: can't generate a secret number with a length of 0!");
        } else {
            StringBuilder hiddenSecret = new StringBuilder();
            for (int i = 0; i < lCode; i++) {
                hiddenSecret.append("*");
            }
            System.out.println("Input the number of possible symbols in the code:");
            int possibilities = s.nextInt();
            if (possibilities < lCode) {
                System.out.println("Error: it's not possible to generate a code with a length of " + lCode + " with " +
                        possibilities + " unique symbols.");
            } else {
                if (possibilities > 36) {
                    System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                    return;
                }
                String chars = "0123456789abcdefghijklmnopqrstuvwxyz";
                if (possibilities < 11) {
                    System.out.println("The secret is prepared: " + hiddenSecret + " (" +
                            chars.charAt(0) + "-" + chars.charAt(9) + ").");
                } else if (possibilities == 12) {
                    System.out.println("The secret is prepared: " + hiddenSecret + " (" +
                            chars.charAt(0) + "-" + chars.charAt(9) + ", a).");
                } else {
                    System.out.println("The secret is prepared: " + hiddenSecret + " (" +
                            chars.charAt(0) + "-" + chars.charAt(9) + ", a-" +
                            chars.charAt(possibilities - 1) + ").");
                }
                System.out.println("Okay, let's start a game!");
                Random rnd = new Random();
                StringBuilder secret = new StringBuilder();
                boolean[] already = new boolean[possibilities];
                for (int i = lCode - 1; i >= 0; i--) {
                    int digit = rnd.nextInt(0, lCode);
                    if (!already[digit]) {
                        already[digit] = true;
                        char symbol = chars.charAt(digit);
                        secret.append(symbol);
                    } else {
                        i++;
                    }
                }
                System.out.println(".");

                int turnCount = 1;

                while (true) {
                    System.out.println("Turn " + turnCount++);
                    String guess = s.next();
                    int cows = 0;
                    int bulls = 0;
                    for (int i = 0; i < lCode; i++) {
                        for (int j = 0; j < lCode; j++) {
                            if (guess.charAt(i) == secret.charAt(j)) {
                                if (i == j) bulls++;
                                else cows++;
                            }
                        }
                    }

                    if (cows != 0 && bulls != 0) {
                        System.out.println("Grade: " + bulls + " bull(s) and " + cows + " cow(s)");
                    } else if (cows != 0 && bulls == 0) {
                        System.out.println("Grade: " + cows + " cow(s)");
                    } else if (bulls != 0 && cows == 0) {
                        System.out.println("Grade: " + bulls + " bull(s)");
                    } else {
                        System.out.println("Grade: None.");
                    }
                    if (bulls == lCode) {
                        System.out.println("Congratulations! You guessed the secret code.");
                        break;
                    }
                }
            }
        }
    }
}
