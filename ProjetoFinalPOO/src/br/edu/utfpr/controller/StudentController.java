/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.controller;

import br.edu.utfpr.model.Enrollment;
import br.edu.utfpr.model.EnrollmentDAO;
import br.edu.utfpr.model.StreamsEnrollment;
import br.edu.utfpr.model.StreamsStudent;
import br.edu.utfpr.model.Student;
import br.edu.utfpr.model.StudentDAO;
import br.edu.utfpr.view.StudentView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author mathe
 */
public class StudentController {

    private static StudentDAO studentDAO = new StudentDAO();

    public static void start(HashMap<String, String> params) {
        StudentView.start();
    }

    public static void register(HashMap<String, String> params) {
        StudentView.register();
    }

    public static void insert(HashMap<String, String> params) {
        Student student = new Student();
        student.setRa(Integer.parseInt(params.get("ra")));
        student.setName(params.get("name"));
        student.setCpf(Long.parseLong(params.get("cpf")));
        student.setCourse(params.get("course"));
        if(studentDAO.insert(student)){
            StudentController.list(params);
        }else {
            StudentView.ErrorDialog("insert");
        }

        
    }

    public static void list(HashMap<String, String> params) {
        List<Student> studentList = studentDAO.list();
        StudentView.list(studentList);
    }

    public static void search(HashMap<String, String> params) {
        StudentView.search();
    }

    public static void show(HashMap<String, String> params) {
        Student student = new Student();
        List<Student> studentList = studentDAO.list();
        StreamsStudent st = new StreamsStudent();
        if (params.get("op").equals("1")) {
            student = st.searchByRa(studentList, Integer.parseInt(params.get("ra")));
        } else if (params.get("op").equals("2")) {
            student = st.searchByName(studentList, params.get("name"));
        }

        StudentView.show(student);
    }

    public static void edit(HashMap<String, String> params) {
        int ra = Integer.parseInt(params.get("ra"));
        Student student = studentDAO.search(ra);
        StudentView.edit(student);
    }

    public static void alter(HashMap<String, String> params) {
        int ra = Integer.parseInt(params.get("ra"));
        Student student = studentDAO.search(ra);
        student.setName(params.get("name"));
        student.setCpf(Long.parseLong(params.get("cpf")));
        student.setCourse(params.get("course"));
        studentDAO.alter(student);
        StudentView.show(student);
    }

    public static void remove(HashMap<String, String> params) {
        int ra = Integer.parseInt(params.get("ra"));

        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        Enrollment enrollment = new Enrollment();
        List<Enrollment> enrollmentList = enrollmentDAO.list();
        StreamsEnrollment st = new StreamsEnrollment();
        enrollment = st.searchByRa(enrollmentList, ra);
        if (enrollment == null) {
            Student student = studentDAO.search(ra);
            studentDAO.remove(ra);
            StudentView.remove(student);
        } else {
            StudentView.ErrorDialog("delete");
            StudentView.start();
        }

    }

    public static void saveFiles(List<Student> studentList) {
        JFileChooser destino = new JFileChooser();
        if (destino.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                FileOutputStream fileOutput = new FileOutputStream(destino.getSelectedFile().getPath());
                ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
                objectOutput.writeObject(studentList);
                System.out.println("Teacher list saved successfully!!");
                objectOutput.close();
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /* caso a prof queria ler o arquivo salvo. Vou deixar comentado pois no trabalho não pedia para implementar
    essa função
    
      public static void readFile(){
        List<Student> studentList = null;
        JFileChooser fileChooser = new JFileChooser();
        if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
           try{
               FileInputStream fileInput = new FileInputStream(fileChooser.getSelectedFile().getPath());
               ObjectInputStream objectInput = new ObjectInputStream(fileInput);
                try{
                studentList = (List<Student>) objectInput.readObject();
            }catch (ClassNotFoundException e){
               System.out.println("Error: " + e.getMessage());
            }
            }catch (FileNotFoundException e){
                    System.out.println("Error: " + e.getMessage());
            }catch(IOException e){
                System.out.println("Error: " + e.getMessage());
           }
        }
        StudentView.showStudentList(studentList);
    }
     */
    public static void goTo(HashMap<String, String> parameter) {
        String destiny = parameter.get("destiny");

        switch (destiny) {
            case "Search","search","1" ->
                StudentController.search(parameter);
            case "Register","register","2" ->
                StudentController.register(parameter);
            case "List","list","3" ->
                StudentController.list(parameter);
            case "Edit","edit","4" ->
                MainController.start(parameter);
            /* case "read","Read", "5" -> 
                StudentController.readFile();*/
            default -> {
                StudentView.invalidOption(destiny);
                StudentController.start(parameter);
            }
        }
    }

}
