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
public class StreamsTeacher {
    
    public Teacher searchByCpf(List<Teacher> teacher, long cpf){
        Teacher selected  = teacher.stream()
                .filter(i-> i.getCpf() == cpf)
                
                .findAny()
                .orElse(null);
        return selected;
    }
    
    public Teacher searchByName(List<Teacher> teacher, String name){
        Teacher selected  = teacher.stream()
                .filter(i-> name.equals(i.getName()))
                .findAny()
                .orElse(null);
        
        return selected;
    }
    
}
