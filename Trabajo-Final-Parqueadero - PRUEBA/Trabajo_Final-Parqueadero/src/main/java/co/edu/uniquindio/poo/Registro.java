package co.edu.uniquindio.poo;

import java.time.Duration;
import java.time.LocalDateTime;

public class Registro {
    private Vehiculo vehiculo;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;

    public Registro(Vehiculo vehiculo, LocalDateTime fechaEntrada, LocalDateTime fechaSalida) {
        assert vehiculo != null : "El vehículo no puede ser nulo";
        assert fechaEntrada != null : "La fecha de entrada no puede ser nula";
        this.vehiculo = vehiculo;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public LocalDateTime getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDateTime fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public double calcularCosto() {
        LocalDateTime salida = fechaSalida != null ? fechaSalida : LocalDateTime.now();
        Duration duracion = Duration.between(fechaEntrada, salida);
        long horasEstacionadas = duracion.toHours();
        if (duracion.toMinutes() % 60 != 0) {
            horasEstacionadas++;
        }
        double tarifaPorHora = vehiculo.getTarifaPorHora();
        return horasEstacionadas * tarifaPorHora;
    }
}
