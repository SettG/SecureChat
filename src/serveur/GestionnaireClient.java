package serveur;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;


public class GestionnaireClient implements Runnable {

    public static ArrayList<GestionnaireClient> gestClient = new ArrayList<>();
    private String username;
    private Socket socket;
    private BufferedReader bufferreader;
    private BufferedWriter bufferwriter;
    private HashMap<String,Key> dictionnaireDeCle=new HashMap<>();


    public GestionnaireClient(Socket socket) {

        try {
            this.socket = socket;
            this.bufferwriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferreader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = bufferreader.readLine();

            // Utiliser ObjectInputStream pour recevoir l'objet clé
            ObjectInputStream objectInputStream = new ObjectInputStream(this.socket.getInputStream());
            Key key = (Key) objectInputStream.readObject();

            this.dictionnaireDeCle.put(this.username,key);

            gestClient.add(this);
            messagedeConnection("Serveur : " + this.username + " : " + "est arrivé sur le serveur");


        } catch (IOException e) {
            fermer(socket, bufferreader, bufferwriter);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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
                    Key key = dictionnaireDeCle.get(this.username);
                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.ENCRYPT_MODE, key);
                    byte[] data = message.getBytes();
                    byte[] result = cipher.doFinal(data);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(result);
                    objectOutputStream.writeObject(key);
                    objectOutputStream.flush();

                }
            } catch (IOException e) {
                fermer(socket, bufferreader, bufferwriter);
            } catch (NoSuchPaddingException e) {
                throw new RuntimeException(e);
            } catch (IllegalBlockSizeException e) {
                throw new RuntimeException(e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (BadPaddingException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeyException e) {
                throw new RuntimeException(e);
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
