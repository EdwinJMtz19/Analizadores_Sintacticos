
package analizador_lexico;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Vista extends javax.swing.JFrame {
private static DefaultTableModel modelo;
    ArrayList<Token> tokensd = new ArrayList<Token>();
    public Vista() {
        initComponents();
        this.getContentPane().setBackground(Color.WHITE);
        String[] nombresColumnas = {"TOKEN", "VALOR"};
        modelo=new DefaultTableModel(nombresColumnas,0);
        tToken.setBackground(Color.WHITE); 
        tToken.setModel(modelo); 
        
        Formato();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tToken = new javax.swing.JTable();
        BTNAceptar = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        btncero = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tpPanel = new javax.swing.JTextPane();
        cargar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(java.awt.Color.white);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("ANALIZADOR SINTÁCTICO");

        jLabel2.setText(" ");

        tToken.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tToken.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tToken.setAlignmentY(1.0F);
        tToken.setGridColor(new java.awt.Color(255, 255, 255));
        tToken.setRowHeight(24);
        tToken.setShowHorizontalLines(false);
        jScrollPane1.setViewportView(tToken);

        BTNAceptar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        BTNAceptar.setText("ANALIZAR");
        BTNAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNAceptarActionPerformed(evt);
            }
        });

        scrollPane.setOpaque(false);
        scrollPane.setRequestFocusEnabled(false);

        txtArea.setColumns(20);
        txtArea.setFont(new java.awt.Font("Monospaced", 0, 22)); // NOI18N
        txtArea.setRows(5);
        scrollPane.setViewportView(txtArea);

        btncero.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btncero.setText("RESTABLECER");
        btncero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnceroActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(tpPanel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        cargar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cargar.setText("CARGAR");
        cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton1.setText("GUARDAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BTNAceptar)
                                .addGap(48, 48, 48)
                                .addComponent(btncero))
                            .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cargar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(375, 375, 375)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNAceptar)
                    .addComponent(btncero)
                    .addComponent(cargar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel2)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void Formato(){
        JPanel lineNumberPanel = new JPanel();
        lineNumberPanel.setLayout(new BoxLayout(lineNumberPanel, BoxLayout.Y_AXIS));
        lineNumberPanel.setBackground(Color.GRAY);
    txtArea.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { updateLineNumbers(); }
            public void removeUpdate(DocumentEvent e) { updateLineNumbers(); }
            public void changedUpdate(DocumentEvent e) { updateLineNumbers(); }

            private void updateLineNumbers() {
                lineNumberPanel.removeAll();
                int lines = txtArea.getLineCount();
                for (int i = 1; i <= lines; i++) {
                    JLabel label = new JLabel(String.valueOf(i));
                    label.setFont(new Font("Monospaced", Font.PLAIN, 22));
                    lineNumberPanel.add(label);
                }
                lineNumberPanel.revalidate();
                lineNumberPanel.repaint();
            }
        });
    scrollPane.setRowHeaderView(lineNumberPanel);
    
    }
    private void BTNAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNAceptarActionPerformed
        DefaultStyledDocument doc = new DefaultStyledDocument();  
        tpPanel.setDocument(doc);
        StyledDocument style = tpPanel.getStyledDocument();
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setFontFamily(attributes, "Arial"); 
        StyleConstants.setFontSize(attributes, 20); 
        
        tokensd.clear();
        String[] lineas = txtArea.getText().split("\\n");
        int l=0;
        modelo.setRowCount(0);        
        for (String cadena : lineas) {
                   String[] partes =cadena.split("(?=\\s)|(?<=\\s)|(?=[.=])|(?<=[.=])");
                   l++;
                   for(String token:partes){
                        Agrega(token,l);
                   }
                Analizador_sintactico analiza=new Analizador_sintactico();
                    if (analiza.parseAsignacion(tokensd)){
                        continue;
                        } else{
                        
                        try {
                                StyleConstants.setForeground(attributes, Color.RED);
                                doc.insertString(doc.getLength(), "\nlINEA INVÁLIDA "+l, attributes); // Inserta al final del documento
                            } catch (Exception e) {
                                System.out.println("error");
                            }
                       
                    } 
                    tokensd.clear();
                    
                }
       
            
    
    
    }//GEN-LAST:event_BTNAceptarActionPerformed

    private void btnceroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnceroActionPerformed
    tokensd.clear();
    modelo.setRowCount(0);
    txtArea.setText("");
    tpPanel.setText("");
    
    }//GEN-LAST:event_btnceroActionPerformed

    private void cargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null); 
        if (result == JFileChooser.APPROVE_OPTION) {
       java.io.File selectedFile = fileChooser.getSelectedFile();
       try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
           StringBuilder text = new StringBuilder();
           String line;
           while ((line = reader.readLine()) != null) {
                    line = line.trim(); 
                    text.append(line).append("\n"); 
                }
           
           txtArea.setText(text.toString());
       } catch (Exception e) {
          System.out.println("Error");
          

       }
   }

    }//GEN-LAST:event_cargarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
             String contenido = txtArea.getText(); 
             String rutaArchivo = ""; 

JFileChooser fileChooser = new JFileChooser();
int seleccion = fileChooser.showSaveDialog(null); 
if (seleccion == JFileChooser.APPROVE_OPTION) {
    File archivo = fileChooser.getSelectedFile();
    rutaArchivo = archivo.getAbsolutePath();
}

 if (!rutaArchivo.isEmpty()) { 
   try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(rutaArchivo), StandardCharsets.UTF_8))) {
        bw.write(contenido.trim());
    }catch (Exception ex) {
                       System.out.println("Error al guardar el archivo");
    }
} else {
    System.out.println("No se seleccionó ningún archivo."); 
        }
 
    }//GEN-LAST:event_jButton1ActionPerformed

@SuppressWarnings("empty-statement")
    public void Agrega(String dato,int linea){
        Object[] fila={};
        boolean matched=false;
  
    for (Token.Tipos tokenTipo : Token.Tipos.values()) {
                Pattern patron = Pattern.compile(tokenTipo.patron);
                Matcher m = patron.matcher(dato);
               
                if(m.matches()) {
                    
                    Token tk = new Token();
                    tk.setTipo(tokenTipo);
                    tk.setValor(dato);
                    tokensd.add(tk);
                    if(tokenTipo!=Token.Tipos.ESPACIO)
                    fila=new Object[]{tokenTipo,dato};
                    matched = true;
                    break;
                }
            }
     if(!matched) {
                    Token tk = new Token();
                    tk.settoken("INVALIDO");
                    tk.setValor(dato);
                    tokensd.add(tk);
                fila=new Object[]{"TOKEN NO RECONOCIDO",dato};
                }
     modelo.insertRow(modelo.getRowCount(), fila);
      
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
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNAceptar;
    private javax.swing.JButton btncero;
    private javax.swing.JButton cargar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable tToken;
    private javax.swing.JTextPane tpPanel;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables
}
