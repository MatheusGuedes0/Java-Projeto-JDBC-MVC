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
public class StreamsSubject {
    
     public Subject searchByID(List<Subject> subject, int IDClass){
        Subject selected  = subject.stream()
                .filter(i-> i.getIDClass() == IDClass)
                
                .findAny()
                .orElse(null);
        return selected;
    }
    
    public Subject searchByName(List<Subject> subject, String name){
        Subject selected  = subject.stream()
                .filter(i-> name.equals(i.getName()))
                .findAny()
                .orElse(null);
        
        return selected;
    }
    
     public Subject searchByTeacherCPF(List<Subject> subject, long cpfTeacher){
        Subject selected  = subject.stream()
                .filter(i-> i.getCpfTeacher()== cpfTeacher)
                .findAny()
                .orElse(null);
        return selected;
    }
    
    
}
