import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CityListUpdater {
    public static void main(String[] args) {
        // Read the city list from file
        List<String> cityList = readCityListFromFile();

        // Prompt the user to enter cities
        System.out.println("Enter your recent cities (enter 'stop' to quit):");
        Scanner scanner = new Scanner(System.in);
        String city = scanner.nextLine();

        // Add the cities to the array list
        while (!city.equalsIgnoreCase("stop")) {
            if (!cityList.contains(city)) {
                cityList.add(city);
                System.out.println("Added '" + city + "' to the city list.");
            } else {
                // If the city already exists in the list display a msg
                System.out.println("City already exists in the list.");
            }

            city = scanner.nextLine();
        }

        // Write the city list to file
        writeCityListToFile(cityList);
        System.out.println("City list updated successfully.");
    }

    // Reads the city list from file and returns it as an array list
    private static List<String> readCityListFromFile() {
        List<String> cityList = new ArrayList<>();

        // Read the city list from file
        try (BufferedReader reader = new BufferedReader(new FileReader("Post-AP/File_exercises/Cities.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                cityList.add(line);
            }
        } catch (IOException e) {
            // If the file can't read display a msg
            System.out.println("Error reading the city list from file.");
        }
        return cityList;
    }

    // Writes the city list to file
    private static void writeCityListToFile(List<String> cityList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Post-AP/File_exercises/Cities.txt"))) {
            // Write each city to a new line
            for (String city : cityList) {
                writer.write(city);
                writer.newLine();
            }
        } catch (IOException e) {
            // If the file can't be written display a msg
            System.out.println("Error writing the city list to file.");
        }
    }
}
