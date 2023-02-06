import java.util.ArrayList;
import java.util.Scanner;

public class PhoneNumberLister {
    public static void main(String[] args) {
        ArrayList<Integer> phoneNumbers = new ArrayList<Integer>();
        Scanner scanner = new Scanner(System.in);

        while (phoneNumbers.size() < 100) {
            System.out.print("(Enter 0 if you are done)\nEnter a 7-digit phone number: ");
            int phoneNumber = scanner.nextInt();

            if (!phoneNumbers.contains(phoneNumber)) {
                phoneNumbers.add(phoneNumber);
            } else {
                System.out.println("Number already in list. Please enter a new number.\n(Or enter 0 if your done)");
            }

            if (phoneNumber == 0) {
                break;
            }
        }

        System.out.println("Final phone number list: " + phoneNumbers);
    }
}