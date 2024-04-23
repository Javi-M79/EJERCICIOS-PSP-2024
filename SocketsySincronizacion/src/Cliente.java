import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private static String host = "localhost";

    //INICIO DEL CLIENTE.
    public Cliente(int port) throws IOException {
        System.out.println("(Cliente) Conectado por el puerto " + port);
        socket = new Socket();
        InetSocketAddress adress = new InetSocketAddress(port);
        socket.connect(adress);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }


    public void stopCliente() {
        System.out.println("(Cliente( Cerrando conexiones...");
        try {
            socket.close();
            out.close();
            in.close();
        } catch (IOException e) {
            System.out.println("Error cerrando las conexiones del cliente...");
            throw new RuntimeException(e);
        }


    }


    public static void main(String[] args) throws IOException {
        Scanner teclado = new Scanner(System.in);

        Cliente cliente = new Cliente(128);
        int opcion = 0;
        System.out.println("Bienvenido. Elija una opcion:");
        System.out.println("1. Lectura.");
        System.out.println("2. Escritura.");
        opcion = teclado.nextInt();

        while (opcion != 1 && opcion != 2) {
            System.out.println("Introduzca una opcion valida por favor:");
            opcion = teclado.nextInt();

        }

        //Enviamos la opcion elegida al servidor.

        cliente.out.writeInt(opcion);
        BufferedReader reader = new BufferedReader(new InputStreamReader(cliente.in));
        String mensaje = reader.readLine();
        System.out.println(mensaje);

    }
}
