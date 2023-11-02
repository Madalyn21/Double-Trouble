public class main {
    public static void main(String[] args) {
        int gamePlay = 0; // Initialize the variable to keep track of the game progress.
        game myGame = new game(); // Create a new instance of the 'game' class.
        String winner = ""; // Initialize a variable to store the winner's name.

        // Check the turn order to decide who goes first.
        if (myGame.turnOrder()) {
            // If the player goes first:
            while (gamePlay == 0) {
                myGame.printBoard(); // Print the game board.
                myGame.playerTurn(); // Allow the player to take their turn.
                winner = "Player"; // Set the winner to "Player."
                myGame.printBoard(); // Print the updated game board.
                myGame.cpuTurn(); // Allow the CPU to take its turn.
                winner = "CPU"; // Set the winner to "CPU."
                System.out.println("Computer went"); // Print a message indicating the CPU's move.

                gamePlay = gamePlay + myGame.gameOver(); // Check if the game is over and update the game progress.
            }
        } else {
            // If the CPU goes first:
            while (gamePlay == 0) {
                myGame.printBoard(); // Print the game board.
                myGame.cpuTurn(); // Allow the CPU to take its turn.
                System.out.println("Computer went"); // Print a message indicating the CPU's move.
                winner = "CPU"; // Set the winner to "CPU."
                myGame.printBoard(); // Print the updated game board.
                myGame.playerTurn(); // Allow the player to take their turn.
                winner = "Player"; // Set the winner to "Player."
                gamePlay = gamePlay + myGame.gameOver(); // Check if the game is over and update the game progress.
            }
        }

        System.out.println("Game Over"); // Print "Game Over" when the game ends.
        System.out.println("The winner is the " + winner); // Print the name of the winner.
    }
}
