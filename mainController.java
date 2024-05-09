package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date; // Import Date class
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class mainController implements Initializable {

    @FXML
    private TableView<emloyee> tvName;
    @FXML
    private TableColumn<emloyee, Integer> colEmpID;
    @FXML
    private TableColumn<emloyee, String> colFirstName;
    @FXML
    private TableColumn<emloyee, String> colLastName;
    @FXML
    private TableColumn<emloyee, Integer> colAge;
    @FXML
    private TableColumn<emloyee, Integer> colSalary;

    @FXML
    private TextField empIdField;
    @FXML
    private TextField ageField;
    // Define other fields as needed
    @FXML
    private Date birthDate; // Add @FXML annotation
    @FXML
    private long phone; // Add @FXML annotation
    @FXML
    private String address; // Add @FXML annotation
    @FXML
    private String shopName; // Add @FXML annotation

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showEmployee();
    }

    private void showEmployee() {
        // Create ObservableList to hold employee data
        ObservableList<emloyee> employeeList = FXCollections.observableArrayList();

        // Retrieve employee data from the database and populate the ObservableList
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int empID = resultSet.getInt("emp_ID");
                String firstName = resultSet.getString("fname");
                String lastName = resultSet.getString("lname");
                int age = resultSet.getInt("age");
                int salary = resultSet.getInt("salary");

                // Create Employee object and add it to the list
                emloyee employee = new emloyee(empID, age, salary, birthDate, firstName, lastName, phone, address, shopName);

                employeeList.add(employee);
            }

            // Populate TableView with employee data
            tvName.setItems(employeeList);

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error retrieving employee data: " + e.getMessage());
        }
    }

    @FXML
    private void insertEmployee() {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, Integer.parseInt(empIdField.getText()));
            statement.setInt(2, Integer.parseInt(ageField.getText()));
            // Set other parameters...
            statement.executeUpdate();
            showAlert("Employee inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error inserting employee: " + e.getMessage());
        }
    }

    @FXML
    private void updateEmployee() {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE employee SET age = ? WHERE emp_ID = ?")) {
            statement.setInt(1, Integer.parseInt(ageField.getText()));
            statement.setInt(2, Integer.parseInt(empIdField.getText()));
            // Set other parameters for update...
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                showAlert("Employee updated successfully!");
            } else {
                showAlert("No employee found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error updating employee: " + e.getMessage());
        }
    }

    @FXML
    private void deleteEmployee() {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM employee WHERE emp_ID = ?")) {
            statement.setInt(1, Integer.parseInt(empIdField.getText()));
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                showAlert("Employee deleted successfully!");
            } else {
                showAlert("No employee found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error deleting employee: " + e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
}}