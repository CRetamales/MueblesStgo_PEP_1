package cl.MSapp.repositories;

import cl.MSapp.entities.Empleados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpleadosRepository extends JpaRepository<Empleados, Long> {
    @Query(value = "SELECT * FROM empleados WHERE rut = ?1", nativeQuery = true)
    public List<Empleados> findByRut(String rut);

}
