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
import sistem.model.BarangModel;

/**
 *
 * @author user
 */
public class BarangManager {
    public static List<BarangModel> semuaBarang(String q){
    List<BarangModel> barangModelList = new ArrayList<>();
    
        try {
            Connection con = ConnectionHelper.getConnection();//menginisiasikan conn dan memngggil method getConnection dari class ConenectionHelper
            Statement stmn = con.createStatement();//pemanggilan statement untuk melakukan pemanggilan statement sql agar bisa melakukann query
            ResultSet rs = stmn.executeQuery("select * from barang WHERE nama_barang LIKE '%"+q+"%' ");//Melalukan ExecuteQuery agar bisa mengambil data dari database lalu disimpan ke variabel rs atau ResultSet dan melakukan pencarian
            
            while (rs.next()) { //Mengecek dan melakukan perulangan bila ada data
               //mengeset properti dari model
               //yang datanya diambil dari database
               BarangModel barangModel = new BarangModel();
               
                barangModel.setId_barang(Integer.parseInt(rs.getString("id_barang")));
                barangModel.setNama_barang(rs.getString("nama_barang"));
                barangModel.setExp_date(rs.getString("exp_date"));
                barangModel.setJumlah(rs.getInt("jumlah"));
                barangModel.setHarga(rs.getInt("harga"));
                barangModel.setKategori(String.valueOf(rs.getString("kategori")));
               
               barangModelList.add(barangModel);//Menambahkan propnerti kedalam barang modoel list
            }
        } catch (SQLException e) {//Menangkap error apablila terjadi error
            Logger.getLogger(CheckConnection.class.getName()).log(Level.SEVERE, null, e); //melakukan logging apabila ada error didalam class CheckConnection 
        }
    return barangModelList;//Mengembalikan nilai barang model list
    }
}
