package Final_Project;

public class CheckingAccount extends Account {
    private double monthlyFee;

    public CheckingAccount() {
        super();
        this.monthlyFee = 5.0; // default fee
    }

    public double getMonthlyFee() {
        return this.monthlyFee;
    }

    public void setMonthlyFee(double fee) {
        this.monthlyFee = fee;
    }

    @Override
    public double getInterest() {
        return 0.0; // checking accounts don't earn interest
    }

    @Override
    public void makePayment(double amount) {
        withdraw(amount); // payment reduces the balance
    }
}