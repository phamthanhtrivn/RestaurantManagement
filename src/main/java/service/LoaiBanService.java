/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.rmi.RemoteException;
import java.util.List;
import model.LoaiBan;

/**
 *
 * @author THANHTRI
 */
public interface LoaiBanService extends GenericService<LoaiBan, String>{
    List<LoaiBan> getListLoaiBan() throws RemoteException;
    LoaiBan getLoaiBanTheoMa(String maLB)throws RemoteException;
    LoaiBan getLoaiBanByName(String tenLoaiBan)throws RemoteException;
}
