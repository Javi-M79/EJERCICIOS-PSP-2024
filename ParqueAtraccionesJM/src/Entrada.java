import java.util.ArrayList;
import java.util.Scanner;

public class Entrada {


    public static void main(String[] args) throws InterruptedException {

        Scanner teclado = new Scanner(System.in);
        //Generamos un numero aleatorio para calcular la asistencia.
        int asistencia = (int) (Math.random() * 100 + 1);
        //Metemos todos los asistentes en un Arraylist
        ArrayList<ParqueAtracciones> totalasistentes = new ArrayList<>();

        for (int i = 0; i < asistencia; i++) {
            ParqueAtracciones parqueAtracciones = new ParqueAtracciones(i+1);
            totalasistentes.add(parqueAtracciones);
            parqueAtracciones.start();
        }
        //Esperamos que todos los hilos terminen antes de mostrar los contadores.
        for (ParqueAtracciones p : totalasistentes
        ) {
            p.join();
        }



        System.out.println("Han pasado un total de " + ParqueAtracciones.getContadorPuerta1() + " personas por la puerta 1.");
        System.out.println("Han pasado un total de " + ParqueAtracciones.getContadorPuerta2() + " personas por la puerta 2.");
        System.out.println("Han pasado un total de " + ParqueAtracciones.getContadorPuerta3() + " personas por la puerta 3.");
        System.out.println("Han pasado un total de " + ParqueAtracciones.getContadorTotal() + " por el parque.");
    }


}

