/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Koneksi.Database;
import java.sql.Connection;
import java.util.List;
import javax.swing.JOptionPane;
import Model.Pelanggan;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import View.MPelanggan;
import java.util.logging.Level;
import java.sql.PreparedStatement;
import java.util.logging.Logger;

import java.util.List;

/**
 *
 * @author Praktek
 */
public class DAO_Pelanggan implements Model_DAO<Pelanggan>{
    
    //konstruktor
    public DAO_Pelanggan() {
        connection = Database.KoneksiDB();
    }
    
    Connection connection;
    String INSERT = "INSERT INTO pelanggan_2011500333 (KdPlg,NmPlg,AlamatPlg,TelpPlg) values(?,?,?,?)";
    String UPDATE = "UPDATE pelanggan_2011500333 SET NmPlg=?, AlamatPlg=?, TelpPlg=? WHERE KdPlg=?";
    String DELETE = "DELETE FROM pelanggan_2011500333 WHERE KdPlg=?";
    String SELECT = "SELECT * FROM pelanggan_2011500333";
    String CARI = "SELECT *FROM pelanggan_2011500333 WHERE KdPlg LIKE ?";
    String COUNTER = "SELECT max(Kdplg) as kode FROM pelanggan_2011500333";

    @Override
    public int autonumber(Pelanggan object) {
        PreparedStatement statement = null ;
       int nomor = 0;
       try{
           statement = connection.prepareStatement(COUNTER);
           ResultSet rs = statement.executeQuery();
           if(rs.next()){
               nomor=rs.getInt("Kode")+1;
               
           }
               
       }catch (Exception e){
           e.printStackTrace();
       }
    return nomor;
    }

    @Override
    public void insert(Pelanggan object) {
        PreparedStatement statement = null ;
     try{
         statement =  connection.prepareStatement(CARI);
         statement.setInt(1, object.getKode());
         ResultSet rs = statement.executeQuery();
         if(rs.next())
             JOptionPane.showMessageDialog(null,"data sudah pernah disimpan");
     else{
             PreparedStatement statement2 = null ;
             statement2 = connection.prepareStatement(INSERT);
             statement2.setInt(1, object.getKode());
              statement2.setString(2, object.getNama());
              statement2.setString(3, object.getAlamat());
              statement2.setString(4, object.getTelp()); 
             statement2.executeUpdate();
              JOptionPane.showMessageDialog(null,"data berhasil disimpan");
             }
    }catch (Exception e){
    e.printStackTrace();
}finally{
    try{
        statement.close();
    }catch (SQLException ex){
        Logger.getLogger(DAO_Pelanggan.class.getName()).log(Level.SEVERE,null,ex);
    }
}
    }

    @Override
    public void update(Pelanggan object) {
        PreparedStatement statement = null ;
        try{
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, object.getNama());
            statement.setString(2, object.getAlamat());
            statement.setString(3, object.getTelp());
            statement.setInt(4, object.getKode());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null,"data berhasil diubah");
        }catch (Exception e){
    e.printStackTrace();
}finally{
    try{
        statement.close();
    }catch (SQLException ex){
        Logger.getLogger(DAO_Pelanggan.class.getName()).log(Level.SEVERE,null,ex);
    }
    }
    }

    @Override
    public void delete(Integer kode) {
        PreparedStatement statement = null ;
      try{
          statement = connection.prepareStatement(DELETE);
          statement.setInt(1, kode);
          statement.executeUpdate();
          JOptionPane.showMessageDialog(null,"data berhasil dihapus");
      }catch (Exception e){
    e.printStackTrace();
}finally{
    try{
        statement.close();
    }catch (SQLException ex){
        Logger.getLogger(DAO_Pelanggan.class.getName()).log(Level.SEVERE,null,ex);
    }
    }
    }

    @Override
    public List<Pelanggan> getAll() {
        List<Pelanggan> list = null;
        PreparedStatement statement = null ;
        try{
            list = new ArrayList<Pelanggan>();
            statement = connection.prepareStatement(SELECT);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Pelanggan p = new Pelanggan();
                p.setKode(rs.getInt("KdPlg"));
                p.setNama(rs.getString("NmPlg"));
                 p.setAlamat(rs.getString("AlamatPlg"));
                   p.setTelp(rs.getString("TelpPlg"));
                   list.add(p);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Pelanggan> getCari(String key) {
        List<Pelanggan> list = null;
        PreparedStatement statement = null ;
        try{
            list = new ArrayList<Pelanggan>();
            statement = connection.prepareStatement(CARI);
            statement.setString(1, "%"+key+"%");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Pelanggan p = new Pelanggan();
                p.setKode(rs.getInt("KdPlg"));
                p.setNama(rs.getString("NmPlg"));
                 p.setNama(rs.getString("AlamatPlg"));
                   p.setNama(rs.getString("TelpPlg"));
                   list.add(p);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
}
