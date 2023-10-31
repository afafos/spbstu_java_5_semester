public class Main {
    public static void main(String[] args) {
        int num = 2;
        char grade = 'B';
        String day = "Monday";

        switch (num) {
            case 1:
                System.out.println("Case 1: Number is 1");
                break;
            case 2:
                System.out.println("Case 2: Number is 2");
                break;
            default:
                System.out.println("Default case");
        }

        switch (grade) {
            case 'A':
                System.out.println("Case A: Grade is A");
                break;
            case 'B':
                System.out.println("Case B: Grade is B");
                break;
            default:
                System.out.println("Default case");
        }

        switch (day) {
            case "Sunday":
                System.out.println("Case Sunday: It's Sunday");
                break;
            case "Monday":
                System.out.println("Case Monday: It's Monday");
                break;
            default:
                System.out.println("Default case");
        }
    }
}
