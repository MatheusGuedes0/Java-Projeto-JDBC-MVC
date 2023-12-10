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
public class Subject /*implements Serializable*/ {

    private int IDClass;
    private String name;
    private int totalHours;
    private long cpfTeacher;

    public Subject() {
    }

    public int getIDClass() {
        return IDClass;
    }

    public void setIDClass(int IDCourse) {
        this.IDClass = IDCourse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

   

    public long getCpfTeacher() {
        return cpfTeacher;
    }

    public void setCpfTeacher(long cpfTeacher) {
        this.cpfTeacher = cpfTeacher;
    }

    @Override
    public String toString() {
        return "IDClass: " + IDClass + ", Name: " + name + ", TotalHours: " + totalHours + ", Teacher's cpf: " + cpfTeacher
                +"\n";
    }

}
