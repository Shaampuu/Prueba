package co.edu.uniquindio.poo;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class AppController {
    private Parqueadero parqueadero;

    public AppController(Parqueadero parqueadero) {
        this.parqueadero = parqueadero;
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        boolean ejecutando = true;

        while (ejecutando) {
            System.out.println("\n=== MENÚ ===");
            System.out.println("1. Registrar entrada de vehículo");
            System.out.println("2. Registrar salida de vehículo");
            System.out.println("3. Imprimir reporte diario");
            System.out.println("4. Imprimir reporte mensual");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarEntradaVehiculo();
                    break;
                case 2:
                    registrarSalidaVehiculo();
                    break;
                case 3:
                    imprimirReporteDiario();
                    break;
                case 4:
                    imprimirReporteMensual();
                    break;
                case 5:
                    ejecutando = false;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        }

        scanner.close();
    }

    private void registrarEntradaVehiculo() {
        Scanner scanner = new Scanner(System.in);

        // Solicitar datos del vehículo al usuario
        System.out.println("Ingrese los datos del vehículo:");
        System.out.print("Placa: ");
        String placa = scanner.nextLine();
        System.out.print("Modelo: ");
        int modelo = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner
        System.out.print("Nombre del propietario: ");
        String nombrePropietario = scanner.nextLine();

        // Crear objeto Propietario
        Propietario propietario = new Propietario(nombrePropietario);

        // Crear objeto Vehiculo
        Vehiculo vehiculo = null;
        System.out.println("Seleccione el tipo de vehículo:");
        System.out.println("1. Carro");
        System.out.println("2. Moto");
        System.out.print("Opción: ");
        int opcionTipoVehiculo = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        switch (opcionTipoVehiculo) {
            case 1:
                System.out.println("Seleccione el tipo de carro:");
                System.out.println("1. Camioneta");
                System.out.println("2. Deportivo");
                System.out.println("3. Bus");
                System.out.print("Opción: ");
                int opcionTipoCarro = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer del scanner

                TipoCarro tipoCarro = null;
                switch (opcionTipoCarro) {
                    case 1:
                        tipoCarro = TipoCarro.CAMIONETA;
                        break;
                    case 2:
                        tipoCarro = TipoCarro.DEPORTIVO;
                        break;
                    case 3:
                        tipoCarro = TipoCarro.BUS;
                        break;
                    default:
                        System.out.println("Opción inválida. Se asignará el tipo por defecto.");
                }

                vehiculo = new Carro(tipoCarro, placa, modelo, propietario);
                break;
            case 2:
                System.out.print("Velocidad máxima: ");
                int velocidadMaxima = scanner.nextInt();
                vehiculo = new Moto(placa, modelo, propietario, velocidadMaxima);
                break;
            default:
                System.out.println("Opción inválida. No se registrará el vehículo.");
        }

        // Registrar la entrada del vehículo en el parqueadero
        if (vehiculo != null) {
            if (parqueadero.buscarYParquearVehiculo(vehiculo)) {
                System.out.println("Vehículo registrado exitosamente.");
            } else {
                System.out.println("No hay puestos disponibles. No se pudo registrar el vehículo.");
            }
        }
        scanner.close();
    }

    private void registrarSalidaVehiculo() {
        Scanner scanner = new Scanner(System.in);

        // Solicitar datos del vehículo al usuario
        System.out.println("Ingrese la placa del vehículo que desea retirar:");
        System.out.print("Placa: ");
        String placa = scanner.nextLine();

        // Buscar el vehículo en el parqueadero
        Vehiculo vehiculo = parqueadero.buscarVehiculo(placa);

        // Registrar la salida del vehículo del parqueadero
        if (vehiculo != null) {
            parqueadero.registrarSalidaVehiculo(vehiculo);
            System.out.println("Vehículo retirado exitosamente.");
        } else {
            System.out.println("No se encontró ningún vehículo con la placa especificada.");
        }
        scanner.close();
    }

    private void imprimirReporteDiario() {
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Generar y mostrar el reporte diario del parqueadero
        Map<TipoVehiculo, Double> reporteDiario = parqueadero.generarReporteDiario(fechaActual);
        System.out.println("Reporte Diario del Parqueadero (" + fechaActual + "):");
        for (Map.Entry<TipoVehiculo, Double> entry : reporteDiario.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
    }

    private void imprimirReporteMensual() {
        Scanner scanner = new Scanner(System.in);

        // Solicitar mes y año al usuario
        System.out.println("Ingrese el mes y el año del reporte mensual:");
        System.out.print("Mes (número): ");
        int mes = scanner.nextInt();
        System.out.print("Año: ");
        int año = scanner.nextInt();

        // Generar y mostrar el reporte mensual del parqueadero
        Map<TipoVehiculo, Double> reporteMensual = parqueadero.generarReporteMensual(mes, año);
        System.out.println("Reporte Mensual del Parqueadero (" + mes + "/" + año + "):");
        for (Map.Entry<TipoVehiculo, Double> entry : reporteMensual.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }

        scanner.close();
    }

}
