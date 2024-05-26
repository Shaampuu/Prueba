package co.edu.uniquindio.poo;

public abstract class Vehiculo {
    private String placa;
    private int modelo;
    private Propietario propietario;
    private int horasEstacionadas;
    private double tarifaPorHora;
    private Registro registro;

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

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public int getHorasEstacionadas() {
        return horasEstacionadas;
    }

    public void setHorasEstacionadas(int horasEstacionadas) {
        this.horasEstacionadas = horasEstacionadas;
    }

    public double getTarifaPorHora() {
        return tarifaPorHora;
    }

    public void setTarifaPorHora(double tarifaPorHora) {
        this.tarifaPorHora = tarifaPorHora;
    }

    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    public abstract double calcularTarifa();
}
