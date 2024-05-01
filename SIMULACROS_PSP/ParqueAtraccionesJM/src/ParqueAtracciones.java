import java.util.concurrent.Semaphore;

public class ParqueAtracciones extends Thread {

    private static int contadorTotal = 0;
    private static int contadorPuerta1 = 0;
    private static int contadorPuerta2 = 0;
    private static int contadorPuerta3 = 0;
    private int id;


    //Por cada puerta pueden entrar simultaneamente 5 personas.
    private Semaphore puerta1 = new Semaphore(5);
    private Semaphore puerta2 = new Semaphore(5);
    private Semaphore puerta3 = new Semaphore(5);


    public ParqueAtracciones(int id) {
        this.id = id;

    }

    public void run() {

        try {

            Thread.sleep(1000);

            //Control de acceso a cada una de las puertas mediante un numero aleatorio
            int puerta = (int) (Math.random() * 3 + 1);

            switch (puerta) {
                case 1: {
                    puerta1.acquire();
                    Thread.sleep(1000);
                    System.out.println("El asistente numero " + this.id + " entra en la puerta 1");
                    contadorPuerta1++;
                    contadorTotal++;
                    puerta1.release();
                    break;
                }
                case 2: {
                    puerta2.acquire();
                    Thread.sleep(1000);
                    System.out.println("El asistente numero " + this.id + " entra en la puerta 2");
                    contadorPuerta2++;
                    contadorTotal++;
                    puerta2.release();
                    break;
                }
                case 3: {
                    puerta3.acquire();
                    Thread.sleep(1000);
                    System.out.println("El asistente numero " + this.id + " entra en la puerta 3");
                    contadorPuerta3++;
                    contadorTotal++;
                    puerta3.release();
                    break;
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getContadorTotal() {
        return contadorPuerta1 + contadorPuerta2 + contadorPuerta3;
    }

    public static void setContadorTotal(int contadorTotal) {
        ParqueAtracciones.contadorTotal = contadorTotal;
    }

    public static int getContadorPuerta1() {
        return contadorPuerta1;
    }

    public static void setContadorPuerta1(int contadorPuerta1) {
        ParqueAtracciones.contadorPuerta1 = contadorPuerta1;
    }

    public static int getContadorPuerta2() {
        return contadorPuerta2;
    }

    public static void setContadorPuerta2(int contadorPuerta2) {
        ParqueAtracciones.contadorPuerta2 = contadorPuerta2;
    }

    public static int getContadorPuerta3() {
        return contadorPuerta3;
    }

    public static void setContadorPuerta3(int contadorPuerta3) {
        ParqueAtracciones.contadorPuerta3 = contadorPuerta3;
    }
}
