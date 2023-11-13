package client;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class ThreadClientCode {

    private String username;
    private Socket socket;
    private BufferedReader bufferReader;
    private BufferedWriter bufferWriter;
    private NewJFrame jf;

    public ThreadClientCode(Socket socket, String username) {
        try {
            this.socket = socket;
            this.bufferWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
            bufferWriter.write(username);
            bufferWriter.newLine();
            bufferWriter.flush();
            this.recevoirMessage();
        } catch (IOException e) {
            fermer(socket, bufferReader, bufferWriter);
        }
        this.jf = new NewJFrame(bufferWriter, bufferReader, username, socket);
        jf.setVisible(true);
    }

    // lire les messages du serveur

    public void recevoirMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String message;
                while (socket.isConnected()) {
                    try {
                        message = bufferReader.readLine();
                        if (message != null) {
                            String[] data = message.split(",");
                            if (data[0].equals("$*£/$585£%/*55954%")) {
                                System.out.println("j'ai reçu");
                                jf.clearUserArea();
                                for (int k = 1; k < data.length; k++) {
                                    jf.afficheruser(data[k]);
                                }
                            } else {
                                jf.afficherMessage(message);
                            }
                        }
                    } catch (IOException e) {
                        fermer(socket, bufferReader, bufferWriter);
                    }
                }
            }
        }).start();
    }

    public void fermer(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnknownHostException, IOException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
    }
}