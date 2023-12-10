/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.model;

import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;
/**
 *
 * @author mathe
 */
public class ConnectionDAO {
    Connection connection;
    private static ConnectionDAO instance = null;
    private Logger logger = Logger.getLogger("ConnectionDAO");
    
    public ConnectionDAO(){
        try {
            String url = "jdbc:postgresql://localhost:5432/POO-FinalProjectDataBase";
            String user = "postgres";
            String password = "110557Ernest0!";
            connection = DriverManager.getConnection(url,user,password);
            logger.info("Successful connection with the dataBase!");
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error establishing connection to the database!");
            e.getMessage();
        }
    }
    
    public Connection getConnection(){
        return connection;
    }
    public static ConnectionDAO getInstance(){
        if(instance ==null){
            instance = new ConnectionDAO();
        }
        
        return instance;
    }
    
    public void closeConnection(){
        try {
            if(connection != null){
                connection.close();
                logger.info("Connection with dataBase closed successfully!");
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING,"Error closing database connection", e.getMessage());
        }
    }
}
