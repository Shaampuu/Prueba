package co.edu.uniquindio.poo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class Parqueadero {
    private int cantidadPuestos;
    private final Collection<Moto> motos;
    private final Collection<Carro> carros;
    private final Puesto[][] puestos;
    private final ArrayList<Registro> historialRegistros = new ArrayList<>();

    public Parqueadero(int columnas, int filas) {
        assert columnas > 0 : "El número de columnas debe ser mayor a cero";
        assert filas > 0 : "El número de filas debe ser mayor a cero";

        this.cantidadPuestos = columnas * filas;

        motos = new LinkedList<>();
        carros = new LinkedList<>();

        puestos = new Puesto[columnas][filas];
        for (int posicionI = 0; posicionI < columnas; posicionI++) {
            for (int posicionJ = 0; posicionJ < filas; posicionJ++) {
                puestos[posicionI][posicionJ] = new Puesto(posicionI, posicionJ, null, columnas, filas);
            }
        }
    }

    public int getCantidadPuestos() {
        return cantidadPuestos;
    }

    public boolean verificarDisponibilidad(int posicionI, int posicionJ) {
        return !puestos[posicionI][posicionJ].estaOcupado();
    }

    public void ocuparPuestos(int posicionI, int posicionJ, Vehiculo vehiculo, double tarifaPorHora) {
        if (verificarDisponibilidad(posicionI, posicionJ)) {
            puestos[posicionI][posicionJ].ocuparPuesto(vehiculo);
            Registro registro = new Registro(LocalTime.now(), null, tarifaPorHora, vehiculo);
            historialRegistros.add(registro);
        } else {
            System.out.println("El puesto ya está ocupado.");
        }
    }

    public boolean buscarYParquearVehiculo(Vehiculo vehiculo, double tarifaPorHora) {
        for (int posicionI = 0; posicionI < puestos.length; posicionI++) {
            for (int posicionJ = 0; posicionJ < puestos[0].length; posicionJ++) {
                if (verificarDisponibilidad(posicionI, posicionJ)) {
                    ocuparPuestos(posicionI, posicionJ, vehiculo, tarifaPorHora);
                    return true;
                }
            }
        }
        System.out.println("No hay puesto disponible");
        return false;
    }

    public void liberarPuesto(int posicionI, int posicionJ) {
        Puesto puesto = puestos[posicionI][posicionJ];
        if (puesto.estaOcupado()) {
            Vehiculo vehiculo = puesto.getVehiculo();
            LocalDate fechaSalida = LocalDate.now();
            double tarifaPorHora = 10.0; // Asumiendo una tarifa fija, esto podría depender del tipo de vehículo
            double ingreso = calcularIngreso(vehiculo, fechaSalida, tarifaPorHora);

            Registro registro = new Registro(vehiculo, fechaSalida, ingreso);
            historialRegistros.add(registro);

            puesto.liberarPuesto();
        }
    }
    
    private double calcularIngreso(Vehiculo vehiculo, LocalDate fechaSalida, double tarifaPorHora) {
        // Implementar lógica para calcular el ingreso basado en el tiempo estacionado
        return tarifaPorHora;
    }

    public String obtenerPropietario(int posicionI, int posicionJ) {
        if (puestos[posicionI][posicionJ].estaOcupado()) {
            return puestos[posicionI][posicionJ].getVehiculo().getPropietario().getNombre();
        } else {
            return "El puesto está vacio.";
        }
    }

    public void agregarMoto(Moto moto) {
        assert !validarPlacaMotoExiste(moto.getPlaca()) : "La placa ya existe";
        motos.add(moto);
    }

    public Moto getMoto(String placa) {
        Moto motoInteres = null; 
        for (Moto moto : motos) {
            if (moto.getPlaca().equals(placa)) {
                motoInteres = moto;
            }
        }
        return motoInteres;
    }

    public Collection<Moto> getMotos() {
        return Collections.unmodifiableCollection(motos);
    }

    public void agregarCarro(Carro carro) {
        assert !validarPlacaCarroExiste(carro.getPlaca()) : "La placa ya existe";
        carros.add(carro);
    }

    public Carro getCarro(String placa) {
        Carro carroInteres = null; 
        for (Carro carro : carros) {
            if (carro.getPlaca().equals(placa)) {
                carroInteres = carro;
            }
        }
        return carroInteres;
    }

    public Collection<Carro> getCarros() {
        return Collections.unmodifiableCollection(carros);
    }

    private boolean validarPlacaMotoExiste(String placa) {
        boolean existe = false; 
        for (Moto moto : motos) {
            if (moto.getPlaca().equals(placa)) {
                existe = true;
            }
        }
        return existe;
    }

    private boolean validarPlacaCarroExiste(String placa) {
        boolean existe = false; 
        for (Carro carro : carros) {
            if (carro.getPlaca().equals(placa)) {
                existe = true;
            }
        }
        return existe;
    }

    public ArrayList<Registro> getHistorialRegistros() {
        return historialRegistros;
    }

    public Map<TipoVehiculo, Double> generarRepoteDiario(LocalDate fecha) {
        Map<TipoVehiculo, Double> reporteDiario = new HashMap<>();
    
        // Inicializar el mapa con valores por defecto
        for (TipoVehiculo tipo : TipoVehiculo.values()) {
            reporteDiario.put(tipo, 0.0);
        }
    
        for (Registro registro : historialRegistros) {
            // Verificar si la fecha de entrada del registro coincide con la fecha dada
            if (registro.getFechaEntrada().toLocalDate().equals(fecha)) {
                actualizarReporte(registro, reporteDiario);
            }
        }
        return reporteDiario;
    }
    
    // Método auxiliar para actualizar el reporte con la información del registro dado
    private void actualizarReporte(Registro registro, Map<TipoVehiculo, Double> reporte) {
        double costo = registro.calcularCosto();
        Vehiculo vehiculo = registro.getVehiculo();
    
        if (vehiculo instanceof Moto) {
            Moto moto = (Moto) vehiculo;
            reporte.computeIfPresent(moto.getTipoVehiculo(), (tipo, acumulado) -> acumulado + costo);
        } else if (vehiculo instanceof Carro) {
            reporte.computeIfPresent(TipoVehiculo.CARRO, (tipo, acumulado) -> acumulado + costo);
        }
    }
    
    

    public Map<TipoVehiculo, Double> generarReporteMensual(int mes, int anio) {
        Map<TipoVehiculo, Double> reporte = new EnumMap<>(TipoVehiculo.class);
        
        for (Registro registro : historialRegistros) {
            LocalDateTime fechaSalida = registro.getFechaSalida();
            if (fechaSalida.getMonthValue() == mes && fechaSalida.getYear() == anio) {
                TipoVehiculo tipoVehiculo = registro.getVehiculo().getTipoVehiculo();
                double ingreso = registro.getIngreso();
                reporte.put(tipoVehiculo, reporte.getOrDefault(tipoVehiculo, 0.0) + ingreso);
            }
        }
        
        return reporte;
    }
    

}

