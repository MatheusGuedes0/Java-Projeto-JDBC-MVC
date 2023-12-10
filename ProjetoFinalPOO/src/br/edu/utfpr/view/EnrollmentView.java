/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.view;

import br.edu.utfpr.controller.EnrollmentController;
import br.edu.utfpr.model.Enrollment;
import br.edu.utfpr.model.Student;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mathe
 */
public class EnrollmentView {

    public static void start() {
        HashMap<String, String> parameter = new HashMap<>();

        String destiny = JOptionPane.showInputDialog(null, "=========================ENROLLMENT MENU=========================\n"
                + "\n[1]-Search/Remove/Edit - search, remove or edit a specific student's enrollment\n"
                + "[2]-Register - enroll a student \n"
                + "[3]-List - list existing registrations \n"
                + "[4]-Start - return to main menu\n"
                // + " [5]- Read - Read file"
                + "\nChose a option: ");

        parameter.put("destiny", destiny);

        EnrollmentController.goTo(parameter);
    }

    public static void list(List<Enrollment> enrollmentList) {
        HashMap<String, String> parameter = new HashMap<>();

        int op = JOptionPane.showConfirmDialog(null, "=======================================LIST OF ENROLLMENT=======================================\n\n"
                + enrollmentList.toString() + "\nDo you want to save this list?");
        if (op == JOptionPane.YES_OPTION) {
            EnrollmentController.saveFiles(enrollmentList);
            EnrollmentController.start(parameter);
        } else {
            EnrollmentController.start(parameter);
        }

    }

    public static void register() {
        HashMap<String, String> parameter = new HashMap<>();
        try {
            String ra = JOptionPane.showInputDialog(null, "Provide details to enroll a student\n\nRA: ");
            parameter.put("ra", ra);
            String idclass = JOptionPane.showInputDialog(null, "Class ID: ");
            parameter.put("idclass", idclass);

            EnrollmentController.insert(parameter);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            EnrollmentView.start();
        }
    }

    public static void search() {
        HashMap<String, String> parameter = new HashMap<>();
        try {
            String op = JOptionPane.showInputDialog("Choose a option:\n [1] - Class ID\n [2] - RA");

            if (op.equals("1") || op.equals("class ID") || op.equals("CLASS ID") || op.equals("ID") || op.equals("class id")) {
                String idclass = JOptionPane.showInputDialog(null, "Enter the desired class ID: ");
                parameter.put("op", "1");
                parameter.put("idclass", idclass);
            } else if (op.equals("2") || op.equals("ra") || op.equals("Ra") || op.equals("RA")) {
                String ra = JOptionPane.showInputDialog(null, "Enter the desired RA: ");
                parameter.put("op", "2");
                parameter.put("ra", ra);
                EnrollmentController.show(parameter);
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            EnrollmentController.start(parameter);
        }
    }

    public static void show(Enrollment enrollment) {
       HashMap<String, String> parameter = new HashMap<>();

        parameter.put("idenroll", String.valueOf(enrollment.getIDEnroll()));
        String destiny = JOptionPane.showInputDialog(null, "Enrollment:\n" + enrollment + "\nChoose a option:\n[1] - Edit\n[2]-Remove\n[3] - back enrollment menu");
        if (destiny.equals("1")) {
            EnrollmentController.edit(parameter);
        } else if (destiny.equals("2")) {
            EnrollmentController.remove(parameter);
        } else if (destiny.equals("3")) {
             EnrollmentController.start(parameter);
        } else {
            EnrollmentView.invalidOption(destiny);
            EnrollmentView.show(enrollment);
        }
    }

    public static void edit(Enrollment enrollment) {
        HashMap<String, String> parameter = new HashMap<>();
        try{
        parameter.put("idenroll", String.valueOf(enrollment.getIDEnroll()));
        JOptionPane.showMessageDialog(null, "Provide details to edit the register below\n" + enrollment.toString());
        String ra = JOptionPane.showInputDialog(null, "Enter the RA: ");
        parameter.put("ra", ra);
        String idclass = JOptionPane.showInputDialog(null, "Enter the Class ID: ");
        parameter.put("idclass", idclass);
        EnrollmentController.alter(parameter);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            EnrollmentView.start();
        }
    }

    public static void alter(Enrollment enrollment) {
        HashMap<String, String> parameter = new HashMap<>();
        parameter.put("idenroll", String.valueOf(enrollment.getIDEnroll()));

        JOptionPane.showMessageDialog(null, "The registry was changed successfully" + enrollment);

        EnrollmentController.show(parameter);
    }

    public static void remove(Enrollment enrollment) {
        HashMap<String, String> parameter = new HashMap<>();
        parameter.put("idenroll", String.valueOf(enrollment.getIDEnroll()));
        JOptionPane.showMessageDialog(null, "The registry was deleted successfully\n" + enrollment);

        EnrollmentController.start(parameter);
    }

    public static void invalidOption(String destiny) {
        JOptionPane.showMessageDialog(null, "invalid option: " + destiny);
    }
    
    public static void insertErrorDialog() {
        JOptionPane.showMessageDialog(null, "Error inserting Enrollment data: Student is already enrolled in the subject");
    }
    
     /* Função que mostra a lista de matricula que foi salvado. Vou deixar comentado pois no trabalho 
    não pedia para implementar essa função
    
    public static void showEnrollmentList(List<Enrollment> enrollmentList) {
        HashMap<String, String> parameter = new HashMap<>();
        JOptionPane.showMessageDialog(null, enrollmentList);
        EnrollmentController.start(parameter);
    }
     */

}
