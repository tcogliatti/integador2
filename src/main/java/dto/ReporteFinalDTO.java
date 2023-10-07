package dto;

public class ReporteFinalDTO {
    private String nombreCarrera;
    private Long estudiantesInscriptos;
    private Long estudiantesGraduados;
    private int anio;

    public ReporteFinalDTO(String nombreCarrera, Long estudiantesInscriptos, Long estudiantesGraduados, int anio) {
        this.nombreCarrera = nombreCarrera;
        this.estudiantesInscriptos = estudiantesInscriptos;
        this.estudiantesGraduados = estudiantesGraduados;
        this.anio = anio;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public Long getEstudiantesInscriptos() {
        return estudiantesInscriptos;
    }

    public Long getEstudiantesGraduados() {
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
