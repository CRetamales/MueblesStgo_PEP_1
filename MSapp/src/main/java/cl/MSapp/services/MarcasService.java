package cl.MSapp.services;

import cl.MSapp.entities.Marcas;

import java.util.List;

public interface MarcasService {
    //CRUD
    //READ ALL MARCAS
    public List<Marcas> listAllMarcas();
    //BY ID
    public Marcas getMarcasById(Long id);

    //CREATE
    public Marcas createMarcas(Marcas marcas);
    //UPDATE
    public Marcas updateMarcas(Marcas marcas);
    //DELETE
    public void deleteMarcas(Long id);

    //OTHERS
    public Marcas findMarcasByRut(String rut);
}
