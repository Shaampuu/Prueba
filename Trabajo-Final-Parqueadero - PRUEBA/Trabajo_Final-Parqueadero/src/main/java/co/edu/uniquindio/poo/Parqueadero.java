package co.edu.uniquindio.poo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Parqueadero {
    private final int cantidadPuestos;
    private final List<Moto> motos;
    private final List<Carro> carros;
    private final Puesto[][] puestos;
    private final List<Registro> historialRegistros = new ArrayList<>();

    public Parqueadero(int columnas, int filas) {
        if (columnas <= 0 || filas <= 0) {
            throw new IllegalArgumentException("El número de columnas y filas debe ser mayor a cero");
        }

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

    public Vehiculo buscarVehiculo(String placa) {
        for (Carro carro : carros) {
            if (carro.getPlaca().equals(placa)) {
                return carro;
            }
        }
        for (Moto moto : motos) {
            if (moto.getPlaca().equals(placa)) {
                return moto;
            }
        }
        return null;
    }

    public void registrarSalidaVehiculo(Vehiculo vehiculo) {
        // Iterar sobre cada registro en el historial de registros del parqueadero
        for (Registro registro : historialRegistros) {
            // Verificar si el vehículo en el registro coincide con el vehículo que se va a retirar
            // y si aún no tiene una fecha de salida registrada
            if (registro.getVehiculo().equals(vehiculo) && registro.getFechaSalida() == null) {
                // Establecer la fecha de salida del vehículo como la fecha y hora actuales
                registro.setFechaSalida(LocalDateTime.now());
                
                // Liberar el puesto ocupado por el vehículo si es necesario
                // Aquí deberías implementar la lógica según el diseño de tu parqueadero
                // Por ejemplo, puedes marcar el puesto como disponible nuevamente
                // o realizar cualquier otra acción necesaria para el proceso de salida del vehículo
                
                // Salir del bucle una vez que se haya encontrado y actualizado el registro adecuado
                break;
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
        if (validarPlacaMotoExiste(moto.getPlaca())) {
            throw new IllegalArgumentException("La placa ya existe");
        }
        motos.add(moto);
    }

    public Moto getMoto(String placa) {
        for (Moto moto : motos) {
            if (moto.getPlaca().equals(placa)) {
                return moto;
            }
        }
        return null;
    }

    public List<Moto> getMotos() {
        return Collections.unmodifiableList(motos);
    }

    public void agregarCarro(Carro carro) {
        if (validarPlacaCarroExiste(carro.getPlaca())) {
            throw new IllegalArgumentException("La placa ya existe");
        }
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

    public List<Carro> getCarros() {
        return Collections.unmodifiableList(carros);
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

    public List<Registro> getHistorialRegistros() {
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
        TipoVehiculo tipoVehiculo = vehiculo instanceof Moto ? TipoVehiculo.MOTO : ((Carro) vehiculo).getTipoCarro().getTipoVehiculo();
        reporte.merge(tipoVehiculo, costo, Double::sum);
    }
}
