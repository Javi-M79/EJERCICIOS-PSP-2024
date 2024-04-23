import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread {

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private ServerSocket serverSocket;
    private static String host = "localhost";


    public Servidor(int port) throws IOException {

        System.out.println("(Servidor) Escuchando por el puerto " + port);
        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }

    public void lectura(File file) throws FileNotFoundException {

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            PrintStream printStream = new PrintStream(out);

            if (file.canRead()) {
                String texto = "";
                String linea;
                while ((linea = reader.readLine()) != null) {
                    texto += linea;


                }
                printStream.println("Contenido del archivo:" + texto);
            } else {
                printStream.println("(Servidor) El fichero no se puede leer");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private synchronized void escritura(File file) {


    }


    public static void main(String[] args) throws IOException {

        File file = new File("src/resources/archivo.txt");
        Servidor servidor = new Servidor(128);

        int opcion = servidor.in.readInt();

        switch (opcion) {

            case 1:
                servidor.lectura(file);
                break;

            case 2:
                servidor.escritura(file);
                servidor.out.writeUTF("Has elegido la opcion 2.");
                break;

        }


    }


}
