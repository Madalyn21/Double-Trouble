import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class game {
    // Fields to store the counts of different colored objects
    static int green;
    static int orange;
    static int yellow;

    public game() {
        // Initialize counts for each color
        green = 3;
        orange = 5;
        yellow = 7;
    }

    public static void play(String color, int num) throws invalidPlay {
        // Check the validity of the color input
        if (!(color.equalsIgnoreCase("green") || color.equalsIgnoreCase("yellow") || color.equalsIgnoreCase("orange"))) {
            throw new invalidPlay();
        }

        // Handle the player's move
        if (color.equalsIgnoreCase("green")) {
            if (game.green >= num && num > 0) {
                game.green = game.green - num;
            } else {
                throw new invalidPlay();
            }
        } else if (color.equalsIgnoreCase("yellow")) {
            if (game.yellow >= num && num > 0) {
                game.yellow = game.yellow - num;
            } else {
                throw new invalidPlay();
            }
        } else if (color.equalsIgnoreCase("orange")) {
            if (game.orange >= num && num > 0) {
                game.orange = game.orange - num;
            } else {
                throw new invalidPlay();
            }
        }
    }

    public static void playerTurn() {
        // Handle the player's turn
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter color: ");
        String color = scanner.nextLine();
        System.out.println("Enter number: ");
        int num = Integer.parseInt(scanner.nextLine());

        try {
            play(color, num);
        } catch (invalidPlay e) {
            System.out.println("Invalid play, try again");
            playerTurn(); // Recursively prompt the player for a valid move.
        }
    }

    public static void cpuTurn() {
        // Determine the CPU's move based on Nim sum strategy
        int nimsum = game.green ^ game.yellow ^ game.orange;
        if (nimsum != 0) {
            if ((game.green ^ nimsum) < game.green) {
                game.green = game.green ^ nimsum;
            }
            if ((game.orange ^ nimsum) < game.orange) {
                game.orange = game.orange ^ nimsum;
            }
            if ((game.yellow ^ nimsum) < game.yellow) {
                game.yellow = game.yellow ^ nimsum;
            }
        } else if (nimsum == 0) {
            // If nimsum is 0, choose a random move
            Random rand = new Random();

            ArrayList<String> choices = new ArrayList<String>(3);
            if (game.green > 0) {
                choices.add("green");
            }
            if (game.yellow > 0) {
                choices.add("yellow");
            }
            if (game.orange > 0) {
                choices.add("orange");
            }
            Collections.shuffle(choices);

            // Perform the chosen CPU move
            if (choices.get(0).equals("green")) {
                game.green = game.green - rand.nextInt((game.green - 1) + 1) + 1;
            }
            if (choices.get(0).equals("yellow")) {
                game.yellow = game.yellow - rand.nextInt((game.yellow - 1) + 1) + 1;
            }
            if (choices.get(0).equals("orange")) {
                game.orange = game.orange - rand.nextInt((game.orange - 1) + 1) + 1;
            }
        }
    }

    public static void printBoard() {
        // Print the current state of the game board
        System.out.print("Green:  ");
        for (int i = 0; game.green > i; i++) {
            System.out.print("### ");
        }
        System.out.println(" ");

        System.out.print("Orange: ");
        for (int i = 0; game.orange > i; i++) {
            System.out.print("### ");
        }
        System.out.println(" ");

        System.out.print("Yellow: ");
        for (int i = 0; game.yellow > i; i++) {
            System.out.print("### ");
        }
        System.out.println(" ");
    }

    public static int gameOver() {
        // Check if the game is over, and return 1 if it's over, else return 0
        if ((game.orange == 0) && (game.yellow == 0) && (game.green == 0)) {
            return 1;
        } else {
            return 0;
        }
    }

    public static boolean turnOrder() {
        // Determine whether the player or CPU goes first
        boolean userFirst = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to go first? (y/n): ");
        String userTurn = scanner.nextLine();
        if (userTurn.equalsIgnoreCase("y")) {
            userFirst = true;
        }
        return userFirst;
    }
}
