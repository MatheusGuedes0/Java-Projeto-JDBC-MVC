/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.controller;

import br.edu.utfpr.view.MainView;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mathe
 */
public class MainController {

    public static void start(HashMap<String, String> parameter) {
        MainView.start();
    }

    public static void goTo(HashMap<String, String> parameter) {
        String destiny = parameter.get("destiny");

        switch (destiny) {
            case "Start","start" ->
                MainController.start(parameter);
            case "Teacher", "teacher","1" ->
                TeacherController.start(parameter);
            case "subject","Subject","2" ->
                SubjectController.start(parameter);
            case "student","Student","3" ->
                StudentController.start(parameter);
            case "Enrollment","enrollment","4" ->
                EnrollmentController.start(parameter);
            case "Quit", "quit", "5" ->
                System.exit(0);
            default -> {
                MainView.invalidOption(destiny);
            }
        }

    }
}
