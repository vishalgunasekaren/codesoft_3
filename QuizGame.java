import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizGame {
    private static final int TOTAL_QUESTIONS = 3;
    private static final int TIME_LIMIT = 10; // Time limit for each question in seconds

    private String[] questions = {
        // "What is the capital of France?",
        "Which planet is known as the Red Planet?",
        "What is the powerhouse of the cell?"
    };

    private String[][] options = {
        // {"A) Madrid", "B) Paris", "C) Rome", "D) Berlin"},
        {"A) Venus", "B) Mars", "C) Jupiter", "D) Saturn"},
        {"A) Mitochondria", "B) Nucleus", "C) Ribosome", "D) Golgi Apparatus"}
    };

    private char[] answers = {'B', 'B', 'A'}; // Correct answers

    private int currentQuestionIndex = 0;
    private int score = 0;
    private Scanner scanner = new Scanner(System.in);
    private Timer timer;

    public void startQuiz() {
        displayQuestion();

        // Start timer
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up!");
                nextQuestion();
            }
        }, TIME_LIMIT * 1000);
    }

    private void displayQuestion() {
        System.out.println("\nQuestion " + (currentQuestionIndex + 1) + ": " + questions[currentQuestionIndex]);

        for (String option : options[currentQuestionIndex]) {
            System.out.println(option);
        }

        System.out.print("Enter your answer (A, B, C, or D): ");
        char userAnswer = Character.toUpperCase(scanner.next().charAt(0));

        if (userAnswer == answers[currentQuestionIndex]) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect! The correct answer is " + answers[currentQuestionIndex]);
        }

        nextQuestion();
    }

    private void nextQuestion() {
        timer.cancel(); // Cancel the current timer

        if (currentQuestionIndex < TOTAL_QUESTIONS - 1) {
            currentQuestionIndex++;
            startQuiz(); // Start the next question
        } else {
            displayResult();
        }
    }

    private void displayResult() {
        System.out.println("\nQuiz completed!");
        System.out.println("Your score: " + score + "/" + TOTAL_QUESTIONS);

        int correctAnswers = score;
        int incorrectAnswers = TOTAL_QUESTIONS - score;
        System.out.println("Correct answers: " + correctAnswers);
        System.out.println("Incorrect answers: " + incorrectAnswers);
    }

    public static void main(String[] args) {
        QuizGame quizGame = new QuizGame();
        quizGame.startQuiz();
    }
}
