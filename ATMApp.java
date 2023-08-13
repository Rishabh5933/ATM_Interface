import java.util.Scanner;

class Account {
    private String userId;
    private String userPin;
    private double balance;

    public Account(String userId, String userPin, double balance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public boolean isValidPin(String pin) {
        return userPin.equals(pin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transfer(Account recipient, double amount) {
        if (withdraw(amount)) {
            recipient.deposit(amount);
            return true;
        }
        return false;
    }
}

public class ATMApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Account userAccount = new Account("12345", "1234", 1000.0);

        System.out.print("Enter User ID: ");
        String userIdInput = scanner.nextLine();

        System.out.print("Enter User PIN: ");
        String userPinInput = scanner.nextLine();

        if (userAccount.getUserId().equals(userIdInput) && userAccount.isValidPin(userPinInput)) {
            System.out.println("Login Successful!");
            boolean isRunning = true;

            while (isRunning) {
                System.out.println("Select an option:");
                System.out.println("1. View Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");

                int choice = scanner.nextInt();
                double amount;

                switch (choice) {
                    case 1:
                        System.out.println("Your Balance: " + userAccount.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: ");
                        amount = scanner.nextDouble();
                        userAccount.deposit(amount);
                        System.out.println("Deposit Successful. Your new balance: " + userAccount.getBalance());
                        break;
                    case 3:
                        System.out.print("Enter withdrawal amount: ");
                        amount = scanner.nextDouble();
                        if (userAccount.withdraw(amount)) {
                            System.out.println("Withdrawal Successful. Your new balance: " + userAccount.getBalance());
                        } else {
                            System.out.println("Insufficient funds or invalid amount.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter recipient's User ID: ");
                        String recipientId = scanner.next();
                        System.out.print("Enter transfer amount: ");
                        amount = scanner.nextDouble();

                        if (userAccount.getUserId().equals(recipientId)) {
                            System.out.println("You cannot transfer to yourself.");
                        } else {
                            // For this simple example, we assume that recipient account exists.
                            Account recipientAccount = new Account("54321", "4321", 500.0);
                            if (userAccount.transfer(recipientAccount, amount)) {
                                System.out.println("Transfer Successful. Your new balance: " + userAccount.getBalance());
                            } else {
                                System.out.println("Transfer failed. Insufficient funds or invalid amount.");
                            }
                        }
                        break;
                    case 5:
                        isRunning = false;
                        System.out.println("Thank you for using our ATM.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } else {
            System.out.println("Invalid User ID or PIN. Exiting...");
        }
    }
}
