package cl.MSapp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Reportes")
@Table(name = "reportes")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Reportes {

    /**
     * Atributos de la clase Reportes
     * @param id - Identificador del reporte
     * @param rut - Rut del empleado
     * @param nombre_empleado - Nombre del empleado
     * @param categoria - Categoria del empleado
     * @param anho_empresa - Años de servicio en la empresa del empleado
     * @param sueldo_fijo - Sueldo fijo del empleado
     * @param bonificacion_anho - Bonificación por años de servicio del empleado
     * @param pago_horas_extras - Pago por horas extras del empleado
     * @param descuentos - Descuentos del empleado
     * @param sueldo_bruto - Sueldo bruto del empleado
     * @param cotizacion_previsional - Cotización previsional del empleado
     * @param cotizacion_salud - Cotización de salud del empleado
     * @param sueldo_final - Sueldo final del empleado
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "rut")
    private String rut;

    @Column(name = "nombre_empleado")
    private String nombre_empleado;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "anho_empresa")
    private int anho_empresa;

    @Column(name = "sueldo_fijo")
    private int sueldo_fijo;

    @Column(name = "bonificacion_anho")
    private int bonificacion_anho;

    @Column(name = "pago_horas_extras")
    private int pago_horas_extras;

    @Column(name = "descuentos")
    private int descuentos;

    @Column(name = "sueldo_bruto")
    private int sueldo_bruto;

    @Column(name = "cotizacion_previsional")
    private int cotizacion_previsional;

    @Column(name = "cotizacion_salud")
    private int cotizacion_salud;

    @Column(name = "sueldo_final")
    private int sueldo_final;

}
