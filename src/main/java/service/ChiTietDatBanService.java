/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.rmi.RemoteException;
import java.util.List;
import model.ChiTietDatBan;

/**
 *
 * @author THANHTRI
 */
public interface ChiTietDatBanService extends GenericService<ChiTietDatBan, ChiTietDatBan.ChiTietDatBanId> {

    boolean luuCTDB(ChiTietDatBan ctdb) throws RemoteException;

    List<ChiTietDatBan> getListByMaDDB(String maDDB) throws RemoteException;

    boolean deleteListByMaDDB(String maDDB) throws RemoteException;

    List<Object[]> getChiTietDonDatBan(String maDDB) throws RemoteException;
}
