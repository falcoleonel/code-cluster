
package chatclient;
import chatserver.ChatServer;
import chatserver.ChatServerInterface;
import java.rmi.Naming;
import javax.swing.JOptionPane;

public class ChatClientUI extends javax.swing.JFrame {
/*PAGINA 11*/
    // Referencia al client que utiliza esta UI
private ChatClient client;
// Referencia al servidor
private ChatServerInterface server;
// Variable que indica si el cliente está conectado
private boolean isConnected;
private int flag;

    public ChatClientUI() {
        initComponents();
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTxtName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTxtServerIP = new javax.swing.JTextField();
        jBtnConnect = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTxtLog = new javax.swing.JTextArea();
        jTxtMessage = new javax.swing.JTextField();
        jBtnSend = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Nombre Usuario:");

        jTxtName.setText("ICHI");
        jTxtName.setToolTipText("");
        jTxtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtNameActionPerformed(evt);
            }
        });

        jLabel2.setText("IP Del Servidor:");

        jTxtServerIP.setText("192.168.84.171");
        jTxtServerIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtServerIPActionPerformed(evt);
            }
        });

        jBtnConnect.setText("Conectar");
        jBtnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnConnectActionPerformed(evt);
            }
        });

        jTxtLog.setColumns(20);
        jTxtLog.setRows(5);
        jScrollPane1.setViewportView(jTxtLog);

        jBtnSend.setText("Enviar");
        jBtnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTxtMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnSend))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTxtName)
                            .addComponent(jTxtServerIP, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnConnect)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTxtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTxtServerIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jBtnConnect, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnSend))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
public void writeMessage(String message){
    jTxtLog.setText(jTxtLog.getText() + "\n" + message);
}
    private void jBtnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnConnectActionPerformed
        // TODO add your handling code here:
        if(flag!=1){
            try {
                System.out.println("yo");
                
                client = new ChatClientClass(jTxtName.getText());
                System.out.println("Todo bien Se guardo el nombre");
                
                client.setGUI(this);
                System.out.println("Todo bien Setup del GUI");
                
                server = (ChatServerInterface) Naming.lookup("rmi://" + java.net.InetAddress.getLocalHost().getHostAddress() + "/ChatService");
                //192.168.84.167
                //server = (ChatServerInterface) Naming.lookup("rmi://192.168.1.84/ChatService");
                //server = (ChatServerInterface) Naming.lookup("//192.168.84.167:1099/ChatService");
                //server = (ChatServerInterface) Naming.lookup("rmi://192.168.84.167/ChatService");
                //server = (ChatServerInterface) Naming.lookup("rmi://192.168.84.173/ChatService");
                System.out.println("Todo bien Se Conecto, ahora logear");
                
                server.login(client);
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(this, "Error, no se pudo conectar.");
            }
            
            flag = 1;
        }else{
        if (!isConnected) {
            if (jTxtName.getText().length()==0) {
                JOptionPane.showMessageDialog(this, "Debes escribir un nombre");
                return;
            }
            if (jTxtServerIP.getText().length()==0) {
                JOptionPane.showMessageDialog(this, "Debes escribir una IP.");
                return;
            }
            try {
                System.out.println("Todo bien Lego nombre");
                System.out.println(jTxtName.getText());
                
                client = new ChatClientClass(jTxtName.getText());
                System.out.println("Todo bien Se guardo el nombre");
                
                client.setGUI(this);
                System.out.println("Todo bien Setup del GUI");
                
                server = (ChatServerInterface) Naming.lookup("rmi://" + jTxtServerIP.getText().replaceAll("\\s+","") + "/ChatService");
                //192.168.84.167
                //server = (ChatServerInterface) Naming.lookup("rmi://192.168.1.84/ChatService");
                //server = (ChatServerInterface) Naming.lookup("//192.168.84.167:1099/ChatService");
                //server = (ChatServerInterface) Naming.lookup("rmi://192.168.84.167/ChatService");
                //server = (ChatServerInterface) Naming.lookup("rmi://192.168.84.173/ChatService");
                System.out.println("Todo bien Se Conecto, ahora logear");
                
                boolean lol = server.login(client);
                
                jBtnConnect.setText("Desconectar");
                isConnected = true;
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(this, "Error, no se pudo conectar.");
            }
        } 
        else {
            try {
                server.logout(client);
            } catch (Exception ex) {
                System.err.println("Error al desconectar.");
            }
            jBtnConnect.setText("Conectar");
            isConnected = false;
        }
        }
    }//GEN-LAST:event_jBtnConnectActionPerformed

    private void jBtnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSendActionPerformed
        if (!isConnected) {
            JOptionPane.showMessageDialog(this, "Debes conectarte primero");
            return;
        }
        String message = jTxtMessage.getText();
        message = jTxtName.getText() + ": " + message;
        jTxtMessage.setText("");
        try {
                                                        server.publish(message, client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jBtnSendActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        System.out.println("Adios");
        try {
            if (isConnected)
                server.logout(client);
        } catch (Exception ex) {
            System.err.println("Error al desconectar");
       }

    }//GEN-LAST:event_formWindowClosing

    private void jTxtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtNameActionPerformed

    private void jTxtServerIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtServerIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtServerIPActionPerformed

    public static void main(String args[]) {
        
        
        try{
            // Inicia el rmiregistry en el puerto 1099 (Puerto por defecto)
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            // Enlaza un objeto ChatServer al nombre ChatService en el registro.
            ChatServerInterface cs = new ChatServer();
            Naming.rebind( "ChatService", cs);
            //Naming.rebind( "//192.168.0.11:1234/ChatService", cs);
            System.out.println("El back end del cliente esta listo.");
            System.out.println(java.net.InetAddress.getLocalHost().getHostAddress());
           // System.out.println(getLocalHost().getHostAddress())
           
        } catch (Exception e){
            System.err.println("El back end del cliente fallo: " + e);
        }
        
       
    
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatClientUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnConnect;
    private javax.swing.JButton jBtnSend;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTxtLog;
    private javax.swing.JTextField jTxtMessage;
    private javax.swing.JTextField jTxtName;
    private javax.swing.JTextField jTxtServerIP;
    // End of variables declaration//GEN-END:variables
}
