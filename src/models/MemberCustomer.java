package models;

public class MemberCustomer extends Customer{
    private String membershipStatus;
    private double discountRate;

    public MemberCustomer() {
        this.membershipStatus = null;
        this.discountRate = 0.0;
    }

    public MemberCustomer(String customerName, int customerID, String phoneNumber, int age, String membershipStatus, double discountRate) {
        super(customerName, customerID, phoneNumber, age);
        this.membershipStatus = membershipStatus;
        this.discountRate = discountRate;
    }

    //setters
    public void setMembershipStatus(String membershipStatus) {this.membershipStatus = membershipStatus;}
    public void setDiscountRate(double discountRate) {this.discountRate = discountRate;}

    //getters
    public String getMembershipStatus() {return membershipStatus;}
    public double getDiscountRate() {return discountRate;}

    public double calcDiscount(double totalAmount) {return totalAmount * discountRate;}
    @Override
    public String toString() {
        return "MemberCustomer{customerName='" + customerName + "', membershipStatus='" + membershipStatus + "', discountRate=" + discountRate + "}";
    }
}
