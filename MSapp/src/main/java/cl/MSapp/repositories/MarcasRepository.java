package cl.MSapp.repositories;

import cl.MSapp.entities.Marcas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MarcasRepository extends JpaRepository<Marcas, Long> {

    @Query(value = "SELECT * FROM marcas WHERE rut = ?1", nativeQuery = true)
    public Marcas findByRut(String rut);
}
