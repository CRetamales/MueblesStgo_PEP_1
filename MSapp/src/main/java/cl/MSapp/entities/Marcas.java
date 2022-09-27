package cl.MSapp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Marcas")
@Table(name = "marcas")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Marcas {

    /**
     * Atributos de la clase Marcas
     * @param id - Identificador de la marca
     * @param fecha - Fecha de la marca
     *              - Formato: yyyy-MM-dd
     *              - Ejemplo: 2020-01-01
     *              - Ejemplo: 2020-12-31
     *              - Ejemplo: 2020-02-29
     * @param hora - Hora de la marca
     *             - Formato: HH:mm
     *             - Ejemplo: 08:00
     *             - Ejemplo: 12:00
     *             - Ejemplo: 18:59
     * @param rut - Rut del empleado
     *            - Formato: 12.345.678-9
     *            - Ejemplo: 12.345.678-9
     *            - Ejemplo: 12.345.678-0
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "hora")
    private String hora;

    @Column(name = "rut")
    private String rut;

}
