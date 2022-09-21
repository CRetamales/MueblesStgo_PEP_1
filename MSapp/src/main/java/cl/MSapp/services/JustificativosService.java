package cl.MSapp.services;

import cl.MSapp.entities.Justificativos;

import java.util.List;

public interface JustificativosService {
    //CRUD
    //READ ALL JUSTIFICATIVOS
    public List<Justificativos> listAllJustificativos();
    //BY ID
    public Justificativos getJustificativosById(Long id);

    //CREATE
    public Justificativos createJustificativos(Justificativos justificativos);
    //UPDATE
    public Justificativos updateJustificativos(Justificativos justificativos);
    //DELETE
    public void deleteJustificativos(Long id);

    //OTHERS
    public List<Justificativos> findJustificativosByRut(String rut);

}
