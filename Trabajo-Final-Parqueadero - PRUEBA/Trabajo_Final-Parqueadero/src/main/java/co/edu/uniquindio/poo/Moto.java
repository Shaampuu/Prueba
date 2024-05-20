package co.edu.uniquindio.poo;

public class Moto extends Vehiculo {

    private int velocidadMaxima;
    private TipoMoto tipoMoto;

    public Moto(String placa, int modelo, Propietario propietario, int velocidadMaxima) {
        super(placa, modelo, propietario);
        Validaciones.validarPlaca(placa);
        Validaciones.validarModelo(modelo);
        Validaciones.validarPropietario(propietario);
        if (velocidadMaxima <= 0) {
            throw new IllegalArgumentException("La velocidad máxima debe ser mayor a 0 (cero)");
        }
        this.velocidadMaxima = velocidadMaxima;
    }

    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public void setVelocidadMaxima(int velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    @Override
    public double getTarifaPorHora() {
        if (velocidadMaxima >= 0 && velocidadMaxima <= 100) {
            return 5.0;
        } else if (velocidadMaxima > 100 && velocidadMaxima <= 150) {
            return 7.0;
        } else {
            return 10.0;
        }
    }
}
