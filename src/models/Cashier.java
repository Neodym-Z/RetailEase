package models;

import java.nio.file.Path;
import java.util.Scanner;

public class Cashier extends User {
    private int shiftNumber;
    Path productsPath = Path.of("products.txt");
    Path transactionsPath = Path.of("transactions.txt");

    public Cashier(String username, int userID, String userPassword, int shiftNumber) {
        super(username, userID, userPassword);
        this.shiftNumber = shiftNumber;
    }

    public int getShiftNumber() { return shiftNumber; }

    public void processTransaction(Scanner input) {
        System.out.println("Cashier " + username + " is processing the transaction.");
        System.out.println("\n=========================================");
        System.out.println("     CASHIER " + username + ": NEW TRANSACTION     ");
        System.out.println("=========================================");

        System.out.println("Enter Customer Name: ");
        String name = input.nextLine();

        System.out.println("Enter Customer Age: ");
        int age = input.nextInt();
        input.nextLine(); //clear buffer

        System.out.println("Enter Customer Phone Number: ");
        String phoneNum = input.nextLine();

        System.out.println("Enter customer ID: ");
        int custID = input.nextInt();
        input.nextLine();

        System.out.println("Is Customer a Member? (1 for Yes, 2 for No)");
        int choice = input.nextInt();
        input.nextLine(); // clear buffer

        Customer customerObj;
        if (choice == 1) {
            customerObj = new MemberCustomer(name, custID, phoneNum, age, "Active");
            System.out.print("Member Customer Discount 5% applied");
        }
        else{
            customerObj = new Customer(name, custID, phoneNum, age);
        }

        int transactionId = (int) (System.currentTimeMillis() % 100000);
        Transaction currentTransaction = new Transaction(transactionId, customerObj);
        Product[] productsBuffer = new Product[1000];
        String[] rawLinesBuffer = new String[1000]; 
        int productCount = 0;

        try {
            if (java.nio.file.Files.exists(productsPath)) {
                java.io.BufferedReader br = java.nio.file.Files.newBufferedReader(productsPath);
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty()) continue;
                    rawLinesBuffer[productCount] = line;
                    String[] data = line.split(";");
                    
                    int id = Integer.parseInt(data[0].trim());
                    String pName = data[1].trim();
                    double price = Double.parseDouble(data[2].trim());
                    int stock = Integer.parseInt(data[3].trim());
                    String type = data[4].trim();

                    if (type.equalsIgnoreCase("regulated")) {
                        productsBuffer[productCount] = new RegulatedProduct(id, pName, price, stock, 18);
                    } else if (type.equalsIgnoreCase("weighted")) {
                        productsBuffer[productCount] = new WeightedProduct(id, pName, stock, 1.0, price);
                    } else {
                        productsBuffer[productCount] = new Product(id, pName, price, stock);
                    }
                    productCount++;
                }
                br.close();
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to load inventory: " + e.getMessage());
            return;
        }

        // 3. The Shopping Cart Loop
        boolean checkout = false;
        while (!checkout) {
            System.out.print("\nEnter Product ID to add to cart (or -1 to checkout): ");
            int targetID = input.nextInt();
            input.nextLine(); // Clear buffer bug

            if (targetID == -1) {
                checkout = true;
                break;
            }

            int foundIndex = -1;
            for (int i = 0; i < productCount; i++) {
                if (productsBuffer[i].getProductID() == targetID) {
                    foundIndex = i;
                    break;
                }
            }

            if (foundIndex == -1) {
                System.out.println("[ERROR] Product ID not found!");
                continue;
            }

            Product selectedProd = productsBuffer[foundIndex];
            System.out.print("Enter Quantity for " + selectedProd.getProductName() + ": ");
            int qty = input.nextInt();
            input.nextLine(); // Clear buffer bug

            if (qty <= 0) {
                System.out.println("[ERROR] Invalid quantity.");
                continue;
            }

            if (selectedProd.getStockQuantity() < qty) {
                System.out.println("[ERROR] Insufficient stock! Available: " + selectedProd.getStockQuantity());
                continue;
            }

            // Add the item to your transaction cart (this handles the age check automatically!)
            currentTransaction.addLineItem(new LineItem(selectedProd, qty));
            
            // Deduct stock in memory
            selectedProd.updateStock(qty);
            
            // Keep the raw text line updated for saving later
            String[] splitData = rawLinesBuffer[foundIndex].split(";");
            rawLinesBuffer[foundIndex] = selectedProd.getProductID() + ";" + selectedProd.getProductName() + ";" + selectedProd.getPrice() + ";" + selectedProd.getStockQuantity() + ";" + splitData[4];
        }

        // 4. Calculate total amount and process the sale math
        currentTransaction.processSale();

        // 5. Save the updated stock back to products.txt
        try {
            java.io.BufferedWriter bw = java.nio.file.Files.newBufferedWriter(productsPath);
            for (int i = 0; i < productCount; i++) {
                bw.write(rawLinesBuffer[i] + "\n");
            }
            bw.close();
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to save updated stock: " + e.getMessage());
        }
        this.printReceipt(currentTransaction);

        currentTransaction.saveToFile();
    }

    public void printReceipt(Transaction tx) {
        System.out.println("=== RETAILEASE POS SYSTEM ===");
        System.out.println("Receipt ID: #" + tx.getTransactionId());
        
        if (tx.getCustomerObj() != null) {
            System.out.println("Customer: " + tx.getCustomerObj().getCustomerName());
        }
        System.out.println("Printed by Cashier: " + this.username);
        System.out.println("------------------------------");

        // Safely pull the data out of the transaction object
        LineItem[] items = tx.getLineItems();
        
        
        // we loop through the lineItems array until we hit a null space.
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) break; // Stop when the cart runs out of added items
            
            LineItem item = items[i];
            System.out.println(item.getProduct().getProductName() + " x" + item.getQuantity());
        }

        System.out.println("------------------------------");
        System.out.println("TOTAL AMOUNT: RM" + tx.getTotalAmount());
        System.out.println("==============================");
    }
}

