/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.model;

import java.sql.*;
import java.util.List;

/**
 *
 * @author mathe
 */
public abstract class AbstractDAO<T,U> {

    protected abstract T mapToEntity(ResultSet rs);

    protected abstract boolean insert(T entity);

    public abstract List<T> list();

    protected abstract T search(U key);

    protected abstract boolean alter(T entity);

    protected abstract boolean remove(U key);

}
