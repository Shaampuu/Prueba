package co.edu.uniquindio.poo;

public abstract class Vehiculo {
    private String placa; 
    private int modelo;
    private Propietario propietario;
    private TipoVehiculo tipoVehiculo;

    public Vehiculo (String placa, int modelo, Propietario propietario, TipoVehiculo tipoVehiculo){
        assert placa != null && !placa.isBlank() : "La placa debe ser diferente de null";
        assert modelo > 0 : "el modelo del vehiculo debe ser mayor a 0 (cero)";
        assert tipoVehiculo != null : "El tipo de vehiculo debe ser diferente de null";
        
        this.placa=placa;
        this.modelo=modelo;
        this.propietario = propietario;
        this.tipoVehiculo=tipoVehiculo; 
    }

    public String getPlaca(){
        return placa; 
    }

    public void setPlaca(String placa){
        this.placa=placa;
    } 

    public int getModelo(){
        return modelo;
    }

    public void setModelo(int modelo){
        this.modelo=modelo;
    }

    public TipoVehiculo getTipoVehiculo(){
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo){
        this.tipoVehiculo=tipoVehiculo;
    }

    public Propietario getPropietario(){
        return propietario;
    }
}