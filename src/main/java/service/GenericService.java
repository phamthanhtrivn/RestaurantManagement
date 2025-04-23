/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author THANHTRI
 */
public interface GenericService<T, ID> extends Remote {
    
    boolean save(T t) throws RemoteException;
    boolean update(T t) throws RemoteException;
    boolean delete(ID id) throws RemoteException;
    T findById(ID id) throws RemoteException;
    List<T> getAll() throws RemoteException;
}
