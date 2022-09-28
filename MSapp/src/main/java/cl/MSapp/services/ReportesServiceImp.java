package cl.MSapp.services;

import cl.MSapp.entities.Empleados;
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

    @Override
    public List<Reportes> listAllReportes() {
        List<Reportes> reportes = reportesRepository.findAll();

        //Por cada reporte en la lista de reportes se le asigna los datos del empleado
        for (Reportes reporte : reportes) {
            Empleados empleado = empleadosService.findEmpleadosByRut(reporte.getRut());
            reporte.setNombre_empleado(empleado.getNombres()+" "+empleado.getApellidos());
            reporte.setCategoria(empleado.getCategoria());
            //Años de servicio a partir de la fecha de ingreso
            reporte.setAnho_empresa(empleadosService.anhosServicio(reporte.getRut()));

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
