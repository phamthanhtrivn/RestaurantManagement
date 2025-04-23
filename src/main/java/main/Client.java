/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.rmi.RemoteException;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import model.Ban;
import service.BanService;

/**
 *
 * @author THANHTRI
 */
public class Client {
    
    
    
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();
        
        BanService banService = (BanService) context.lookup("rmi://localhost:7551/banService");
        
        List<Ban> list = banService.getAll();
        
        list.forEach(ban -> System.out.println(ban));
    }
}
