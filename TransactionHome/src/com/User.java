package com;

import husi.io.File;
import java.util.ArrayList;

/**

 @author Desktop
 */
public class User {

    private String firstName;
    private String lastName;
    private String password;
    private File transFile;
    private double balance;
    private ArrayList<String> transactions;

    public User(String fname, String lname, double balance, ArrayList<String> transactions) {
        firstName = fname;
        lastName = lname;
        this.balance = balance;
        this.transactions = transactions;
    }

    public final void setTransFile(File f) {
        transFile = f;
    }

    public final File getTransFile() {
        return transFile;
    }

    public final void setPassword(String password) {
        this.password = password;
    }

    public final String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }

    void deposit(double amount) {
        balance += amount;
    }

    void withdraw(double amount) {
        balance -= amount;
    }

    void addTransaction(String toString) {
        transactions.add(toString);
    }

}
