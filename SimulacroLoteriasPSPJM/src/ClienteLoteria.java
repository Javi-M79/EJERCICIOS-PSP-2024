import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClienteLoteria {

    static Scanner teclado = new Scanner(System.in);
    private DataInputStream in;
    private DataOutputStream out;
    private FileReader fileReader;
    private BufferedReader reader;
    private Socket socket;

    //INICIO DEL CLIENTE
    public ClienteLoteria(int port) throws IOException {
        System.out.println("(Cliente) Conectado en el puerto " + port);
        InetSocketAddress address = new InetSocketAddress(port);
        socket = new Socket();
        socket.connect(address);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }

    //PARADA DEL CLIENTE
    public void stopCliente() throws IOException {
        socket.close();
        in.close();
        out.close();
    }

    public static void main(String[] args) throws IOException {

        ClienteLoteria cliente = new ClienteLoteria(128);
        int numeroJugado;
        System.out.println("Introduce el numero que has jugado:");
        numeroJugado = teclado.nextInt();
        //ENVIAMOS EL NUMERO AL SERVIDOR
        cliente.out.writeInt(numeroJugado);

        //LECTURA DEL MENSAJE DEL SERVIDOR.
        BufferedReader reader = new BufferedReader(new InputStreamReader(cliente.in));
        String mensaje = reader.readLine();
        System.out.println(mensaje);
    }

}
