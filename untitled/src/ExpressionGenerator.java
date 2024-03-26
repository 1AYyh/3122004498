import java.util.Random;

import java.util.Random;

public class ExpressionGenerator {
    private Random random = new Random();
    private int range;

    public ExpressionGenerator(int range) {
        this.range = range;
    }

    public String generate() {
        // 为简化，这里仅生成含两个数字的表达式
        int number1 = random.nextInt(range) + 1; // 避免生成0
        int number2 = random.nextInt(range) + 1;
        String[] operators = {"+", "-", "*", "/"};
        String operator = operators[random.nextInt(operators.length)];

        // 确保不会出现负数或除以0
        if (operator.equals("-") && number1 < number2) {
            int temp = number1;
            number1 = number2;
            number2 = temp;
        } else if (operator.equals("/")) {
            number1 = number1 * number2; // 确保能够整除
        }

        return number1 + " " + operator + " " + number2;
    }
}
