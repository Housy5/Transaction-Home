/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.Date;

public class Transaction {

    private double amount;
    private String mention;
    private Date timestamp;
    private char mode;

    public Transaction(double amount, String mention, Date timestamp, char mode) {
        this.amount = amount;
        this.mention = mention;
        this.timestamp = timestamp;
        this.mode = mode;
    }

    @Override
    public String toString() {
        return String.format("%c%.2f %s %s\n", mode, amount, mention, timestamp.toString());
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMention() {
        return mention;
    }

    public void setMention(String mention) {
        this.mention = mention;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public char getMode() {
        return mode;
    }

}
