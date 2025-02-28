import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String question;
    String[] options;
    int correctAnswerIndex;

    public Question(String question, String[] options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }
}
class Main {
    private static List<Question> questions = new ArrayList<>();
    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static final int TIME_LIMIT = 10;
    public static void main(String[] args) {
        loadQuestions();
        startQuiz();
    }

    private static void loadQuestions() {
        questions.add(new Question("What is the capital of France?", new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"}, 2));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"1. Earth", "2. Mars", "3. Jupiter", "4. Saturn"}, 1));
        questions.add(new Question("What is the largest ocean on Earth?", new String[]{"1. Atlantic", "2. Indian", "3. Arctic", "4. Pacific"}, 3));
        questions.add(new Question("Who wrote 'Hamlet'?", new String[]{"1. Charles Dickens", "2. Mark Twain", "3. William Shakespeare", "4. J.K. Rowling"}, 2));
        questions.add(new Question("What is the chemical symbol for water?", new String[]{"1. O2", "2. H2O", "3. CO2", "4. NaCl"}, 1));
    }

    private static void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        for (currentQuestionIndex = 0; currentQuestionIndex < questions.size(); currentQuestionIndex++) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            System.out.println("\n" + currentQuestion.question);
            for (String option : currentQuestion.options) {
                System.out.println(option);
            }
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up! Moving to the next question.");
                    currentQuestionIndex++;
                    if (currentQuestionIndex < questions.size()) {
                        startQuiz();
                    } else {
                        displayResult();
                    }
                }
            }, TIME_LIMIT * 1000);
            int userAnswer = -1;
            try {
                userAnswer = Integer.parseInt(scanner.nextLine()) - 1; 
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
            timer.cancel();
            if (userAnswer == currentQuestion.correctAnswerIndex) {
                score++;
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect! The correct answer was: " + currentQuestion.options[currentQuestion.correctAnswerIndex]);
            }
        }


        displayResult();
    }

    private static void displayResult() {
        System.out.println("\nQuiz Finished!");
        System.out.println("Your score: " + score + "/" + questions.size());
        System.out.println("Thank you for playing!");
    }
}
