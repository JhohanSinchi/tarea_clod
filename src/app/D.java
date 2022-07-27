package app;

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.Conexion;

public class D extends javax.swing.JFrame {

    String destino = "C:/appjava/tmp/";

    public D() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCargar = new javax.swing.JButton();
        jpImagen = new javax.swing.JPanel();
        btnMostrar = new javax.swing.JButton();
        lblImagen = new javax.swing.JLabel();
        btnCargarDisco = new javax.swing.JButton();
        btnMostrarDisco = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnCargar.setText("Cargar BD");
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpImagenLayout = new javax.swing.GroupLayout(jpImagen);
        jpImagen.setLayout(jpImagenLayout);
        jpImagenLayout.setHorizontalGroup(
            jpImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
        );
        jpImagenLayout.setVerticalGroup(
            jpImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 139, Short.MAX_VALUE)
        );

        btnMostrar.setText("Mostrar BD");
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });

        lblImagen.setText("jLabel1");

        btnCargarDisco.setText("Cargar Disco");
        btnCargarDisco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarDiscoActionPerformed(evt);
            }
        });

        btnMostrarDisco.setText("Mostrar Disco");
        btnMostrarDisco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarDiscoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnCargar)
                        .addGap(18, 18, 18)
                        .addComponent(btnMostrar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCargarDisco)
                        .addGap(18, 18, 18)
                        .addComponent(btnMostrarDisco))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jpImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104)
                        .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jpImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCargar)
                    .addComponent(btnMostrar)
                    .addComponent(btnCargarDisco)
                    .addComponent(btnMostrarDisco))
                .addGap(55, 55, 55))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed

        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.PNG", "png");
        fc.setFileFilter(filtro);
        int seleccion = fc.showOpenDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fc.getSelectedFile();

            PreparedStatement ps;
            ResultSet rs;
            Conexion objCon = new Conexion();

            try {

                FileInputStream fis = new FileInputStream(fichero);

                Connection con = objCon.getConexion();
                ps = con.prepareStatement("INSERT INTO datos (imagen) VALUES (?)");
                ps.setBinaryStream(1, fis, (int) fichero.length());
                ps.execute();
                JOptionPane.showMessageDialog(null, "Imagen Guardada");

            } catch (HeadlessException | FileNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al Guardar Imagen");
                System.out.println(ex);
            }
        }
    }//GEN-LAST:event_btnCargarActionPerformed

    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed
        PreparedStatement ps;
        ResultSet rs;
        Conexion objCon = new Conexion();

        try {
            Connection con = objCon.getConexion();
            ps = con.prepareStatement("SELECT imagen FROM datos WHERE id=?");
            ps.setInt(1, 1);
            rs = ps.executeQuery();

            BufferedImage buffimg = null;
            byte[] image = null;
            while (rs.next()) {
                image = rs.getBytes("imagen");
                InputStream img = rs.getBinaryStream(1); // reading image as InputStream
                try {
                    buffimg = ImageIO.read(img);
                    ImagenMySQL imagen = new ImagenMySQL(jpImagen.getHeight(), jpImagen.getWidth(), buffimg);
                    jpImagen.add(imagen);
                    jpImagen.repaint();
                } catch (IOException ex) {
                    Logger.getLogger(D.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            Image img = Toolkit.getDefaultToolkit().createImage(image);
            ImageIcon icon = new ImageIcon(img);
            lblImagen.setIcon(icon);

            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }//GEN-LAST:event_btnMostrarActionPerformed

    private void btnCargarDiscoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarDiscoActionPerformed

        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.PNG", "png");
        fc.setFileFilter(filtro);
        int seleccion = fc.showOpenDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fc.getSelectedFile();
            String ruta = fichero.getAbsolutePath();
            String nombreArchivo = fichero.getName();
            String archivo = destino + "" + nombreArchivo;

            File folder = new File(destino);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            copyFile_Java7(ruta, archivo);

        }
    }//GEN-LAST:event_btnCargarDiscoActionPerformed

    private void btnMostrarDiscoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarDiscoActionPerformed

        File dir = new File(destino);
        String[] ficheros = dir.list();
        if (ficheros == null) {
            JOptionPane.showMessageDialog(null, "No hay ficheros en el directorio especificado");
        } else {
            int x = jpImagen.getWidth();
            int y = jpImagen.getHeight();

            ImagenExterna Imagen = new ImagenExterna(x, y, destino + "" + ficheros[0]);
            jpImagen.add(Imagen);
            jpImagen.repaint();

        }

    }//GEN-LAST:event_btnMostrarDiscoActionPerformed

    public static void copyFile_Java7(String origen, String destino) {
        try {
            Path FROM = Paths.get(origen);
            Path TO = Paths.get(destino);
            CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
            };

            Files.copy(FROM, TO, options);
            JOptionPane.showMessageDialog(null, "Imagen Guardada");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al Guardar Imagen");
            System.err.println(e.toString());
        }
    }

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
            java.util.logging.Logger.getLogger(D.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(D.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(D.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(D.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new D().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnCargarDisco;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JButton btnMostrarDisco;
    private javax.swing.JPanel jpImagen;
    private javax.swing.JLabel lblImagen;
    // End of variables declaration//GEN-END:variables
}
