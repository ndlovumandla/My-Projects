package com.ndlovumandla.inventory.controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ndlovumandla.inventory.models.Product;

public class InventoryManager implements Serializable {
    private List<Product> products;

    public InventoryManager() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(String productId) {
        products.removeIf(product -> product.getId().equals(productId));
    }

    public List<Product> getProducts() {
        return products;
    }

    public Optional<Product> findProduct(String productId) {
        return products.stream().filter(product -> product.getId().equals(productId)).findFirst();
    }

    public void updateStock(String productId, int newQuantity) {
        findProduct(productId).ifPresent(product -> product.setQuantity(newQuantity));
    }

    public void updateProduct(String productId, String newName, double newPrice) {
        findProduct(productId).ifPresent(product -> {
            product.setName(newName);
            product.setPrice(newPrice);
        });
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder("Inventory Report:\n");
        report.append(String.format("%-10s %-20s %-10s %-10s\n", "ID", "Name", "Quantity", "Price"));
        report.append("-----------------------------------------------------\n");
        for (Product product : products) {
            report.append(String.format("%-10s %-20s %-10d $%-10.2f\n",
                    product.getId(), product.getName(), product.getQuantity(), product.getPrice()));
        }
        return report.toString();
    }

    public void saveToFile(String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(products);
        }
    }

    public void loadFromFile(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            products = (List<Product>) ois.readObject();
        }
    }
}
