/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.controller;

import br.edu.utfpr.model.StreamsSubject;
import br.edu.utfpr.model.StreamsTeacher;
import br.edu.utfpr.model.Subject;
import br.edu.utfpr.model.SubjectDAO;
import br.edu.utfpr.model.Teacher;
import br.edu.utfpr.model.TeacherDAO;
import br.edu.utfpr.view.TeacherView;
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
public class TeacherController {

    private static TeacherDAO teacherDAO = new TeacherDAO();

    public static void start(HashMap<String, String> params) {
        TeacherView.start();
    }

    public static void register(HashMap<String, String> params) {
        TeacherView.register();
    }

    public static void insert(HashMap<String, String> params) {
        Teacher teacher = new Teacher();
        teacher.setCpf(Long.parseLong(params.get("cpf")));
        teacher.setName(params.get("name"));
        teacher.setTelephone(Long.parseLong(params.get("telephone")));
        teacher.setSalary(Double.parseDouble(params.get("salary")));
        if(teacherDAO.insert(teacher)){
             TeacherController.list(params);
        }else {
            TeacherView.ErrorDialog("insert");
        }

    }

    public static void list(HashMap<String, String> params) {
        List<Teacher> teacherList = teacherDAO.list();
        TeacherView.list(teacherList);
    }

    public static void search(HashMap<String, String> params) {
        TeacherView.search();
    }

    public static void show(HashMap<String, String> params) {

        Teacher teacher = new Teacher();
        List<Teacher> teacherList = teacherDAO.list();
        StreamsTeacher st = new StreamsTeacher();
        if (params.get("op").equals("1")) {
            teacher = st.searchByCpf(teacherList, Long.parseLong(params.get("cpf")));
        } else if (params.get("op").equals("2")) {
            teacher = st.searchByName(teacherList, params.get("name"));
        }

        TeacherView.show(teacher);

    }

    public static void edit(HashMap<String, String> params) {
        long cpf = Long.parseLong(params.get("cpf"));
        Teacher teacher = teacherDAO.search(cpf);
        TeacherView.edit(teacher);
    }

    public static void alter(HashMap<String, String> params) {
        long cpf = Long.parseLong(params.get("cpf"));
        Teacher teacher = teacherDAO.search(cpf);
        teacher.setName(params.get("name"));
        teacher.setSalary(Double.parseDouble(params.get("salary")));
        teacher.setTelephone(Long.parseLong(params.get("telephone")));
        teacherDAO.alter(teacher);
        TeacherView.show(teacher);
    }

    public static void remove(HashMap<String, String> params) {
        long cpf = Long.parseLong(params.get("cpf"));

        SubjectDAO subjectDAO = new SubjectDAO();
        Subject subject = new Subject();
        List<Subject> subjectList = subjectDAO.list();
        StreamsSubject st = new StreamsSubject();
        subject = st.searchByTeacherCPF(subjectList, cpf);
        if (subject == null) {
            Teacher teacher = teacherDAO.search(cpf);
            teacherDAO.remove(cpf);
            TeacherView.remove(teacher);
        } else {     
            TeacherView.ErrorDialog("delete");
            TeacherView.start();
        }
    }

    public static void saveFiles(List<Teacher> teacherList) {
        JFileChooser destino = new JFileChooser();
        if (destino.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                FileOutputStream fileOutput = new FileOutputStream(destino.getSelectedFile().getPath());
                ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
                objectOutput.writeObject(teacherList);
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
        List<Teacher> teacherList = null;
        JFileChooser fileChooser = new JFileChooser();
        if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
           try{
               FileInputStream fileInput = new FileInputStream(fileChooser.getSelectedFile().getPath());
               ObjectInputStream objectInput = new ObjectInputStream(fileInput);
                try{
                teacherList = (List<Teacher>) objectInput.readObject();
            }catch (ClassNotFoundException e){
               System.out.println("Error: " + e.getMessage());
            }
            }catch (FileNotFoundException e){
                    System.out.println("Error: " + e.getMessage());
            }catch(IOException e){
                System.out.println("Error: " + e.getMessage());
           }
        }
        TeacherView.showTeacherList(teacherList);
    }
     */
    public static void goTo(HashMap<String, String> parameter) {
        String destiny = parameter.get("destiny");

        switch (destiny) {
            case "Search","search","1" ->
                TeacherController.search(parameter);
            case "Register","register","2" ->
                TeacherController.register(parameter);
            case "List","list","3" ->
                TeacherController.list(parameter);
            case "Edit","edit","4" ->
                MainController.start(parameter);
            /*case "read","Read", "5" -> 
                TeacherController.readFile();*/
            default -> {
                TeacherView.invalidOption(destiny);
                TeacherController.start(parameter);
            }
        }
    }

}
