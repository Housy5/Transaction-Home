/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.login.LoginHandler;

/**

 @author Desktop
 */
public class MainClass {

    public MainClass() {
        GarbageCollector gc = new GarbageCollector();
        LoginHandler lh = new LoginHandler();
        boolean auto = lh.autoLogin();
        if (auto == false) {
            LoginView lv = new LoginView();
            lv.setVisible(true);
        }
        gc.start();
    }

    public static void main(String[] args) {
        MainClass mainClass = new MainClass();
    }

}
