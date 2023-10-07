package dto;

public class CarreraCiudadDTO {


    private String nombre_carrera;
    private String nombre_estudiante;
    private String apellido_estudiante;
    private String ciudad;


    public CarreraCiudadDTO( String nombre_carrera, String nombre_estudiante,String apellido_estudiante, String ciudad) {

        this.nombre_carrera = nombre_carrera;
        this.nombre_estudiante = nombre_estudiante;
        this.apellido_estudiante= apellido_estudiante;
        this.ciudad = ciudad;
    }



    public String getNombre_carrera() {
        return nombre_carrera;
    }

    public String getNombre_estudiante() {
        return nombre_estudiante;
    }
    public String getApellido_estudiante() {
        return apellido_estudiante;
    }
    public String getCiudad() {
        return ciudad;
    }
}
