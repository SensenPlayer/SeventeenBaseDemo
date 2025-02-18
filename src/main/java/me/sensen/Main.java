package me.sensen;

/**
 * @author Sensen
 */
public class Main {
    private static final char[] SYMBOLS = "0123456789ABCDEFG".toCharArray();

    // 十进制转十七进制（整数）
    public static String decimalToSeventeen(int decimal) {
        if (decimal == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        while (decimal > 0) {
            int remainder = decimal % 17;
            sb.append(SYMBOLS[remainder]);
            decimal /= 17;
        }
        return sb.reverse().toString();
    }

    // 十七进制转十进制
    public static int seventeenToDecimal(String seventeen) {
        String s = seventeen.toUpperCase();
        int decimal = 0;
        int power = 1;

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            int value = Character.isDigit(c) ?
                    c - '0' :
                    c - 'A' + 10;
            decimal += value * power;
            power *= 17;
        }
        return decimal;
    }

    // 测试用例
    public static void main(String[] args) {
        testConversion(0, "0");
        testConversion(17, "10");
        testConversion(289, "100");
        testConversion(4913, "1000");
        testConversion(67, "3G");
        testConversion(100, "5F");
    }

    private static void testConversion(int decimal, String expectedSeventeen) {
        // 正向转换测试
        String actualSeventeen = decimalToSeventeen(decimal);
        System.out.printf("十进制 %4d → 十七进制 %-4s | 测试 %s%n",
                decimal, actualSeventeen,
                actualSeventeen.equals(expectedSeventeen) ? "通过" : "失败");

        // 逆向转换测试
        int convertedBack = seventeenToDecimal(actualSeventeen);
        System.out.printf("十七进制 %4s → 十进制 %4d | 测试 %s%n%n",
                actualSeventeen, convertedBack,
                convertedBack == decimal ? "通过" : "失败");
    }
}