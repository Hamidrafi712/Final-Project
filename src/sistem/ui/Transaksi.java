/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistem.ui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import sistem.helper.ConnectionHelper;
import sistem.manager.TransaksiManager;
import sistem.manager.NotaManager;
import sistem.model.NotaModel;
import sistem.model.BarangModel;
import sistem.model.TransaksiModel;
import static sistem.ui.DataProduk.listBarangModel;
import static sistem.ui.DataProduk.loadBarang;

/**
 *
 * @author Rapiii
 */
public class Transaksi extends javax.swing.JFrame {

    /**
     * Creates new form Transaksi
     */
    //deklarasi variable
    int transaksiId = 0;
    String sql;

    public Transaksi() {
        initComponents();
        setLocationRelativeTo(null);
        initTableTransaksi();
        initTableNota();
        loadTransaksi();
        loadNota();
    }

    //Dekalrasi variable
    public static DefaultTableModel tableModel;
    public static DefaultTableModel tableNota;
    public static List<TransaksiModel> listTransaksiModel;
    public static List<NotaModel> listNotaModel;

    public void initTableTransaksi() {
        //Deklarasi array bookColumns tipe data String
        String[] transaksiColumns = new String[]{"ID Transaksi", "ID Pelanggan", "ID Barang", "Nama Barang", "Jumlah", "Harga",
            "Total", "Tanggal Tx"};

        int[] columnWidth = {//Deklarasi array columWidth tipe data int
        //75, 75,75,75,75,75,75, 75//lebar tiap kolom
        };

        tableModel = new DefaultTableModel(transaksiColumns, 0);//Deklarasi tableModel 
        transaksiTabel.setModel(tableModel);//Set table model
        transaksiTabel.setRowHeight(20);//set jumlah baris table

        int i = 0;
        for (int width : columnWidth) {
            TableColumn column = transaksiTabel.getColumnModel().getColumn(i++);//set column bookTable
            column.setMaxWidth(width);//set lebar table
            column.setPreferredWidth(width);//set lebar table                
        }
    }

    public void initTableNota() {
        //Deklarasi array bookColumns tipe data String
        String[] notaColumns = new String[]{"ID Transaksi", "ID Pelanggan", "ID Barang", "Nama Barang", "Jumlah", "Harga",
            "Total", "Tanggal Tx"};

        int[] columnWidth = {//Deklarasi array columWidth tipe data int
        //75, 75,75,75,75,75,75, 75//lebar tiap kolom
        };

        tableNota = new DefaultTableModel(notaColumns, 0);//Deklarasi tableModel 
        notaTabel.setModel(tableNota);//Set table model
        notaTabel.setRowHeight(20);//set jumlah baris table

        int i = 0;
        for (int width : columnWidth) {
            TableColumn column = notaTabel.getColumnModel().getColumn(i++);//set column bookTable
            column.setMaxWidth(width);//set lebar table
            column.setPreferredWidth(width);//set lebar table                
        }
    }

    public static void loadTransaksi() {//Method untuk memuat data transaksi
        listTransaksiModel = new ArrayList<>();//Deklarasi arrayList
        listTransaksiModel = TransaksiManager.semuaTransaksi();//lalu nilainya adalah return nilai dari method semua transaksi
        tableModel.setRowCount(0);//set unutk jumlah baris menjadi 0
        listTransaksiModel.forEach(transaksiModel -> {//Melakukan perulangan untuk memasukkan data transaksi
            tableModel.addRow(new Object[]{
                transaksiModel.getId_transaksi(),
                transaksiModel.getId_pelanggan(),
                transaksiModel.getId_barang(),
                transaksiModel.getNama_barang(),
                transaksiModel.getJumlah(),
                transaksiModel.getHarga(),
                transaksiModel.getTotal(),
                transaksiModel.getTanggal_tx()
            });
        });
    }

    public static void loadNota() {//Method untuk memuat data transaksi
        listNotaModel = new ArrayList<>();//Deklarasi arrayList
        listNotaModel = NotaManager.semuaNota();//lalu nilainya adalah return nilai dari method semua transaksi
        tableNota.setRowCount(0);//set unutk jumlah baris menjadi 0
        listNotaModel.forEach(notaTransaksi -> {//Melakukan perulangan untuk memasukkan data transaksi
            tableNota.addRow(new Object[]{
                notaTransaksi.getId_transaksi(),
                notaTransaksi.getId_pelanggan(),
                notaTransaksi.getId_barang(),
                notaTransaksi.getNama_barang(),
                notaTransaksi.getJumlah(),
                notaTransaksi.getHarga(),
                notaTransaksi.getTotal(),
                notaTransaksi.getTanggal_tx()
            });
        });
    }

    public Transaksi(String title, int id) {
        initComponents();
        setLocationRelativeTo(null);
        transaksiId = id;
        try {
            Connection conn = ConnectionHelper.getConnection(); //digunanakan untuk membangun koneksi
            Statement stm = conn.createStatement(); //digunakan untuk pengiriman statement SQL tanpa parameter, agar bisa melalukan query
            System.out.println(id);
            ResultSet rs = stm.executeQuery("SELECT * FROM transaksi WHERE id_transaksi = " + id); //Melalukan ExecuteQuery agar bisa mengambil data dari database lalu disimpan ke variabel rs atau ResultSet
            while (rs.next()) { //Melakukan iterable atau looping bila memiliki datanya di dalam database
                //menset data yang ditambahkann oleh  user
                idPelanggan.setText(rs.getString("id_pelanggan"));
                idBarang.setText(rs.getString("id_barang"));
                namaBarang.setText(rs.getString("nama_barang"));
                jumlahBrg.setText(rs.getString("jumlah"));
                hargaBrg.setText(rs.getString("harga"));
                total.setText(rs.getString("total"));
            }
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }
    }

    //method fungsi untuk kembalian saat melakukan pembayaran
    public void getTotal() {
        try {
            int totalVar = Integer.parseInt(total.getText());
            int bayar = Integer.parseInt(bayarUang.getText());
            int hasil = (bayar - totalVar);
            kembaliUang.setText(String.valueOf(hasil));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    //method fungsi untuk total saat menghitung jumlah barnag dan harga
    public void getTotalSemua() {
        try {
            int jumlah = Integer.parseInt(jumlahBrg.getText());
            int harga = Integer.parseInt(hargaBrg.getText());
            int hasil = (jumlah * harga);
            total.setText(String.valueOf(hasil));
            totalSemua.setText(String.valueOf(hasil));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    void tambah() {
        try {
            //mendapatkan data tanggal sesuao tanggal di komputer(saat transaksi)
            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
            //PrepStmt.setTimestamp(1, date);

            Connection conn = ConnectionHelper.getConnection();
            Statement stm = conn.createStatement();
            String query = "INSERT INTO `transaksi` (`id_pelanggan`,`pelanggan_id_pelanggan`, `id_barang`,`barang_id_barang`, `nama_barang`, `jumlah`, `harga`, `total`,`tanggal_tx`) "
                    + "VALUES ('" + idPelanggan.getText() + "','" + idPelanggan.getText() + "', '" + idBarang.getText() + "','" + idBarang.getText() + "', '" + namaBarang.getText() + "', '" + jumlahBrg.getText() + "', '" + hargaBrg.getText() + "', '" + total.getText() + "', '" + date + "');";

            stm.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data Sudah di Tambahkan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void tambahNota() {
        try {
            //mendapatkan data tanggal sesuao tanggal di komputer(saat transaksi)
            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
            //PrepStmt.setTimestamp(1, date);

            Connection conn = ConnectionHelper.getConnection();
            Statement stm = conn.createStatement();
            String query = "INSERT INTO `nota` (`id_pelanggan`, `id_barang`, `nama_barang`, `jumlah`, `harga`, `total`,`tgl_tx`) "
                    + "VALUES ('" + idPelanggan.getText() + "', '" + idBarang.getText() + "', '" + namaBarang.getText() + "', '" + jumlahBrg.getText() + "', '" + hargaBrg.getText() + "', '" + total.getText() + "', '" + date + "');";

            stm.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data Sudah di Tambahkan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField5 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        totalSemua = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        bayarUang = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        kembaliUang = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        idPelanggan = new javax.swing.JTextField();
        idBarang = new javax.swing.JTextField();
        namaBarang = new javax.swing.JTextField();
        hargaBrg = new javax.swing.JTextField();
        jumlahBrg = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        hitungBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        transaksiTabel = new javax.swing.JTable();
        tambahBtn = new javax.swing.JButton();
        kembaliBtn = new javax.swing.JButton();
        hitungSemua = new javax.swing.JButton();
        cetakBtn = new javax.swing.JButton();
        refreshBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        stokTxt = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        notaTabel = new javax.swing.JTable();
        deleteBtn = new javax.swing.JButton();

        jTextField5.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 5, true));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 102));
        jLabel8.setText("Total Rp :");

        totalSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalSemuaActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 102));
        jLabel9.setText("Bayar Rp :");

        bayarUang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayarUangActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 102));
        jLabel10.setText("Kembalian Rp :");

        kembaliUang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliUangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(kembaliUang))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(totalSemua, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                                    .addComponent(bayarUang)))
                            .addComponent(jLabel10))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalSemua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bayarUang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kembaliUang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setBackground(new java.awt.Color(0, 102, 102));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Transaksi");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(346, 346, 346)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setText("Id Pelanggan :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setText("Id Barang :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setText("Nama Barang :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setText("Jumlah :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setText("Harga :");

        idPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idPelangganActionPerformed(evt);
            }
        });

        idBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idBarangActionPerformed(evt);
            }
        });

        namaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaBarangActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 102));
        jLabel7.setText("Total :");

        total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalActionPerformed(evt);
            }
        });

        hitungBtn.setBackground(new java.awt.Color(0, 102, 102));
        hitungBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        hitungBtn.setForeground(new java.awt.Color(255, 255, 255));
        hitungBtn.setText("Hitung");
        hitungBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitungBtnActionPerformed(evt);
            }
        });

        transaksiTabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Transaksi", "ID Pelanggan", "ID Barang", "Nama Barang", "Jumlah", "Harga", "Total", "Tanggal Tx"
            }
        ));
        jScrollPane1.setViewportView(transaksiTabel);

        tambahBtn.setBackground(new java.awt.Color(0, 0, 255));
        tambahBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tambahBtn.setForeground(new java.awt.Color(255, 255, 255));
        tambahBtn.setText("Tambahkan");
        tambahBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahBtnActionPerformed(evt);
            }
        });

        kembaliBtn.setBackground(new java.awt.Color(255, 0, 0));
        kembaliBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        kembaliBtn.setForeground(new java.awt.Color(255, 255, 255));
        kembaliBtn.setText("Kembali");
        kembaliBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliBtnActionPerformed(evt);
            }
        });

        hitungSemua.setBackground(new java.awt.Color(0, 102, 102));
        hitungSemua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        hitungSemua.setForeground(new java.awt.Color(255, 255, 255));
        hitungSemua.setText("Hitung");
        hitungSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitungSemuaActionPerformed(evt);
            }
        });

        cetakBtn.setBackground(new java.awt.Color(0, 0, 255));
        cetakBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cetakBtn.setForeground(new java.awt.Color(255, 255, 255));
        cetakBtn.setText("Cetak");
        cetakBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakBtnActionPerformed(evt);
            }
        });

        refreshBtn.setBackground(new java.awt.Color(0, 102, 102));
        refreshBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        refreshBtn.setForeground(new java.awt.Color(255, 255, 255));
        refreshBtn.setText("Refresh");
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });

        jButton1.setText("Cari");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 102));
        jLabel11.setText("Stok :");

        notaTabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Transaksi", "ID Pelanggan", "ID Barang", "Nama Barang", "Jumlah", "Harga", "Total", "Tanggal Tx"
            }
        ));
        jScrollPane2.setViewportView(notaTabel);

        deleteBtn.setBackground(new java.awt.Color(0, 102, 102));
        deleteBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(hitungBtn)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                            .addComponent(idBarang)
                            .addComponent(namaBarang)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jumlahBrg)
                                    .addComponent(hargaBrg, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(stokTxt))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(tambahBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(hitungSemua)
                        .addGap(18, 18, 18)
                        .addComponent(cetakBtn)
                        .addGap(43, 43, 43))))
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(kembaliBtn)
                    .addComponent(refreshBtn)
                    .addComponent(deleteBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(idPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(idBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, Short.MAX_VALUE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(namaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jumlahBrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(stokTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(hargaBrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hitungBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tambahBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hitungSemua)
                            .addComponent(cetakBtn))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(deleteBtn)
                        .addGap(52, 52, 52)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(refreshBtn)
                        .addGap(18, 18, 18)
                        .addComponent(kembaliBtn)
                        .addGap(11, 11, 11)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void idPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idPelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idPelangganActionPerformed

    private void idBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idBarangActionPerformed

    private void namaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaBarangActionPerformed

    private void totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_totalActionPerformed

    private void tambahBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahBtnActionPerformed
        // TODO add your handling code here:
        if (transaksiId == 0) {
            tambah();
            tambahNota();
        } else {
        }

        Integer stokbarang, jumlah, sisa;
        stokbarang = Integer.parseInt(stokTxt.getText());
        jumlah = Integer.parseInt(jumlahBrg.getText());

        sisa = (stokbarang - jumlah);
        try {
            Connection conn = ConnectionHelper.getConnection(); //digunakan untuk mendapatkan komeksi dengan database
            Statement stmn = conn.createStatement(); //digunakan untuk pengiriman statement SQL tanpa parameter, agar bisa melalukan query
            stmn.executeUpdate("UPDATE barang SET jumlah = '" + sisa + "' WHERE id_barang = " + idBarang.getText());
        } catch (Exception e) {
            System.out.println("ERROR" + e.getMessage());
        }

        idPelanggan.setText("");
        idBarang.setText("");
        namaBarang.setText("");
        jumlahBrg.setText("");
        hargaBrg.setText("");
        total.setText("");
        totalSemua.setText("");
        bayarUang.setText("");
        kembaliUang.setText("");
    }//GEN-LAST:event_tambahBtnActionPerformed

    private void kembaliBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliBtnActionPerformed
        // TODO add your handling code here:
        new Beranda().setVisible(true);
        dispose();
    }//GEN-LAST:event_kembaliBtnActionPerformed

    private void totalSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalSemuaActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_totalSemuaActionPerformed

    private void bayarUangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayarUangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bayarUangActionPerformed

    private void kembaliUangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliUangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kembaliUangActionPerformed

    private void cetakBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakBtnActionPerformed
        // TODO add your handling code here:
        try {
            JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("NotaPembelian.jasper"), null, ConnectionHelper.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_cetakBtnActionPerformed

    private void hitungSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitungSemuaActionPerformed
        // TODO add your handling code here:
        getTotal();

    }//GEN-LAST:event_hitungSemuaActionPerformed

    private void hitungBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitungBtnActionPerformed
        // TODO add your handling code here:
        getTotalSemua();
    }//GEN-LAST:event_hitungBtnActionPerformed

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) transaksiTabel.getModel();
        model.setRowCount(0);
        loadTransaksi();
        loadNota();
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new CariBarang().setVisible(true);
        dispose();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = notaTabel.getSelectedRow();
        if (selectedRow == -1) {
            showMessage("Pilihlah Baris Terlebih Dahulu", 2);
        } else {
            int option = JOptionPane.showConfirmDialog(this, "Apakah anda yakin akan menghapus data ini?", "Hapus Data", JOptionPane.WARNING_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                try {
                    Connection conn = ConnectionHelper.getConnection(); //digunakan untuk mendapatkan komeksi dengan database
                    Statement stmn = conn.createStatement(); //digunakan untuk pengiriman statement SQL tanpa parameter, agar bisa melalukan query
                    NotaModel nota = listNotaModel.get(selectedRow);
                    stmn.executeUpdate("DELETE FROM nota WHERE id_transaksi = " + nota.getId_transaksi());
                    loadNota();
                } catch (Exception e) {
                    System.out.println("ERROR" + e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bayarUang;
    private javax.swing.JButton cetakBtn;
    private javax.swing.JButton deleteBtn;
    public javax.swing.JTextField hargaBrg;
    private javax.swing.JButton hitungBtn;
    private javax.swing.JButton hitungSemua;
    public javax.swing.JTextField idBarang;
    public javax.swing.JTextField idPelanggan;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField5;
    public javax.swing.JTextField jumlahBrg;
    private javax.swing.JButton kembaliBtn;
    private javax.swing.JTextField kembaliUang;
    public javax.swing.JTextField namaBarang;
    private javax.swing.JTable notaTabel;
    private javax.swing.JButton refreshBtn;
    public javax.swing.JTextField stokTxt;
    private javax.swing.JButton tambahBtn;
    private javax.swing.JTextField total;
    private javax.swing.JTextField totalSemua;
    private javax.swing.JTable transaksiTabel;
    // End of variables declaration//GEN-END:variables

    private void showMessage(String pilihlah_Baris_Terlebih_Dahulu, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
