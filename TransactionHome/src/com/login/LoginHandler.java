package com.login;

import com.LoginView;
import com.MainView;
import com.io.FileHandler;
import javax.swing.JOptionPane;

public class LoginHandler implements Loginable {

    private FileHandler fh;

    public LoginHandler() {
        initFields();
    }

    private void initFields() {
        fh = new FileHandler();
    }

    private void loginFailed() {
        JOptionPane.showMessageDialog(null, "Incorrect password or username.\nPlease try again.");
        LoginView lv = new LoginView();
        lv.setVisible(true);
    }

    private void loginAccepted(String fname, String lname) {
        MainView mv = new MainView(fname, lname);
        mv.setVisible(true);
        mv.initUser();
    }

    public boolean autoLogin() {
        if (isLoggedIn()) {
            String fname = fh.getLoginFile().getProperty("CFNAME");
            String lname = fh.getLoginFile().getProperty("CLNAME");
            if (fname != null && lname != null) {
                MainView mv = new MainView(fname, lname);
                mv.setVisible(true);
                mv.initUser();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean isLoggedIn() {
        return Boolean.parseBoolean(fh.getLoginFile().getProperty("LOGGED_IN"));
    }

    public boolean userExists(String username) {
        return fh.validateUser(username);
    }

    public final void newUser(String username, String password) {
        fh.newUser(username, password);
    }

    @Override
    public void login(String fname, String lname, String password, boolean remember) {
        String username = String.format("%s_%s", fname, lname);
        if (fh.validateUser(username)) {
            if (fh.validatePass(username, password)) {
                loginAccepted(fname, lname);
                if (remember) {
                    remember(fname, lname);
                }
            } else {
                loginFailed();
            }
        } else {
            loginFailed();
        }
    }

    public void forget() {
        fh.forget();
    }

    private void remember(String fname, String lname) {
        fh.remember(fname, lname);
    }

    public static String getUsername(String fname, String lname) {
        return String.format("%s_%s", fname, lname);
    }

}
