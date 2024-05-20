package co.edu.uniquindio.poo;

public class App {
    public static void main(String[] args) {
        Parqueadero parqueadero = new Parqueadero(3, 3);
        AppController appController = new AppController(parqueadero);
        appController.iniciar();;
    }
}
