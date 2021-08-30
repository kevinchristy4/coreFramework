package org.core.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {

    private static final String MYSQL_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static Connection connection = null;


    public static void connect(String url, String userName, String password) throws Exception {
        //load driver
        Class.forName(Database.MYSQL_DRIVER_CLASS);
        Logger.getLogger().info("JDBC driver has been loaded successsfully!");

        //establish connection
        if(Database.connection == null){
            Database.connection = DriverManager.getConnection(url,userName,password);
        } else {
            Logger.getLogger().info("A connection already exists, will not establish a new connection...");
        }
    }

    public static ResultSet execute(String query) throws Exception {

        if(Database.connection == null)	throw new Exception("Expected a valid MySQL connection before querying..");
        if(query == null || query.isEmpty()) throw new Exception("Statement cannot be null or emtpy..");
        Statement statement = Database.connection.prepareStatement(query.trim());
        Logger.getLogger().info("Executing query..");
        Boolean hasMoreResultSets = statement.execute(query);
        ResultSet rs = statement.getResultSet();
        statement.close();
        return rs;
    }

    public static void disconnect(){
        try {
            if(Database.connection != null){
                Database.connection.close();
                Database.connection = null;
                Logger.getLogger().info("JDBC connection closed!");
            } else {
                Logger.getLogger().info("JDBC connection has already been closed..");
            }
        } catch(Exception e){} //ignore any exception on closing connection
    }

}
