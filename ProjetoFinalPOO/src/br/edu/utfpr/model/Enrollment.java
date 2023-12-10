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
public class Enrollment /*implements Serializable*/{
    private int IDEnroll;
    private int IDClass;
    private int ra;
    
    public Enrollment(){
        
    }

    public int getIDEnroll() {
        return IDEnroll;
    }

    public void setIDEnroll(int IDEnroll) {
        this.IDEnroll = IDEnroll;
    }

    
    public int getIDClass() {
        return IDClass;
    }

    public void setIDClass(int IDClass) {
        this.IDClass = IDClass;
    }

    public int getRa() {
        return ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }

    @Override
    public String toString() {
        return  "IDEnroll=" + IDEnroll + ", IDClass=" + IDClass + ", ra=" + ra + '\n';
    }

    
    
    
    
}
