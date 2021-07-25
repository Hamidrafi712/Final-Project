/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistem.model;

/**
 *
 * @author Rapiii
 */
public class PelangganModel {
    //deklarasi
    private int id_pelanggan;
    private String nama;
    private int no_telp;
    private String alamat;
    
    public PelangganModel(){
        
    }
    
    //melakukan getter and setter
    public int getId_pelanggan() {
        return id_pelanggan;
    }

    public void setId_pelanggan(int id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(int no_telp) {
        this.no_telp = no_telp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    
}
