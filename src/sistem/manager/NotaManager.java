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
import sistem.model.NotaModel;
import sistem.model.TransaksiModel;
/**
 *
 * @author Rapiii
 */
public class NotaManager {
    public static List<NotaModel> semuaNota(){
    List<NotaModel> notaModelList = new ArrayList<>();
    
        try {
            Connection con = ConnectionHelper.getConnection();//menginisiasikan conn dan memngggil method getConnection dari class ConenectionHelper
            Statement stmn = con.createStatement();//pemanggilan statement untuk melakukan pemanggilan statement sql agar bisa melakukann query
            ResultSet rs = stmn.executeQuery("select * from nota");//Melalukan ExecuteQuery agar bisa mengambil data dari database lalu disimpan ke variabel rs atau ResultSet
            
            while (rs.next()) { //Mengecek dan melakukan perulangan bila ada data
               //mengeset properti didalam class model
               //yang datanya diambil dari database
               NotaModel notaModel = new NotaModel();
               
                notaModel.setId_transaksi(Integer.parseInt(rs.getString("id_transaksi")));
                notaModel.setId_pelanggan(rs.getInt("id_pelanggan"));
                notaModel.setId_barang(rs.getInt("id_barang"));
                notaModel.setNama_barang(rs.getString("nama_barang"));
                notaModel.setJumlah(rs.getInt("jumlah"));
                notaModel.setHarga(rs.getInt("harga"));
                notaModel.setTotal(rs.getInt("total"));
                notaModel.setTanggal_tx(rs.getString("tgl_tx"));
               
               notaModelList.add(notaModel);//Menambahkan propnerti kedalam barang modoel list
            }
        } catch (SQLException e) {//Menangkap error apablila terjadi error
            Logger.getLogger(CheckConnection.class.getName()).log(Level.SEVERE, null, e); //melakukan logging apabila ada error didalam class CheckConnection 
        }
    return notaModelList;//Mengembalikan nilai barang model list
    }
}
