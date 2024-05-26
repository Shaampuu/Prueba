package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el número de columnas del parqueadero:");
        int columnas = scanner.nextInt();
        System.out.println("Ingrese el número de filas del parqueadero:");
        int filas = scanner.nextInt();
        Parqueadero parqueadero = new Parqueadero(columnas, filas);

        int opcion;
        boolean continuar = true;

        while (continuar) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Agregar Carro");
            System.out.println("2. Agregar Moto");
            System.out.println("3. Parquear Vehículo");
            System.out.println("4. Liberar Vehículo");
            System.out.println("5. Obtener Propietario por Puesto");
            System.out.println("6. Generar Reporte Diario");
            System.out.println("7. Generar Reporte Mensual");
            System.out.println("8. Calcular Costo Total");
            System.out.println("9. Mostrar Registros");
            System.out.println("10. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    if (parqueadero.getCantidadPuestos() <= parqueadero.getCarros().size() + parqueadero.getMotos().size()) {
                        System.out.println("No hay espacio disponible en el parqueadero.");
                        break;
                    }
                    System.out.println("Ingrese la placa del carro:");
                    String placaCarro = scanner.nextLine();
                    System.out.println("Ingrese el modelo del carro:");
                    int modeloCarro = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ingrese el nombre del propietario:");
                    String nombrePropietarioCarro = scanner.nextLine();
                    System.out.println("Ingrese el tipo de carro (1: CARRO, 2: CAMIONETA, 3: DEPORTIVO):");
                    int tipoCarroInt = scanner.nextInt();
                    scanner.nextLine();

                    TipoCarro tipoCarro;
                    switch (tipoCarroInt) {
                        case 2:
                            tipoCarro = TipoCarro.CAMIONETA;
                            break;
                        case 3:
                            tipoCarro = TipoCarro.DEPORTIVO;
                            break;
                        default:
                            tipoCarro = TipoCarro.CARRO;
                            break;
                    }

                    Propietario propietarioCarro = new Propietario(nombrePropietarioCarro);
                    Carro carro = new Carro(tipoCarro, placaCarro, modeloCarro, propietarioCarro);
                    parqueadero.agregarCarro(carro);
                    System.out.println("Carro agregado con éxito.");
                    break;

                case 2:
                    if (parqueadero.getCantidadPuestos() <= parqueadero.getCarros().size() + parqueadero.getMotos().size()) {
                        System.out.println("No hay espacio disponible en el parqueadero.");
                        break;
                    }
                    System.out.println("Ingrese la placa de la moto:");
                    String placaMoto = scanner.nextLine();
                    System.out.println("Ingrese el modelo de la moto:");
                    int modeloMoto = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ingrese el nombre del propietario:");
                    String nombrePropietarioMoto = scanner.nextLine();
                    System.out.println("Ingrese la velocidad máxima de la moto:");
                    int velocidadMaxima = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ingrese el tipo de moto (1: MOTO_CLASICA, 2: MOTO_HIBRIDA):");
                    int tipoMotoInt = scanner.nextInt();
                    scanner.nextLine();

                    TipoMoto tipoMoto;
                    switch (tipoMotoInt) {
                        case 2:
                            tipoMoto = TipoMoto.MOTO_HIBRIDA;
                            break;
                        default:
                            tipoMoto = TipoMoto.MOTO_CLASICA;
                            break;
                    }

                    Propietario propietarioMoto = new Propietario(nombrePropietarioMoto);
                    Moto moto = new Moto(placaMoto, modeloMoto, propietarioMoto, velocidadMaxima, tipoMoto);
                    parqueadero.agregarMoto(moto);
                    System.out.println("Moto agregada con éxito.");
                    break;

                case 3:
                    System.out.println("Ingrese la placa del vehículo a parquear:");
                    String placaParquear = scanner.nextLine();
                    Vehiculo vehiculoParquear = parqueadero.getCarro(placaParquear);
                    if (vehiculoParquear == null) {
                        vehiculoParquear = parqueadero.getMoto(placaParquear);
                    }
                    if (vehiculoParquear != null) {
                        System.out.println("Ingrese la tarifa por hora:");
                        double tarifaPorHora = scanner.nextDouble();
                        System.out.println("Ingrese las horas a estacionar:");
                        int horasEstacionadas = scanner.nextInt();
                        scanner.nextLine();  // Consumir el salto de línea

                        vehiculoParquear.setTarifaPorHora(tarifaPorHora);
                        vehiculoParquear.setHorasEstacionadas(horasEstacionadas);

                        if (parqueadero.buscarYParquearVehiculo(vehiculoParquear)) {
                            System.out.println("Vehículo parqueado con éxito.");
                        } else {
                            System.out.println("No hay puesto disponible.");
                        }
                    } else {
                        System.out.println("Vehículo no encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("Ingrese la placa del vehículo a liberar:");
                    String placaLiberar = scanner.nextLine();
                    Vehiculo vehiculoLiberar = parqueadero.getCarro(placaLiberar);
                    if (vehiculoLiberar == null) {
                        vehiculoLiberar = parqueadero.getMoto(placaLiberar);
                    }
                    if (vehiculoLiberar != null) {
                        double costo = liberarVehiculo(parqueadero, vehiculoLiberar);
                        System.out.println("Vehículo liberado con éxito. El costo total es: " + costo);
                    } else {
                        System.out.println("Vehículo no encontrado.");
                    }
                    break;

                case 5:
                    System.out.println("Ingrese la posición I del puesto:");
                    int posicionI = scanner.nextInt();
                    System.out.println("Ingrese la posición J del puesto:");
                    int posicionJ = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Propietario del vehículo en (" + posicionI + "," + posicionJ + "): "
                            + parqueadero.obtenerPropietario(posicionI, posicionJ));
                    break;

                case 6:
                    System.out.println("Generando reporte diario...");
                    Map<TipoVehiculo, Double> reporteDiario = parqueadero.generarReporteDiario(LocalDateTime.now().toLocalDate());
                    for (Map.Entry<TipoVehiculo, Double> entry : reporteDiario.entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                    break;

                case 7:
                    System.out.println("Ingrese el mes (1-12):");
                    int mes = scanner.nextInt();
                    System.out.println("Ingrese el año:");
                    int año = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Generando reporte mensual...");
                    Map<TipoVehiculo, Double> reporteMensual = parqueadero.generarReporteMensual(mes, año);
                    for (Map.Entry<TipoVehiculo, Double> entry : reporteMensual.entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                    break;

                case 8:
                    System.out.println("Calculando costo total...");
                    double costoTotal = 0.0;
                    for (Registro registro : parqueadero.getHistorialRegistros()) {
                        if (registro.getFechaSalida() != null) {
                            double costo = registro.calcularCosto();
                            System.out.println("Costo del vehículo con placa " + registro.getVehiculo().getPlaca() + ": " + costo);
                            costoTotal += costo;
                        }
                    }
                    System.out.println("Costo Total del Estacionamiento: " + costoTotal);
                    break;

                case 9:
                    System.out.println("Mostrando todos los registros:");
                    for (Registro registro : parqueadero.getHistorialRegistros()) {
                        System.out.println("Vehículo: " + registro.getVehiculo().getPlaca() + ", Entrada: " + registro.getFechaEntrada() + ", Salida: " + registro.getFechaSalida() + ", Costo: " + (registro.getFechaSalida() != null ? registro.calcularCosto() : "Aún estacionado"));
                    }
                    break;

                case 10:
                    continuar = false;
                    System.out.println("Saliendo del programa.");
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }

        scanner.close();
    }

    private static double liberarVehiculo(Parqueadero parqueadero, Vehiculo vehiculo) {
        for (int i = 0; i < parqueadero.getPuestos().length; i++) {
            for (int j = 0; j < parqueadero.getPuestos()[i].length; j++) {
                Puesto puesto = parqueadero.getPuestos()[i][j];
                if (puesto.estaOcupado() && puesto.getVehiculo().equals(vehiculo)) {
                    LocalDateTime fechaSalida = puesto.getVehiculo().getRegistro().getFechaEntrada().plusHours(vehiculo.getHorasEstacionadas());
                    Registro registro = puesto.getVehiculo().getRegistro();
                    registro.setFechaSalida(fechaSalida);
                    parqueadero.liberarPuesto(i, j);
                    return registro.calcularCosto();
                }
            }
        }
        return 0.0;
    }
}
