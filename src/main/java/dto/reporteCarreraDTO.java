package dto;

public class reporteCarreraDTO {
    private int idCarrera;
    private String nombre;

    public int getIdCarrera() {
        return idCarrera;
    }
    public String getNombre() {
        return nombre;
    }
    public int getDuracion() {
        return duracion;
    }

    private int duracion;

    public reporteCarreraDTO(int idCarrera, String nombre, int duracion) {
        this.idCarrera = idCarrera;
        this.nombre = nombre;
        this.duracion = duracion;
    }

}
