public class Operations {
    public static void main(String[] args) {

        boolean a = true;
        boolean b = false;
        boolean resultAnd = a && b;
        boolean resultOr = a || b;
        boolean resultNotA = !a;

        System.out.println("Logical AND: " + resultAnd);
        System.out.println("Logical OR: " + resultOr);
        System.out.println("Logical NOT (a): " + resultNotA);

        int x = 5;
        int y = 10;
        int min = x < y ? x : y;
        System.out.println("Minimum value: " + min);


        int num1 = 0b1100;  // Binary representation of 12
        int num2 = 0b1010;  // Binary representation of 10

        int bitwiseAnd = num1 & num2;
        int bitwiseOr = num1 | num2;
        int bitwiseXor = num1 ^ num2;
        int bitwiseNot = ~num1;

        System.out.println("Bitwise AND: " + Integer.toBinaryString(bitwiseAnd));  // 1000 (8 in decimal)
        System.out.println("Bitwise OR: " + Integer.toBinaryString(bitwiseOr));    // 1110 (14 in decimal)
        System.out.println("Bitwise XOR: " + Integer.toBinaryString(bitwiseXor));  // 0110 (6 in decimal)
        System.out.println("Bitwise NOT: " + Integer.toBinaryString(bitwiseNot));  // 1111111111111111111111111111011 (-13 in decimal)

        int num3 = 16;  // Binary representation: 10000

        int rightShift = num3 >> 2;        // 00100 (4 in decimal)
        int leftShift = num3 << 3;         // 10000000 (128 in decimal)
        int unsignedRightShift = num3 >>> 2;  // 00100 (4 in decimal)

        System.out.println("Right Shift: " + rightShift);
        System.out.println("Left Shift: " + leftShift);
        System.out.println("Unsigned Right Shift: " + unsignedRightShift);
    }
}

