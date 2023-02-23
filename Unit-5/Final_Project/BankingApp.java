package Final_Project;
import java.util.Scanner; // Import scanner class

// Create BankingApp class
public class BankingApp {
    // Create private static Bank object
    private static Bank bank = new Bank();
    // Create private static Scanner object
    private static Scanner scanner = new Scanner(System.in);

    // Create private static void createAccount() method
    public static void main(String[] args) {
        boolean quit = false;

        while (!quit) {
            printMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    payCreditCard();
                    break;
                case 6:
                    printTotalBalance();
                    break;
                case 7:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    // Create private static void printMenu() method
    private static void printMenu() {
        System.out.println("Banking App Menu");
        System.out.println("-----------------");
        System.out.println("1. Create account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. Pay credit card");
        System.out.println("6. Print total balance");
        System.out.println("7. Quit");
        System.out.print("Enter choice: ");
    }

    // Create private static void createAccount() method
    private static void createAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        System.out.print("Enter account type (1. Checking, 2. Savings, 3. Credit card): ");
        int accountType = scanner.nextInt();

        if (accountType == 1) {
            CheckingAccount account = new CheckingAccount();
            account.setAccountNumber(accountNumber);
            bank.addAccount(account);
        } else if (accountType == 2) {
            SavingsAccount account = new SavingsAccount();
            account.setAccountNumber(accountNumber);
            bank.addAccount(account);
        } else if (accountType == 3) {
            CreditCard account = new CreditCard();
            account.setAccountNumber(accountNumber);
            bank.addAccount(account);
        } else {
            System.out.println("Invalid account type.");
        }
    }

    // Create private static void deposit() method
    private static void deposit() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        Account account = bank.findAccount(accountNumber);
        if (account == null) {
            System.out.println("Account not found.");
        } else {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
            System.out.println("Deposit successful.");
        }
    }

    // Create private static void withdraw() method
    private static void withdraw() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        Account account = bank.findAccount(accountNumber);
        if (account == null) {
            System.out.println("Account not found.");
        } else {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            if (account.withdraw(amount)) {
                System.out.println("Withdrawal successful.");
            } else {
                System.out.println("Insufficient funds.");
            }
        }
    }

    //  Create private static void transfer() method
    private static void transfer() {
        System.out.print("Enter source account number: ");
        String sourceAccountNumber = scanner.next();
        Account sourceAccount = bank.findAccount(sourceAccountNumber);
        if (sourceAccount == null) {
            System.out.println("Source account not found.");
        } else {
            System.out.print("Enter destination account number: ");
            String destinationAccountNumber = scanner.next();
            Account destinationAccount = bank.findAccount(destinationAccountNumber);
            if (destinationAccount == null) {
                System.out.println("Destination account not found.");
            } else {
                System.out.print("Enter transfer amount: ");
                double amount = scanner.nextDouble();
                bank.transferFunds(sourceAccount, destinationAccount, amount);
                System.out.println("Transfer successful.");
            }
        }
    }

    // Create private static void payCreditCard() method
    private static void payCreditCard() {
        System.out.print("Enter credit card account number: ");
        String accountNumber = scanner.next();
        Account account = bank.findAccount(accountNumber);
        if (account == null) {
            System.out.println("Account not found.");
        } else {
            System.out.print("Enter payment amount: ");
            double amount = scanner.nextDouble();
            if (account instanceof CreditCard) {
                CreditCard creditCard = (CreditCard) account;
                creditCard.makePayment(amount);
                System.out.println("Payment successful.");
            } else {
                System.out.println("Account is not a credit card.");
            }
        }
    }

    // Create private static void printTotalBalance() method
    private static void printTotalBalance() {
        System.out.println("Total balance: " + bank.getTotalBalance());
    }

    // Create a clear screen method (NOT USED)
    // It messes up when trying to print the balances and other stuff
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}