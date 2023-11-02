public class Main {
    public static void main(String[] args) {
        try {
            Triple<Integer> triple1 = new Triple<>(10, 5, 20);
            System.out.println("Min: " + triple1.min());
            System.out.println("Max: " + triple1.max());
            System.out.println("Mean: " + triple1.mean());

            Triple<String> triple2 = new Triple<>("a", "b", "c");
            System.out.println("Min: " + triple2.min());
            System.out.println("Max: " + triple2.max());
            System.out.println("Mean: " + triple2.mean());

        } catch (UnsupportedOperationException e) {
            System.out.println("Unsupported operation: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
