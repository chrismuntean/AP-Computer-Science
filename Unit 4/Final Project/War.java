import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class War {

    public static void main(String[] args) {

        ArrayList<Integer> playerDeck = new ArrayList<Integer>();
        ArrayList<Integer> computerDeck = new ArrayList<Integer>();
        int playerScore = 0;
        int computerScore = 0;

        // Initialize the decks with the point values for each card
        for (int i = 2; i <= 10; i++) {
            playerDeck.add(i);
            computerDeck.add(i);
        }
        playerDeck.add(11); // J
        playerDeck.add(12); // Q
        playerDeck.add(13); // K
        playerDeck.add(1); // A

        computerDeck.add(11); // J
        computerDeck.add(12); // Q
        computerDeck.add(13); // K
        computerDeck.add(1); // A

        // Shuffle the decks
        Collections.shuffle(playerDeck);
        Collections.shuffle(computerDeck);

        // Cut the decks in half
        ArrayList<Integer> playerHalf = new ArrayList<Integer>(playerDeck.subList(0, playerDeck.size() / 2));
        ArrayList<Integer> computerHalf = new ArrayList<Integer>(computerDeck.subList(0, computerDeck.size() / 2));

        Scanner sc = new Scanner(System.in);

        // Play the game
        while (playerHalf.size() > 0 && computerHalf.size() > 0) {
            System.out.println("Press enter to draw a card...");
            sc.nextLine();
            int playerCard = playerHalf.remove(0);
            int computerCard = computerHalf.remove(0);
            System.out.println("Player drew " + playerCard + ", Computer drew " + computerCard);

            if (playerCard > computerCard) {
                playerScore += playerCard - computerCard;
                System.out.println("Player wins this round! Score: " + playerScore);
            } else if (computerCard > playerCard) {
                computerScore += computerCard - playerCard;
                System.out.println("Computer wins this round! Score: " + computerScore);
            } else {
                System.out.println("It's a tie! No points awarded.");
            }
        }

        // Game over, display final score
        System.out.println("Game over! Final score: Player: " + playerScore + ", Computer: " + computerScore);
        if (playerScore > computerScore) {
            System.out.println("Player wins!");
        } else if (computerScore > playerScore) {
            System.out.println("Computer wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }
}