package cl.MSapp.services;

import cl.MSapp.entities.Empleados;
import cl.MSapp.repositories.EmpleadosRepository;
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
