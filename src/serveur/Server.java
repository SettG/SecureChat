    package serveur;

    import java.io.IOException;
    import java.net.ServerSocket;
    import java.net.Socket;

    public class Server {
        private ServerSocket ss;

        public Server(ServerSocket ss) {
            this.ss = ss;
        }

        public void startServeur() {
            // il le programme est bloqué jusqu'à ce qu'un client se connecte
            try {
                while (!ss.isClosed()) {
                    Socket socket = ss.accept();
                    System.out.println("Un client est arrivé");
                    GestionnaireClient gestClient = new GestionnaireClient(socket);
                    Thread thread = new Thread(gestClient);
                    thread.start();
                    gestClient.afficherUser();
                }
            } catch (IOException e) {

            }
        }

        public void fermerServerSocket() {
            try {
                if (ss != null) {
                    ss.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public static void main(String[] args) throws IOException {
            // TODO Auto-generated method stub
            ServerSocket serverSocket = new ServerSocket(5556);
            Server server = new Server(serverSocket);
            server.startServeur();

        }

    }
