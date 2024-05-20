package co.edu.uniquindio.poo;

public abstract class Vehiculo {
    private String placa;
    private int modelo;
    private Propietario propietario;

    public Vehiculo(String placa, int modelo, Propietario propietario) {
        Validaciones.validarPlaca(placa);
        Validaciones.validarModelo(modelo);
        Validaciones.validarPropietario(propietario);
        this.placa = placa;
        this.modelo = modelo;
        this.propietario = propietario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        Validaciones.validarPlaca(placa);
        this.placa = placa;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        Validaciones.validarModelo(modelo);
        this.modelo = modelo;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        Validaciones.validarPropietario(propietario);
        this.propietario = propietario;
    }

    public abstract double getTarifaPorHora();
}
