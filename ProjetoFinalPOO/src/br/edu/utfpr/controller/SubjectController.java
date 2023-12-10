/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.controller;

import br.edu.utfpr.model.Enrollment;
import br.edu.utfpr.model.EnrollmentDAO;
import br.edu.utfpr.model.StreamsEnrollment;
import br.edu.utfpr.model.StreamsSubject;
import br.edu.utfpr.model.Subject;
import br.edu.utfpr.model.SubjectDAO;
import br.edu.utfpr.view.SubjectView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author mathe
 */
public class SubjectController {

    private static SubjectDAO subjectDAO = new SubjectDAO();

    public static void start(HashMap<String, String> params) {
        SubjectView.start();
    }

    public static void register(HashMap<String, String> params) {
        SubjectView.register();
    }

    public static void insert(HashMap<String, String> params) {
        Subject subject = new Subject();
        subject.setName(params.get("name"));
        subject.setTotalHours(Integer.parseInt(params.get("totalHours")));
        subject.setCpfTeacher(Long.parseLong(params.get("cpfTeacher")));
        Subject subjectcompar = new Subject();
        List<Subject> subjectList = subjectDAO.list();
        StreamsSubject st = new StreamsSubject();
        subjectcompar = st.searchByName(subjectList, params.get("name"));
        if (subjectcompar == null) {
            subjectDAO.insert(subject);
            SubjectController.list(params);
        } else {
            SubjectView.ErrorDialog("insert");
            SubjectView.start();
        }

    }

    public static void list(HashMap<String, String> params) {
        List<Subject> subjectList = subjectDAO.list();
        SubjectView.list(subjectList);
    }

    public static void search(HashMap<String, String> params) {
        SubjectView.search();
    }

    public static void show(HashMap<String, String> params) {
        Subject subject = new Subject();
        List<Subject> subjectList = subjectDAO.list();
        StreamsSubject st = new StreamsSubject();
        if (params.get("op").equals("1")) {
            subject = st.searchByID(subjectList, Integer.parseInt(params.get("idclass")));
        } else if (params.get("op").equals("2")) {
            subject = st.searchByName(subjectList, params.get("name"));
        }

        SubjectView.show(subject);
    }

    public static void edit(HashMap<String, String> params) {
        int idclass = Integer.parseInt(params.get("idclass"));
        Subject subject = subjectDAO.search(idclass);
        SubjectView.edit(subject);
    }

    public static void alter(HashMap<String, String> params) {
        int idclass = Integer.parseInt(params.get("idclass"));
        Subject subject = subjectDAO.search(idclass);
        subject.setName(params.get("name"));
        subject.setTotalHours(Integer.parseInt(params.get("totalHours")));
        subject.setCpfTeacher(Long.parseLong(params.get("cpfTeacher")));
        subjectDAO.alter(subject);
        SubjectView.show(subject);
    }

    public static void remove(HashMap<String, String> params) {
        int idclass = Integer.parseInt(params.get("idclass"));
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        Enrollment enrollment = new Enrollment();
        List<Enrollment> enrollmentList = enrollmentDAO.list();
        StreamsEnrollment st = new StreamsEnrollment();
        enrollment = st.searchByID(enrollmentList, idclass);
        if (enrollment == null) {
            Subject subject = subjectDAO.search(idclass);

            subjectDAO.remove(idclass);

            SubjectView.remove(subject);
        } else {
            SubjectView.ErrorDialog("delete");
            SubjectView.start();
        }

    }

    public static void saveFiles(List<Subject> subjectList) {
        JFileChooser destino = new JFileChooser();
        if (destino.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                FileOutputStream fileOutput = new FileOutputStream(destino.getSelectedFile().getPath());
                ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
                objectOutput.writeObject(subjectList);
                System.out.println("Teacher list saved successfully!!");
                objectOutput.close();
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /*caso a prof queria ler o arquivo salvo. Vou deixar comentado pois no trabalho não pedia para implementar
    essa função
    
      public static void readFile(){
        List<Subject> subjectList = null;
        JFileChooser fileChooser = new JFileChooser();
        if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
           try{
               FileInputStream fileInput = new FileInputStream(fileChooser.getSelectedFile().getPath());
               ObjectInputStream objectInput = new ObjectInputStream(fileInput);
                try{
                subjectList = (List<Subject>) objectInput.readObject();
            }catch (ClassNotFoundException e){
               System.out.println("Error: " + e.getMessage());
            }
            }catch (FileNotFoundException e){
                    System.out.println("Error: " + e.getMessage());
            }catch(IOException e){
                System.out.println("Error: " + e.getMessage());
           }
        }
        SubjectView.showSubjectList(subjectList);
    }
     */
    public static void goTo(HashMap<String, String> parameter) {
        String destiny = parameter.get("destiny");

        switch (destiny) {
            case "Search","search","1" ->
                SubjectController.search(parameter);
            case "Register","register","2" ->
                SubjectController.register(parameter);
            case "List","list","3" ->
                SubjectController.list(parameter);
            case "Edit","edit","4" ->
                MainController.start(parameter);
            /*case "read","Read", "5" -> 
                SubjectController.readFile();*/
            default -> {
                SubjectController.start(parameter);
            }
        }
    }

}
