/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.ChiTietDatBan;


/**
 *
 * @author THANHTRI
 */
public interface ChiTietDatBanDAO extends GenericDAO<ChiTietDatBan, ChiTietDatBan.ChiTietDatBanId>{
    
    boolean luuCTDB(ChiTietDatBan ctdb);
    List<ChiTietDatBan> getListByMaDDB(String maDDB);
    boolean deleteListByMaDDB(String maDDB);
    List<Object[]> getChiTietDonDatBan(String maDDB);
}
