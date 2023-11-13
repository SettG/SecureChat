package serveur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class GestionnaireClient implements Runnable {

    public static ArrayList<GestionnaireClient> gestClient = new ArrayList<>();

    private String username;
    private Socket socket;
    private BufferedReader bufferreader;
    private BufferedWriter bufferwriter;


    public GestionnaireClient(Socket socket) {

        try {
            this.socket = socket;
            this.bufferwriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferreader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = bufferreader.readLine();
            gestClient.add(this);
            messagedeConnection("Serveur : " + this.username + " : " + "est arrivé sur le serveur");


        } catch (IOException e) {
            fermer(socket, bufferreader, bufferwriter);
        }

    }
    public void afficherUser() {
        String s = "$*£/$585£%/*55954%,";
        for (GestionnaireClient u : gestClient) {
            s += u.username + ",";
        }
        messagedeConnection(s);

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        String messageduClient;
        while (socket.isConnected()) {
            try {
                messageduClient = bufferreader.readLine();
                if (messageduClient != null)
                    messagedeConnection(" " + this.username + " : " + messageduClient);
                else {
                    fermer(socket, bufferreader, bufferwriter);
                }
            } catch (IOException e) {
                fermer(socket, bufferreader, bufferwriter);
                break;
            }
        }
    }

    public void messagedeConnection(String message) {
        for (GestionnaireClient client : gestClient) {
            try {
                if (!client.username.equals(username)) {
                    client.bufferwriter.write(message);
                    client.bufferwriter.newLine();
                    client.bufferwriter.flush();

                }
            } catch (IOException e) {
                fermer(socket, bufferreader, bufferwriter);
            }
        }
    }

    public void supprimerClient() {
        gestClient.remove(this);
        messagedeConnection("Serveur: " + username + " a_quitté_le_chat");
        afficherUser();
    }

    public void fermer(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        supprimerClient();
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
}
