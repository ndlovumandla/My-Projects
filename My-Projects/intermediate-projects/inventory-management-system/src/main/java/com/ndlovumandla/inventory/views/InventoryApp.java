package com.ndlovumandla.inventory.views;

import java.io.File;
import java.io.IOException;

import com.ndlovumandla.inventory.controllers.InventoryManager;
import com.ndlovumandla.inventory.models.Product;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InventoryApp extends Application {
    private InventoryManager inventoryManager;
    private static final String FILE_PATH = "inventory.dat";

    private TableView<Product> tableView;

    @Override
    public void start(Stage primaryStage) {
        inventoryManager = new InventoryManager();
        loadInventory();

        // Create UI elements
        TextField nameField = new TextField();
        nameField.setPromptText("Product Name");
        TextField quantityField = new TextField();
        quantityField.setPromptText("Quantity");
        TextField priceField = new TextField();
        priceField.setPromptText("Price");
        TextField updateIdField = new TextField();
        updateIdField.setPromptText("Product ID to Update");
        TextField newQuantityField = new TextField();
        newQuantityField.setPromptText("New Quantity");
        TextField deleteIdField = new TextField();
        deleteIdField.setPromptText("Product ID to Delete");
        TextField updateNameField = new TextField();
        updateNameField.setPromptText("New Product Name");
        TextField updatePriceField = new TextField();
        updatePriceField.setPromptText("New Price");

        Button addButton = new Button("Add Product");
        Button updateButton = new Button("Update Stock");
        Button updateProductButton = new Button("Update Product");
        Button deleteButton = new Button("Delete Product");
        Button reportButton = new Button("Generate Report");
        Button saveButton = new Button("Save Inventory");
        Button loadButton = new Button("Load Inventory");

        // Set action for Add Product button
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            if (name.isEmpty() || quantityField.getText().isEmpty() || priceField.getText().isEmpty()) {
                showAlert("Input Error", "Please fill in all fields.");
                return;
            }
            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());
            Product product = new Product(String.valueOf(System.currentTimeMillis()), name, quantity, price);
            inventoryManager.addProduct(product);
            clearFields(nameField, quantityField, priceField);
            updateTableView();
        });

        // Set action for Update Stock button
        updateButton.setOnAction(e -> {
            String productId = updateIdField.getText();
            if (productId.isEmpty() || newQuantityField.getText().isEmpty()) {
                showAlert("Input Error", "Please fill in all fields.");
                return;
            }
            int newQuantity = Integer.parseInt(newQuantityField.getText());
            inventoryManager.updateStock(productId, newQuantity);
            clearFields(updateIdField, newQuantityField);
            updateTableView();
        });

        // Set action for Update Product button
        updateProductButton.setOnAction(e -> {
            String productId = updateIdField.getText();
            String newName = updateNameField.getText();
            if (newName.isEmpty() || updatePriceField.getText().isEmpty()) {
                showAlert("Input Error", "Please fill in all fields.");
                return;
            }
            double newPrice = Double.parseDouble(updatePriceField.getText());
            inventoryManager.updateProduct(productId, newName, newPrice);
            clearFields(updateIdField, updateNameField, updatePriceField);
            updateTableView();
        });

        // Set action for Delete Product button
        deleteButton.setOnAction(e -> {
            String productId = deleteIdField.getText();
            if (productId.isEmpty()) {
                showAlert("Input Error", "Please enter a Product ID to delete.");
                return;
            }
            inventoryManager.removeProduct(productId);
            clearFields(deleteIdField);
            updateTableView();
        });

        // Set action for Generate Report button
        reportButton.setOnAction(e -> {
            String report = inventoryManager.generateReport();
            showAlert("Inventory Report", report);
        });

        // Set action for Save Inventory button
        saveButton.setOnAction(e -> {
            try {
                inventoryManager.saveToFile(FILE_PATH);
                showAlert("Success", "Inventory saved successfully.");
            } catch (IOException ex) {
                showAlert("Error", "Failed to save inventory.");
            }
        });

        // Set action for Load Inventory button
        loadButton.setOnAction(e -> {
            loadInventory();
            updateTableView();
            showAlert("Success", "Inventory loaded successfully.");
        });

        // Set up the TableView for displaying products
        tableView = new TableView<>();
        TableColumn<Product, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableView.getColumns().addAll(idColumn, nameColumn, quantityColumn, priceColumn);
        updateTableView();

        // Set up the layout with labels
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Add labels and input fields
        grid.add(new Label("Product Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Quantity:"), 2, 0);
        grid.add(quantityField, 3, 0);
        grid.add(new Label("Price:"), 0, 1);
        grid.add(priceField, 1, 1);
        grid.add(addButton, 2, 1);

        grid.add(new Label("Update Product ID:"), 0, 2);
        grid.add(updateIdField, 1, 2);
        grid.add(new Label("New Quantity:"), 2, 2);
        grid.add(newQuantityField, 3, 2);
        grid.add(updateButton, 0, 3);

        grid.add(new Label("Delete Product ID:"), 0, 4);
        grid.add(deleteIdField, 1, 4);
        grid.add(deleteButton, 2, 4);

        grid.add(new Label("New Product Name:"), 0, 5);
        grid.add(updateNameField, 1, 5);
        grid.add(new Label("New Price:"), 2, 5);
        grid.add(updatePriceField, 3, 5);
        grid.add(updateProductButton, 0, 6);

        grid.add(reportButton, 0, 7);
        grid.add(saveButton, 1, 7);
        grid.add(loadButton, 2, 7);
        
        grid.add(tableView, 0, 8, 4, 1); // Span across columns

        VBox vbox = new VBox(grid);
        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Inventory Management System");
        primaryStage.show();
    }

    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void loadInventory() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try {
                inventoryManager.loadFromFile(FILE_PATH);
            } catch (IOException | ClassNotFoundException e) {
                showAlert("Error", "Failed to load inventory.");
            }
        }
    }

    private void updateTableView() {
        tableView.getItems().clear();
        tableView.getItems().addAll(inventoryManager.getProducts());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
