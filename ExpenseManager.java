package backend;
import java.sql.*;
import java.util.Scanner;

public class ExpenseManager {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:XE"; // default Oracle XE connection
        String user = "system"; // your Oracle username
        String password = "your_password_here"; // your Oracle password

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to Oracle Database!");

            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\nChoose option: 1) Add Expense 2) View All 3) Exit");
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    System.out.print("Enter description: ");
                    String desc = sc.nextLine();
                    System.out.print("Enter amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter type (income/expense): ");
                    String type = sc.nextLine();

                    String sql = "INSERT INTO expenses (description, amount, type) VALUES (?, ?, ?)";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, desc);
                    ps.setDouble(2, amount);
                    ps.setString(3, type);
                    ps.executeUpdate();
                    ps.close();
                    System.out.println("✅ Expense saved!");
                }
                else if (choice == 2) {
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM expenses ORDER BY id");

                    System.out.println("\n--- All Expenses ---");
                    while (rs.next()) {
                        System.out.println(rs.getInt("id") + ". " + rs.getString("description") +
                                " - " + rs.getDouble("amount") + " (" + rs.getString("type") + ")");
                    }
                    rs.close();
                    stmt.close();
                }
                else {
                    System.out.println("👋 Goodbye!");
                    break;
                }
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
