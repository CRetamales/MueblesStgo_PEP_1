package cl.MSapp.services;

import cl.MSapp.entities.Empleados;
import cl.MSapp.repositories.EmpleadosRepository;
import cl.MSapp.entities.Reportes;
import cl.MSapp.repositories.ReportesRepository;
import cl.MSapp.Complementarias;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpleadosServiceImp implements EmpleadosService {

    @Autowired
    private EmpleadosRepository empleadosRepository;

    @Autowired
    private ReportesRepository reportesRepository;

    @Override
    public List<Empleados> listAllEmpleados() {
        return empleadosRepository.findAll();
    }

    @Override
    public Empleados getEmpleadosById(Long id) {
        return empleadosRepository.findById(id).orElse(null);
    }

    @Override
    public Empleados createEmpleados(Empleados empleados) {
        Reportes reporte = new Reportes();
        reporte.setRut(empleados.getRut());
        reporte.setNombre_empleado("a");
        reporte.setCotizacion_salud(1);
        reporte.setCategoria("C");
        reporte.setDescuentos(1);
        reporte.setSueldo_bruto(1);
        reporte.setCotizacion_previsional(1);
        reporte.setSueldo_fijo(1);
        reporte.setBonificacion_anho(1);
        reporte.setPago_horas_extras(1);
        reporte.setAnho_empresa(1);
        reporte.setSueldo_final(1);
        reportesRepository.save(reporte);
        return empleadosRepository.save(empleados);
    }

    @Override
    public Empleados updateEmpleados(Empleados empleados) {
        Empleados empleadoNuevo = getEmpleadosById(empleados.getId());
        if(empleadoNuevo != null){
            empleadoNuevo.setId(empleados.getId());
            empleadoNuevo.setRut(empleados.getRut());
            empleadoNuevo.setApellidos(empleados.getApellidos());
            empleadoNuevo.setNombres(empleados.getNombres());
            empleadoNuevo.setFecha_nacimiento(empleados.getFecha_nacimiento());
            empleadoNuevo.setCategoria(empleados.getCategoria());
            empleadoNuevo.setFecha_ingreso(empleados.getFecha_ingreso());
            return empleadosRepository.save(empleadoNuevo);
        }
        return null;
    }

    @Override
    public void deleteEmpleados(Long id) {
        Empleados empleado = getEmpleadosById(id);
        if(empleado != null){
            empleadosRepository.delete(empleado);
        }
        return;
    }

    //OTROS METODOS
    @Override
    public Empleados findEmpleadosByRut(String rut) {
        return empleadosRepository.findByRut(rut).get(0);
    }

    //METODO PARA CALCULAR AÑOS DE SERVICIO
    @Override
    public int anhosServicio(String rut) {
        Empleados empleado = findEmpleadosByRut(rut);
        Complementarias complementarias = new Complementarias();
        return complementarias.getCantidadAnhos_Actual(empleado.getFecha_ingreso());
    }

}
