/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author THANHTRI
 */
public interface GenericDAO<T, ID> {
    
    boolean save(T t);
    boolean update(T t);
    boolean delete(ID id);
    T findById(ID id);
    List<T> getAll();
    
}
