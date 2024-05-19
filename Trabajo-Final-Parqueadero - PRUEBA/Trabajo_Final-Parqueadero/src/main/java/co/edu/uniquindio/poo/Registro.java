package co.edu.uniquindio.poo;

import java.time.LocalTime;
import java.time.Duration;
import java.time.LocalDate;

public class Registro {
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private double tarifaPorHora;
    private Vehiculo vehiculo;
    private LocalDate fecha;

    public Registro(LocalTime horaEntrada, LocalTime horaSalida, double tarifaPorHora, Vehiculo vehiculo){

        this.horaEntrada = horaEntrada;
        this.horaSalida = null;
        this.tarifaPorHora = tarifaPorHora; 
        this.vehiculo = vehiculo;
        this.fecha = LocalDate.now();
    }

    public LocalTime getHoraEntrada(){
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada){
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida(){
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida){
        this.horaSalida = horaSalida;
    }

    public double getTarifaPorHora(){
        return tarifaPorHora;
    }

    public void setTarifaPorHora(double tarifaPorHora){
        this.tarifaPorHora = tarifaPorHora; 
    }

    public Vehiculo getVehiculo(){
        return vehiculo;
    }

    public LocalDate getFecha(){
        return fecha;
    }

    public long calcularDuracionHoras(){
        if (horaSalida == null){
            return 0;
        }
        return Duration.between(horaEntrada, horaSalida).toHours();
    }

    public double calcularCosto(){
        long duracionHoras = calcularDuracionHoras();
        return duracionHoras * tarifaPorHora;
    }
}