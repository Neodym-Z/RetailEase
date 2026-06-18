package models;

public class Customer {
    protected String customerName;
    protected int customerID;
    protected String phoneNumber;
    protected int age;

    public Customer() {
        this.customerName = null;
        this.customerID = 0;
        this.phoneNumber = null;
        this.age = 0;
    }

    public Customer(String customerName, int customerID, String phoneNumber, int age) {
        this.customerName = customerName;
        this.customerID = customerID;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public String getCustomerName() { return customerName; }
    public int getCustomerID() { return customerID; }
    public String getPhoneNumber() { return phoneNumber; }
    public int getAge() { return age; }

    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setCustomerID(int customerID) { this.customerID = customerID; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setAge(int age) { this.age = age; }

    public void displayProfile() {
        System.out.println("Customer Profile:");
        System.out.println("ID: " + customerID);
        System.out.println("Name: " + customerName);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Age: " + age);
    }

    @Override
    public String toString() {
        return "Customer{" + "customerName='" + customerName + '\'' + ", customerID=" + customerID + ", age=" + age + '}';
    }
}