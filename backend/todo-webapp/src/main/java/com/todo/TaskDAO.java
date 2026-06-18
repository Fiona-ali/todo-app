package com.todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TaskDAO {

    public void addTask(String title) {

        String sql =
            "INSERT INTO tasks(title, completed) VALUES (?, ?)";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement stmt =
                    con.prepareStatement(sql);

            stmt.setString(1, title);
            stmt.setBoolean(2, false);

            stmt.executeUpdate();

            System.out.println("Task Added!");

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void getAllTasks() {

    String sql = "SELECT * FROM tasks";

    try {
        Connection con = DBConnection.getConnection();

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()) {

            System.out.println(
                rs.getInt("id") + " | " +
                rs.getString("title") + " | " +
                rs.getBoolean("completed")
            );
        }

        con.close();

    } catch(Exception e) {
        e.printStackTrace();
    }
}

public void deleteTask(int id) {

    String sql = "DELETE FROM tasks WHERE id = ?";

    try {
        Connection con = DBConnection.getConnection();

        PreparedStatement stmt =
                con.prepareStatement(sql);

        stmt.setInt(1, id);

        stmt.executeUpdate();

        System.out.println("Task Deleted!");

        con.close();

    } catch(Exception e) {
        e.printStackTrace();
    }
}

public void markCompleted(int id) {

    String sql =
        "UPDATE tasks SET completed = true WHERE id = ?";

    try {

        Connection con = DBConnection.getConnection();

        PreparedStatement stmt =
                con.prepareStatement(sql);

        stmt.setInt(1, id);

        stmt.executeUpdate();

        System.out.println("Task Updated!");

        con.close();

    } catch(Exception e) {
        e.printStackTrace();
    }
}
}