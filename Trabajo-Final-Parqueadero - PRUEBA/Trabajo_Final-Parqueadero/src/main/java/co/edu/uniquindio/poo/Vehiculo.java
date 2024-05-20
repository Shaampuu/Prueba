package co.edu.uniquindio.poo;

public abstract class Vehiculo {
    private String placa; 
    private int modelo;
    private Propietario propietario;

    public Vehiculo(String placa, int modelo, Propietario propietario) {
        assert placa != null && !placa.isBlank() : "La placa debe ser diferente de null";
        assert modelo > 0 : "El modelo del vehiculo debe ser mayor a 0 (cero)";
        assert propietario != null : "El propietario debe ser diferente de null";

        this.placa = placa;
        this.modelo = modelo;
        this.propietario = propietario;
    }

    public String getPlaca() {
        return placa; 
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    } 

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public abstract double getTarifaPorHora();
}
