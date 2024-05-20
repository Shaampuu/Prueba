package co.edu.uniquindio.poo;

public class Moto extends Vehiculo{
    private int velocidadMaxima;
    
    public Moto(String placa, int modelo, Propietario propietario, TipoVehiculo tipoVehiculo, int velocidadMaxima){
        super(placa, modelo, propietario, tipoVehiculo);
        assert velocidadMaxima > 0 : "La velocidad maxima debe de ser mayor a 0 (cero)";
        
        this.velocidadMaxima=velocidadMaxima;
    }

    public int getVelocidadMaxima(){
        return velocidadMaxima;
    }

    public void setVelocidadMaxima(int velocidadMaxima){
        this.velocidadMaxima=velocidadMaxima;
    }

    @Override
    public double getTarifaPorHora() {
        // Definir la tarifa por hora según la velocidad máxima de la moto
        if (velocidadMaxima >= 0 && velocidadMaxima <= 100) {
            return 5.0; // Tarifa para motos con velocidad máxima menor o igual a 100 km/h
        } else if (velocidadMaxima > 100 && velocidadMaxima <= 150) {
            return 7.0; // Tarifa para motos con velocidad máxima entre 101 y 150 km/h
        } else {
            return 10.0; // Tarifa por defecto para otras velocidades máximas
        }
    }
}
