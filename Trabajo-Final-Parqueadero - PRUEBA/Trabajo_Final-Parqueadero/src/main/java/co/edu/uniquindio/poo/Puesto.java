package co.edu.uniquindio.poo;

public class Puesto {
    private final int posicionI;
    private final int posicionJ;
    private Vehiculo vehiculo;
    private boolean ocupado;
    private final int columnas;
    private final int filas;

    public Puesto(int posicionI, int posicionJ, Vehiculo vehiculo, int columnas, int filas) {
        this.posicionI = posicionI;
        this.posicionJ = posicionJ;
        this.vehiculo = vehiculo;
        this.ocupado = vehiculo != null;
        this.columnas = columnas;
        this.filas = filas;
    }

    public int getPosicionI() {
        return posicionI;
    }

    public int getPosicionJ() {
        return posicionJ;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public boolean estaOcupado() {
        return ocupado;
    }

    public void ocuparPuesto(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        this.ocupado = true;
    }

    public void liberarPuesto() {
        this.vehiculo = null;
        this.ocupado = false;
    }
}
