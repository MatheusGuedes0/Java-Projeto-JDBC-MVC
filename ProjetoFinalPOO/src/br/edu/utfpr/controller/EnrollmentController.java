/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.controller;

import br.edu.utfpr.model.Enrollment;
import br.edu.utfpr.model.EnrollmentDAO;
import br.edu.utfpr.model.StreamsEnrollment;

import br.edu.utfpr.view.EnrollmentView;
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
public class EnrollmentController {

    private static EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

    public static void start(HashMap<String, String> params) {
        EnrollmentView.start();
    }

    public static void register(HashMap<String, String> params) {
        EnrollmentView.register();
    }

    public static void insert(HashMap<String, String> params) {
        Enrollment enrollment = new Enrollment();
        Enrollment enrollmentCompar = new Enrollment();
        List<Enrollment> enrollmentList = enrollmentDAO.list();
        StreamsEnrollment st = new StreamsEnrollment();

        enrollmentCompar = st.searchByRa(enrollmentList, Integer.parseInt(params.get("ra")));

        if (enrollmentCompar == null) {
            enrollment.setIDClass(Integer.parseInt(params.get("idclass")));
            enrollment.setRa(Integer.parseInt(params.get("ra")));
            enrollmentDAO.insert(enrollment);
            EnrollmentController.list(params);
        } else {
            EnrollmentView.insertErrorDialog();
            EnrollmentView.start();
        }

    }

    public static void list(HashMap<String, String> params) {
        List<Enrollment> enrollmentList = enrollmentDAO.list();
        EnrollmentView.list(enrollmentList);
    }

    public static void search(HashMap<String, String> params) {
        EnrollmentView.search();
    }

    public static void show(HashMap<String, String> params) {
        Enrollment enrollment = new Enrollment();
        List<Enrollment> enrollmentList = enrollmentDAO.list();
        StreamsEnrollment st = new StreamsEnrollment();
        if (params.get("op").equals("1")) {
            enrollment = st.searchByID(enrollmentList, Integer.parseInt(params.get("idclass")));
        } else if (params.get("op").equals("2")) {
            enrollment = st.searchByRa(enrollmentList, Integer.parseInt(params.get("ra")));
        }

        EnrollmentView.show(enrollment);
    }

    public static void edit(HashMap<String, String> params) {
        int idenroll = Integer.parseInt(params.get("idenroll"));
        Enrollment enrollment = enrollmentDAO.search(idenroll);
        EnrollmentView.edit(enrollment);
    }

    public static void alter(HashMap<String, String> params) {
        int idenroll = Integer.parseInt(params.get("idenroll"));
        Enrollment enrollment = enrollmentDAO.search(idenroll);
        enrollment.setIDClass(Integer.parseInt(params.get("idclass")));
        enrollmentDAO.alter(enrollment);
        EnrollmentView.show(enrollment);
    }

    public static void remove(HashMap<String, String> params) {
        int idenroll = Integer.parseInt(params.get("idenroll"));
        Enrollment enrollment = enrollmentDAO.search(idenroll);

        enrollmentDAO.remove(idenroll);

        EnrollmentView.remove(enrollment);
    }

    public static void saveFiles(List<Enrollment> enrollmentList) {
        JFileChooser destino = new JFileChooser();
        if (destino.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                FileOutputStream fileOutput = new FileOutputStream(destino.getSelectedFile().getPath());
                ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
                objectOutput.writeObject(enrollmentList);
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
        List<Enrollment> enrollmentList = null;
        JFileChooser fileChooser = new JFileChooser();
        if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
           try{
               FileInputStream fileInput = new FileInputStream(fileChooser.getSelectedFile().getPath());
               ObjectInputStream objectInput = new ObjectInputStream(fileInput);
                try{
                enrollmentList = (List<Enrollment>) objectInput.readObject();
            }catch (ClassNotFoundException e){
               System.out.println("Error: " + e.getMessage());
            }
            }catch (FileNotFoundException e){
                    System.out.println("Error: " + e.getMessage());
            }catch(IOException e){
                System.out.println("Error: " + e.getMessage());
           }
        }
        EnrollmentView.showEnrollmentList(enrollmentList);
    }
     */
    public static void goTo(HashMap<String, String> parameter) {
        String destiny = parameter.get("destiny");

        switch (destiny) {
            case "Search","search","1" ->
                EnrollmentController.search(parameter);
            case "Register","register","2" ->
                EnrollmentController.register(parameter);
            case "List","list","3" ->
                EnrollmentController.list(parameter);
            case "Edit","edit","4" ->
                MainController.start(parameter);
            /*case "read","Read", "5" -> 
                EnrollmentController.readFile();*/
            default -> {
                EnrollmentView.invalidOption(destiny);
                EnrollmentController.start(parameter);
            }
        }
    }

}
