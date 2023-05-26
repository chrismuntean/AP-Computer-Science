import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OrderProcessor {
    // Paths to the orders and prices files
    private static final String ORDERS_FILE = "Post-AP/File_exercises/Orders.txt";
    private static final String PRICES_FILE = "Post-AP/File_exercises/Prices.txt";

    public static void main(String[] args) {
        // Get the output file name from the user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the output file: ");
        String outputFile = "Post-AP/File_exercises/" + scanner.nextLine();

        // Read prices into a map for easy lookup
        Map<String, Double> prices = readPrices(PRICES_FILE);

        try (BufferedReader reader = new BufferedReader(new FileReader(ORDERS_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            // Skip the header line in the orders file
            reader.readLine();

            String line;
            double total = 0.0;

            // Calculate the subtotal for each order and write it to the output file and add it to the total
            while ((line = reader.readLine()) != null) {
                String[] order = line.split("\\s+");
                String id = order[0];
                int quantity = Integer.parseInt(order[1]);

                double price = prices.getOrDefault(id, 0.0);
                double subtotal = price * quantity;

                writer.write(id + " " + quantity + " " + subtotal);
                writer.newLine();

                total += subtotal;
            }

            // Write the total to the output file
            writer.write("Total: " + total);
            System.out.println("Order processing complete. Output written to " + outputFile);
        } catch (IOException e) {
            // If an error occurs display a msg
            System.out.println("An error occurred while processing the order.");
        }
    }

    // Reads the prices from the prices file and returns them in a map
    private static Map<String, Double> readPrices(String pricesFile) {
        Map<String, Double> prices = new HashMap<>();

        // Read the prices from the file and add them to the map
        try (BufferedReader reader = new BufferedReader(new FileReader(pricesFile))) {
            String line;
            // Skip the header line in the prices file
            reader.readLine();

            // Read each line and add the price to the map
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                String id = parts[0];
                String priceStr = parts[1].substring(1); // Remove the dollar sign

                // Try to parse the price and add it to the map
                try {
                    double price = Double.parseDouble(priceStr);
                    prices.put(id, price);
                } catch (NumberFormatException e) {
                    // If the price is invalid display a msg
                    System.out.println("Invalid price format for ID: " + id);
                }
            }
        } catch (IOException e) {
            // If an error occurs display a msg
            System.out.println("An error occurred while reading the prices.");
        }

        return prices;
    }
}
