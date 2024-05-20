package co.edu.uniquindio.poo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Parqueadero {
    private final int cantidadPuestos;
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

    public void ocuparPuestos(int posicionI, int posicionJ, Vehiculo vehiculo) {
        if (verificarDisponibilidad(posicionI, posicionJ)) {
            LocalDateTime fechaEntrada = LocalDateTime.now(); // Obtener la fecha y hora actual
            puestos[posicionI][posicionJ].ocuparPuesto(vehiculo);
            Registro registro = new Registro(vehiculo, fechaEntrada, null); // Pasar fecha de entrada
            historialRegistros.add(registro);
        } else {
            System.out.println("El puesto ya está ocupado.");
        }
    }

    public boolean buscarYParquearVehiculo(Vehiculo vehiculo) {
        for (int posicionI = 0; posicionI < puestos.length; posicionI++) {
            for (int posicionJ = 0; posicionJ < puestos[0].length; posicionJ++) {
                if (verificarDisponibilidad(posicionI, posicionJ)) {
                    ocuparPuestos(posicionI, posicionJ, vehiculo);
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
            LocalDateTime fechaSalida = LocalDateTime.now();
    
            // Buscar el registro correspondiente en el historial de registros
            Registro registro = null;
            for (Registro reg : historialRegistros) {
                if (reg.getVehiculo().equals(vehiculo) && reg.getFechaSalida() == null) {
                    registro = reg;
                    break;
                }
            }
    
            if (registro != null) {
                registro.setFechaSalida(fechaSalida);
                puesto.liberarPuesto();
            } else {
                System.err.println("No se encontró el registro correspondiente para el vehículo.");
            }
        }
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
        for (Carro carro : carros) {
            if (carro.getPlaca().equals(placa)) {
                return carro;
            }
        }
        return null;
    }
    
    public Collection<Carro> getCarros() {
        return Collections.unmodifiableCollection(carros);
    }
    
    private boolean validarPlacaMotoExiste(String placa) {
        for (Moto moto : motos) {
            if (moto.getPlaca().equals(placa)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean validarPlacaCarroExiste(String placa) {
        for (Carro carro : carros) {
            if (carro.getPlaca().equals(placa)) {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Registro> getHistorialRegistros() {
        return historialRegistros;
    }
    public Map<TipoVehiculo, Double> generarReporteDiario(LocalDate fecha) {
        Map<TipoVehiculo, Double> reporteDiario = inicializarReporte();
    
        for (Registro registro : historialRegistros) {
            if (esFechaIgual(registro, fecha)) {
                actualizarReporte(registro, reporteDiario);
            }
        }
        return reporteDiario;
    }
    
    public Map<TipoVehiculo, Double> generarReporteMensual(int mes, int año) {
        Map<TipoVehiculo, Double> reporteMensual = inicializarReporte();
    
        for (Registro registro : historialRegistros) {
            if (esMesYAñoIguales(registro, mes, año)) {
                actualizarReporte(registro, reporteMensual);
            }
        }
        return reporteMensual;
    }
    
    private Map<TipoVehiculo, Double> inicializarReporte() {
        Map<TipoVehiculo, Double> reporte = new HashMap<>();
        for (TipoCarro tipoCarro : TipoCarro.values()) {
            reporte.put(tipoCarro.getTipoVehiculo(), 0.0);
        }
        for (TipoMoto tipoMoto : TipoMoto.values()) {
            reporte.put(tipoMoto.getTipoVehiculo(), 0.0);
        }
        return reporte;
    }
    
    private boolean esFechaIgual(Registro registro, LocalDate fecha) {
        return registro.getFechaEntrada().toLocalDate().equals(fecha);
    }
    
    private boolean esMesYAñoIguales(Registro registro, int mes, int año) {
        LocalDate fechaEntrada = registro.getFechaEntrada().toLocalDate();
        return fechaEntrada.getMonthValue() == mes && fechaEntrada.getYear() == año;
    }
    
    private void actualizarReporte(Registro registro, Map<TipoVehiculo, Double> reporte) {
        double costo = registro.calcularCosto();
        Vehiculo vehiculo = registro.getVehiculo();
    
        if (vehiculo instanceof Moto) {
            reporte.compute(TipoVehiculo.MOTO, (tipo, acumulado) -> (acumulado == null) ? costo : acumulado + costo);
        } else if (vehiculo instanceof Carro) {
            TipoCarro tipoCarro = ((Carro) vehiculo).getTipoCarro();
            if (tipoCarro != null) {
                reporte.compute(tipoCarro.getTipoVehiculo(), (tipo, acumulado) -> (acumulado == null) ? costo : acumulado + costo);
            } else {
                System.err.println("Tipo de vehículo desconocido: " + vehiculo.getClass().getSimpleName());
            }
        } else {
            System.err.println("Tipo de vehículo desconocido: " + vehiculo.getClass().getSimpleName());
        }
    }
    
        
              
}
