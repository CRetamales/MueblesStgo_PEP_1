package cl.MSapp.services;

import cl.MSapp.Complementarias;
import cl.MSapp.entities.Empleados;
import cl.MSapp.entities.Marcas;
import cl.MSapp.entities.Reportes;
import cl.MSapp.repositories.ReportesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportesServiceImp implements ReportesService {

    @Autowired
    private ReportesRepository reportesRepository;

    @Autowired
    private EmpleadosService empleadosService;

    @Autowired
    private MarcasService marcasService;

    @Autowired
    private HorasService horasService;

    @Autowired
    private JustificativosService justificativosService;

    @Override
    public List<Reportes> listAllReportes() {
        List<Reportes> reportes = reportesRepository.findAll();
        int sueldo_fijo = 0;
        //Por cada reporte en la lista de reportes se le asigna los datos del empleado
        for (Reportes reporte : reportes) {
            Empleados empleado = empleadosService.findEmpleadosByRut(reporte.getRut());
            String categoria_empleado = empleado.getCategoria();
            int servicio_ahnos = empleadosService.anhosServicio(reporte.getRut());
            int bonificacion_anhos = 0;
            //Se asigna el sueldo fijo segun la categoria del empleado
            switch (categoria_empleado) {
                case "A":
                    sueldo_fijo = 1700000;
                    break;
                case "B":
                    sueldo_fijo = 1200000;
                    break;
                case "C":
                    sueldo_fijo = 800000;
                    break;
            }
            //Se asigna la bonificacion segun los anhos de antiguedad del empleado
            if (servicio_ahnos >= 5 && servicio_ahnos < 10) {
                bonificacion_anhos = (int)( (float) sueldo_fijo * 0.05); //5%
            } else if (servicio_ahnos >= 10 && servicio_ahnos < 15) {
                bonificacion_anhos = (int)( (float) sueldo_fijo * 0.08); //8%
            } else if (servicio_ahnos >= 15 && servicio_ahnos < 20) {
                bonificacion_anhos = (int)( (float) sueldo_fijo * 0.11); //11%
            } else if (servicio_ahnos >= 20 && servicio_ahnos < 25) {
                bonificacion_anhos = (int)( (float) sueldo_fijo * 0.14); //14%
            } else if (servicio_ahnos >= 25) {
                bonificacion_anhos = (int)( (float) sueldo_fijo * 0.17); //17%
            }

            int horas_extras = 0;
            int monto_horas_extras = 0;
            int descuentos = 0;
            //int dias_trabajados = 0;
            Complementarias complementarias = new Complementarias();
            List<Marcas> marcas = marcasService.findMarcasByRut(reporte.getRut());
            //Se recorren las marcas del empleado (descuentos y horas extras)
            //La posicion i es la marca de entrada y la posicion i+1 es la marca de salida
            for (int i = 0; i < marcas.size(); i++){
                if (i % 2 == 0){
                    //Si la marca se encuentra entre el lunes y el viernes
                    complementarias.setFecha(marcas.get(i).getFecha());
                    if(complementarias.isRangeLunesViernes()){
                        //dias_trabajados++;
                        //Si hay horas extras
                        if(marcas.get(0).calcularHorasTrabajadas(marcas.get(i+1)) > 10){
                            //Despues se valida si el empleado tiene justificativo para las horas extras
                            horas_extras += marcas.get(0).calcularHorasTrabajadas(marcas.get(i+1)) - 8;
                        }
                        //Si hay descuentos
                        int retraso = marcas.get(i).calcularRetraso();
                        if(retraso > 10 && retraso <= 25)
                        {
                            descuentos += 1;
                        } else if (retraso > 25 && retraso <= 45){
                            descuentos += 3;
                        } else if (retraso > 45 && retraso <= 70){
                            descuentos += 6;
                        } else if (retraso > 70){
                            //Se debe verificar si el empleado tiene justificativo para no descontar
                            descuentos += 15;
                        }
                    }
                }
            }

            //Se calcula el monto de las horas extras
            //Dependiendo de la categoria del empleado
            switch (categoria_empleado) {
                case "A":
                    monto_horas_extras = horas_extras * 25000;
                    break;
                case "B":
                    monto_horas_extras = horas_extras * 20000;
                    break;
                case "C":
                    monto_horas_extras = horas_extras * 10000;
                    break;
            }

            //Se calcula los montos de los descuentos
            //Dado el sueldo fijo
            int monto_descuentos = (int) ((float) sueldo_fijo * ((float) descuentos / 100));

            //Se calcula el sueldo bruto
            int sueldo_bruto = sueldo_fijo + bonificacion_anhos + monto_horas_extras - monto_descuentos;

            //Cotizaciones
            int cotizacion_previsional = (int) ((float) sueldo_bruto * 0.10); //10%
            int cotizacion_salud = (int) ((float) sueldo_bruto * 0.08); //8%

            //Se calcula el sueldo final
            int sueldo_final = sueldo_bruto - cotizacion_previsional - cotizacion_salud;


            // Seteamos los datos del empleado al reporte
            reporte.setNombre_empleado(empleado.getNombres()+" "+empleado.getApellidos());
            reporte.setCategoria(empleado.getCategoria());
            reporte.setSueldo_fijo(sueldo_fijo);
            //Años de servicio a partir de la fecha de ingreso
            reporte.setAnho_empresa(servicio_ahnos);
            reporte.setBonificacion_anho(bonificacion_anhos);
            reporte.setPago_horas_extras(monto_horas_extras);
            reporte.setDescuentos(monto_descuentos);
            reporte.setSueldo_bruto(sueldo_bruto);
            reporte.setCotizacion_previsional(cotizacion_previsional);
            reporte.setCotizacion_salud(cotizacion_salud);
            reporte.setSueldo_final(sueldo_final);
        }
        return reportesRepository.findAll();
    }

    @Override
    public Reportes getReportesById(Long id) {
        return reportesRepository.findById(id).orElse(null);
    }

    @Override
    public Reportes createReportes(Reportes reportes) {
        return reportesRepository.save(reportes);
    }

    @Override
    public Reportes updateReportes(Reportes reportes){
        Reportes reportesNueva = getReportesById(reportes.getId());
        if(reportesNueva != null){
            reportesNueva.setId(reportes.getId());
            reportesNueva.setRut(reportes.getRut());
            reportesNueva.setNombre_empleado(reportes.getNombre_empleado());
            reportesNueva.setCategoria(reportes.getCategoria());
            reportesNueva.setAnho_empresa(reportes.getAnho_empresa());
            reportesNueva.setSueldo_fijo(reportes.getSueldo_fijo());
            reportesNueva.setBonificacion_anho(reportes.getBonificacion_anho());
            reportesNueva.setPago_horas_extras(reportes.getPago_horas_extras());
            reportesNueva.setDescuentos(reportes.getDescuentos());
            reportesNueva.setSueldo_bruto(reportes.getSueldo_bruto());
            reportesNueva.setCotizacion_previsional(reportes.getCotizacion_previsional());
            reportesNueva.setCotizacion_salud(reportes.getCotizacion_salud());
            reportesNueva.setSueldo_final(reportes.getSueldo_final());
            return reportesRepository.save(reportesNueva);
        }
        return null;
    }

    @Override
    public void deleteReportes(Long id) {
        Reportes reportes = getReportesById(id);
        if(reportes != null){
            reportesRepository.delete(reportes);
        }
        return;
    }

    @Override
    public List<Reportes> findReportesByRut(String rut) {
        return reportesRepository.findByRut(rut);
    }

}
