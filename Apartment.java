import java.util.*;

class Appartment {
    public static Scanner s = new Scanner(System.in);
    static Manager m = new Manager(); // create Manager object to access room details

    public static void main(String[] args) {
        while(true){
        System.out.println("Welcome to MSN apartment");
        System.out.println("Please choose options:");
        System.out.println("1. Looking for relatives");
        System.out.println("2. Take new room");
        System.out.println("3. Just to see");
        System.out.println("4. TO see all member is apartments");
        System.out.println("5. Exit ");

        int choice = s.nextInt();

        switch (choice) {
            case 1 -> searchFor();
            case 2 -> allotRoom();
            case 3 -> justToSee();
            case 4 -> toallmem();
            case 5 -> {
                System.out.println("Thank you sir Visit again ");
                return;
            }
            default -> System.out.println("This service is not provided here, sir.");
        }
    }
    }

    public static void searchFor() {
        System.out.println("Please enter room number: ");
        int roomNo = s.nextInt();
        Details d = m.checkRoom(roomNo);
        if (d != null) {
            System.out.println("Name is: " + d.getName() + ", Flat no is: " + roomNo + ", Floor is: " + d.getFloor());
        } else {
            System.out.println("No such room found.");
        }
    }
    public static void toallmem(){
        System.out.println("Sorry sir this data is confidentialy we cannot show you : ");
        System.out.println("please go to manager they will help : ");
        System.out.println("Enter manager pin ");
        int ppin = 2580;
        int pin = s.nextInt();
        if(pin==ppin){
            System.out.println("List of all residents in MSN Apartment:");
            for (Map.Entry<Integer, Details> entry : m.acc.entrySet()) {
                int roomNo = entry.getKey();
                Details d = entry.getValue();
                System.out.println("Room No: " + roomNo + ", Name: " + d.getName() + ", Floor: " + d.getFloor());
            }
            System.out.println("Thank you for");

        }
        else{
            System.out.println("invalid pin ");

        }
    }

    public static void allotRoom() {
        System.out.println("Please tell me room type you are looking for: ");
        System.out.println("1. 1BHK (3000/day)");
        System.out.println("2. 2BHK (5000/day)");
        System.out.println("3. 3BHK (7000/day)");
        int type = s.nextInt();
        int costPerDay = 0;

        switch (type) {
            case 1 -> costPerDay = 3000;
            case 2 -> costPerDay = 5000;
            case 3 -> costPerDay = 7000;
            default -> {
                System.out.println("Invalid choice!");
                return; // Exit the method
            }

        }

        System.out.println("Enter number of days you want to stay:");
        int days = s.nextInt();
        s.nextLine(); // Consume the leftover newline

        System.out.println("Please enter your name: ");
        String name = s.nextLine();

        System.out.println("Please enter your phone number: ");
        String phone = s.nextLine();

        int totalCost = costPerDay * days;
        System.out.println("\nThank you! Here are your booking details:");
        System.out.println("--------------------------------------------");
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Room type: " + type + "BHK");
        System.out.println("Days: " + days);
        System.out.println("Total Cost: Rs. " + totalCost);
        System.out.println("--------------------------------------------");
        System.out.println("You will soon receive a message with your room number and stay details.");
        System.out.println("Thank you for choosing us! 😊");
    }

    public static void justToSee() {
        System.out.println("Just visiting! Thank you for coming.");
    }
}

class Details {
    private String name;
    private int pin;
    private int floor;

    public Details(String name, int pin, int floor) {
        this.name = name;
        this.pin = pin;
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public int getPin() {
        return pin;
    }

    public int getFloor() {
        return floor;
    }
}

class Manager {
    Map<Integer, Details> acc;

    public Manager() {
        acc = new HashMap<>();
        acc.put(1, new Details("Shaik Mastan Vali", 1234, 1));
    acc.put(2, new Details("John Doe", 5678, 2));
    acc.put(3, new Details("Jane Smith", 9101, 3));
    acc.put(4, new Details("Mastan", 2345, 1));
    acc.put(5, new Details("Madesh", 3456, 2));
    acc.put(6, new Details("Anwar", 4567, 3));
    acc.put(7, new Details("Nizam", 5678, 4));
    acc.put(8, new Details("Majeed", 6789, 5));
    acc.put(9, new Details("Yasir", 7890, 6));
    acc.put(10, new Details("Usman", 8901, 7));
    acc.put(11, new Details("Ali", 9012, 8));
    acc.put(12, new Details("Avinash", 1235, 9));
    acc.put(13, new Details("Aravind", 2346, 10));
    acc.put(14, new Details("Raghu", 3457, 11));
    acc.put(15, new Details("Madhu", 4568, 12));
    acc.put(16, new Details("Irfan", 5679, 13));
    }

    public Details checkRoom(int room) {
        return acc.get(room);
    }
}
