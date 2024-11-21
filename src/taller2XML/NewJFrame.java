/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package taller2XML;

import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author martin
 */
public class NewJFrame extends javax.swing.JFrame {

  DefaultTableModel modelo;
     
    public NewJFrame() {
    initComponents(); 
    modelo = (DefaultTableModel) jTable1.getModel();
    modelo.setRowCount(0);
    cargarXML();
    }

    
    private void guardarXML() {
        try {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.newDocument();

        // Nodo raíz
        Element root = documento.createElement("productos");
        documento.appendChild(root);

    
        for (int i = 0; i < modelo.getRowCount(); i++) {
            // Validar que no haya valores nulos
            if (modelo.getValueAt(i, 0) == null || modelo.getValueAt(i, 1) == null ||
                modelo.getValueAt(i, 2) == null || modelo.getValueAt(i, 3) == null) {
                JOptionPane.showMessageDialog(this, "Error: Hay celdas vacías en la fila " + (i + 1) + ".");
                return;
            }

          
            String nombre = modelo.getValueAt(i, 0).toString();
            String codigo = modelo.getValueAt(i, 1).toString();
            String precio = modelo.getValueAt(i, 2).toString();
            String categoria = modelo.getValueAt(i, 3).toString();

           
            Element producto = documento.createElement("producto");

            Element nombreElement = documento.createElement("nombre");
            nombreElement.appendChild(documento.createTextNode(nombre));

            Element codigoElement = documento.createElement("codigo");
            codigoElement.appendChild(documento.createTextNode(codigo));

            Element precioElement = documento.createElement("precio");
            precioElement.appendChild(documento.createTextNode(precio));

            Element categoriaElement = documento.createElement("categoria");
            categoriaElement.appendChild(documento.createTextNode(categoria));

            producto.appendChild(nombreElement);
            producto.appendChild(codigoElement);
            producto.appendChild(precioElement);
            producto.appendChild(categoriaElement);
            root.appendChild(producto);
        }

        
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        File output = new File("productos.xml");
        transformer.transform(new DOMSource(documento), new StreamResult(output));
        JOptionPane.showMessageDialog(this, "Archivo XML guardado exitosamente.");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al guardar XML: " + e.getMessage());
    }
    }
    
    
    
    private void cargarXML() {
        try {
            File archivo = new File("productos.xml");
            if (!archivo.exists()) return; 

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(archivo);
            documento.getDocumentElement().normalize();

            NodeList listaProductos = documento.getElementsByTagName("producto");

            for (int i = 0; i < listaProductos.getLength(); i++) {
                Node nodo = listaProductos.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nodo;

                    String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
                    String codigo = elemento.getElementsByTagName("codigo").item(0).getTextContent();
                    String precio = elemento.getElementsByTagName("precio").item(0).getTextContent();
                    String categoria = elemento.getElementsByTagName("categoria").item(0).getTextContent();

                    modelo.addRow(new Object[]{nombre, codigo, precio, categoria});
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar XML: " + e.getMessage());
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

        campocodigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        campoprecio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        campocategoria = new javax.swing.JTextField();
        botonagregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        camponombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("Precio del producto");

        jLabel4.setText("Categoria del producto");

        botonagregar.setText("Agregar");
        botonagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonagregarActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Producto", "Codigo", "Precio", "Categoria"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Nombre del Producto");

        jLabel2.setText("Codigo del Producto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(campocategoria))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(campocodigo)
                                    .addComponent(camponombre)
                                    .addComponent(campoprecio, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(397, 397, 397)
                        .addComponent(botonagregar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(camponombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(campocodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(campoprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(campocategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(botonagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonagregarActionPerformed
    String nombre = camponombre.getText();
    String codigo = campocodigo.getText();
    String precio = campoprecio.getText();
    String categoria = campocategoria.getText();

    if (nombre.isEmpty() || codigo.isEmpty() || precio.isEmpty() || categoria.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
        return;
    }

    modelo.addRow(new Object[]{nombre, codigo, precio, categoria});
    guardarXML();

    camponombre.setText("");
    campocodigo.setText("");
    campoprecio.setText("");
    campocategoria.setText("");

    }//GEN-LAST:event_botonagregarActionPerformed



  
    private void eliminarProducto() {
        int filaSeleccionada = jTable1.getSelectedRow();
        if (filaSeleccionada >= 0) {
            modelo.removeRow(filaSeleccionada);
            guardarXML();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar.");
        }
    }
    
    
    private void modificarProducto() {
        int filaSeleccionada = jTable1.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String nombre = JOptionPane.showInputDialog("Nombre:", modelo.getValueAt(filaSeleccionada, 0));
            String codigo = JOptionPane.showInputDialog("Código:", modelo.getValueAt(filaSeleccionada, 1));
            String precio = JOptionPane.showInputDialog("Precio:", modelo.getValueAt(filaSeleccionada, 2));
            String categoria = JOptionPane.showInputDialog("Categoría:", modelo.getValueAt(filaSeleccionada, 3));

            modelo.setValueAt(nombre, filaSeleccionada, 0);
            modelo.setValueAt(codigo, filaSeleccionada, 1);
            modelo.setValueAt(precio, filaSeleccionada, 2);
            modelo.setValueAt(categoria, filaSeleccionada, 3);

            guardarXML();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para modificar.");
        }
    }










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
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonagregar;
    private javax.swing.JTextField campocategoria;
    private javax.swing.JTextField campocodigo;
    private javax.swing.JTextField camponombre;
    private javax.swing.JTextField campoprecio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
