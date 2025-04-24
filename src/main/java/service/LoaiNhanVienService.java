/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.rmi.RemoteException;
import model.LoaiNhanVien;

/**
 *
 * @author THANHTRI
 */
public interface LoaiNhanVienService extends GenericService<LoaiNhanVien, String> {
    LoaiNhanVien getLoaiNhanVienByViTri(String viTri) throws RemoteException;
}
