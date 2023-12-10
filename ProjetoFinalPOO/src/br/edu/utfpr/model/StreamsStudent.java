/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.model;

import java.util.List;

/**
 *
 * @author mathe
 */
public class StreamsStudent {
      public Student searchByRa(List<Student> student, int ra){
        Student selected  = student.stream()
                .filter(i-> i.getRa() == ra)
                
                .findAny()
                .orElse(null);
        return selected;
    }
    
    public Student searchByName(List<Student> student, String name){
        Student selected  = student.stream()
                .filter(i-> name.equals(i.getName()))
                .findAny()
                .orElse(null);
        
        return selected;
    }
}
