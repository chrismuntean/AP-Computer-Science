package Final_Project;

public class CreditCard extends Account {
    private double creditLimit;
    private double currentBalance;

    public CreditCard() {
        super();
        this.creditLimit = 1000.0; // default credit limit
        this.currentBalance = 0.0;
    }

    public double getCreditLimit() {
        return this.creditLimit;
    }

    public void setCreditLimit(double limit) {
        this.creditLimit = limit;
    }

    public double getCurrentBalance() {
        return this.currentBalance;
    }

    public void setCurrentBalance(double balance) {
        this.currentBalance = balance;
    }

    @Override
    public double getInterest() {
        return getCurrentBalance() * getInterestRate(); // interest is based on current balance and interest rate
    }

    @Override
    public void makePayment(double amount) {
        if (amount > getCurrentBalance()) {
            System.out.println("Error: payment exceeds current balance.");
            return;
        }
        this.currentBalance -= amount; // payment reduces current balance
        withdraw(amount); // payment also reduces account balance
    }

    public void makePurchase(double amount) {
        if (amount > creditLimit - currentBalance) {
            System.out.println("Error: purchase would exceed credit limit.");
            return;
        }
        this.currentBalance += amount; // purchase increases current balance
        deposit(amount); // purchase also increases account balance
    }
}