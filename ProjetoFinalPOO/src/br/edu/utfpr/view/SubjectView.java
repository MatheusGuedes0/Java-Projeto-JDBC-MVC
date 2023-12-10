/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.view;

import br.edu.utfpr.controller.SubjectController;
import br.edu.utfpr.model.Subject;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mathe
 */
public class SubjectView {

    public static void start() {
        HashMap<String, String> parameter = new HashMap<>();

        String destiny = JOptionPane.showInputDialog(null, "=========================SUBJECT MENU=========================\n"
                + "\n[1]-Search/Remove/Edit - search, remove or edit the data of a specific subject\n"
                + "[2]-Register - register a new subject \n"
                + "[3]-List - list all registered subjects \n"
                + "[4]-Start - return to main menu\n"
                //+ " [5]- Read - Read file"
                + "\nChose a option: ");

        parameter.put("destiny", destiny);

        SubjectController.goTo(parameter);
    }

    public static void list(List<Subject> subjectList) {
        HashMap<String, String> parameter = new HashMap<>();

        int op = JOptionPane.showConfirmDialog(null, "=================================LIST OF SUBJECTS=================================\n\n"
                + subjectList.toString() + "\nDo you want to save this list?");
        if (op == JOptionPane.YES_OPTION) {
            SubjectController.saveFiles(subjectList);
            SubjectController.start(parameter);
        } else {
            SubjectController.start(parameter);
        }
    }

    public static void register() {
        HashMap<String, String> parameter = new HashMap<>();
        try {
            String name = JOptionPane.showInputDialog(null, "Provide details to register a new Subject\n\nName: ");
            parameter.put("name", name);
            String totalHours = JOptionPane.showInputDialog(null, "Total Hours: ");
            parameter.put("totalHours", totalHours);
            String cpfTeacher = JOptionPane.showInputDialog(null, "Teacher's CPF: ");
            parameter.put("cpfTeacher", cpfTeacher);

            SubjectController.insert(parameter);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            SubjectView.start();
        }
    }

    public static void search() {
        HashMap<String, String> parameter = new HashMap<>();
        try {
            String op = JOptionPane.showInputDialog("Choose a option:\n [1] - Class ID\n [2] - Subject name\n [3]-Back to Subject Menu");
            if (op.equals("1") || op.equals("class ID") || op.equals("CLASS ID") || op.equals("ID") || op.equals("class id")) {
                String idclass = JOptionPane.showInputDialog(null, "Enter the desired Class ID: ");
                parameter.put("op", "1");
                parameter.put("idclass", idclass);

            } else if (op.equals("2") || op.equals("name") || op.equals("Name") || op.equals("NAME")) {
                String name = JOptionPane.showInputDialog(null, "Enter the desired name: ");
                parameter.put("op", "2");
                parameter.put("name", name);
            } else if (op.equals("3") || op.equals("back") || op.equals("Back") || op.equals("BACK")) {
                SubjectView.start();
            } else {
                SubjectView.invalidOption(op);
                SubjectView.search();
            }
            SubjectController.show(parameter);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            SubjectController.start(parameter);
        }
    }

    public static void show(Subject subject) {
        HashMap<String, String> parameter = new HashMap<>();
        parameter.put("idclass", String.valueOf(subject.getIDClass()));

        String destiny = JOptionPane.showInputDialog(null, "Subject:\n" + subject + "\nChoose a option:\n[1] - Edit\n[2]-Remove\n[3] - back to subject menu");
        if (destiny.equals("1")) {
            SubjectController.edit(parameter);
        } else if (destiny.equals("2")) {
            SubjectController.remove(parameter);
        } else if (destiny.equals("3")) {
            SubjectController.start(parameter);
        } else {
            SubjectView.invalidOption(destiny);
            SubjectView.show(subject);
        }

    }

    public static void edit(Subject subject) {
        HashMap<String, String> parameter = new HashMap<>();
        try {
            parameter.put("idclass", String.valueOf(subject.getIDClass()));
            JOptionPane.showMessageDialog(null, "provide details to edit the register below\n" + subject.toString());
            String name = JOptionPane.showInputDialog(null, " Enter the Name of subject: ");
            parameter.put("name", name);
            String totalHours = JOptionPane.showInputDialog(null, "Enter the Total Hours of Subject: ");
            parameter.put("totalHours", totalHours);
            String cpfTeacher = JOptionPane.showInputDialog(null, "Enter the CPF of the teacher responsible for the subject: ");
            parameter.put("cpfTeacher", cpfTeacher);

            SubjectController.alter(parameter);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            SubjectView.start();
        }
    }

    public static void alter(Subject subject) {
        HashMap<String, String> parameter = new HashMap<>();
        parameter.put("idclass", String.valueOf(subject.getIDClass()));

        JOptionPane.showMessageDialog(null, "The registry was changed successfully" + subject);

        SubjectController.show(parameter);
    }

    public static void remove(Subject subject) {
        HashMap<String, String> parameter = new HashMap<>();
        parameter.put("idclass", String.valueOf(subject.getIDClass()));
        JOptionPane.showMessageDialog(null, "the registry was deleted successfully\n" + subject);

        SubjectController.start(parameter);
    }

    public static void invalidOption(String destiny) {
        JOptionPane.showMessageDialog(null, "invalid option: " + destiny);
    }

   public static void ErrorDialog(String message) {
        if (message.equals("delete")) {
            JOptionPane.showMessageDialog(null, "Error deleting Subject data: Subject data is stored in another record");
        }
        if (message.equals("insert")) {
            JOptionPane.showMessageDialog(null, "Error when entering new subject data: subject already registered");
        }
    }
    /* Função que mostra a lista de disciplinas que foi salvado. Vou deixar comentado pois no trabalho
    não pedia para implementar essa função
    
    public static void showSubjectList(List<Subject> subjectList) {
        HashMap<String, String> parameter = new HashMap<>();
        JOptionPane.showMessageDialog(null, subjectList);
        SubjectController.start(parameter);
    }
     */

}
