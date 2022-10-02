package cl.MSapp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;

@Entity(name = "Empleados")
@Table(name = "empleados")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Empleados {

    /**
     * Atributos de la clase Empleados
     * @param id - Identificador del empleado
     * @param rut - Rut del empleado
     * @param apellidos - Apellidos del empleado
     * @param nombres - Nombres del empleado
     * @param fecha_nacimiento - Fecha de nacimiento del empleado
     * @param categoria - Categoria del empleado
     * @param fecha_ingreso - Fecha de ingreso del empleado
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "rut")
    private String rut;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "fecha_nacimiento")
    private String fecha_nacimiento;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "fecha_ingreso")
    private String fecha_ingreso;

    //Otras funciones complementarias

    public String getNombreCompleto(){
        return this.nombres + " " + this.apellidos;
    }

}
