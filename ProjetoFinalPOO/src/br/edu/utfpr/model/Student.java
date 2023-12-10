/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.model;

//import java.io.Serializable;

/**
 *
 * @author mathe
 */
public class Student /*implements Serializable */{
    private int ra;
    private String name;
    private long cpf;
    private String course;


    
    public Student(){
    }
    
    
    public int getRa() {
        return ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "RA: " + ra + ", Name: " + name + ", Cpf: " + cpf + ", Course: " + course
                +"\n";
    }
    
    
   
}
