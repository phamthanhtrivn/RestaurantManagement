/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.HoaDonDAO;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import model.HoaDon;
import service.HoaDonService;

/**
 *
 * @author THANHTRI
 */
public class HoaDonServiceImpl extends GenericServiceImpl<HoaDon, String> implements HoaDonService {
    private HoaDonDAO hoaDonDAO;
    
    public HoaDonServiceImpl(HoaDonDAO hoaDonDAO) throws RemoteException {
        super(hoaDonDAO);
        this.hoaDonDAO = hoaDonDAO;
    }

    @Override
    public List<Integer> loadNam() throws RemoteException {
        return hoaDonDAO.loadNam();
    }

    @Override
    public List<HoaDon> thongKeHoaDon(String type, Map<String, String> params) throws RemoteException {
        return hoaDonDAO.thongKeHoaDon(type, params);
    }

    @Override
    public List<Object[]> thongKeMon(String type, Map<String, String> params) throws RemoteException {
        return hoaDonDAO.thongKeMon(type, params);
    }

    @Override
    public String createMaHD() throws RemoteException {
        return hoaDonDAO.createMaHD();
    }
}
