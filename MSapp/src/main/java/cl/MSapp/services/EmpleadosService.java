package cl.MSapp.services;

import cl.MSapp.entities.Empleados;

import java.util.List;

public interface EmpleadosService {
    //CRUD
    //READ ALL EMPLEADOS
    public List<Empleados> listAllEmpleados();
    //BY ID
    public Empleados getEmpleadosById(Long id);

    //CREATE
    public Empleados createEmpleados(Empleados empleados);
    //UPDATE
    public Empleados updateEmpleados(Empleados empleados);
    //DELETE
    public void deleteEmpleados(Long id);

    //OTHERS
    public Empleados findEmpleadosByRut(String rut);
}
