package co.edu.uniquindio.poo;

import java.time.Duration;
import java.time.LocalDateTime;

public class Registro {
    
    private Vehiculo vehiculo;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;

    public Registro(Vehiculo vehiculo, LocalDateTime fechaEntrada, LocalDateTime fechaSalida) {
        this.vehiculo = vehiculo;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }

    public Registro(Vehiculo vehiculo, LocalDateTime fechaEntrada) {
        this.vehiculo = vehiculo;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida= null;
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
    
    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public double calcularCosto() {
        assert fechaSalida != null : "El vehículo aún está estacionado, la fecha de salida es null.";
    
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
        return horasEstacionadas * tarifaPorHora;
    }
    
    
}
