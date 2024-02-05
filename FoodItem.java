/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import javafx.beans.property.SimpleStringProperty;

public class FoodItem {
        private final SimpleStringProperty foodName;
    private final SimpleStringProperty harga;
    private final SimpleStringProperty detail;

    public FoodItem(String foodName, String harga, String detail) {
        this.foodName = new SimpleStringProperty(foodName);
        this.harga = new SimpleStringProperty(harga);
        this.detail = new SimpleStringProperty(detail);
    }

    public String getFoodName() {
        return foodName.get();
    }

    public void setFoodName(String foodName) {
        this.foodName.set(foodName);
    }

    public String getHarga() {
        return harga.get();
    }

    public void setHarga(String harga) {
        this.harga.set(harga);
    }

    public String getDetail() {
        return detail.get();
    }

    public void setDetail(String detail) {
        this.detail.set(detail);
    }
}


