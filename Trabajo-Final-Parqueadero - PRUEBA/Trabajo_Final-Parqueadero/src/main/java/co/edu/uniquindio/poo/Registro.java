package co.edu.uniquindio.poo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Registro {
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private double tarifaPorHora;
    private Vehiculo vehiculo;

    public Registro(LocalTime horaEntrada, LocalTime horaSalida, double tarifaPorHora, Vehiculo vehiculo) {
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.tarifaPorHora = tarifaPorHora;
        this.vehiculo = vehiculo;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public double getTarifaPorHora() {
        return tarifaPorHora;
    }

    public void setTarifaPorHora(double tarifaPorHora) {
        this.tarifaPorHora = tarifaPorHora;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public double calcularCosto() {
        if (horaSalida != null) {
            long horasEstacionado = horaEntrada.until(horaSalida).toHours();
            return horasEstacionado * tarifaPorHora;
        } else {
            // Si el vehículo aún está estacionado, no se puede calcular el costo
            return 0;
        }
    }
}
