package cl.MSapp.repositories;

import cl.MSapp.entities.Justificativos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JustificativosRepository extends JpaRepository<Justificativos, Long> {
    @Query(value = "SELECT * FROM justificativos WHERE rut = ?1", nativeQuery = true)
    public List<Justificativos> findByRut(String rut);

}
