package co.edu.uniquindio.poo;

public class Carro extends Vehiculo {

    private TipoCarro tipoCarro;

    public Carro(TipoCarro tipoCarro, String placa, int modelo, Propietario propietario) {
        super(placa, modelo, propietario);
        this.tipoCarro = tipoCarro;
        Validaciones.validarPlaca(placa);
        Validaciones.validarModelo(modelo);
        Validaciones.validarPropietario(propietario);
    }

    public Carro(String placa, int modelo, Propietario propietario, TipoCarro tipoCarro) {
        super(placa, modelo, propietario);
        this.tipoCarro = tipoCarro;
    }

    public TipoCarro getTipoCarro() {
        return tipoCarro;
    }

    public void setTipoCarro(TipoCarro tipoCarro) {
        this.tipoCarro = tipoCarro;
    }

    @Override
    public double getTarifaPorHora() {
        switch (tipoCarro) {
            case CAMIONETA:
                return 15.0;
            case DEPORTIVO:
                return 20.0;
            case BUS:
                return 30.0;
            default:
                return 10.0;
        }
    }
}
