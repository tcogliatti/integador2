package dto;

public class EstudianteDTO {

    private int dni;

    private int lu;

    private String nombre;
    private String apellido;

    private int edad;

    private String genero;

    private String direccion;

    public EstudianteDTO(int dni, int lu, String nombre, String apellido, int edad, String genero, String direccion) {
        this.dni = dni;
        this.lu = lu;
        this.apellido = apellido;
        this.nombre= nombre;
        this.edad = edad;
        this.genero = genero;
        this.direccion = direccion;
    }
    public int getDni() {
        return dni;
    }

    public int getLu() {
        return lu;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public String getGenero() {
        return genero;
    }

    public String getDireccion() {
        return direccion;
    }

    @Override
    public String toString() {
        return "Estudiante {" +
                "dni=" + dni +
                ", lu=" + lu +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
