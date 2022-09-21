package cl.MSapp.services;

import cl.MSapp.entities.Horas;

import java.util.List;

public interface HorasService {
    //CRUD
    //READ ALL HORAS
    public List<Horas> listAllHoras();
    //BY ID
    public Horas getHorasById(Long id);

    //CREATE
    public Horas createHoras(Horas horas);
    //UPDATE
    public Horas updateHoras(Horas horas);
    //DELETE
    public void deleteHoras(Long id);

    //OTHERS
    public List<Horas> findHorasByRut(String rut);

}
