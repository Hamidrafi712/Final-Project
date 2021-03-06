/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistem.ui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import sistem.helper.ConnectionHelper;

/**
 *
 * @author Rapiii
 */
public class TambahProduk extends javax.swing.JFrame {

    /**
     * Creates new form TambahProduk
     */
    
    //deklarasi variabel
    String formtitle = "";
    int produkId = 0;
    
    public TambahProduk() {
        initComponents();
        setLocationRelativeTo(null);
        
    }
    
    public TambahProduk(String title, int id){
        initComponents();
        setLocationRelativeTo(null);
        tambahProduk.setText(title);
        formtitle = title;
        produkId = id;
        try {
            Connection conn = ConnectionHelper.getConnection(); //digunanakan untuk membangun koneksi
            Statement stm = conn.createStatement(); //digunakan untuk pengiriman statement SQL tanpa parameter, agar bisa melalukan query
            System.out.println(id);
            ResultSet rs = stm.executeQuery("SELECT * FROM barang WHERE id_barang = " + id); //Melalukan ExecuteQuery agar bisa mengambil data dari database lalu disimpan ke variabel rs atau ResultSet
            while (rs.next()) { //Melakukan iterable atau looping bila memiliki datanya di dalam database
                //menset data yang ditambahkann oleh  user
                namaProduk.setText(rs.getString("nama_barang"));
                expTgl.setText(rs.getString("exp_date"));
                stokBrg.setText(rs.getString("jumlah"));
                hargaBrg.setText(rs.getString("harga"));
                kategoriComboBox.setSelectedItem(rs.getString("kategori"));
            }
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }
//        validateExite();       
    }
    
    //method unutk menampilkann informasi berhasil atau tidak menyimpan data buku
    public void showMessage(String message, int type){
        if(type == 1){
            JOptionPane.showMessageDialog(this, message, "Sukses", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, message, "Gagal", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    void updateKonsumen(int id) {
        try {
            Connection conn = ConnectionHelper.getConnection();
            Statement stm = conn.createStatement();
            String query = "UPDATE barang SET nama_barang = '" + namaProduk.getText() + "',"
                    + "exp_date = '" + expTgl.getText()+ "',"
                    + "jumlah = '" + stokBrg.getText() + "',"
                    + "harga = '" + hargaBrg.getText() + "',"
                    + "kategori = '" +kategoriComboBox.getSelectedItem() + "' WHERE id_barang = " + id + " ";

            stm.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data sudah di update ", "infomasi", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data tidak terupdate" + e.getMessage(), "infomasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    //method untuk menguhubungkan antara NB dengan localhost(database)
    void tambah(){
        try{
            Connection conn = ConnectionHelper.getConnection();
            Statement stm = conn.createStatement();
            String query = "INSERT INTO `barang` (`nama_barang`, `exp_date`, `jumlah`, `harga`, `kategori`) "
                    + "VALUES ('"+namaProduk.getText() +"', '"+ expTgl.getText()+ "', '"+ stokBrg.getText()+"', '"+hargaBrg.getText()+"', '"+kategoriComboBox.getSelectedItem() +"' );";
            
            stm.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Data Sudah di Tambahkan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
        
        catch (Exception e){
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        tambahProduk = new javax.swing.JLabel();
        namaProduk = new javax.swing.JTextField();
        hargaBrg = new javax.swing.JTextField();
        stokBrg = new javax.swing.JTextField();
        kategoriComboBox = new javax.swing.JComboBox<>();
        cancelBtn = new javax.swing.JButton();
        sumbitBtn = new javax.swing.JButton();
        expTgl = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setText("Tanggal Exp :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setText("Nama Barang :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setText("Stok Barang :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setText("Harga :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setText(" Kategori :");

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        tambahProduk.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tambahProduk.setForeground(new java.awt.Color(255, 255, 255));
        tambahProduk.setText("Tambah Data Produk");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(tambahProduk)
                .addContainerGap(214, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(tambahProduk)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        namaProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaProdukActionPerformed(evt);
            }
        });

        stokBrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stokBrgActionPerformed(evt);
            }
        });

        kategoriComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SMBK", "ATK" }));

        cancelBtn.setBackground(new java.awt.Color(255, 0, 0));
        cancelBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cancelBtn.setForeground(new java.awt.Color(255, 255, 255));
        cancelBtn.setText("Batal");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        sumbitBtn.setBackground(new java.awt.Color(0, 0, 255));
        sumbitBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        sumbitBtn.setForeground(new java.awt.Color(255, 255, 255));
        sumbitBtn.setText("Simpan");
        sumbitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sumbitBtnActionPerformed(evt);
            }
        });

        expTgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expTglActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel2))
                                        .addGap(1, 1, 1)))
                                .addGap(46, 46, 46)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(hargaBrg, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                                    .addComponent(stokBrg, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                                    .addComponent(namaProduk, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                                    .addComponent(kategoriComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(expTgl)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(256, 256, 256)
                                .addComponent(cancelBtn)
                                .addGap(28, 28, 28)
                                .addComponent(sumbitBtn)))
                        .addGap(0, 72, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(namaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(expTgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(stokBrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hargaBrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(kategoriComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBtn)
                    .addComponent(sumbitBtn))
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void stokBrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stokBrgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stokBrgActionPerformed

    private void namaProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaProdukActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
        new DataProduk().setVisible(true);
        dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void sumbitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sumbitBtnActionPerformed
        // TODO add your handling code here:
        if (produkId == 0) {
            tambah();
        } else {
            updateKonsumen(produkId);
        }
        new DataProduk().setVisible(true);
        dispose();
        
    }//GEN-LAST:event_sumbitBtnActionPerformed

    private void expTglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expTglActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_expTglActionPerformed

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
            java.util.logging.Logger.getLogger(TambahProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TambahProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TambahProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TambahProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TambahProduk().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JTextField expTgl;
    private javax.swing.JTextField hargaBrg;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox<String> kategoriComboBox;
    private javax.swing.JTextField namaProduk;
    private javax.swing.JTextField stokBrg;
    private javax.swing.JButton sumbitBtn;
    private javax.swing.JLabel tambahProduk;
    // End of variables declaration//GEN-END:variables
}
