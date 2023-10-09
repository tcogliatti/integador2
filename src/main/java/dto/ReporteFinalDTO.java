package dto;

public class ReporteFinalDTO {
    private String nombreCarrera;
    private int estudiantesInscriptos;
    private int estudiantesGraduados;
    private int anio;

    public ReporteFinalDTO(int anio, String nombreCarrera, int estudiantesInscriptos, int estudiantesGraduados) {
        this.nombreCarrera = nombreCarrera;
        this.estudiantesInscriptos = estudiantesInscriptos;
        this.estudiantesGraduados = estudiantesGraduados;
        this.anio = anio;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public int getEstudiantesInscriptos() {
        return estudiantesInscriptos;
    }

    public int getEstudiantesGraduados() {
        return estudiantesGraduados;
    }


    public int getAnio() {
        return anio;
    }

    @Override
    public String toString() {
        return "Carrera='" + nombreCarrera  +
                ", Año=" + anio +
                ", Inscriptos=" + estudiantesInscriptos +
                ", Graduados=" + estudiantesGraduados;
    }
}
