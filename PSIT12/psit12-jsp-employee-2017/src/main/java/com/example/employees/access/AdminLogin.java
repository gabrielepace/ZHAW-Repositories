package com.example.employees.access;

public class AdminLogin {

    public static boolean checkLogin(String name, String password) {
        return ((name.equals("Admin") && password.equals("123")) || (name.equals("pacegab1") && password.equals("123")));
    }

    private AdminLogin() {
        throw new IllegalStateException();
    }
}
