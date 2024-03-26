import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExerciseValidator {
    public static void validate(String exerciseFile, String answerFile) throws IOException {
        List<String> exercises = FileHandler.readLines(exerciseFile);
        List<String> answers = FileHandler.readLines(answerFile);

        int correct = 0;
        List<Integer> correctIndexes = new ArrayList<>();
        List<Integer> wrongIndexes = new ArrayList<>();

        for (int i = 0; i < exercises.size(); i++) {
            // 假设每个答案是计算后的最简形式，且直接与题目结果匹配
            // 实际实现中需要考虑解析和计算表达式，此处直接以答案匹配作为示例
            String expectedAnswer = calculateAnswer(exercises.get(i));
            if (expectedAnswer.equals(answers.get(i))) {
                correct++;
                correctIndexes.add(i + 1);
            } else {
                wrongIndexes.add(i + 1);
            }
        }

        // 输出结果到Grade.txt
        try {
            List<String> results = new ArrayList<>();
            results.add("Correct: " + correct + " " + correctIndexes.toString());
            results.add("Wrong: " + (exercises.size() - correct) + " " + wrongIndexes.toString());
            FileHandler.writeLines("Grade.txt", results);
        } catch (IOException e) {
            System.out.println("Failed to write Grade.txt: " + e.getMessage());
        }
    }

    // 示例方法，实际需要实现表达式的解析和计算
    private static String calculateAnswer(String exercise) {
        // 解析和计算表达式，返回计算结果的字符串表示
        return ""; // 简化示例，实际应返回计算结果
    }
}
