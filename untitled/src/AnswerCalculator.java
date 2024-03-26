public class AnswerCalculator {
    public static String calculate(String expression) {
        // 将表达式分割成部分
        String[] parts = expression.split(" ");
        int result = Integer.parseInt(parts[0]); // 初始化结果为第一个数字

        for (int i = 1; i < parts.length; i += 2) {
            String operator = parts[i];
            int nextNumber = Integer.parseInt(parts[i + 1]);

            switch (operator) {
                case "+":
                    result += nextNumber;
                    break;
                case "-":
                    result -= nextNumber;
                    break;
                case "*":
                    result *= nextNumber;
                    break;
                case "/":
                    result /= nextNumber; // 注意：这里不处理除数为0的情况
                    break;
            }
        }

        return String.valueOf(result);
    }
}
