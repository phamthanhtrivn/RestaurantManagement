/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.rmi.RemoteException;
import model.LoaiKhachHang;

/**
 *
 * @author THANHTRI
 */
public interface LoaiKhachHangService extends GenericService<LoaiKhachHang, String> {
    LoaiKhachHang TimLoaiKhachHangTim(String maLoai) throws RemoteException;
    LoaiKhachHang getLoaiKhachHangByName(String tenLoaiKH)throws RemoteException;
}
