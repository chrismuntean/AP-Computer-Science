import java.util.ArrayList;
import java.util.Random;

public class ArrayListSort {
    public static void main(String[] args) {
        int size = 10;
        int maxRandomValue = 100;

        ArrayList<Integer> numbers = generateRandomArrayList(size, maxRandomValue);
        System.out.println("Unsorted ArrayList: " + numbers);

        selectionSort(numbers);
        System.out.println("Sorted ArrayList: " + numbers);

        int randomIndex = new Random().nextInt(size);
        int target = numbers.get(randomIndex);
        
        System.out.println("\nRandomly selected target integer: " + target);

        int index = binarySearch(numbers, target);
        if (index != -1) {
            System.out.println("Target " + target + " found at index: " + index);
        } else {
            System.out.println("Target " + target + " not found.");
        }
    }

    public static ArrayList<Integer> generateRandomArrayList(int size, int maxRandomValue) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            arrayList.add(random.nextInt(maxRandomValue));
        }
        return arrayList;
    }

    public static void selectionSort(ArrayList<Integer> arrayList) {
        int size = arrayList.size();
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (arrayList.get(j) < arrayList.get(minIndex)) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arrayList.get(i);
                arrayList.set(i, arrayList.get(minIndex));
                arrayList.set(minIndex, temp);
            }
        }
    }

    public static int binarySearch(ArrayList<Integer> arrayList, int target) {
        int left = 0;
        int right = arrayList.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = arrayList.get(mid);

            if (midValue == target) {
                return mid;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
