import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class BankAccount {
    private String accountHolder;
    private double balance;

    // Constructor
    public BankAccount(String accountHolder) {
        this.accountHolder = accountHolder;
        this.balance = 0.0;
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: $" + amount);
        } else {
            System.out.println("Invalid or insufficient funds.");
        }
    }

    // Method to check balance
    public void checkBalance() {
        System.out.println("Balance: $" + balance);
    }

    // Method to transfer funds
    public boolean transfer(BankAccount targetAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount);
            targetAccount.deposit(amount);
            System.out.println("Transferred: $" + amount + " to " + targetAccount.accountHolder);
            return true;
        } else {
            System.out.println("Transfer failed. Check amount and balance.");
            return false;
        }
    }
}

public class BankyApp {
    private static Map<String, BankAccount> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Banky Application ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Transfer Funds");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            int option = scanner.nextInt();

            switch (option) {
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
                    checkBalance();
                    break;
                case 5:
                    transferFunds();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter account holder's name: ");
        String name = scanner.next();
        if (!accounts.containsKey(name)) {
            accounts.put(name, new BankAccount(name));
            System.out.println("Account created for " + name);
        } else {
            System.out.println("Account already exists.");
        }
    }

    private static void deposit() {
        System.out.print("Enter account holder's name: ");
        String name = scanner.next();
        BankAccount account = accounts.get(name);
        if (account != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
        } else {
            System.out.println("Account does not exist.");
        }
    }

    private static void withdraw() {
        System.out.print("Enter account holder's name: ");
        String name = scanner.next();
        BankAccount account = accounts.get(name);
        if (account != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        } else {
            System.out.println("Account does not exist.");
        }
    }

    private static void checkBalance() {
        System.out.print("Enter account holder's name: ");
        String name = scanner.next();
        BankAccount account = accounts.get(name);
        if (account != null) {
            account.checkBalance();
        } else {
            System.out.println("Account does not exist.");
        }
    }

    private static void transferFunds() {
        System.out.print("Enter your account holder's name: ");
        String fromName = scanner.next();
        BankAccount fromAccount = accounts.get(fromName);
        if (fromAccount != null) {
            System.out.print("Enter recipient account holder's name: ");
            String toName = scanner.next();
            BankAccount toAccount = accounts.get(toName);
            if (toAccount != null) {
                System.out.print("Enter transfer amount: ");
                double amount = scanner.nextDouble();
                fromAccount.transfer(toAccount, amount);
            } else {
                System.out.println("Recipient account does not exist.");
            }
        } else {
            System.out.println("Your account does not exist.");
        }
    }
}