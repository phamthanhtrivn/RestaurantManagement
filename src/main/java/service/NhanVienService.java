/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.rmi.RemoteException;
import model.NhanVien;

/**
 *
 * @author THANHTRI
 */
public interface NhanVienService extends GenericService<NhanVien, String> {
    
    NhanVien dangNhap(String username, String password) throws RemoteException;
}
