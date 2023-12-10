/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.view;

import br.edu.utfpr.controller.MainController;
import br.edu.utfpr.controller.TeacherController;
import br.edu.utfpr.model.Teacher;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mathe
 */
public class TeacherView {

    public static void start() {
        HashMap<String, String> parameter = new HashMap<>();

        String destiny = JOptionPane.showInputDialog(null, "=========================TEACHER MENU=========================\n"
                + "\n[1]-Search/Remove/Edit - search, remove or edit the data of a specific teacher\n"
                + "[2]-Register - register a new teacher \n"
                + "[3]-List - list all registered teachers \n"
                + "[4]-Start - return to main menu\n"
                //+ " [5]- Read - Read file"
                + "\nChose a option: ");

        parameter.put("destiny", destiny);

        TeacherController.goTo(parameter);
    }

    public static void list(List<Teacher> teacherList) {
        HashMap<String, String> parameter = new HashMap<>();

        int op = JOptionPane.showConfirmDialog(null, "=======================================LIST OF TEACHERS=======================================\n\n"
                + teacherList.toString() + "\nDo you want to save this list?");
        if (op == JOptionPane.YES_OPTION) {
            TeacherController.saveFiles(teacherList);
            TeacherController.start(parameter);
        } else {
            TeacherController.start(parameter);
        }

    }

    public static void register() {
        HashMap<String, String> parameter = new HashMap<>();
        try {
            String cpf = JOptionPane.showInputDialog(null, "Provide details to register a new Teacher\n\nCPF: ");
            parameter.put("cpf", cpf);
            String name = JOptionPane.showInputDialog(null, "Name: ");
            parameter.put("name", name);
            String telephone = JOptionPane.showInputDialog(null, "Telephone: ");
            parameter.put("telephone", telephone);
            String salary = JOptionPane.showInputDialog(null, "Salary: ");
            parameter.put("salary", salary);

            TeacherController.insert(parameter);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            TeacherView.start();
        }
    }

    public static void search() {
        HashMap<String, String> parameter = new HashMap<>();
        try {
            String op = JOptionPane.showInputDialog("Choose a option:\n [1] - CPF\n [2] - Name\n [3]-Back to Teacher's Menu");
            if (op.equals("1") || op.equals("cpf") || op.equals("CPF") || op.equals("Cpf")) {
                String cpf = JOptionPane.showInputDialog(null, "Enter the desired CPF: ");
                parameter.put("op", "1");
                parameter.put("cpf", cpf);

            } else if (op.equals("2") || op.equals("name") || op.equals("Name") || op.equals("NAME")) {
                String name = JOptionPane.showInputDialog(null, "Enter the desired name: ");
                parameter.put("op", "2");
                parameter.put("name", name);
            } else if (op.equals("3") || op.equals("back") || op.equals("Back") || op.equals("BACK")) {
                TeacherView.start();
            } else {
                TeacherView.invalidOption(op);
                TeacherView.search();
            }
            TeacherController.show(parameter);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            TeacherController.start(parameter);
        }
    }

    public static void show(Teacher teacher) {

        HashMap<String, String> parameter = new HashMap<>();

        parameter.put("cpf", String.valueOf(teacher.getCpf()));
        String destiny = JOptionPane.showInputDialog(null, "Teacher:\n" + teacher + "\nChoose a option:\n[1] - Edit\n[2]-Remove\n[3] - back teacher's menu");
        if (destiny.equals("1")) {
            TeacherController.edit(parameter);
        } else if (destiny.equals("2")) {
            TeacherController.remove(parameter);
        } else if (destiny.equals("3")) {
            TeacherController.start(parameter);
        } else {
            TeacherView.invalidOption(destiny);
            TeacherView.show(teacher);
        }
    }

    public static void edit(Teacher teacher) {
        HashMap<String, String> parameter = new HashMap<>();
        try {
            parameter.put("cpf", String.valueOf(teacher.getCpf()));
            JOptionPane.showMessageDialog(null, "Provide details to edit the register below\n" + teacher.toString());
            String name = JOptionPane.showInputDialog(null, " Enter the Name: ");
            parameter.put("name", name);
            String telephone = JOptionPane.showInputDialog(null, "Enter the Telephone: ");
            parameter.put("telephone", telephone);
            String salary = JOptionPane.showInputDialog(null, "Enter the Salary: ");
            parameter.put("salary", salary);

            TeacherController.alter(parameter);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            TeacherView.start();
        }
    }

    public static void alter(Teacher teacher) {
        HashMap<String, String> parameter = new HashMap<>();
        parameter.put("cpf", String.valueOf(teacher.getCpf()));

        JOptionPane.showMessageDialog(null, "The registry was changed successfully" + teacher);

        TeacherController.show(parameter);
    }

    public static void remove(Teacher teacher) {
        HashMap<String, String> parameter = new HashMap<>();
        parameter.put("cpf", String.valueOf(teacher.getCpf()));
        JOptionPane.showMessageDialog(null, "the registry was deleted successfully\n" + teacher);

        TeacherController.start(parameter);
    }

    public static void invalidOption(String destiny) {
        JOptionPane.showMessageDialog(null, "invalid option: " + destiny);
    }

    public static void ErrorDialog(String message) {
        if (message.equals("delete")) {
            JOptionPane.showMessageDialog(null, "Error deleting teacher data: teacher data is stored in another record");
        }
        if (message.equals("insert")) {
            JOptionPane.showMessageDialog(null, "Error when entering new teacher data: teacher already registered");
        }
    }

    /* Função que mostra a lista de professores que foi salvado. Vou deixar comentado pois no trabalho 
    não pedia para implementar essa função
    
    public static void showTeacherList(List<Teacher> teacherList) {
        HashMap<String, String> parameter = new HashMap<>();
        JOptionPane.showMessageDialog(null, teacherList);
        TeacherController.start(parameter);
    }
     */
}
