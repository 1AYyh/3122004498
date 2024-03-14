import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class PaperPlagiarismChecker {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java PaperPlagiarismChecker <original_file_path> <plagiarized_file_path> <output_file_path>");
            return;
        }

        String originalFilePath = args[0];
        String plagiarizedFilePath = args[1];
        String outputFilePath = args[2];

        try {
            // 读取并处理两个文件
            String originalText = new String(Files.readAllBytes(Paths.get(originalFilePath)));
            String plagiarizedText = new String(Files.readAllBytes(Paths.get(plagiarizedFilePath)));

            // 计算重复率
            double similarity = calculateSimilarity(originalText, plagiarizedText);

            // 将相似度结果写入输出文件
            writeSimilarityToOutput(similarity, outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double calculateSimilarity(String originalText, String plagiarizedText) {
        // 将字符串转换为字符数组，然后转换为列表
        List<String> originalWords = Arrays.asList(originalText.toLowerCase().split(""));
        List<String> plagiarizedWords = Arrays.asList(plagiarizedText.toLowerCase().split(""));

        Set<String> originalSet = new HashSet<>(originalWords);
        Set<String> plagiarizedSet = new HashSet<>(plagiarizedWords);

        Set<String> intersection = new HashSet<>(originalSet);
        intersection.retainAll(plagiarizedSet);
        Set<String> union = new HashSet<>(originalSet);
        union.addAll(plagiarizedSet);

        return union.isEmpty() ? 0.0 : (double) intersection.size() / union.size();
    }

    private static void writeSimilarityToOutput(double similarity, String outputFilePath) throws IOException {
        try (PrintWriter out = new PrintWriter(outputFilePath)) {
            out.printf("%.2f", similarity * 100); // 将相似度转换为百分比形式
        }
    }
}
