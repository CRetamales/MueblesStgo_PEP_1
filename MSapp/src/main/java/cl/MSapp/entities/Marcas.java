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

    /* Otros metodos */

    public int getHoras() {
        return Integer.parseInt(hora.substring(0, 2));
    }

    public int getMinutos() {
        return Integer.parseInt(hora.substring(3, 5));
    }

    /**
     * Funcion que dado marca de entrada y la marca de salida calcula la cantidad de horas trabajadas
     * @param entrada - Marca de entrada
     * @param salida - Marca de salida
     * @return - Cantidad de horas trabajadas
     */
    public static int calcularHorasTrabajadas(Marcas entrada, Marcas salida) {
        int horaEntradaInt = entrada.getHoras();
        int horaSalidaInt =  salida.getHoras();
        int minutosEntradaInt = entrada.getMinutos();
        int minutosSalidaInt =  salida.getMinutos();
        int horasTrabajadas = horaSalidaInt - horaEntradaInt;
        int minutosTrabajados = minutosSalidaInt - minutosEntradaInt;
        if (minutosTrabajados < 0) {
            horasTrabajadas -= 1;
        }
        return horasTrabajadas;
    }

    /**
     * Funcion que dado la marca de entrada y la marca de salida calcula la cantidad de horas extras trabajadas
     * @param entrada - Marca de entrada
     * @param salida - Marca de salida
     * @return - Cantidad de horas extras trabajadas
     */
    public static int calcularHorasExtras(Marcas entrada, Marcas salida) {
        int horaEntradaInt = entrada.getHoras();
        int horaSalidaInt = salida.getHoras();
        int minutosEntradaInt = entrada.getMinutos();
        int minutosSalidaInt = salida.getMinutos();
        int horasTrabajadas = horaSalidaInt - horaEntradaInt;
        int minutosTrabajados = minutosSalidaInt - minutosEntradaInt;
        if (minutosTrabajados < 0) {
            horasTrabajadas -= 1;
        }
        if (horasTrabajadas > 10) {
            return horasTrabajadas - 10;
        } else {
            return 0;
        }
    }

    /**
     * Funcion que dado la marca de entrada determina cuantos minutos se ha retrasado
     * @param entrada - Marca de entrada
     * @return - Cantidad de minutos de retraso
     */
    public static int calcularRetraso(Marcas entrada) {
        int horaEntradaInt = entrada.getHoras();
        int minutosEntradaInt = entrada.getMinutos();
        int minutosRetraso = 0;
        if (horaEntradaInt > 8) {
            minutosRetraso += (horaEntradaInt - 8) * 60;
        }
        if (minutosEntradaInt > 0) {
            minutosRetraso += minutosEntradaInt;
        }
        return minutosRetraso;
    }


}
