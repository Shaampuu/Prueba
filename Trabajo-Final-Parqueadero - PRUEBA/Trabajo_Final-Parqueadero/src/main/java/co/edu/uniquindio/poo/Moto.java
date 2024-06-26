package co.edu.uniquindio.poo;

public class Moto extends Vehiculo {

    private int velocidadMaxima;
    private TipoMoto tipoMoto;

    public Moto(String placa, int modelo, Propietario propietario, int velocidadMaxima, TipoMoto tipoMoto) {
        super(placa, modelo, propietario);
        assert velocidadMaxima > 0 : "La velocidad máxima debe ser mayor a 0 (cero)";
        this.velocidadMaxima = velocidadMaxima;
        this.tipoMoto = tipoMoto;
    }

    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public void setVelocidadMaxima(int velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    public TipoMoto getTipoMoto() {
        return tipoMoto;
    }

    public void setTipoMoto(TipoMoto tipoMoto) {
        this.tipoMoto = tipoMoto;
    }

    @Override
    public double calcularTarifa() {
        return getTarifaPorHora() * getHorasEstacionadas();
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
