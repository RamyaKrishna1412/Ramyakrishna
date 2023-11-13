import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        System.out.println("Welcome to the Number Game!");
        boolean playAgain = true;
        int totalAttempts = 0;
        int totalRounds = 0;

        Scanner scanner = new Scanner(System.in);

        while (playAgain) {
            
            Random random = new Random();
            int secretNumber = random.nextInt(100) + 1;
            int attempts = 0;
            int roundsWon = 0;

            
            while (true) {
                System.out.print("Enter your guess (1-100): ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == secretNumber) {
                    System.out.println("Congratulations! You guessed the correct number " + secretNumber + " in " + attempts + " attempts.");
                    roundsWon++;
                    break;
                } else if (userGuess < secretNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }

                
                if (attempts == 10) {
                    System.out.println("Sorry, you've reached the maximum number of attempts. The correct number was " + secretNumber + ".");
                    break;
                }
            }

            totalAttempts += attempts;
            totalRounds += roundsWon;

            
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        }

        
        System.out.println("Thanks for playing! Your total attempts: " + totalAttempts + ", Rounds won: " + totalRounds);
        scanner.close();
    }
}
