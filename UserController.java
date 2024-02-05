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
import javafx.application.Platform;
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
public class UserController {
     @FXML
    private TableView<FoodItem> tableView;
    
    
    @FXML
    private void showBeranda() throws java.io.IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardUser.fxml"));
        Parent berandaRoot = loader.load();
        Scene berandaScene = new Scene(berandaRoot);
        // Mendapatkan stage dari current scene
        Stage currentStage = (Stage) tableView.getScene().getWindow();
        // Set scene baru ke stage
        currentStage.setScene(berandaScene);
}
    @FXML
    private void showProduk() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HalamanProduk.fxml"));
        Parent berandaRoot = loader.load();
        Scene berandaScene = new Scene(berandaRoot);
        // Mendapatkan stage dari current scene
        Stage currentStage = (Stage) tableView.getScene().getWindow();
        // Set scene baru ke stage
        currentStage.setScene(berandaScene);
        }

    @FXML
    private void exitApplication() throws IOException {
        // Implementasi untuk keluar dari aplikasi
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginForm.fxml"));
        Parent berandaRoot = loader.load();
        Scene berandaScene = new Scene(berandaRoot);
        // Mendapatkan stage dari current scene
        Stage currentStage = (Stage) tableView.getScene().getWindow();
        // Set scene baru ke stage
        currentStage.setScene(berandaScene);
    }
    
}
