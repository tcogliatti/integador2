package Repository;

import dto.ReporteFinalDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReporteFinalRepository {

    public List<ReporteFinalDTO> reporteFinal(EntityManager em) {

        Query query = em.createNativeQuery("WITH  " +
                "Inscriptos AS( " +
                " SELECT c.nombre AS carrera_nombre, " +
                "m.inscripcion AS anio, " +
                "COUNT(m.estudiante_dni) AS inscriptos, " +
                "0 AS graduados " +
                "FROM Matricula m INNER JOIN Carrera c ON m.carrera_idCarrera=c.idCarrera " +
                "GROUP BY carrera_nombre, m.inscripcion) ," +
                "" +
                "Graduados AS( " +
                "SELECT c.nombre AS carrera_nombre, " +
                "m.graduacion AS anio, " +
                "0 AS inscriptos, " +
                "COUNT(m.estudiante_dni) AS graduados " +
                "FROM Matricula m INNER JOIN Carrera c ON m.carrera_idCarrera=c.idCarrera " +
                " GROUP BY carrera_nombre, m.graduacion ) " +
                "SELECT IF(i.carrera_nombre IS NOT NULL, i.carrera_nombre, g.carrera_nombre) AS carrera, IF(i.carrera_nombre IS NOT NULL, i.anio, g.anio) AS anio, COALESCE(i.inscriptos, 0) AS inscriptos,  COALESCE(g.graduados, 0) AS graduados " +
                "FROM Inscriptos i " +
                "LEFT JOIN " +
                "(SELECT * FROM Graduados) g " +
                "ON g.carrera_nombre = i.carrera_nombre AND g.anio = i.anio " +
                "UNION  " +
                "SELECT  IF(i.carrera_nombre IS NOT NULL, i.carrera_nombre, g.carrera_nombre) AS carrera, IF(i.carrera_nombre IS NOT NULL, i.anio, g.anio) AS anio, COALESCE(i.inscriptos, 0) AS inscriptos,  COALESCE(g.graduados, 0) AS graduados " +
                "FROM Inscriptos i " +
                "RIGHT JOIN  " +
                "(SELECT * FROM Graduados) g " +
                "ON g.carrera_nombre = i.carrera_nombre AND g.anio = i.anio " +
                "ORDER BY carrera,  anio ASC ");
        List<Object[]> resultado = query.getResultList();
        List<ReporteFinalDTO> reporte = new ArrayList<>();

        for (Object[] r : resultado) {
            String nombre = (String) r[0];
            int anio = (Integer) r[1];
            Long inscriptos = (Long) r[2];
            Long graduados = (Long) r[3];
            reporte.add(new ReporteFinalDTO(nombre,inscriptos,graduados,anio));
            //System.out.println(nombre + " , Año: " + anio + ", Inscriptos: " + inscriptos + ", Graduados: " + graduados);

        }
        return reporte;
    }

}
