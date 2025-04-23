/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.rmi.RemoteException;
import model.LoaiMonAn;

/**
 *
 * @author THANHTRI
 */
public interface LoaiMonAnService extends GenericService<LoaiMonAn, String> {
    
    LoaiMonAn findByName(String name) throws RemoteException;
}
