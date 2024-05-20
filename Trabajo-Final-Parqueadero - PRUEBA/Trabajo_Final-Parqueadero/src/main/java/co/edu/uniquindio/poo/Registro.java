package co.edu.uniquindio.poo;

import java.time.Duration;
import java.time.LocalDateTime;

public class Registro {
    
    private Vehiculo vehiculo;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private double tarifaPorHora;

    public Registro(Vehiculo vehiculo, LocalDateTime fechaEntrada, LocalDateTime fechaSalida, double tarifaPorHora) {
        this.vehiculo = vehiculo;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.tarifaPorHora = tarifaPorHora;
    }
    

    public LocalDateTime getFechaEntrada() {
        return fechaEntrada;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }
    

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public double getTarifaPorHora() {
        return tarifaPorHora;
    }

    public double calcularCosto() {
        // Calcular la duración del estacionamiento
        Duration duracion = Duration.between(fechaEntrada, fechaSalida);
        
        // Calcular el número de horas estacionadas
        long horasEstacionadas = duracion.toHours();
        
        // Si ha estacionado por menos de una hora, se cobra una hora completa
        if (duracion.toMinutes() % 60 != 0) {
            horasEstacionadas++;
        }
        
        // Obtener la tarifa específica para este tipo de vehículo
        double tarifaPorHora = vehiculo.getTarifaPorHora();
        
        // Calcular el costo total
        double costoTotal = horasEstacionadas * tarifaPorHora;
        
        return costoTotal;
    }
}
