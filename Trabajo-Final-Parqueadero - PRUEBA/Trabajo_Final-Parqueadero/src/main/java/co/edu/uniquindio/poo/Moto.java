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
}
