/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.rmi.RemoteException;
import model.KhuyenMai;

/**
 *
 * @author THANHTRI
 */
public interface KhuyenMaiService extends GenericService<KhuyenMai, String> {
    
    String maTuSinh () throws RemoteException;
        KhuyenMai findByTenKM(String tenKM)throws RemoteException;
}
