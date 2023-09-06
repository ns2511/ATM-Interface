import java.util.ArrayList;
import java.util.Scanner;

    class Transaction {
        private String type;
        private double amount;

        public Transaction(String type, double amount) {
            this.type = type;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return type + ": $" + amount;
        }
    }

    class BankAccount {
        private double balance;
        private ArrayList<Transaction> transactionHistory = new ArrayList<>();

        public BankAccount(double initialBalance) {
            balance = initialBalance;
        }
        public void login(){
            while(true) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("please enter account number: ");
                long accountNo = scanner.nextLong();
                System.out.println("please enter your 4 digit pin:");
                int pin = scanner.nextInt();
                if (accountNo > 0 && pin > 0) {
                    break;
                }
                else{
                    System.out.println("wrong account or pin. please try again!");
                }
            }
            System.out.println("Welcome to the ATM. Your Initial Fund is $1000.0");
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                transactionHistory.add(new Transaction("Deposit", amount));
                System.out.println("deposit successful your new balance is: " + getBalance());
            }
            else{
                System.out.println("invalid amount!");
            }

        }

        public void withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                transactionHistory.add(new Transaction("Withdraw", amount));
                System.out.println("Withdraw Successful please collect cash!");
            }
            else if (amount < 1){
                System.out.println("Invalid Amount!");
            }
            else{
                System.out.println("Insufficient Balance!");
            }

        }

        public void getTransactionHistory() {
            if(transactionHistory.size()==0){
                System.out.println("No transaction history found!");
            }
            for (Transaction  transaction : transactionHistory){
                System.out.println(transaction);
            }

        }

        public void transfer(double transferAmount) {
            if(transferAmount>0 && transferAmount <= balance) {
                balance -= transferAmount;
                transactionHistory.add(new Transaction("Transfer", transferAmount));
                System.out.println("$" + transferAmount + " transfer successfully, your current balance is: " + getBalance());
            }
              else if (transferAmount < 1){
                System.out.println("Invalid Amount!");
            }
            else{
                System.out.println("Insufficient Balance!");
            }

        }
    }

    public class ATMInterface {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            BankAccount account = new BankAccount(1000.0);
            account.login();

            while (true) {
                System.out.println();
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Transaction History");
                System.out.println("5. Transfer Money");
                System.out.println("6. Quit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Your balance is: $" + account.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter the amount to deposit: $");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter the amount to withdraw: $");
                        double withdrawAmount = scanner.nextDouble();
                        account.withdraw(withdrawAmount);
                        break;
                    case 4:
                        System.out.println("Transaction History:");
                        account.getTransactionHistory();
                        break;
                    case 5:
                        System.out.print("Enter the amount to transfer: $");
                        double transferAmount = scanner.nextDouble();
                        account.transfer(transferAmount);
                        break;
                    case 6:
                        System.out.println("Thank you for using the ATM!");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }


