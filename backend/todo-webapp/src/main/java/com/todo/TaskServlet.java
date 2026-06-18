package com.todo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs =
                    st.executeQuery("SELECT * FROM tasks");

            out.println("<h1>Todo Tasks</h1>");

            while(rs.next()) {

                out.println(
                    "<p>" +
                    rs.getInt("id") +
                    " - " +
                    rs.getString("title") +
                    "</p>"
                );
            }

            con.close();

        } catch(Exception e) {

            out.println("<h2>Error</h2>");
            out.println(e.getMessage());

        }
    }
}