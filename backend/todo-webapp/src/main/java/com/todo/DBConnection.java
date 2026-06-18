package com.todo;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() throws Exception {

        String url = "jdbc:mysql://localhost:3306/todo_db";
        String user = "root";
        String password = "Fionafinnu@2006";

        return DriverManager.getConnection(
                url,
                user,
                password
        );
    }

    public static void main(String[] args) {
        try {
            Connection con = getConnection();
            System.out.println("Connected Successfully!");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}