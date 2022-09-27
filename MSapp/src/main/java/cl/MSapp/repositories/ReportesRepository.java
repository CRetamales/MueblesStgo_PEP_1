package cl.MSapp.repositories;

import cl.MSapp.entities.Reportes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportesRepository extends JpaRepository<Reportes, Long> {
    @Query(value = "SELECT * FROM reportes WHERE rut = ?1", nativeQuery = true)
    public List<Reportes> findByRut(String rut);

}
