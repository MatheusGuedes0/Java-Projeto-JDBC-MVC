/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.view;

import br.edu.utfpr.controller.MainController;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author mathe
 */
public class MainView {
    public static void start(){
        HashMap<String,String> parameter = new HashMap<>();
  
        String destiny = JOptionPane.showInputDialog(null,"==========MAIN MENU==========\n"
                + "\nWhich menu would you like to acess:\n"
                + "\n 1-Teacher\n 2-Subject\n 3-Student\n 4-Enrollment\n 5-Quit System"
        +"\nChoose a option: ");
        parameter.put("destiny", destiny);
        
        MainController.goTo(parameter);
  
    }
    
    public static void invalidOption(String destiny){
        JOptionPane.showMessageDialog(null, "Invalid option: " + destiny);
        MainView.start();
    }
    
}
