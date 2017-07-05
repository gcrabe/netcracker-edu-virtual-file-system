/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nc.edu.vfs.database;

import com.nc.edu.vfs.graph.FileSystemGraphRepository;
import com.nc.edu.vfs.graph.Vertex;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gc
 */
public class DbGateway {
    
    private static String URL = "jdbc:mysql://localhost:3307/vfs_db?zeroDateTimeBehavior=convertToNull";
    private static String USER = "root";
    private static String PASS = "usbw";
    
//    private static void setProterties() {
//        FileInputStream fis = null;
//        Properties properties = new Properties();
//        
//        try {
//            fis = new FileInputStream("src/main/resources/dbconfig.properties");
//            properties.load(fis);
//            
//            URL = properties.getProperty("db.url");
//            USER = properties.getProperty("db.user");
//            PASS = properties.getProperty("db.pass");
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(DbGateway.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.print("Error: property file not found!\n");
//        } catch (IOException ex) {
//            Logger.getLogger(DbGateway.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.print("Error: incorrect loading of properties!\n");
//        } finally {
//            if (fis != null) {
//                try {
//                    fis.close();
//                } catch (IOException ex) {
//                    Logger.getLogger(DbGateway.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//    }
    
    private static Connection getConnection() {
        Connection connection = null;
        
        try {
            connection = DriverManager.getConnection(URL, USER,PASS);
        } catch (SQLException ex) {
            Logger.getLogger(DbGateway.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("DATABASE CONNECTION ERROR!!!");
        }
        
        return connection;
    }
    
    public static void clear() {
        Connection connection = getConnection();
        Statement statement = null;
        
        try {
            statement = connection.createStatement();
            String sql1 = "DELETE FROM `files` WHERE 1=1";
            String sql2 = "DELETE FROM `folders` WHERE 1=1";
            
            boolean res1 = statement.execute(sql1);
            boolean res2 = statement.execute(sql2);
        } catch (SQLException ex) {
            Logger.getLogger(DbGateway.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("DATABASE CLEANING ERROR!!!");
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DbGateway.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("DATABASE CLEANING > CLOSE STATEMENT ERROR!!!");
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DbGateway.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("DATABASE CLEANING > CLOSE CONNECTION ERROR!!!");
                }
            }
        }
    }
    
    private static void insertFile(Vertex v) {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        
        String sql = "INSERT INTO  `files` (  `id` ,  `folder_id` ,  `name` )" +
                " VALUES ( ?, ?, ? )";
        
        try {
            statement = connection.prepareStatement(sql);
            
            statement.setInt(1, v.getId());
            statement.setInt(2, v.getParent());
            statement.setString(3, v.getName());
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DbGateway.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("DATABASE FILE INSERTING ERROR!!!");
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DbGateway.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("DATABASE FILE INSERTING > CLOSE STATEMENT ERROR!!!");
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DbGateway.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("DATABASE FILE INSERTING > CLOSE CONNECTION ERROR!!!");
                }
            }
        }
    }
    
    private static void insertFolder(Vertex v) {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        
        String sql = "INSERT INTO  `folders` (  `id` ,  `parent_id` ,  `name` )" +
                " VALUES ( ?, ?, ? )";
        
        try {
            statement = connection.prepareStatement(sql);
            
            statement.setInt(1, v.getId());
            statement.setInt(2, v.getParent());
            statement.setString(3, v.getName());
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DbGateway.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("DATABASE FOLDER INSERTING ERROR!!!");
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DbGateway.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("DATABASE FOLDER INSERTING > CLOSE STATEMENT ERROR!!!");
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DbGateway.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("DATABASE FOLDER INSERTING > CLOSE CONNECTION ERROR!!!");
                }
            }
        }
    }
    
    public static void update() {
        ArrayList<Vertex> vertexList = FileSystemGraphRepository.getAll();
        
        for (Vertex v : vertexList) {
            if (v.getType().equals("file")) {
                insertFile(v);
            } else {
                insertFolder(v);
            }
        }
    }
}
