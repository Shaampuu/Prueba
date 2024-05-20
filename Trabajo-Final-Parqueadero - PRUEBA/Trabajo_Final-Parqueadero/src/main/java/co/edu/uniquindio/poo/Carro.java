package co.edu.uniquindio.poo;

public class Carro extends Vehiculo {

    private TipoCarro tipoCarro;
    public Carro( TipoCarro tipoCarro, String placa, int modelo, Propietario propietario, TipoVehiculo tipoVehiculo) {
        super(placa, modelo, propietario, tipoVehiculo);
        this.tipoCarro=tipoCarro;
        validarPlaca(placa);
        validarModelo(modelo);
        validarPropietario(propietario);
    }

    
    public TipoCarro getTipoCarro() {
        return tipoCarro;
    }

    public void setTipoCarro(TipoCarro tipoCarro) {
        this.tipoCarro = tipoCarro;
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
    @Override
    public double getTarifaPorHora() {
        // Definir la tarifa por hora según el tipo de carro
        switch (tipoCarro) {
            case CAMIONETA:
                return 15.0; // Tarifa para una camioneta
            case DEPORTIVO:
                return 20.0; // Tarifa para un carro deportivo
            case BUS:
                return 30.0; // Tarifa para un autobús
            default:
                return 10.0; // Tarifa por defecto para otros tipos de carro
        }
}
}
