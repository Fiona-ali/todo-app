package com.todo;

public class TestDAO {

    public static void main(String[] args) {

        TaskDAO dao = new TaskDAO();

        dao.markCompleted(1);
dao.getAllTasks();
dao.deleteTask(2);
dao.getAllTasks();
        dao.getAllTasks();
    }
}