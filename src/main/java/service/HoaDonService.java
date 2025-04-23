/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import model.HoaDon;

/**
 *
 * @author THANHTRI
 */
public interface HoaDonService extends GenericService<HoaDon, String> {

    List<Integer> loadNam() throws RemoteException;

    List<HoaDon> thongKeHoaDon(String type, Map<String, String> params) throws RemoteException;

    List<Object[]> thongKeMon(String type, Map<String, String> params) throws RemoteException;

    String createMaHD() throws RemoteException;
}
