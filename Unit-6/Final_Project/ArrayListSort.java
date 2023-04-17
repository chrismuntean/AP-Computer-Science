import java.util.ArrayList;
import java.util.Random;

public class ArrayListSort {
    public static void main(String[] args) {
        int size = 10;
        int maxRandomValue = 100;

        // Generate an ArrayList of random integers.
        ArrayList<Integer> numbers = generateRandomArrayList(size, maxRandomValue);
        System.out.println("Unsorted ArrayList: " + numbers);

        // Sort the ArrayList.
        selectionSort(numbers);
        System.out.println("Sorted ArrayList: " + numbers);

        // Select a random target integer.
        int randomIndex = new Random().nextInt(size);
        int target = numbers.get(randomIndex);
        
        System.out.println("\nRandomly selected target integer: " + target);

        // Perform a binary search on the sorted ArrayList.
        int index = binarySearch(numbers, target);
        if (index != -1) {
            System.out.println("Target " + target + " found at index: " + index);
        } else {
            System.out.println("Target " + target + " not found.");
        }
    }

    // This method generates an ArrayList of random integers.
    public static ArrayList<Integer> generateRandomArrayList(int size, int maxRandomValue) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Random random = new Random();

        // Generate random integers and add them to the ArrayList.
        for (int i = 0; i < size; i++) {
            arrayList.add(random.nextInt(maxRandomValue));
        }

        // Return the ArrayList.
        return arrayList;
    }

    // This method does a selection sort on an ArrayList of integers.
    public static void selectionSort(ArrayList<Integer> arrayList) {
        int size = arrayList.size();

        // The loop will terminate when i == size - 1.
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;

            // Find the minimum value in the unsorted part of the ArrayList.
            for (int j = i + 1; j < size; j++) {
                if (arrayList.get(j) < arrayList.get(minIndex)) {
                    minIndex = j;
                }
            }

            // Swap the minimum value with the current value.
            if (minIndex != i) {
                int temp = arrayList.get(i);
                arrayList.set(i, arrayList.get(minIndex));
                arrayList.set(minIndex, temp);
            }
        }
    }

    // This method does a binary search on an ArrayList of integers.
    public static int binarySearch(ArrayList<Integer> arrayList, int target) {
        int left = 0;
        int right = arrayList.size() - 1;

        // The loop will terminate when left > right.
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = arrayList.get(mid);

            // If the target is found, return the index.
            if (midValue == target) {
                return mid;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // If the target is not found, return -1.
        return -1;
    }
}
