import java.util.*;
import java.sql.*;

public class srcall {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/db"; // Change 'db' to your DB name
        String username = "root";
        String password = "1234";

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("✅ Database connected successfully!");

            Statement stmt = con.createStatement();
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\nWELCOME TO MSN HOTELS");
                System.out.println("1. New Reservation");
                System.out.println("2. Search for a person");
                System.out.println("3. Show all data");
                System.out.println("4. Delete entire database");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                int ch = sc.nextInt();

                switch (ch) {
                    case 1 -> Registration(con, sc);
                    case 2 -> Searchforperson(con, sc);
                    case 3 -> toseedata(con, stmt);
                    case 4 -> deletedata(con);
                    case 5 -> {
                        System.out.println("Exiting... Thank you for using MSN Hotels.");
                        sc.close();
                        con.close();
                        return;
                    }
                    default -> System.out.println("❌ Invalid choice! Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void Registration(Connection con, Scanner sc) {
        try {
            sc.nextLine(); // clear newline
            System.out.print("Enter the name of the person: ");
            String name = sc.nextLine();

            System.out.print("Enter the Room no: ");
            int roomno = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Contact no: ");
            String contact = sc.nextLine();

            String insert = "INSERT INTO hotel (guest_name, Room_no, contact_no, Reser_data) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
            PreparedStatement prp = con.prepareStatement(insert);
            prp.setString(1, name);
            prp.setInt(2, roomno);
            prp.setString(3, contact);

            int rows = prp.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Registered successfully!");
            } else {
                System.out.println("❌ Registration failed.");
            }

            prp.close();
        } catch (Exception e) {
            System.out.println("❌ Registration Error: " + e.getMessage());
        }
    }

    public static void Searchforperson(Connection con, Scanner sc) {
        try {
            System.out.print("Enter the Reservation ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            String search = "SELECT * FROM hotel WHERE Room_no = ?";
            PreparedStatement pr = con.prepareStatement(search);
            pr.setInt(1, id);

            ResultSet rs = pr.executeQuery();

            System.out.println("R_Id   | Name        | Room_no | Contact_no     | DateOFReservation");
            System.out.println("---------------------------------------------------------------------");
            if(rs.next())
            {
            while (rs.next()) {
                int ids = rs.getInt("Reservation_id");
                String name = rs.getString("guest_name");
                int roomNo = rs.getInt("Room_no");
                String contact = rs.getString("contact_no");
                Timestamp date = rs.getTimestamp("Reser_data");

                System.out.printf("%-7d| %-12s| %-8d| %-15s| %s\n",
                        ids, name, roomNo, contact, date.toString());
            }}else {


                System.out.println("❌ No reservation found with ID: " + id);
            }

            rs.close();
            pr.close();
        } catch (SQLException e) {
            System.out.println("❌ Search Error: " + e.getMessage());
        }
    }

    public static void toseedata(Connection con, Statement stmt) {
        try {
            System.out.println("\n📋 All Reservations in MSN HOTEL:");
            ResultSet rs = stmt.executeQuery("SELECT * FROM hotel");

            System.out.println("R_Id   | Name        | Room_no | Contact_no     | DateOFReservation");
            System.out.println("---------------------------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("Reservation_id");
                String name = rs.getString("guest_name");
                int roomNo = rs.getInt("Room_no");
                String contact = rs.getString("contact_no");
                Timestamp date = rs.getTimestamp("Reser_data");

                System.out.printf("%-7d| %-12s| %-8d| %-15s| %s\n",
                        id, name, roomNo, contact, date.toString());
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("❌ Error showing data: " + e.getMessage());
        }
    }

    public static void deletedata(Connection con) {
        try {
            System.out.print("Deleting all data");
            for (int i = 0; i < 6; i++) {
                System.out.print(".");
                Thread.sleep(300);
            }
            System.out.println();

            String del = "DELETE FROM hotel";
            Statement stmt = con.createStatement();
            int rows = stmt.executeUpdate(del);

            System.out.println("✅ " + rows + " record(s) deleted.");
            stmt.close();
        } catch (Exception e) {
            System.out.println("❌ Error deleting data: " + e.getMessage());
        }
    }
}
