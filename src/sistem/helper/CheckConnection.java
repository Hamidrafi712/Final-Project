/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistem.helper;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author user
 */
public class CheckConnection {
    public static void main(String[] args) { //deklarasi main method
        isConnected();//Memanggil Method isConnetced
        tampilBarang();//Memanggil Method tampilBarang
        tampilPelanggan();//Memanggil method tampilPelanggan
        tampilTransaksi();//Memanggil method tampilTransaksi
    }
    
    private static boolean isConnected(){//dibuat untuk menangani eror yang mungkin terjadi saat proses koneksi dengan database
        try { //membuat try-catch untuk menangani error
            ConnectionHelper.getConnection(); // memanggil class connectionhelper untuk mendapatkan koneksi, jika berhasil maka
            System.out.println("Database COnnected"); //print 
            return true;
        }
        catch(SQLException ex){
            Logger.getLogger(CheckConnection.class.getName()).log(Level.SEVERE, null, ex); //untuk melakukan logging untuk pencatatan error
            System.out.println("Failed to connected database"); //print
            return false; //mengembalikan nilai false bila gagal konek 
        }
    }
    
    public static void tampilBarang() {
        try {//membuat try-catch untuk menangani error
            Connection conn = ConnectionHelper.getConnection(); //digunakan untuk mendapatkan komeksi dengan database
            Statement stmn = conn.createStatement(); //digunakan untuk pengiriman statement SQL tanpa parameter, agar bisa melalukan query
            ResultSet rs = stmn.executeQuery("Select * barang"); //Melalukan ExecuteQuery agar bisa mengambil data dari database //lalu disimpan ke variabel rs atau ResultSet
            
            while (rs.next()){ //Melakukan iterable atau looping bila memiliki datanya di dalam database
                System.out.println
                         ("ID Barang         : " + rs.getString("id_barang")
                        +"\nNama Barang      : "+rs.getString("nama_barang")
                        +"\nTanggal Ex       : "+rs.getString("exp_date")
                        +"\nJumlah           : "+rs.getString("jumlah")
                        +"\nHarga            : "+rs.getString("harga")
                        +"\nKategori         : "+rs.getString("kategori"));
            }
        }
        catch (SQLException ex){//menggunakan SQLException untuk menangkap error
            Logger.getLogger(CheckConnection.class.getName()).log(Level.SEVERE, null, ex); //untuk melakukan logging untuk pencatatan error
        }
    }
    
    public static void tampilPelanggan() {
        try {//membuat try-catch untuk menangani error
            Connection conn = ConnectionHelper.getConnection(); //digunakan untuk mendapatkan komeksi dengan database
            Statement stmn = conn.createStatement(); //digunakan untuk pengiriman statement SQL tanpa parameter, agar bisa melalukan query
            ResultSet rs = stmn.executeQuery("Select * pelanggan"); //Melalukan ExecuteQuery agar bisa mengambil data dari database //lalu disimpan ke variabel rs atau ResultSet
            
            while (rs.next()){ //Melakukan iterable atau looping bila memiliki datanya di dalam database
                System.out.println
                         ("ID Pelanggan         : " + rs.getString("id_pelanggan")
                        +"\nNama Pelanggan      : "+rs.getString("nama")
                        +"\nNo Telp             : "+rs.getString("no_telp")
                        +"\nAlamat              : "+rs.getString("alamat"));
            }
        }
        catch (SQLException ex){//menggunakan SQLException untuk menangkap error
            Logger.getLogger(CheckConnection.class.getName()).log(Level.SEVERE, null, ex); //untuk melakukan logging untuk pencatatan error
        }
    }
    
    public static void tampilTransaksi(){
        try {//membuat try-catch untuk menangani error
            Connection conn = ConnectionHelper.getConnection(); //digunakan untuk mendapatkan komeksi dengan database
            Statement stmn = conn.createStatement(); //digunakan untuk pengiriman statement SQL tanpa parameter, agar bisa melalukan query
            ResultSet rs = stmn.executeQuery("Select * transaksi"); //Melalukan ExecuteQuery agar bisa mengambil data dari database //lalu disimpan ke variabel rs atau ResultSet
            
            while (rs.next()){ //Melakukan iterable atau looping bila memiliki datanya di dalam database
                System.out.println
                         ("ID Transaksi         : " + rs.getString("id_transaksi")
                        +"\nID Pelanggan     : "+rs.getString("id_pelanggan")
                        +"\nID Barang        : "+rs.getString("id_barang")         
                        +"\nNama Barang      : "+rs.getString("nama_barang")
                        +"\nTanggal Tx       : "+rs.getString("tanggal_tx")
                        +"\nJumlah           : "+rs.getString("jumlah")
                        +"\nHarga            : "+rs.getString("harga")
                        +"\nTotal         : "+rs.getString("total"));
            }
        }
        catch (SQLException ex){//menggunakan SQLException untuk menangkap error
            Logger.getLogger(CheckConnection.class.getName()).log(Level.SEVERE, null, ex); //untuk melakukan logging untuk pencatatan error
        }
    }
    
    public static void tampilNota(){
        try {//membuat try-catch untuk menangani error
            Connection conn = ConnectionHelper.getConnection(); //digunakan untuk mendapatkan komeksi dengan database
            Statement stmn = conn.createStatement(); //digunakan untuk pengiriman statement SQL tanpa parameter, agar bisa melalukan query
            ResultSet rs = stmn.executeQuery("Select * nota"); //Melalukan ExecuteQuery agar bisa mengambil data dari database //lalu disimpan ke variabel rs atau ResultSet
            
            while (rs.next()){ //Melakukan iterable atau looping bila memiliki datanya di dalam database
                System.out.println
                         ("ID Transaksi         : " + rs.getString("id_transaksi")
                        +"\nID Pelanggan     : "+rs.getString("id_pelanggan")
                        +"\nID Barang        : "+rs.getString("id_barang")         
                        +"\nNama Barang      : "+rs.getString("nama_barang")
                        +"\nTanggal Tx       : "+rs.getString("tgl_tx")
                        +"\nJumlah           : "+rs.getString("jumlah")
                        +"\nHarga            : "+rs.getString("harga")
                        +"\nTotal         : "+rs.getString("total"));
            }
        }
        catch (SQLException ex){//menggunakan SQLException untuk menangkap error
            Logger.getLogger(CheckConnection.class.getName()).log(Level.SEVERE, null, ex); //untuk melakukan logging untuk pencatatan error
        }
    }
}
