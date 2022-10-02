package cl.MSapp;


import java.text.SimpleDateFormat;
import java.util.Calendar;

// Metodos complementarios para el funcionamiento de la aplicacion
// y asi no haya codigo repetido
public class Complementarias
{
    private String fecha;

    /* Fecha */

    /**
     * Funcion que retorna el año de la hora
     * @return año (int)
     */
    public int getAnho(){
        return Integer.parseInt(this.fecha.substring(0,4));
    }

    /**
     * Funcion que retorna el mes de la hora
     * @return mes (int)
     */
    public int getMes(){
        return Integer.parseInt(this.fecha.substring(5,7));
    }

    /**
     * Funcion que retorna el dia de la hora
     * @return dia (int)
     */
    public int getDia(){
        return Integer.parseInt(this.fecha.substring(8,10));
    }

    /* Calculos */

    /**
     * Funcion que retorna la cantidad de años que hay entre una fecha y la actual
     * @param fecha1 - Fecha 1
     *               Formato: yyyy-mm-dd
     * @return años (int)
     */
    public int getCantidadAnhos_Actual(String fecha1){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        int anhoActual = Integer.parseInt(formatter.format(new java.util.Date()));
        int anho = Integer.parseInt(fecha1.substring(0,4));
        return anhoActual - anho;
    }


    /**
     * Funcion que retorna si es dia habil o no
     * @return true si es dia habil, false si no lo es
     */
    public boolean esDiaHabil() {
        int anho = this.getAnho();
        int mes = this.getMes();
        int dia = this.getDia();
        if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
            if (dia >= 1 && dia <= 31) {
                return true;
            }
        } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            if (dia >= 1 && dia <= 30) {
                return true;
            }
        } else if (mes == 2) {
            if (anho % 4 == 0) {
                if (dia >= 1 && dia <= 29) {
                    return true;
                }
            } else {
                if (dia >= 1 && dia <= 28) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Funcion que retorna si el dia se encuentra entre el lunes y el viernes
     * @return true si se encuentra entre lunes y viernes, false si no lo esta
     */
    public boolean isRangeLunesViernes(){
        Calendar c = Calendar.getInstance();
        c.set(this.getAnho(), this.getMes(), this.getDia());
        int dia = c.get(Calendar.DAY_OF_WEEK);
        if(dia >= 2 && dia <= 6){
            return true;
        }
        return false;
    }

}
