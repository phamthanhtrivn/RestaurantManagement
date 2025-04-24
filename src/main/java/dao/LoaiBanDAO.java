/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.LoaiBan;

/**
 *
 * @author THANHTRI
 */
public interface LoaiBanDAO extends GenericDAO<LoaiBan, String>{
    List<LoaiBan> getListLoaiBan();
    LoaiBan getLoaiBanTheoMa(String maLB);
    LoaiBan getLoaiBanByName(String tenLoaiBan);
    
    
}
