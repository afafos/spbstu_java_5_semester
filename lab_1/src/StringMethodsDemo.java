public class StringMethodsDemo {
    public static void main(String[] args) {

        String sampleString = "Hello, World!";

        // 1. Get the length of the string
        int length = sampleString.length();
        System.out.println("1. Length of the string: " + length);

        // 2. Concatenate strings
        String newString = sampleString.concat(" Welcome to Java!");
        System.out.println("2. Concatenated String: " + newString);

        // 3. Substring
        String substring = sampleString.substring(7); // Starting from index 7
        System.out.println("3. Substring: " + substring);

        // 4. String to uppercase
        String upperCaseString = sampleString.toUpperCase();
        System.out.println("4. Uppercase: " + upperCaseString);

        // 5. String to lowercase
        String lowerCaseString = sampleString.toLowerCase();
        System.out.println("5. Lowercase: " + lowerCaseString);

        // 6. Replace characters
        String replacedString = sampleString.replace("World", "Java");
        System.out.println("6. Replaced String: " + replacedString);

        // 7. Check if it contains a substring
        boolean containsWorld = sampleString.contains("World");
        System.out.println("7. Contains 'World': " + containsWorld);

        // 8. Find the index of a character or substring
        int indexOfComma = sampleString.indexOf(",");
        System.out.println("8. Index of ',': " + indexOfComma);

        // 9. Split the string into an array
        String[] words = sampleString.split(" ");
        System.out.println("9. Split into words: ");
        for (String word : words) {
            System.out.println("   - " + word);
        }

        // 10. Trim leading and trailing spaces
        String stringWithSpaces = "   Hello, world!   ";
        String trimmedString = stringWithSpaces.trim();
        System.out.println("10. Trimmed String: " + trimmedString);
    }
}
