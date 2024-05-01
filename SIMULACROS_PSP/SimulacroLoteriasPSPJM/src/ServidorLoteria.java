import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorLoteria {
    private ServerSocket serverSocket;
    private DataInputStream in;
    private DataOutputStream out;
    private int numeroPremiado;
    private FileReader fileReader;
    private BufferedReader reader;
    private Socket socket;


    //INICIO DEL SERVIDOR
    public ServidorLoteria(int port) throws IOException {
        System.out.println("(Servidor) Escuchando por el puerto " + port);
        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

    }

    // PARADA DEL SERVIDOR
    public void stoServidor() {

        try {
            serverSocket.close();
            socket.close();
            in.close();
            out.close();

        } catch (IOException e) {
            System.out.println("Error cerrando las conexiones del servidor.");
            throw new RuntimeException(e);
        }
    }


    //METODO COMPROBACION DEL NUMERO DE LOTERIA

    public void lectura(File file, int numeroCliente) throws IOException {

        PrintStream printStream = new PrintStream(out);
        fileReader = new FileReader(file);
        reader = new BufferedReader(fileReader);
        String numeroLeido = reader.readLine();
        this.numeroPremiado = Integer.parseInt(numeroLeido);

        if (numeroPremiado != numeroCliente) {
            printStream.println("El numero no ha sido premiado");
        } else {
            printStream.println("Enhorabuena tu numero ha sido premiado!!");
        }
    }


    public static void main(String[] args) throws IOException {

        //CREAMOS EL ARCHIVO A CONSULTAR
        File file = new File("src/resources/loteria.txt");
        //INICIO DEL SERVIDOR CON CONEXION AL PUERTO 128
        ServidorLoteria servidorLoteria = new ServidorLoteria(128);
        //RECOGEMOS LA VARIABLE ENVIADA POR EL CLIENTE
        int numeroCliente = servidorLoteria.in.readInt();
        //LLAMAMOS AL METODO PASANDOLE POR PARAMETROS EL ARCHIVO Y EL NUMERO DEL CLIENTE./
        servidorLoteria.lectura(file, numeroCliente);


    }

}
