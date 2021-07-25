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
import sistem.model.PelangganModel;

/**
 *
 * @author user
 */
public class PelangganManager {
    
     public static List<PelangganModel> semuaPelanggan(){
    List<PelangganModel> pelangganModelList = new ArrayList<>();
    
        try {
            Connection con = ConnectionHelper.getConnection();//menginisiasikan conn dan memngggil method getConnection dari class ConenectionHelper
            Statement stmn = con.createStatement();//pemanggilan statement untuk melakukan pemanggilan statement sql agar bisa melakukann query
            ResultSet rs = stmn.executeQuery("select * from pelanggan");//Melalukan ExecuteQuery agar bisa mengambil data dari database lalu disimpan ke variabel rs atau ResultSet
            
            while (rs.next()) { //Mengecek dan melakukan perulangan bila ada data
               //mengeset properti didalam class model
               //yang datanya diambil dari database
               PelangganModel pelangganModel = new PelangganModel();
               
                 pelangganModel.setId_pelanggan(Integer.parseInt(rs.getString("id_pelanggan")));
                 pelangganModel.setNama(rs.getString("nama"));
                 pelangganModel.setNo_telp(rs.getInt("no_telp"));
                 pelangganModel.setAlamat(rs.getString("alamat"));
               
               pelangganModelList.add(pelangganModel);//Menambahkan propnerti kedalam barang modoel list
            }
        } catch (SQLException e) {//Menangkap error apablila terjadi error
            Logger.getLogger(CheckConnection.class.getName()).log(Level.SEVERE, null, e); //melakukan logging apabila ada error didalam class CheckConnection 
        }
    return pelangganModelList;//Mengembalikan nilai barang model list
    }
}
