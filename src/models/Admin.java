package models;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class Admin extends User {
    Path productsPath = Path.of("products.txt");

    public Admin(String username, int userID, String userPassword) {
        super(username, userID, userPassword);
    }

    public void addProduct(Scanner input) {
        System.out.println("\n=========================================");
        System.out.println("       INVENTORY: ADD NEW PRODUCT        ");
        System.out.println("=========================================");
        
        try {
            BufferedReader br = Files.newBufferedReader(productsPath);
            BufferedWriter productWriter = Files.newBufferedWriter(productsPath, StandardOpenOption.APPEND); 
            
            String line;
            int productID = 0;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 4) {
                    productID++;
                }
            }
            br.close();
            
            System.out.print("Enter Product Name     : ");
            String name = input.nextLine();
            
            System.out.print("Enter Unit Price (RM)  : ");
            double price = input.nextDouble();
            
            System.out.print("Enter Stock Quantity   : ");
            int quantity = input.nextInt();

            System.out.print("Enter product type   : ");
            String productType = input.nextLine();
            
            productWriter.write("\n" + productID + ";" + name + ";" + price + ";" + quantity + ";" + productType);
            productWriter.close();
            
            System.out.println("\n[SUCCESS] Product catalog updated successfully!\n");
        } catch (IOException E) {
            System.out.println("\n[ERROR] Failed to write to product catalog: " + E.getMessage());
        }
    }

    public void updateProduct(Scanner input) {
        System.out.println("\n=========================================");
        System.out.println("     INVENTORY: UPDATE EXISTING PRODUCT   ");
        System.out.println("=========================================");

        try {
            BufferedReader br = Files.newBufferedReader(productsPath);

            String[] productBuffer = new String[1000]; 
            int productCount = 0; 
            
            String line;
            while ((line = br.readLine()) != null) {
                productBuffer[productCount] = line;
                productCount++;
            }
            br.close();
            
            System.out.print("Enter Product ID to modify: ");
            int ID = input.nextInt();
            input.nextLine(); // Clear scanner buffer bug
            
            if (ID >= 0 && ID < productCount) {
                System.out.println("\n--- Current data row found. Enter new details ---");
                
                System.out.print("Enter New Product Name     : ");
                String name = input.nextLine();
                
                System.out.print("Enter New Unit Price (RM)  : ");
                double price = input.nextDouble();
                
                System.out.print("Enter New Stock Quantity   : ");
                int quantity = input.nextInt();
                
                productBuffer[ID] = ID + ";" + name + ";" + price + ";" + quantity;
                
                BufferedWriter productWriter = Files.newBufferedWriter(productsPath);
                for (int i = 0; i < productCount; i++) {
                    productWriter.write(productBuffer[i] + "\n");
                }
                productWriter.close();
                System.out.println("\n[SUCCESS] Product record ID #" + ID + " updated successfully!\n");
            } else {
                System.out.println("\n[ERROR] Invalid Product ID! Record not found.\n");
            }
        } catch (IOException E) {
            System.out.println("\n[ERROR] Failed to read/write product catalog: " + E.getMessage());
        }
    }

    public void viewSalesSummary() {
        System.out.println("\n=========================================");
        System.out.println("        SYSTEM REPORT: SALES SUMMARY      ");
        System.out.println("=========================================");
        System.out.println("Generating data analytics... please wait.");
        // TODO: Next phase implementation
    }
}