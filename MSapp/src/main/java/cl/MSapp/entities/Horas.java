package cl.MSapp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Horas")
@Table(name = "horas")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Horas {

    /**
     * Atributos de la clase Horas
     * @param id - Identificador de la hora
     * @param rut - Rut del empleado
     * @param fecha - Fecha de la hora
     * @param categoria - Categoria de la Jefatura
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
