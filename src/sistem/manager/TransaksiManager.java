/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistem.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistem.helper.CheckConnection;
import sistem.helper.ConnectionHelper;
import sistem.model.TransaksiModel;

/**
 *
 * @author user
 */
public class TransaksiManager {
    public static List<TransaksiModel> semuaTransaksi(){
    List<TransaksiModel> transaksiModelList = new ArrayList<>();
    
        try {
            Connection con = ConnectionHelper.getConnection();//menginisiasikan conn dan memngggil method getConnection dari class ConenectionHelper
            Statement stmn = con.createStatement();//pemanggilan statement untuk melakukan pemanggilan statement sql agar bisa melakukann query
            ResultSet rs = stmn.executeQuery("select * from transaksi");//Melalukan ExecuteQuery agar bisa mengambil data dari database lalu disimpan ke variabel rs atau ResultSet
            
            while (rs.next()) { //Mengecek dan melakukan perulangan bila ada data
               //mengeset properti didalam class buku
               //yang datanya diambil dari database
               TransaksiModel transaksiModel = new TransaksiModel();
               
                transaksiModel.setId_transaksi(Integer.parseInt(rs.getString("id_transaksi")));
                transaksiModel.setId_pelanggan(rs.getInt("id_pelanggan"));
                transaksiModel.setId_barang(rs.getInt("id_barang"));
                transaksiModel.setNama_barang(rs.getString("nama_barang"));
                transaksiModel.setJumlah(rs.getInt("jumlah"));
                transaksiModel.setHarga(rs.getInt("harga"));
                transaksiModel.setTotal(rs.getInt("total"));
                transaksiModel.setTanggal_tx(rs.getString("tanggal_tx"));
               
               transaksiModelList.add(transaksiModel);//Menambahkan propnerti kedalam barang modoel list
            }
        } catch (SQLException e) {//Menangkap error apablila terjadi error
            Logger.getLogger(CheckConnection.class.getName()).log(Level.SEVERE, null, e); //melakukan logging apabila ada error didalam class CheckConnection 
        }
    return transaksiModelList;//Mengembalikan nilai barang model list
    }
}
