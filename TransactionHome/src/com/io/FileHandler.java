/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.io;

import com.User;
import com.login.LoginHandler;
import husi.io.File;
import husi.util.Encryptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**

 @author Desktop
 */
public class FileHandler {

    public boolean validateUser(String username) {
        File f = new File(String.format("%s.acc", username), false);
        return f.exists();
    }

    public User getUser(String fname, String lname) {
        try {
            String username = LoginHandler.getUsername(fname, lname);
            File f = new File(String.format("%s.acc", username), false);
            if (!f.exists()) {
                return null;
            } else {
                double balance = Double.parseDouble(f.getProperty("BALANCE"));
                Scanner scanner = new Scanner(new FileInputStream(getTransFile(username)));
                ArrayList<String> transData = new ArrayList<>();
                while (scanner.hasNextLine()) {
                    transData.add(scanner.nextLine());
                }
                User user = new User(fname, lname, balance, transData);
                user.setPassword(f.getProperty("PASSWORD"));
                user.setTransFile(new File(f.getProperty("TRANSACTION_FILE"), false));
                return user;
            }
        } catch (FileNotFoundException ex) {
            return null;
        }
    }

    public void remember(String fname, String lname) {
        File f = getLoginFile();
        f.write(String.format("LOGGED_IN = true\nCFNAME = %s\nCLNAME = %s", fname, lname));
    }

    public void forget() {
        File f = getLoginFile();
        f.write("LOGGED_IN = false\nCFNAME = \nCLNAME = ");
    }

    public void clear(String fname, String lname) {
        File f = this.getTransFile(LoginHandler.getUsername(fname, lname));
        f.write("");
        User user = this.getUser(fname, lname);
        user.setBalance(0);
        this.saveUser(user);
    }

    public File getLoginFile() {
        File f = new File("log.dat", false);
        if (!f.exists()) {
            return createLoginFile();
        } else {
            return f;
        }
    }

    public boolean validatePass(String username, String password) {
        File f = new File(String.format("%s.acc", username), false);
        Encryptor encryptor = new Encryptor();
        String storedPass = f.getProperty("PASSWORD");
        return encryptor.decrypt(storedPass).equalsIgnoreCase(password);
    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void newUser(String username, String password) {
        File f = new File(String.format("%s.acc", username), true);
        Encryptor encryptor = new Encryptor();
        StringBuilder sb = new StringBuilder();
        sb.append("PASSWORD = ").append(encryptor.encrypt(password)).append("\nBALANCE = 0.0\nTRANSACTION_FILE = ").append(String.format("%s_trans.dat", username));
        f.write(sb.toString());
        new File(String.format("%s_trans.dat", username), true);
    }

    public void saveUser(User user) {
        String username = LoginHandler.getUsername(user.getFirstName(), user.getLastName());
        File f = new File(String.format("%s.acc", username), false);
        StringBuilder sb = new StringBuilder();
        sb.append("PASSWORD = ").append(user.getPassword()).append("\nBALANCE = ").append(user.getBalance()).append("\nTRANSACTION_FILE = ").append(user.getTransFile().getName());
        f.write(sb.toString());
        sb = new StringBuilder();
        f = user.getTransFile();
        for (String data : user.getTransactions()) {
            sb.append(data).append("\n");
        }
        f.write(sb.toString());
    }

    private File createLoginFile() {
        File f = new File("log.dat", true);
        f.write("LOGGED_IN = false\nCFNAME = \nCLNAME = ");
        return f;
    }

    private File getTransFile(String username) {
        return new File(String.format("%s_trans.dat", username), false);
    }

}
