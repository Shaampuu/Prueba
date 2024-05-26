package co.edu.uniquindio.poo;

import java.time.Duration;
import java.time.LocalDateTime;


public class CalculadorCosto {

     
    public static double calcularCosto(LocalDateTime fechaEntrada, LocalDateTime fechaSalida, double tarifaPorHora) {
        assert fechaSalida != null : "El vehículo aún está estacionado, la fecha de salida es null.";


        Duration duracion = Duration.between(fechaEntrada, fechaSalida);


        long horasEstacionadas = duracion.toHours();


        if (duracion.toMinutes() % 60 != 0) {
            horasEstacionadas++;
        }


        return horasEstacionadas * tarifaPorHora;
    }
}
