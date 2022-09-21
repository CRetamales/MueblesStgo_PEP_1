package cl.MSapp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Justificativos")
@Table(name = "justificativos")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Justificativos {

    /**
     * Atributos de la clase Justificativos
     * @param id - Identificador del justificativo
     * @param rut - Rut del empleado
     * @param fecha - Fecha del justificativo
     *              - Formato: YYYY-MM-DD
     *              - Ejemplo: 2020-01-01
     *              - Ejemplo: 2020-12-31
     *              - Ejemplo: 2020-02-29
     * @param categoria - Categoria del empleado
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "rut")
    private String rut;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "categoria")
    private String categoria;

}
