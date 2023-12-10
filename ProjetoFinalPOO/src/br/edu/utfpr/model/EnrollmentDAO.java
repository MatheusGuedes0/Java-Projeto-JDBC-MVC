/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mathe
 */
public class EnrollmentDAO extends AbstractDAO<Enrollment,Integer>{
    
    private Connection connection;
    private Logger logger = Logger.getLogger("EnrollmentDAO");
    ResultSet rs;
    PreparedStatement stmt;

    public EnrollmentDAO() {
        connection = ConnectionDAO.getInstance().getConnection();
    }

    @Override
    protected Enrollment mapToEntity(ResultSet rs) {
        try {
            Enrollment enrollment = new Enrollment();
             enrollment.setIDEnroll(rs.getInt("idenroll"));
            enrollment.setIDClass(rs.getInt("idclass"));
           enrollment.setRa(rs.getInt("ra"));
            return enrollment;
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    @Override
    public boolean insert(Enrollment enrollment) {
        String sql = "INSERT INTO enrollment(idclass,ra) VALUES(?,?)";
        try {
            stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1, enrollment.getIDClass());
            stmt.setInt(2, enrollment.getRa());
            stmt.execute();
            JOptionPane.showMessageDialog(null,"Successful insertion into database!");
            stmt.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    @Override
    public List<Enrollment> list() {
        String sql = "SELECT * FROM enrollment";
        List<Enrollment> returned = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(sql);
            logger.info(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Enrollment enrollment = mapToEntity(rs);
                returned.add(enrollment);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return returned;
    }

    @Override
    public Enrollment search(Integer key) {
        String sql = "SELECT * FROM enrollment WHERE idenroll=?";
        Enrollment enrollment = new Enrollment();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, key);
            rs = stmt.executeQuery();
            while (rs.next()) {
                enrollment = mapToEntity(rs);
            }
            return enrollment;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                 JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    @Override
    public boolean alter(Enrollment enrollment) {
        String sql = "UPDATE enrollment SET idclass=?, ra=? WHERE idenroll=?";
        try {
            stmt = connection.prepareStatement(sql);
             stmt.setInt(1, enrollment.getIDClass());
              stmt.setInt(2, enrollment.getRa());
              stmt.setInt(3, enrollment.getIDEnroll());
            stmt.execute();
            logger.info("Change made successfully");
            stmt.close();
            return true;
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    @Override
    public boolean remove(Integer key) {
        String sql = "DELETE FROM enrollment WHERE idenroll=?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, key);
            stmt.execute();
            logger.info("Delete command successful");
            stmt.close();
            return true;
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    
}
