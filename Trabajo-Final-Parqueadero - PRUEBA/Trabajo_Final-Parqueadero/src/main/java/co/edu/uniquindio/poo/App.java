package co.edu.uniquindio.poo;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        // Crear propietarios
        Propietario propietario1 = new Propietario("Juan Perez");
        Propietario propietario2 = new Propietario("Maria Gomez");

        // Crear vehículos
        Carro carro1 = new Carro(TipoCarro.CAMIONETA, "ABC123", 2020, propietario1);
        Moto moto1 = new Moto("XYZ789", 2018, propietario2, 120);

        // Crear parqueadero con 3 columnas y 3 filas
        Parqueadero parqueadero = new Parqueadero(3, 3);

        // Agregar vehículos al parqueadero
        parqueadero.agregarCarro(carro1);
        parqueadero.agregarMoto(moto1);

        // Buscar y parquear vehículos
        parqueadero.buscarYParquearVehiculo(carro1);
        parqueadero.buscarYParquearVehiculo(moto1);

        // Mostrar el propietario del vehículo en una posición específica
        System.out.println(parqueadero.obtenerPropietario(0, 0)); // Debería mostrar el nombre del propietario del carro1
        System.out.println(parqueadero.obtenerPropietario(0, 1)); // Debería mostrar el nombre del propietario de la moto1

        // Liberar un puesto y mostrar el propietario nuevamente
        parqueadero.liberarPuesto(0, 0);
        System.out.println(parqueadero.obtenerPropietario(0, 0)); // Debería mostrar "El puesto está vacío."

        // Liberar el puesto de la moto para evitar el error de fecha de salida nula
        parqueadero.liberarPuesto(0, 1);

        // Generar reporte diario y mensual
        System.out.println("Reporte Diario: " + parqueadero.generarReporteDiario(LocalDate.now()));
        System.out.println("Reporte Mensual: " + parqueadero.generarReporteMensual(LocalDate.now().getMonthValue(), LocalDate.now().getYear()));
    }
}
