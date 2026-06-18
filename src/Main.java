import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String accountHolder = "Tushar";
        int pin = 9876;
        double balance = 100000;
        int transactionCount = 0;
        String lastTransaction = "No Transaction yet";
        boolean exitProgram = false;
        boolean isLoggedIn = false;
        System.out.println("\n===== WELCOME TO MINI ATM =====");

        while (!exitProgram) {
            isLoggedIn = false;

            for (int attempt = 1; attempt <= 3; attempt++) {
                System.out.print("\nEnter 4-digit PIN: ");
                int enteredPin = sc.nextInt();

                if (enteredPin < 1000 || enteredPin > 9999) {
                    System.out.println("PIN must contain exactly 4 digits.");
                    int remainingAttempts = 3 - attempt;

                    if (remainingAttempts > 0) {
                        System.out.println("Remaining attempts:" + remainingAttempts);
                    } else {
                        System.out.println("Incorrect PIN");
                    }
                    continue;
                }

                if (enteredPin == pin) {
                    isLoggedIn = true;
                    break;
                } else {

                    int remainingAttempts = 3 - attempt;

                    if (remainingAttempts > 0) {
                        System.out.println("\nIncorrect PIN");
                        System.out.println("Remaining attempts:" + remainingAttempts);
                    } else {
                        System.out.println("Incorrect PIN");
                    }
                }

            }

            if (!isLoggedIn) {
                System.out.println("\nToo many incorrect attempts.");
                System.out.println("Account blocked.");
                return;
            }

            int choice;

            do {
                System.out.println("\n===== MENU =====");

                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Calculate Interest");
                System.out.println("5. View Transaction Count");
                System.out.println("6. Change PIN");
                System.out.println("7. Last Transaction");
                System.out.println("8. Exit");
                System.out.println("================");

                System.out.print("\nEnter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("\nAccount Holder: " + accountHolder);
                        System.out.println("Current balance: " + balance);
                        break;

                    case 2:
                        System.out.print("\nEnter deposit amount: ");
                        double depositAmount = sc.nextDouble();

                        if (depositAmount < 0) {
                            System.out.println("Incorrect amount.");
                            continue;
                        }

                        balance += depositAmount;
                        transactionCount++;
                        lastTransaction = "Deposited Rs. " + depositAmount;

                        System.out.println("Deposit successful.");
                        System.out.println("Updated balance: Rs. " + balance);
                        break;

                    case 3:
                        System.out.print("\nEnter withdrawal amount: ");
                        double withdrawAmount = sc.nextDouble();

                        if (withdrawAmount > balance) {
                            System.out.println("\nInsufficient amount.");
                        } else if ((balance - withdrawAmount) < 500) {
                        System.out.println("Minimum balance of Rs. 500 must be maintained.");
                    } else {
                            balance -= withdrawAmount;
                            transactionCount++;
                            lastTransaction = "Withdrew Rs. " + withdrawAmount;
                            System.out.println("Withdrawal successful.");
                            System.out.println("Updated balance: Rs. " + balance);
                        }
                        break;

                    case 4:
                        System.out.print("\nEnter annual interest rate (%): ");
                        float rate = sc.nextFloat();
                        System.out.print("Enter number of years: ");
                        int years = sc.nextInt();
                        double interest = (balance * rate * years) / 100;
                        System.out.println("\nEstimated Interest: Rs. " + interest);
                        double futureBalance = balance + interest;
                        break;

                    case 5:
                        System.out.println("\nTotal Transactions: " + transactionCount);
                        break;

                    case 6:
                        System.out.println("Enter current PIN: ");
                        int currentPin = sc.nextInt();
                        if (currentPin != pin) {
                            System.out.println("Incorrect current PIN.");
                            break;
                        }

                        System.out.println("Enter new PIN: ");
                        int newPin = sc.nextInt();
                        if (newPin < 1000 || newPin > 9999) {
                            System.out.println("PIN must contain exactly 4 digits.");
                            break;
                        }
                        System.out.println("Confirm new PIN: ");
                        newPin = sc.nextInt();
                        pin = newPin;
                        System.out.println("\nPIN changed successfully.");
                        System.out.println("Please log in again using your new PIN.");
                        isLoggedIn = false;
                        break;

                    case 7:
                        System.out.println("Last Transaction: " + lastTransaction);
                        break;

                    case 8:
                        exitProgram = true;
                        System.out.println("\nThank you for using MINI ATM.");
                        break;

                    default:
                        System.out.println("\nInvalid choice.");
                }
            } while (choice != 8 && isLoggedIn);

        }
            sc.close();
        }
    }