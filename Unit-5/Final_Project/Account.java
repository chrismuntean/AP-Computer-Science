package Final_Project;

public abstract class Account {
    private String accountNumber;
    private double balance;
    private double interestRate;

    // constructor
    public Account() {
        this.balance = 0.0;
        this.interestRate = 0.0;
    }

    // getters and setters
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    // getters and setters
    public String getAccountNumber() {
        return this.accountNumber;
    }

    public double getBalance() {
        return this.balance;
    }

    public double getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    // methods
    public void deposit(double amount) {
        this.balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > this.balance) {
            return false;
        }
        this.balance -= amount;
        return true;
    }

    // abstract methods
    public abstract double getInterest();
    public abstract void makePayment(double amount);
}
