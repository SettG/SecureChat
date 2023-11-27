package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class InterfaceUsernameClient extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private String file = ("monde.png");
    private JTextField txtEdit = new JTextField("Entrez votre username");
    private JTextField zoneEntree = new JTextField("");
    private JTextField PORT = new JTextField("Entrez le port de connexion");
    private JButton button = new JButton("Connexion");
    private String username = "";
    private String portdeConnection = "";
    private ImageIcon back;
    private JLabel user;
    private JLabel background;
    private JLabel port;
    public JTextArea area;
    public ImageIcon image;
    public String pathToFile = ("/test.png");
    private static InterfaceUsernameClient i;
    public String messtoSend = new String();
    public String messtoAffiche;
    private Socket sock;
    private String ip = "192.168.43.69";

    public InterfaceUsernameClient() throws UnsupportedLookAndFeelException {
        super("Interface de connection");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(600, 400));
        this.setResizable(false);
        try {

            this.back = new ImageIcon(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // centrer la fenetre
        this.setLocationRelativeTo(null);
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(null);

        this.button.setBounds(21, 206, 160, 30);
        contentPane.add(this.button);
        this.button.addActionListener(this);

        this.txtEdit.setBounds(21, 148, 199, 30);
        this.txtEdit.addActionListener(this);
        contentPane.add(this.txtEdit);

        this.PORT.setBounds(21, 89, 199, 30);
        this.PORT.addActionListener(this);
        contentPane.add(this.PORT);

        this.background = new JLabel(this.back, JLabel.CENTER);
        this.background.setBounds(0, 0, 600, 400);

        this.user = new JLabel("Vous devez entrer un username", JLabel.CENTER);
        this.user.setBounds(6, 178, 230, 16);
        contentPane.add(user);
        this.user.setVisible(false);
        this.user.setForeground(Color.RED);
        this.user.setFont(new Font("italic", Font.ITALIC, 12));

        this.port = new JLabel("Vous devez entrer un port", JLabel.CENTER);
        this.port.setBounds(6, 120, 230, 16);
        contentPane.add(this.port);
        this.port.setVisible(false);
        this.port.setForeground(Color.RED);
        this.port.setFont(new Font("italic", Font.ITALIC, 12));

        contentPane.add(this.background);

    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        // Import du dernier look graphique de Java
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        i = new InterfaceUsernameClient();
        i.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == this.zoneEntree && event.getActionCommand().isBlank() == false) {
            this.area.append(this.zoneEntree.getText() + "\n");
            this.messtoSend = "";
            this.messtoSend = event.getActionCommand();
            System.out.println(this.zoneEntree.getText());
            this.zoneEntree.setText("");
        }
        if (event.getSource() == this.txtEdit) {
            this.username = event.getActionCommand();
            System.out.println("Username validé :" + this.username);
            this.user.setVisible(false);
        } else if (event.getSource() == PORT) {
            this.portdeConnection = event.getActionCommand();
            System.out.println("Port de connection :" + portdeConnection);
            this.port.setVisible(false);

        } else if (event.getSource().equals(this.button) && this.username.isBlank() == true) {
            this.user.setVisible(true);
        } else if (event.getSource().equals(this.button) && this.portdeConnection.isBlank() == true) {
            this.port.setVisible(true);
        } else if (event.getSource().equals(this.button)) {
            try {
                this.sock = new Socket(this.ip, Integer.parseInt(this.portdeConnection));
                this.fermer();
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void fermer() throws UnsupportedLookAndFeelException, UnknownHostException, IOException {
        this.setVisible(false);
        ThreadClientCode client = new ThreadClientCode(this.sock, this.username);
        client.recevoirMessage();

    }

}