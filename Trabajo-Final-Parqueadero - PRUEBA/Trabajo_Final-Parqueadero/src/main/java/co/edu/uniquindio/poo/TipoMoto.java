package co.edu.uniquindio.poo;
public enum TipoMoto {
    MOTO_CLASICA(TipoVehiculo.MOTO), 
    MOTO_HIBRIDA(TipoVehiculo.MOTO);

    private final TipoVehiculo tipoVehiculo;

    private TipoMoto(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }
}

