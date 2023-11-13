import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String question;
    List<String> options;
    int correctOption;

    Question(String question, List<String> options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }
}

public class QuizApplication {
    private static List<Question> questions = new ArrayList<>();
    private static int userScore = 0;
    private static int currentQuestionIndex = 0;
    private static Timer timer;

    public static void main(String[] args) {
        initializeQuestions();

        System.out.println("Welcome to the Quiz Application!");
        startQuiz();
    }

    private static void initializeQuestions() {
        
        List<String> options1 = List.of("Option A", "Option B", "Option C", "Option D");
        questions.add(new Question("What is the capital of France?", options1, 2));

        List<String> options2 = List.of("Java", "Python", "C#", "JavaScript");
        questions.add(new Question("Which programming language is used for Android app development?", options2, 1));

        
    }

    private static void startQuiz() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            displayQuestion(currentQuestion);

            // Set up timer for the question
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up! Moving to the next question.");
                    checkAnswer(-1); 
                    startQuiz(); 
                }
            }, 15000); 

            
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print("Your choice (1-" + currentQuestion.options.size() + "): ");
                int userChoice = scanner.nextInt();
                checkAnswer(userChoice);
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); 
                checkAnswer(-1); 
            }
        } else {
            endQuiz();
        }
    }

    private static void displayQuestion(Question question) {
        System.out.println("\n" + question.question);
        for (int i = 0; i < question.options.size(); i++) {
            System.out.println((i + 1) + ". " + question.options.get(i));
        }
    }

    private static void checkAnswer(int userChoice) {
        if (userChoice == questions.get(currentQuestionIndex).correctOption) {
            System.out.println("Correct!");
            userScore++;
        } else if (userChoice != -1) {
            System.out.println("Incorrect. The correct answer was: " +
                    questions.get(currentQuestionIndex).options.get(questions.get(currentQuestionIndex).correctOption));
        }

        
        if (timer != null) {
            timer.cancel();
        }

        currentQuestionIndex++;
        startQuiz(); 
    }

    private static void endQuiz() {
        System.out.println("\nQuiz completed!");
        System.out.println("Your final score: " + userScore + " out of " + questions.size());
        
    }
}


