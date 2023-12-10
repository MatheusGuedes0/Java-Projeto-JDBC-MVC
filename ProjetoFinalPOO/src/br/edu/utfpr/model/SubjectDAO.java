/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.model;

import java.sql.ResultSet;
import java.util.List;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mathe
 */
public class SubjectDAO extends AbstractDAO<Subject, Integer> {

    private Connection connection;
    private Logger logger = Logger.getLogger("SubjectDAO");
    ResultSet rs;
    PreparedStatement stmt;

    public SubjectDAO() {
        connection = ConnectionDAO.getInstance().getConnection();
    }

    @Override
    protected Subject mapToEntity(ResultSet rs) {
        try {
            Subject subject = new Subject();
            subject.setIDClass(rs.getInt("idclass"));
            subject.setName(rs.getString("name"));
            subject.setTotalHours(rs.getInt("totalhours"));
            subject.setCpfTeacher(rs.getLong("cpfteacher"));
            return subject;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error executing the query", e);
            return null;
        }
    }

    @Override
    public boolean insert(Subject subject) {
        String sql = "INSERT INTO subject(name,totalhours,cpfteacher) VALUES(?,?,?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, subject.getName());
            stmt.setInt(2, subject.getTotalHours());
            stmt.setLong(3, subject.getCpfTeacher());
            stmt.execute();
            logger.info("Successful insertion into database!");
            stmt.close();
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error inserting data into the database!", e.getMessage());
            return false;
        }
    }

    @Override
    public List<Subject> list() {
        String sql = "SELECT * FROM subject";
        List<Subject> returned = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(sql);
            logger.info(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Subject subject = mapToEntity(rs);
                returned.add(subject);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error when performing querry!", e.getMessage());
        }
        return returned;
    }

    @Override
    public Subject search(Integer key) {
        String sql = "SELECT * FROM  subject WHERE idclass=?";
        Subject subject = new Subject();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, key);
            rs = stmt.executeQuery();
            while (rs.next()) {
                subject = mapToEntity(rs);
            }
            return subject;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error when performing querry!", e.getMessage());
            return null;
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error closing database connection!", e.getMessage());
            }
        }
    }

    @Override
    public boolean alter(Subject subject) {
        String sql = "UPDATE subject SET name=?,totalhours=?,cpfteacher=? WHERE idclass=?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, subject.getName());
            stmt.setInt(2, subject.getTotalHours());
            stmt.setLong(3, subject.getCpfTeacher());
             stmt.setInt(4, subject.getIDClass());
            stmt.execute();
            logger.info("Change made successfully");
            stmt.close();
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error executing change", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean remove(Integer key) {
        String sql = "DELETE FROM subject WHERE idclass=?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, key);
            stmt.execute();
            logger.info("Delete command successful");
            stmt.close();
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting data from database", e.getMessage());
            return false;
        }
    }

}
