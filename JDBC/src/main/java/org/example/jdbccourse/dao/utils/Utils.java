package org.example.jdbccourse.dao.utils;
public class Utils {
    public static  java.sql.Date getSqlDate(java.util.Date date){
        return new java.sql.Date(date.getTime());
    }
}
