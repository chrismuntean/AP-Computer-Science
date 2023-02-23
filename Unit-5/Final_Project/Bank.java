package Final_Project;
import java.util.ArrayList;
import java.util.List;

public class Bank {
  private List<Account> accounts; // list of accounts

  public Bank() {
    accounts = new ArrayList<Account>();
  }

  public void addAccount(Account account) {
    accounts.add(account);
  }

  public void removeAccount(Account account) {
    accounts.remove(account);
  }

  // find an account by account number
  public Account findAccount(String accountNumber) {
    for (Account account : accounts) {
      if (account.getAccountNumber().equals(accountNumber)) {
        return account;
      }
    }
    return null;
  }

  // transfer funds from one account to another
  public void transferFunds(Account source, Account destination, double amount) {
    if (source.withdraw(amount)) {
      destination.deposit(amount);
    }
  }

  // get the total balance of all accounts
  public double getTotalBalance() {
    double totalBalance = 0.0;
    for (Account account : accounts) {
      totalBalance += account.getBalance();
    }
    return totalBalance;
  }
}