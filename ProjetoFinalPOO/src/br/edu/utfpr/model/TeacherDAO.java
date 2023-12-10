/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mathe
 */
public class TeacherDAO extends AbstractDAO<Teacher, Long> {

    private Connection connection;
    private Logger logger = Logger.getLogger("TeacherDAO");
    ResultSet rs;
    PreparedStatement stmt;

    public TeacherDAO() {
        connection = ConnectionDAO.getInstance().getConnection();
    }

    @Override
    protected Teacher mapToEntity(ResultSet rs) {
        try {
            Teacher teacher = new Teacher();
            teacher.setCpf(rs.getLong("cpf"));
            teacher.setName(rs.getString("name"));
            teacher.setTelephone(rs.getLong("telephone"));
            teacher.setSalary(rs.getDouble("salary"));
            return teacher;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error executing the query", e);
            return null;
        }
    }

    @Override
    public boolean insert(Teacher teacher) {
        String sql = "INSERT INTO teacher(cpf,name,telephone,salary) VALUES(?,?,?,?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, teacher.getCpf());
            stmt.setString(2, teacher.getName());
            stmt.setLong(3, teacher.getTelephone());
            stmt.setDouble(4, teacher.getSalary());
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
    public List<Teacher> list() {
        String sql = "SELECT * FROM teacher";
        List<Teacher> returned = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(sql);
            logger.info(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Teacher teacher = mapToEntity(rs);
                returned.add(teacher);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error when performing querry!", e.getMessage());
        }

        return returned;
    }

    @Override
    public Teacher search(Long key) {
        String sql = "SELECT * FROM  teacher WHERE cpf=?";
        Teacher teacher = new Teacher();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, key);
            rs = stmt.executeQuery();
            while (rs.next()) {
                teacher = mapToEntity(rs);
            }
            return teacher;
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
    public boolean remove(Long key) {
        String sql = "DELETE FROM teacher WHERE cpf=?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, key);
            stmt.execute();
            logger.info("Delete command successful");
            stmt.close();
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting data from database", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean alter(Teacher teacher) {
        String sql = "UPDATE teacher SET name=?, telephone=?, salary=? WHERE cpf=?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, teacher.getName());
            stmt.setLong(2, teacher.getTelephone());
            stmt.setDouble(3, teacher.getSalary());
            stmt.setLong(4,teacher.getCpf());
            stmt.execute();
            logger.info("Change made successfully");
            stmt.close();
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Error executing change", e.getMessage());
            return false;
        }
    }

}
