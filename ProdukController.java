/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author ThinkPad
 */
public class ProdukController {
     @FXML
    private TableView<FoodItem> tableView;
    @FXML
    private TableColumn<FoodItem, String> foodNameColumn;
    @FXML
    private TableColumn<FoodItem, String> hargaColumn;
    @FXML
    private TableColumn<FoodItem, String> detailColumn;
    
     public void initialize() {
        // Panggil method untuk menampilkan menu makanan saat inisialisasi
        displayFoodMenu();
     }
    
    private void displayFoodMenu() {
        // Koneksi ke database dan query untuk mendapatkan data menu makanan
        ObservableList<FoodItem> foodItems = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/makanan", "root", "");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM produk");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String foodName = resultSet.getString("nama");
                String harga = resultSet.getString("harga");
                String detail = resultSet.getString("detail");

                foodItems.add(new FoodItem(foodName, harga, detail));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Set data ke dalam TableView
        foodNameColumn.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        hargaColumn.setCellValueFactory(new PropertyValueFactory<>("harga"));
        detailColumn.setCellValueFactory(new PropertyValueFactory<>("detail"));

        tableView.setItems(foodItems);
    }
    
  @FXML
    private void backToBeranda() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardUser.fxml"));
        Parent berandaRoot = loader.load();
        Scene berandaScene = new Scene(berandaRoot);
        // Mendapatkan stage dari current scene
        Stage currentStage = (Stage) tableView.getScene().getWindow();
        // Set scene baru ke stage
        currentStage.setScene(berandaScene);
}
}
