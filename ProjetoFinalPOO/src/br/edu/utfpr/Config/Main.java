/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package br.edu.utfpr.Config;

import br.edu.utfpr.controller.MainController;
import java.util.HashMap;

/**
 *
 * @author mathe
 */
public class Main {

  public static HashMap<String,String> parameter = new HashMap<>();
    public static void main(String[] args) {
        MainController.start(parameter);
    }
    
}
