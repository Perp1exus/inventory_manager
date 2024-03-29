package com.mycompany.inventorymainscreen;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Database {
    private final String url="jdbc:mysql://localhost:3306/"; // location of database connection
    private final String dbName="inventorymanager"; // database name
    private final String driver="com.mysql.cj.jdbc.Driver"; // driver
    private final String userName="root"; 
    private final String password="";
    private Connection connection;

    
    public Connection check(){
        if(connection==null){
            try{
                Class.forName(driver);
                
                this.connection=(Connection)DriverManager.getConnection(url+dbName, userName, password);
                System.out.println("Connection Successful");
            }
            catch(ClassNotFoundException | SQLException sqle){
                System.out.println(sqle);
                System.out.println("Connection Failed");
            }
        }
        return connection;
    }

}
