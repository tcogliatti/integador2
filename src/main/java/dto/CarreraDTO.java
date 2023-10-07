package dto;

public class CarreraDTO {

    private String nombre;

    private int duracion;
    private long cantInscriptos;

    public CarreraDTO( String nombre, int duracion, long cantInscriptos) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.cantInscriptos= cantInscriptos;
    }
    public CarreraDTO( String nombre, long cantInscriptos) {
        this.nombre = nombre;
        this.cantInscriptos= cantInscriptos;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public long getCantInscriptos() {
        return cantInscriptos;
    }
}
