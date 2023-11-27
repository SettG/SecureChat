package client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.DefaultCaret;

public class NewJFrame extends javax.swing.JFrame implements ActionListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public ArrayList<String> userenligne = new ArrayList<>();
    BufferedWriter bufferWriter;
    String username;
    Socket socket;

    public NewJFrame(ObjectOutputStream objectOutputStream, BufferedWriter bufferWriter, BufferedReader bufferReader, String username, Socket socket) {
        this.bufferWriter = bufferWriter;
        this.username = username;
        this.socket = socket;
        initComponents();
    }

    private void initComponents() {

        useronline = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areauser = new javax.swing.JTextArea();
        deconnection = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        area = new javax.swing.JTextArea();
        zonesaisie = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        Menu = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Interface de communication");
        setResizable(false);

        zonesaisie.addActionListener(this);

        useronline.setBackground(new java.awt.Color(102, 102, 102));
        useronline.setText("Utilisateurs Connectés");
        useronline.setToolTipText("");

        areauser.setEditable(false);
        areauser.setColumns(20);
        areauser.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        areauser.setRows(5);
        jScrollPane1.setViewportView(areauser);

        deconnection.setText("Déconnection");
        deconnection.addActionListener(this);
        deconnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    deconnectionActionPerformed(evt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        area.setEditable(false);
        area.setColumns(20);
        area.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        area.setLineWrap(true);
        area.setRows(5);
        area.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                areaKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(area);

        zonesaisie.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        Menu.setText("Préférences");

        jMenu1.setText("Thème");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("Clair");
        jRadioButtonMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jRadioButtonMenuItem1ActionPerformed(evt);
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
            }
        });
        jMenu1.add(jRadioButtonMenuItem1);

        jRadioButtonMenuItem2.setText("Sombre");
        jRadioButtonMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jRadioButtonMenuItem2ActionPerformed(evt);
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
            }
        });
        jRadioButtonMenuItem2.addActionListener(this);
        jRadioButtonMenuItem1.addActionListener(this);
        jMenu1.add(jRadioButtonMenuItem2);

        Menu.add(jMenu1);

        jMenuBar1.add(Menu);

        setJMenuBar(jMenuBar1);

        DefaultCaret caret = (DefaultCaret) area.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup().addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(zonesaisie, javax.swing.GroupLayout.PREFERRED_SIZE, 558,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 558,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(useronline)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup().addGap(34, 34, 34)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(22, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                .createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(deconnection).addGap(51, 51, 51)))))));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 322,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup().addGap(12, 12, 12).addComponent(useronline)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18).addComponent(deconnection)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(zonesaisie,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE)));

        pack();
    }

    private void deconnectionActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
    }

    private void jRadioButtonMenuItem1ActionPerformed(java.awt.event.ActionEvent evt)
            throws UnsupportedLookAndFeelException {

    }

    private void jRadioButtonMenuItem2ActionPerformed(java.awt.event.ActionEvent evt)
            throws UnsupportedLookAndFeelException {

    }

    private void areaKeyTyped(java.awt.event.KeyEvent evt) {

    }

    // Variables
    private javax.swing.JMenu Menu;
    private javax.swing.JTextArea area;
    private javax.swing.JTextArea areauser;
    private javax.swing.JButton deconnection;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel useronline;
    private javax.swing.JTextField zonesaisie;
    public String msstoSend = null;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == zonesaisie && zonesaisie.getText().isBlank() == false) {
            String message = e.getActionCommand();
            int heure = java.time.LocalTime.now().getHour();
            int minutes = java.time.LocalTime.now().getMinute();
            if(message.equals("bye")) {
                System.exit(0);
            }
            try {
                this.bufferWriter.write(message);
                this.bufferWriter.newLine();
                this.bufferWriter.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            area.append("|" + heure + ":" + minutes + "|" + " " + this.username + " " + message + "\n");
            zonesaisie.setText("");
        }
        if (e.getSource() == jRadioButtonMenuItem2) {
            jRadioButtonMenuItem1.setSelected(false);
            jRadioButtonMenuItem2.setSelected(true);
            try {
                // set up du Dark Nimbus Look and Feel
            } catch (Exception ex) {
                System.out.println("erreur d'import du modele graphique");
            }
        }
        if (e.getSource() == jRadioButtonMenuItem1) {
            jRadioButtonMenuItem2.setSelected(false);
            jRadioButtonMenuItem1.setSelected(true);
            try {
                // set up du Dark Nimbus Look and Feel
            } catch (Exception ex) {
                System.out.println("erreur d'import du modele graphique");
            }
        }
        if (e.getSource() == deconnection) {
            System.exit(0);
        }
    }

    public void afficheruser(String user) {
        this.areauser.append(user);
        this.areauser.append("\n");

    }

    public void clearUserArea() {
        this.areauser.setText("");
    }

    public void afficherMessage(String msg) {

        int heure = java.time.LocalTime.now().getHour();
        int minutes = java.time.LocalTime.now().getMinute();
        this.area.append("|" + heure + ":" + minutes + "|" + msg + "\n");

    }

    public static void main(String args[]) throws UnknownHostException, IOException {

    }
}