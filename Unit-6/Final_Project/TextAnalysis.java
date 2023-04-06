import java.util.Scanner;

public class TextAnalysis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a short paragraph:");

        // Get the paragraph from the user
        String paragraph = scanner.nextLine();
        scanner.close();

        // Define variables to store the results
        int nonWhitespaceChars = 0;
        int wordCount = 0;
        int sentenceCount = 0;
        int totalWordLength = 0;

        boolean newWord = true;
        boolean newSentence = true;

        // Loop through each character in the paragraph
        for (char c : paragraph.toCharArray()) {
            if (!Character.isWhitespace(c)) {
                nonWhitespaceChars++;

                if (newWord) {
                    wordCount++;
                    newWord = false;
                }

                if (newSentence) {
                    sentenceCount++;
                    newSentence = false;
                }

                if (c == '.' || c == ',') {
                    newWord = true;
                    if (c == '.') {
                        newSentence = true;
                    }
                } else {
                    totalWordLength++;
                }
            } else {
                newWord = true;
            }
        }

        // Calculate the results
        double averageWordsPerSentence = (double) wordCount / sentenceCount;
        double averageWordLength = (double) totalWordLength / wordCount;

        // Print the results
        System.out.println("Total non-whitespace characters: " + nonWhitespaceChars);
        System.out.println("Total words: " + wordCount);
        System.out.println("Average words per sentence: " + averageWordsPerSentence);
        System.out.println("Average word length: " + averageWordLength);
    }
}
