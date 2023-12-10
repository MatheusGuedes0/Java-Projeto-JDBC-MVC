/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mathe
 */
public class StudentDAO extends AbstractDAO<Student, Integer> {

    private Connection connection;
    private Logger logger = Logger.getLogger("StudentDAO");
    ResultSet rs;
    PreparedStatement stmt;

    public StudentDAO() {
        connection = ConnectionDAO.getInstance().getConnection();
    }

    @Override
    protected Student mapToEntity(ResultSet rs) {

        try {
            Student student = new Student();
            student.setRa(rs.getInt("ra"));
            student.setName(rs.getString("name"));
            student.setCpf(rs.getLong("cpf"));
            student.setCourse(rs.getString("course"));

            return student;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error executing the query", e);
            return null;
        }
    }

    @Override
    public boolean insert(Student student) {
        String sql = "INSERT INTO student(ra,name,cpf,course) VALUES(?,?,?,?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, student.getRa());
            stmt.setString(2, student.getName());
            stmt.setLong(3, student.getCpf());
            stmt.setString(4, student.getCourse());
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
    public List<Student> list() {
        String sql = "SELECT * FROM student";
        List<Student> returned = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(sql);
            logger.info(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Student student = mapToEntity(rs);
                returned.add(student);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error when performing querry!", e.getMessage());
        }
        return returned;
    }

    @Override
    public Student search(Integer key) {
        String sql = "SELECT * FROM  student WHERE ra=?";
        Student student = new Student();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, key);
            rs = stmt.executeQuery();
            while (rs.next()) {
                student = mapToEntity(rs);
            }
            return student;
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
    public boolean alter(Student student) {
        String sql = "UPDATE student SET name=?,cpf=?,course=? WHERE ra=?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, student.getName());
            stmt.setLong(2, student.getCpf());
            stmt.setString(3, student.getCourse());
            stmt.setInt(4, student.getRa());
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
        String sql = "DELETE FROM student WHERE ra=?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, key);
            stmt.execute();
            logger.info("Delete command successful");
            stmt.close();
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error executing change", e.getMessage());
            return false;
        }
    }

}
