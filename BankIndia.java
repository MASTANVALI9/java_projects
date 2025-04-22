import java.util.*;

// Main BankIndia class
public class BankIndia {
    static Scanner scanner = new Scanner(System.in);

    static {
        System.out.println("Hello, welcome to STUDENT BANK OF KURNOOL ");
        System.out.println("Please tell us how we can help you");
    }

    public static void main(String[] args) {
        AccountManager manager = new AccountManager();
        while (true) {
            System.out.println("\nEnter your account number  or press 3 to exit: to see account holder with name press 4 ");
            int accNo = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (accNo == 3) {
                System.out.println("Thank you for using STUDENT Bank OF KURNOOL!");
                return;
            }
            if(accNo==4){
                int pinn=25800;
                System.out.println("Enter Account Manager PIN to see Details about Accounts: ");
                int enpin = scanner.nextInt();
                if(pinn==enpin){
                System.out.println(manager.accountMap);
            }else{
                System.out.println("your are not a manager or provie valid pin :");
            }
        }
            
            if (!manager.accountExists(accNo)) {
                System.out.println("Invalid account number. Try again.");
                continue;
            }

            System.out.print("Please enter your PIN: ");
            String enteredPin = scanner.nextLine();

            if ((!manager.validatePin(accNo, enteredPin)) && !(accNo==4)) {
                System.out.println("Invalid PIN. Try again.");
                continue;
            }

            manager.handleAccount(accNo);
        }
    }
}

// Thread for loading animation
class Animation extends Thread {
    public void run() {
             String[] st= {"0", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100"};
             int n=0;
             while(n<11){
            System.out.print("\r" + st[n] + "% Loading...");
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            n++;
        }
        System.out.println("\r               \r");
    }
}

// Account class with encapsulation
class Account {
    private int accountNumber;
    private String pin;
    private String holderName;
    private int balance;

    public Account(int accountNumber, String pin, String holderName) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.holderName = holderName;
        this.balance = 1000; // default
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public String getHolderName() {
        return holderName;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public boolean withdraw(int amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }
}

// AccountManager class
class AccountManager {
    public  Map<Integer, Account> accountMap;
    private Scanner scanner = BankIndia.scanner;
    public AccountManager() {
        accountMap = new HashMap<>();
        accountMap.put(9991, new Account(9991, "1234", "Anwar"));
        accountMap.put(9992, new Account(9992, "12345", "Mastan"));
        accountMap.put(9993, new Account(9992, "12345", "Rizzu"));
        accountMap.put(9994, new Account(9992, "12345", "Usman"));
        accountMap.put(9995, new Account(9992, "12345", "Aravind"));
        accountMap.put(9996, new Account(9992, "12345", "Irfan"));
        accountMap.put(9997, new Account(9992, "12345", "Yasir"));
        accountMap.put(9998, new Account(9992, "12345", "Majeed"));
        accountMap.put(9999, new Account(9992, "12345", "Nizam"));
        accountMap.put(1000, new Account(9992, "12345", "Madesh"));
    }

    public boolean accountExists(int accountNumber) {
        return accountMap.containsKey(accountNumber);
    }

    public boolean validatePin(int accountNumber, String pin) {
        return accountMap.get(accountNumber).getPin().equals(pin);
    }

    public void handleAccount(int accountNumber) {
        Account acc = accountMap.get(accountNumber);
        new Animation().start();

        System.out.println("\nWelcome, " + acc.getHolderName() + "! What would you like to do?");

        while (true) {
            System.out.println("\n1. Deposit\n2. Withdraw\n3. Check Balance\n4. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter amount to deposit: ");
                    int amount = scanner.nextInt();
                    if (amount > 0) {
                        acc.deposit(amount);
                        new Animation().start();
                        System.out.println("Deposited successfully. New balance: ₹" + acc.getBalance());
                    } else {
                        System.out.println("Invalid amount.");
                    }
                }
                case 2 -> {
                    System.out.print("Enter amount to withdraw: ");
                    int amount = scanner.nextInt();
                    if (acc.withdraw(amount)) {
                        new Animation().start();
                        System.out.println("Withdrawn successfully. New balance: ₹" + acc.getBalance());
                    } else {
                        System.out.println("Insufficient balance.");
                    }
                }
                case 3 -> {
                    new Animation().start();
                    System.out.println("Your balance is: ₹" + acc.getBalance());
                }
                    case 4 -> {
                    System.out.println("Exiting account menu. Thank you, " + acc.getHolderName());
                    return;
                }
                default -> System.out.println("Invalid choice. Please select 1–4.");
            }
        }
    }
}
