/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.Archivo;
import Model.MixPot;
import RMI.InterfaceConsultar;
import RMI.InterfaceDescargar;
import Rmi.descargas;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.AccessException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author williambaquero
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    
    private final String SI_EXISTE = "Encontrado";
    private final String NO_EXISTE = "No encontrado";
    private final String CREADO = "Archivo generado";
    private final int TAM_ESTANDAR = 8;
    
    private List<File> listaArchivos = new ArrayList<>();
    private HashMap<String, List<File>> hashMapArchivos = new HashMap<>();
    
    InterfaceConsultar consultar;
    List<Archivo> combinaciones;
    List<Archivo> archivos;
    
    public Principal() throws NotBoundException, MalformedURLException, RemoteException, IOException {
        InterfaceDescargar descarga = new descargas();
        //CAMBIOS!!!!
        Registry registros = LocateRegistry.createRegistry(12756);
        registros.rebind("rDescargar", descarga);
        archivos = new ArrayList();
        Registry registro = LocateRegistry.getRegistry("192.168.43.131",12743);
        this.consultar = (InterfaceConsultar) registro.lookup("rConsultar");
        
        initComponents();
        cargarArchivos();
        cargarTabla();
    }

    public void cargarArchivos() throws RemoteException, FileNotFoundException, IOException{
        //Poner codigo de guardar archivo en la lista
        String cadena;
        ArrayList<String> informacion = new ArrayList<String>();
        String archivos[]={
//           "prueba1.txt",
//            "prueba2.txt",
//            "prueba3.txt",
            "prueba4.txt",
            "prueba5.txt"
//            "prueba6.txt"
//            "prueba7.txt",
//            "prueba8.txt",
//            "prueba9.txt",
//            "prueba10.txt",
//            "prueba11.txt",
//            "prueba12.txt"
        };
         for(int i = 0 ; i<archivos.length ; i++){
           Archivo aux = new Archivo(archivos[i]);
           FileReader f = new FileReader(archivos[i]);
           BufferedReader b = new BufferedReader(f);
           while((cadena = b.readLine())!=null) {
               informacion.add(cadena);
           }
           aux.contenido = informacion;
           this.archivos.add(aux);
           b.close();
         } 
        for(Archivo aux:this.archivos){
            //this.modelo.addRow(new Object[]{"hola"});
            consultar.guardarArchivo(aux.getNombre(), "192.168.43.131");
        }
    }
    void cargarTabla(){
       clearTable();
       DefaultTableModel modelo = (DefaultTableModel) tablaArchivos.getModel();
       for(Archivo aux:this.archivos){
            modelo.addRow(new Object[]{aux.getNombre()});
        }
       tablaArchivos.setModel(modelo);
    }
    
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel) tablaArchivos.getModel();
        for (int i = 0; i < model.getRowCount() ; i++) {
            model.removeRow(i);
        }
        tablaArchivos.setModel(model);
        CBArchivos.removeAllItems();
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaArchivos = new javax.swing.JTable();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel4 = new javax.swing.JPanel();
        CBArchivos = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnExaminar = new javax.swing.JToggleButton();
        btnSubir = new javax.swing.JToggleButton();
        jLabel5 = new javax.swing.JLabel();
        labelSelected = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableFiles = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        nameGenerate = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        tablaArchivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Archivos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaArchivos);
        if (tablaArchivos.getColumnModel().getColumnCount() > 0) {
            tablaArchivos.getColumnModel().getColumn(0).setResizable(false);
        }

        jToggleButton1.setText("Generar  recapitulativo");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(116, Short.MAX_VALUE)
                .addComponent(jToggleButton1)
                .addGap(94, 94, 94))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToggleButton1)
                .addGap(0, 48, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Archivos Locales", jPanel2);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Archivo");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Lucida Grande", 2, 13)); // NOI18N
        jButton1.setText("Descargar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Ruta Archivo");

        btnExaminar.setText("Examinar");
        btnExaminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExaminarActionPerformed(evt);
            }
        });

        btnSubir.setText("Subir");
        btnSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirActionPerformed(evt);
            }
        });

        jLabel5.setText("Archivo seleccionado: ");

        labelSelected.setText("-");

        tableFiles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Nombre", "Existencia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableFiles);

        jLabel3.setText("Nombre archivo a generar: ");

        nameGenerate.setText("-");

        jButton2.setText("Generar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExaminar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSubir, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(31, 31, 31)
                                .addComponent(nameGenerate))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(50, 50, 50)
                                .addComponent(labelSelected)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CBArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(37, 37, 37)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CBArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnExaminar)
                    .addComponent(btnSubir))
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(labelSelected))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nameGenerate))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Descarga", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        boolean varPrueba = false;
        
        try {
            String archivoSel = CBArchivos.getSelectedItem().toString();
            List<String> maquina = this.consultar.obtenerDireccion(archivoSel);
            List<String> contFinal = new ArrayList();
            int numLineas = 8/maquina.size();
            int auxNum = 0;
            for(int i=0 ; i<maquina.size() ; i++){
                if(varPrueba){
                    List<String> contDescargado = new ArrayList();
                    //CAMBIOS!!!!
                    Registry registre = LocateRegistry.getRegistry(maquina.get(i),12757);
                    InterfaceDescargar descargar = (InterfaceDescargar) registre.lookup("rDescargar");
                    contDescargado = descargar.descargarLineas(auxNum, auxNum+numLineas, archivoSel);
                for(String p:contDescargado){
                    contFinal.add(p);
                }
                    
                }else{
                List<String> contDescargado = new ArrayList();
                //CAMBIOS!!!!
                Registry registre = LocateRegistry.getRegistry(maquina.get(i),12757);
                InterfaceDescargar descargar = (InterfaceDescargar) registre.lookup("rDescargar");
                contDescargado = descargar.descargarLineas(auxNum, auxNum+numLineas, archivoSel);
                for(String p:contDescargado){
                    if (!p.contains("prueba")) {
                        varPrueba=true;
                        System.err.println("Algo pasa, una anomalia");
                    }else{
                        contFinal.add(p);
                        System.out.println(p);
                    }

                }
                    if(!varPrueba){
                    auxNum +=numLineas;
                    }else{
                        
                    }
                
                }
            }
            Archivo finall = new Archivo(archivoSel);
            finall.contenido = contFinal;
            this.archivos.add(finall);
            
        } catch (RemoteException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        try {
            clearTable();
            cargarTabla();
            List<String> aux = consultar.ArchivosRepositorio();
            for(String i:aux){
                CBArchivos.addItem(i);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
       try {
        String cadena;
        ArrayList<String> informacion = new ArrayList<String>();
        String archivos[]={"1.txt","2.txt","3.txt","4.txt","5.txt","6.txt"};
         for(int i = 0 ; i<archivos.length ; i++){
           Archivo aux = new Archivo(archivos[i]);
           FileReader f;
           f = new FileReader(archivos[i]);
           BufferedReader b = new BufferedReader(f);
           while((cadena = b.readLine())!=null) {
               informacion.add(cadena);
           }
           aux.contenido = informacion;
           this.combinaciones.add(aux);
           b.close();
         }
         int i = 1;
        for(Archivo aux:this.combinaciones){
            String ruta = "salida"+i+".txt";
            File archivo = new File(ruta);
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(archivo));
            int partes = 8/aux.contenido.size();
            int camino = 0;
            for(int y=0; y<aux.contenido.size();y++){
                for(Archivo j:this.archivos){
                    if(j.nombre.equals(aux.contenido.get(y))){     
                        if(archivo.exists()) {
                            for(int q=camino;q<camino+partes;q++){
                                bw.write(j.contenido.get(q));
                            }
                        } else {
                            for(int q=camino;q<camino+partes;q++){
                                bw.write(j.contenido.get(q));
                            }
                        }
                        camino += 2; 
                    }
                }
                bw.close();
            }
            i++;
        }
         } catch (FileNotFoundException ex) {
               Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
           } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
      /*
        try {
            formarArchivoGenerado();
        } catch (IOException ex) {
            System.out.println("No se pudo descargar el archivo" + ex);
        }*/
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        List<String> nom = new ArrayList();
        for(Archivo i : this.archivos){
            nom.add(i.getNombre());
        }
        try {
            this.consultar.eliminarArchivos("192.168.43.131", nom);
        } catch (RemoteException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void btnExaminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExaminarActionPerformed

        clearTables();
        selectFile();
        
    }//GEN-LAST:event_btnExaminarActionPerformed

    private void btnSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirActionPerformed

        for (int i = 0; i < hashMapArchivos.get(nameGenerate.getText()).size(); i++) {
            System.out.println(hashMapArchivos.get(nameGenerate.getText()).get(i).getName());
        }
    }//GEN-LAST:event_btnSubirActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            for (int i = 1; i <= 20; i++) {
                formarArchivoGenerado(i);
                
                File file = new File("ArchivoGenerado.txt");

                // File (or directory) with new name
                File file2 = new File("ArchivoGenerado" + i + ".txt");

                if (file2.exists())
                   file.renameTo(file2);
                // Rename file (or directory)
                boolean success = file.renameTo(file2);

                if (!success) {
                   // File was not successfully renamed
                }
            }
        } catch (IOException ex) {
            System.out.println("No se pudo descargar el archivo" + ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    //Limpiar información sucia de las tablas
    public void clearTables (){
        DefaultTableModel model = (DefaultTableModel) tableFiles.getModel();
        for (int i = 0; i < model.getRowCount() ; i++) {
            model.removeRow(i);
        }

/*        
        model = (DefaultTableModel) tableDownloads.getModel();
        for (int i = 0; i < model.getRowCount() ; i++) {
            model.removeRow(i);
        }
        model.setRowCount(0);
        */
    }
    
    //Escoger un archivo 
    public void selectFile() {
        JFileChooser chooser = new JFileChooser("./");
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            
            try {
                readFile(f);
            } catch (IOException ex) {
                System.out.println("Problema leyendo el arvhivo");
            }
            
        } else {
            // user changed their mind
        }
    }
    
    //Leer archivo
    private void readFile(File file) throws FileNotFoundException, IOException{
        
        listaArchivos.clear();
        hashMapArchivos.clear();
        
        BufferedReader br = new BufferedReader(new FileReader(file.getName()));
        DefaultTableModel model = (DefaultTableModel) tableFiles.getModel();
        model.setRowCount(0);
        labelSelected.setText(file.getName());
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            int countLine = 0;

            while (line != null) {
                if(countLine++==0)nameGenerate.setText(line + ".txt");
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
                if (line!=null) {
                    //System.out.println(line);
                    File f = new File("./"+line+".txt");
                    if(f.exists() && !f.isDirectory()) { 
                        model.addRow(new Object[]{line, SI_EXISTE});
                    }else{
                        model.addRow(new Object[]{line, NO_EXISTE});
                    }
                    listaArchivos.add(f);
                    hashMapArchivos.put(nameGenerate.getText(), listaArchivos);
                }
            }
            
            btnSubir.setEnabled(isSubidaOk() ? true : false);
            
            String completo =  sb.toString();
            
        } finally {
            br.close();
        }    
    }
    
    //Comprobar si todos los archivos dentro del archivo descriptor existen
    public boolean isSubidaOk(){
        DefaultTableModel model = (DefaultTableModel) tableFiles.getModel(); 
        for (int i = 0; i < model.getRowCount(); i++) if (model.getValueAt(i, 1).equals(NO_EXISTE)){ hashMapArchivos.clear(); return false;};
        
        return true;
    }
    
    //Generar el archivo para ser descargado 
    public void formarArchivoGenerado(int concatenar) throws IOException{
        
        String nameFile = NO_EXISTE;

        for(Map.Entry<String, List<File>> entry : hashMapArchivos.entrySet()) {
            String key = entry.getKey();
            nameFile = key;
        }
        
        if (!nameFile.equals(NO_EXISTE)) {
            List<String> lines = mixFiles(nameFile);
            String[] strarray = new String[lines.size()];
            lines.toArray(strarray);
            lines = Arrays.asList(strarray);
            Path file = Paths.get(nameFile);
            Files.write(file, lines, Charset.forName("UTF-8"));
            Date dNow = new Date( );
            SimpleDateFormat ft = new SimpleDateFormat ("hh:mm:ss a");
            this.consultar.guardarArchivo(nameFile,"192.168.43.131");
            //DefaultTableModel model = (DefaultTableModel) tableDownloads.getModel(); 
            //model.addRow(new Object[]{nameFile, CREADO,ft.format(dNow)});
            
            System.out.println(" -> Archivo creado con éxito!");
            
        }else{
            System.err.println("No existe fuente para descargar archivos.");
            System.err.println("(Ya probaste subir archivos primero?)");
        }
        

    }
    
    //Con los archivos existentes generar el nuevo
    public List<String> mixFiles(String nameFile) throws FileNotFoundException, IOException{
        
        List<MixPot> listaMixer = new ArrayList<>();
        List<String> lineasArchivos = new ArrayList<>();
        int tamToMix = hashMapArchivos.get(nameFile).size();
        
        for (int i = 0; i < TAM_ESTANDAR ; i++) {
            int elElegido = (int) (Math.random() * tamToMix) + 0;
            MixPot mix = new MixPot(i, hashMapArchivos.get(nameFile).get(elElegido));
            listaMixer.add(mix);
        }
        
        
        for (int i = 0; i < TAM_ESTANDAR ; i++) {
            
            try(BufferedReader br = new BufferedReader(new FileReader(listaMixer.get(i).getArchivo().getName()))) {
                int contador = 0;
                for(String line; (line = br.readLine()) != null; ) {
                    
                    if (listaMixer.get(i).getLinea() == contador) {
                        String agregar = line;
                        lineasArchivos.add(agregar);
                    }
                    
                    contador++;
                    
                }
                // line is not visible here.
            }
            
        }
                
        return lineasArchivos;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]){
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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                try {
                    new Principal().setVisible(true);
                } catch (NotBoundException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBArchivos;
    private javax.swing.JToggleButton btnExaminar;
    private javax.swing.JToggleButton btnSubir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel labelSelected;
    private javax.swing.JLabel nameGenerate;
    private javax.swing.JTable tablaArchivos;
    private javax.swing.JTable tableFiles;
    // End of variables declaration//GEN-END:variables
}
