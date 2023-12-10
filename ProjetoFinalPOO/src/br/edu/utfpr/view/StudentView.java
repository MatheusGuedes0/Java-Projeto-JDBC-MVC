/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.view;

import br.edu.utfpr.controller.StudentController;
import br.edu.utfpr.model.Student;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mathe
 */
public class StudentView {

    public static void start() {
        HashMap<String, String> parameter = new HashMap<>();

        String destiny = JOptionPane.showInputDialog(null, "=========================STUDENT MENU=========================\n"
                + "\n[1]-Search/Remove/Edit - search, remove or edit the data of a specific student\n"
                + "[2]-Register - register a new student \n"
                + "[3]-List - list all registered students \n"
                + "[4]-Start - return to main menu\n"
                //+ " [5]- Read - Read file"
                + "\nChose a option: ");

        parameter.put("destiny", destiny);

        StudentController.goTo(parameter);
    }

    public static void list(List<Student> studentList) {
        HashMap<String, String> parameter = new HashMap<>();

        int op = JOptionPane.showConfirmDialog(null, "=================================LIST OF STUDENTS=================================\n\n"
                + studentList.toString() + "\nDo you want to save this list?");
        if (op == JOptionPane.YES_OPTION) {
            StudentController.saveFiles(studentList);
            StudentController.start(parameter);
        } else {
            StudentController.start(parameter);
        }
    }

    public static void register() {
        HashMap<String, String> parameter = new HashMap<>();
        try {
            String ra = JOptionPane.showInputDialog(null, "Provide details to register a new Student\n\nRA: ");
            parameter.put("ra", ra);
            String name = JOptionPane.showInputDialog(null, "Name: ");
            parameter.put("name", name);
            String cpf = JOptionPane.showInputDialog(null, "CPF: ");
            parameter.put("cpf", cpf);
            String course = JOptionPane.showInputDialog(null, "Course: ");
            parameter.put("course", course);

            StudentController.insert(parameter);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            StudentView.start();
        }
    }

    public static void search() {
        HashMap<String, String> parameter = new HashMap<>();
        try {
            String op = JOptionPane.showInputDialog("Choose a option:\n [1] - RA\n [2] - Name\n [3]-Back to Student Menu");
            if (op.equals("1") || op.equals("ra") || op.equals("Ra") || op.equals("RA")) {
                String ra = JOptionPane.showInputDialog(null, "Enter the desired RA: ");
                parameter.put("op", "1");
                parameter.put("ra", ra);

            } else if (op.equals("2") || op.equals("name") || op.equals("Name") || op.equals("NAME")) {
                String name = JOptionPane.showInputDialog(null, "Enter the desired name: ");
                parameter.put("op", "2");
                parameter.put("name", name);
            } else if (op.equals("3") || op.equals("back") || op.equals("Back") || op.equals("BACK")) {
                StudentView.start();
            } else {
                StudentView.invalidOption(op);
                StudentView.search();
            }
            StudentController.show(parameter);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            StudentController.start(parameter);
        }
    }

    public static void show(Student student) {
        HashMap<String, String> parameter = new HashMap<>();
        parameter.put("ra", String.valueOf(student.getRa()));
        String destiny = JOptionPane.showInputDialog(null, "Student:\n" + student + "\nChoose a option:\n[1] - Edit\n[2]-Remove\n[3] - back to subject menu");
        if (destiny.equals("1")) {
            StudentController.edit(parameter);
        } else if (destiny.equals("2")) {
            StudentController.remove(parameter);
        } else if (destiny.equals("3")) {
            StudentController.start(parameter);
        } else {
            StudentView.invalidOption(destiny);
            StudentView.show(student);
        }

    }

    public static void edit(Student student) {
        HashMap<String, String> parameter = new HashMap<>();
        try {
            parameter.put("ra", String.valueOf(student.getRa()));
            JOptionPane.showMessageDialog(null, "Provide details to edit the register below\n" + student.toString());
            String name = JOptionPane.showInputDialog(null, "Enter the Name: ");
            parameter.put("name", name);
            String cpf = JOptionPane.showInputDialog(null, "Enter the CPF: ");
            parameter.put("cpf", cpf);
            String course = JOptionPane.showInputDialog(null, "Enter the Course: ");
            parameter.put("course", course);

            StudentController.alter(parameter);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            TeacherView.start();
        }
    }

    public static void alter(Student student) {
        HashMap<String, String> parameter = new HashMap<>();
        parameter.put("ra", String.valueOf(student.getRa()));

        JOptionPane.showMessageDialog(null, "The registry was changed successfully" + student);

        StudentController.show(parameter);
    }

    public static void remove(Student student) {
        HashMap<String, String> parameter = new HashMap<>();
        parameter.put("ra", String.valueOf(student.getRa()));
        JOptionPane.showMessageDialog(null, "The registry was deleted successfully\n" + student);

        StudentController.start(parameter);
    }

    public static void invalidOption(String destiny) {
        JOptionPane.showMessageDialog(null, "invalid option: " + destiny);
    }

    public static void ErrorDialog(String message) {
        if (message.equals("delete")) {
            JOptionPane.showMessageDialog(null, "Error deleting Student data: Student data is stored in another record");
        }
        if (message.equals("insert")) {
            JOptionPane.showMessageDialog(null, "Error when entering new student data: student already registered");
        }
    }

    /* Função que mostra a lista de alunos que foi salvado. Vou deixar comentado pois no trabalho 
    não pedia para implementar essa função
    
    public static void showStudentList(List<Student> studentList) {
        HashMap<String, String> parameter = new HashMap<>();
        JOptionPane.showMessageDialog(null, studentList);
        StudentController.start(parameter);
    }
     */
}
