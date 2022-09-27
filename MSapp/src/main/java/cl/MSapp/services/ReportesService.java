package cl.MSapp.services;

import cl.MSapp.entities.Reportes;

import java.util.List;

public interface ReportesService {
    //CRUD
    //READ ALL REPORTES
    public List<Reportes> listAllReportes();
    //BY ID
    public Reportes getReportesById(Long id);

    //CREATE
    public Reportes createReportes(Reportes reportes);
    //UPDATE
    public Reportes updateReportes(Reportes reportes);
    //DELETE
    public void deleteReportes(Long id);

    //OTHERS
    public List<Reportes> findReportesByRut(String rut);

}
