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
public class StreamsEnrollment {
    
  
      public Enrollment searchByRa(List<Enrollment> enrollment, int ra){
        Enrollment selected  = enrollment.stream()
                .filter(i-> i.getRa() == ra)
                
                .findAny()
                .orElse(null);
        return selected;
    }
      
      public Enrollment searchByID(List<Enrollment> enrollment, int IDClass){
        Enrollment selected  = enrollment.stream()
                .filter(i-> i.getIDClass() == IDClass)
                .findAny()
                .orElse(null);
        return selected;
    }
    
}

