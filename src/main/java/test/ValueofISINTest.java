package test;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 3/2/2022
 */
public class ValueofISINTest {

    public static void main(String[] args) throws Exception {
        String ISIN = "US15987TAB70";
        int test = oddEvenEvalforIsin(ISIN);
        int test1 = Character.getNumericValue(ISIN.charAt(11));
        System.out.println("oddEvenEvalforIsin: " + test);
        System.out.println("char int value of the eleventh number:"+test1);
    }

    private static int oddEvenEvalforIsin(String identifier)
            throws Exception {
        //
        // The input cin value (cin) is manipulated digit by digit,
        // a final total is calculated using the Modulus 10 Double Add Double
        // technique. See design documentation.
        //
        //boolean isIsin = true;
        int finalTotal = 0;
        char value;
        int vint;
        int offset = 0;
        int alphaCount = 0;

        for (int i = 0; i < identifier.length(); i++) {
            value = identifier.charAt(i);
            if (!Character.isDigit(value)) {
                alphaCount++;
            }
        }
        for (int i = identifier.length() - 1; i >= 0; --i) {
            value = identifier.charAt(i);
            if (value >= '0' && value <= '9') {
                vint = Character.getNumericValue(value)
                        - Character.getNumericValue('0');
            } else if (value >= 'A' && value <= 'Z') {
                vint = Character.getNumericValue(value)
                        - Character.getNumericValue('A') + 10;
            } else if (value == '*') {
                vint = 36;
            } else if (value == '@') {
                vint = 37;
            } else if (value == '#') {
                vint = 38;
            } else {
                throw new Exception(
                        "Error in Checksum: " + identifier);
            }

            if ((i + offset) % 2 == 0) {
                if (vint > 9) {
                    finalTotal += calculateValue(vint / 10, -1);
                    finalTotal += calculateValue(vint % 10, 1);
                    ++offset;
                } else {
                    finalTotal += calculateValue(vint, 1);
                }
            } else {
                if (vint > 9) {
                    finalTotal += calculateValue(vint / 10, 1);
                    finalTotal += calculateValue(vint % 10, -1);
                    ++offset;
                } else {
                    finalTotal += calculateValue(vint, -1);
                }
            }
        }

        return (10 - (finalTotal % 10)) % 10;
    }

    private static int calculateValue(int num, int oddEven) {
        //
        // every second(even) value is mutiplied by two.
        // 1=even
        //
        if (oddEven == 1) {
            num *= 2;
        }

        if (num > 9) {
            int num1 = num % 10;
            int num2 = num / 10;
            num = num1 + num2;
        }

        return num;
    }
}
