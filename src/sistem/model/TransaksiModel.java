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
public class TransaksiModel {
    //deklarasi variable
    private int id_transaksi;
    private int id_pelanggan;
    private int id_barang;
    private String nama_barang;
    private int jumlah;
    private int harga;
    private int total;
    private String tanggal_tx;
    
    public TransaksiModel(){
        
    }

    //melakukan getter and setter
    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public int getId_pelanggan() {
        return id_pelanggan;
    }

    public void setId_pelanggan(int id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public int getId_barang() {
        return id_barang;
    }

    public void setId_barang(int id_barang) {
        this.id_barang = id_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTanggal_tx() {
        return tanggal_tx;
    }

    public void setTanggal_tx(String tanggal_tx) {
        this.tanggal_tx = tanggal_tx;
    }
    
    
}
