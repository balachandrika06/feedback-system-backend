package com.feedback;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://btsyujh7r1mlkfbqtsly-mysql.services.clever-cloud.com:3306/btsyujh7r1mlkfbqtsly?useSSL=false&serverTimezone=UTC";
        String username = "unvt57y5brwrzljg";
        String password = "YOUR_PASSWORD";
        
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}