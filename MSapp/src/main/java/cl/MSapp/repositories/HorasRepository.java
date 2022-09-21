package cl.MSapp.repositories;

import cl.MSapp.entities.Horas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HorasRepository extends JpaRepository<Horas, Long> {
    @Query(value = "SELECT * FROM horas WHERE rut = ?1", nativeQuery = true)
    public List<Horas> findByRut(String rut);

}
