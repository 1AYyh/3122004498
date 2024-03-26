import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 检查是否提供了足够的参数
        if (args.length == 0) {
            showUsage();
            return;
        }

        try {
            if ("-n".equals(args[0]) && args.length >= 2) {
                int n = Integer.parseInt(args[1]); // 题目数量
                int range = 10; // 默认数值范围
                if (args.length == 4 && "-r".equals(args[2])) {
                    range = Integer.parseInt(args[3]);
                }
                generateQuestionsAndAnswers(n, range);
            } else if ("-e".equals(args[0]) && args.length == 4 && "-a".equals(args[2])) {
                String exerciseFile = args[1];
                String answerFile = args[3];
                ExerciseValidator.validate(exerciseFile, answerFile);
            } else {
                showUsage();
            }
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void generateQuestionsAndAnswers(int n, int range) {
        List<String> questions = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        ExpressionGenerator generator = new ExpressionGenerator(range);

        for (int i = 0; i < n; i++) {
            // 这里的generate()和calculate()方法需要在ExpressionGenerator和AnswerCalculator类中分别实现
            String question = generator.generate(); // 生成题目
            String answer = AnswerCalculator.calculate(question); // 计算答案
            questions.add(question + " = ?");
            answers.add("Answer " + (i + 1) + ": " + answer);
        }

        try {
            FileHandler.writeLines("Exercises.txt", questions);
            FileHandler.writeLines("Answers.txt", answers);
            System.out.println(n + " questions have been generated and saved to Exercises.txt and Answers.txt.");
        } catch (IOException e) {
            System.err.println("Failed to write to files: " + e.getMessage());
        }
    }

    private static void showUsage() {
        System.out.println("Usage:");
        System.out.println("  To generate questions: Myapp.exe -n <number_of_questions> [-r <range>]");
        System.out.println("  To validate answers: Myapp.exe -e <exercise_file> -a <answer_file>");
    }
}
