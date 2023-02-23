package Final_Project;

public class SavingsAccount extends Account {
    private double minimumBalance;

    public SavingsAccount() {
        super();
        this.minimumBalance = 100.0; // default minimum balance
    }

    public double getMinimumBalance() {
        return this.minimumBalance;
    }

    public void setMinimumBalance(double balance) {
        this.minimumBalance = balance;
    }

    @Override
    public double getInterest() {
        return getBalance() * getInterestRate(); // interest is based on balance and interest rate
    }

    @Override
    public void makePayment(double amount) {
        if (getBalance() - amount < minimumBalance) {
            System.out.println("Error: payment would put balance below minimum.");
            return;
        }
        withdraw(amount); // payment reduces the balance
    }
}
