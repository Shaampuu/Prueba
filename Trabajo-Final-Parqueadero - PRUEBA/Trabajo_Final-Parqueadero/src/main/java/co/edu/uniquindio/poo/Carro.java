package co.edu.uniquindio.poo;

public class Carro extends Vehiculo {

    public Carro(String placa, int modelo, Propietario propietario, TipoVehiculo tipoVehiculo) {
        super(placa, modelo, propietario, tipoVehiculo);
        validarPlaca(placa);
        validarModelo(modelo);
        validarPropietario(propietario);
    }

    private void validarPlaca(String placa) {
        assert placa != null && !placa.isEmpty() : "La placa no puede ser nula o vacía";
    }

    private void validarModelo(int modelo) {
        assert modelo > 0 : "El modelo debe ser un número positivo";
    }

    private void validarPropietario(Propietario propietario) {
        assert propietario != null : "El propietario no puede ser nulo";
    }
}
