package Repository;

import dto.ReporteFinalDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReporteFinalRepository {

    public List<ReporteFinalDTO> reporteFinal(EntityManager em) {

        Query query = em.createNativeQuery("SELECT IFNULL(aaa.anio, bbb.anio) AS anio,\n" +
                "       IFNULL(aaa.nombre, bbb.nombre) AS nombre,\n" +
                "       IFNULL(Inscriptos, 0) AS inscriptos,\n" +
                "       IFNULL(Graduados, 0) AS graduados\n" +
                "FROM\n" +
                "  (SELECT anio, C.nombre, COUNT(m.inscripcion = anio) AS Inscriptos\n" +
                "   FROM Matricula m\n" +
                "   JOIN (SELECT DISTINCT m.inscripcion AS anio FROM Matricula m\n" +
                "         WHERE m.inscripcion != 0\n" +
                "        ) anios\n" +
                "   ON m.inscripcion = anios.anio\n" +
                "   JOIN Carrera C ON C.idCarrera = m.carrera_idCarrera\n" +
                "   GROUP BY anio, C.nombre) aaa\n" +
                "\n" +
                "LEFT JOIN\n" +
                "\n" +
                "  (SELECT anio, C.nombre, COUNT(m.graduacion = anio) AS Graduados\n" +
                "   FROM Matricula m\n" +
                "   JOIN (\n" +
                "         SELECT DISTINCT m.graduacion as anio FROM Matricula m\n" +
                "         WHERE m.graduacion != 0\n" +
                "         ORDER BY anio) anios\n" +
                "   ON m.graduacion = anios.anio\n" +
                "   JOIN Carrera C ON C.idCarrera = m.carrera_idCarrera\n" +
                "   GROUP BY anio, C.nombre) bbb\n" +
                "\n" +
                "ON aaa.anio = bbb.anio AND aaa.nombre = bbb.nombre\n" +
                "\n" +
                "UNION\n" +
                "\n" +
                "SELECT IFNULL(aaa.anio, bbb.anio) AS anio,\n" +
                "       IFNULL(aaa.nombre, bbb.nombre) AS nombre,\n" +
                "       IFNULL(Inscriptos, 0) AS Inscriptos,\n" +
                "       IFNULL(Graduados, 0) AS Graduados\n" +
                "FROM\n" +
                "  (SELECT anio, C.nombre, COUNT(m.inscripcion = anio) AS Inscriptos\n" +
                "   FROM Matricula m\n" +
                "   JOIN (SELECT DISTINCT m.inscripcion AS anio FROM Matricula m\n" +
                "         WHERE m.inscripcion != 0\n" +
                "         ) anios\n" +
                "   ON m.inscripcion = anios.anio\n" +
                "   JOIN Carrera C ON C.idCarrera = m.carrera_idCarrera\n" +
                "   GROUP BY anio, C.nombre) aaa\n" +
                "\n" +
                "RIGHT JOIN\n" +
                "\n" +
                "  (SELECT anio, C.nombre, COUNT(m.graduacion = anio) AS Graduados\n" +
                "   FROM Matricula m\n" +
                "   JOIN (\n" +
                "         SELECT DISTINCT m.graduacion as anio FROM Matricula m\n" +
                "         WHERE m.graduacion != 0\n" +
                "         ORDER BY anio) anios\n" +
                "   ON m.graduacion = anios.anio\n" +
                "   JOIN Carrera C ON C.idCarrera = m.carrera_idCarrera\n" +
                "   GROUP BY anio, C.nombre) bbb\n" +
                "\n" +
                "ON aaa.anio = bbb.anio AND aaa.nombre = bbb.nombre\n" +
                "\n" +
                "ORDER BY nombre, anio");
        List<Object[]> resultado = query.getResultList();
        List<ReporteFinalDTO> reporte = new ArrayList<>();


        for (Object[] r : resultado) {
            Integer anio = ((Number) r[0]).intValue();
            String nombre = (String) r[1];
            Integer inscriptos = ((Number) r[2]).intValue();
            Integer graduados = ((Number) r[3]).intValue();
            reporte.add(new ReporteFinalDTO(anio, nombre, inscriptos, graduados));
        }
        return reporte;
    }

}
