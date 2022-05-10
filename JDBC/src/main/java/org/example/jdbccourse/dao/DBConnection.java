package org.example.jdbccourse.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String HOST="127.0.0.1";
    private static final String DB_NAME="jdbc_course_db";
    private static final int HOPE=3306;
    private static final String USERNAME="root";
    private static final String PASSWORD="";
    private  static Connection connection;
    public static Connection getConnection() throws SQLException{
        try {
            connection= DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s",HOST,HOPE,DB_NAME),USERNAME,PASSWORD);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
