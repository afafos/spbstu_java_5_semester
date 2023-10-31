public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No args.");
        } else {
            System.out.println("Args:");
            for (int i = 0; i < args.length; i++) {
                System.out.println("Arg " + (i + 1) + ": " + args[i]);
            }
        }
    }
}