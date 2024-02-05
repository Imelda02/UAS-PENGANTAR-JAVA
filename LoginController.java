
package menu;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author ThinkPad
 */
public class LoginController implements Initializable {
    
 @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization code can go here
    }

    @FXML
    private void loginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (validateLogin(username, password)) {
            System.out.println("Login successful!");
            navigateToDashboard(username); // Mengirim username untuk menentukan level
        } else {
            System.out.println("Login failed!");
            // Tambahkan logika untuk menangani login gagal, misalnya, menampilkan pesan kesalahan
        }
    }

    private boolean validateLogin(String username, String password) {
        try (Connection connection = Koneksi.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return true; // Login berhasil
                } else {
                    System.out.println("Login failed! Username or password incorrect.");
                    return false; // Login gagal
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Login failed! An error occurred: " + e.getMessage());
            return false;
        }
    }

    private void navigateToDashboard(String username) {
        try {
            // Baca level pengguna dari database berdasarkan username
            String level = getUserLevel(username);

            // Tentukan dashboard yang sesuai berdasarkan level pengguna
            String dashboardPath = (level.equalsIgnoreCase("admin")) ? "Dashboard.fxml" : "DashboardUser.fxml";

            // Buka dashboard dalam jendela baru
            Stage dashboardStage = new Stage();
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource(dashboardPath));
            javafx.scene.Parent root = loader.load();
            javafx.scene.Scene scene = new javafx.scene.Scene(root);
            dashboardStage.setTitle("Dashboard");
            dashboardStage.setScene(scene);
            dashboardStage.show();

            // Tutup jendela login setelah login sukses
            Stage loginStage = (Stage) usernameField.getScene().getWindow();
            loginStage.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private String getUserLevel(String username) {
        // Mendapatkan level pengguna dari database berdasarkan username
        try (Connection connection = Koneksi.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT level FROM users WHERE username = ?")) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("level");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
 

}