package dto;

public class CarreraCiudadDTO {


    private String nombre_carrera;
    private String nombre_estudiante;
    private String apellido_estudiante;
    private String ciudad;
    private int LU;


    public CarreraCiudadDTO( String nombre_carrera, String nombre_estudiante,String apellido_estudiante, String ciudad, int LU) {

        this.nombre_carrera = nombre_carrera;
        this.nombre_estudiante = nombre_estudiante;
        this.apellido_estudiante= apellido_estudiante;
        this.ciudad = ciudad;
        this.LU = LU;
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

    public int getLU() { return LU; }


}
