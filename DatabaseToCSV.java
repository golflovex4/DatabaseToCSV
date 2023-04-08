import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseToCSV {

  public static void main(String[] args) {
    String jdbcUrl = "jdbc:mysql://localhost:3306/cheese_pie_oop";
    String username = "root";
    String password = "asd009887";
    String csvFilePath = "Cheese_Pie_oop.csv";

    try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM cheese_pie_oop.sensor_data");
        FileWriter writer = new FileWriter(csvFilePath)) {
      
      // Write header to CSV file
      writer.append("Sen_ID_Hex,Sensor_value,Latest_Timestamp\n");

      // Write data to CSV file
      while (resultSet.next()) {
        String Sen_ID_Hex  = resultSet.getString("Sen_ID_Hex");
        int Sensor_value  = resultSet.getInt("Sensor_value");
        String  Latest_Timestamp     = resultSet.getString("Latest_Timestamp");
        writer.append(String.format("%s,%d,%s\n", Sen_ID_Hex, Sensor_value, Latest_Timestamp));
      }

      System.out.println("Data exported to CSV file successfully!");

    } catch (SQLException | IOException e) {
      e.printStackTrace();
    }
  }
}

