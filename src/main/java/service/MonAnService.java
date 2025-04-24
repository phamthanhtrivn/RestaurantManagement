/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.rmi.RemoteException;
import java.util.List;
import model.MonAn;

/**
 *
 * @author THANHTRI
 */
public interface MonAnService extends GenericService<MonAn, String> {

    List<MonAn> danhSachMonAnTheoMaLoai(String maLoai) throws RemoteException;

    List<MonAn> getMonTheoLoai(String maLoai) throws RemoteException;

    MonAn getMonAnTheoMa(String maMA) throws RemoteException;

    String maTuSinh() throws RemoteException;
}
