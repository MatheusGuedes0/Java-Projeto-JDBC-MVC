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
public class Teacher /*implements Serializable*/{
    private long cpf;
    private String name;
    private long telephone;
    private double salary;
    
    
    
    public Teacher(){
        //Default constructor 
    }
    
    public Teacher(long cpf,String name, long telephone, double salary){
        this.cpf = cpf;
        this.name = name;
        this.telephone = telephone;
        this.salary = salary;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTelephone() {
        return telephone;
    }

    public void setTelephone(long telephone) {
        this.telephone = telephone;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "CPF:" + cpf + ", Name=" + name + ", Telephone: " + telephone + ", Salary: " + salary
                +"\n";
    }
    
    
    
}
