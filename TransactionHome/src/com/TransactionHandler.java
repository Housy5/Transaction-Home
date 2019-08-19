/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.io.FileHandler;
import com.login.LoginHandler;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

/**

 @author Desktop
 */
public class TransactionHandler implements Transferable {

    private String username;
    private String fname, lname;

    public TransactionHandler(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
        username = LoginHandler.getUsername(fname, lname);
    }

    @Override
    public void transfer(Transaction trans) {
        FileHandler fh = new FileHandler();
        if (trans.getMode() == '+') {
            User user = fh.getUser(fname, lname);
            user.deposit(trans.getAmount());
            user.addTransaction(trans.toString());
            fh.saveUser(user);
            MainView mv = new MainView(user.getFirstName(), user.getLastName());
            mv.setVisible(true);
            mv.initUser();
        } else if (trans.getMode() == '-') {
            User user = fh.getUser(fname, lname);
            double balance = user.getBalance();
            if ((balance - trans.getAmount()) < 0) {
                JOptionPane.showMessageDialog(null, "You don't have enough money to do that.");
                Toolkit.getDefaultToolkit().beep();
                MainView mv = new MainView(user.getFirstName(), user.getLastName());
                mv.setVisible(true);
                mv.initUser();
            } else {
                user.withdraw(trans.getAmount());
                user.addTransaction(trans.toString());
                fh.saveUser(user);
                MainView mv = new MainView(fname, lname);
                mv.setVisible(true);
                mv.initUser();
            }
        }
    }

}
