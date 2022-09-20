package cl.MSapp.services;

import cl.MSapp.entities.Marcas;
import cl.MSapp.repositories.MarcasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarcasServiceImp implements MarcasService {

    @Autowired
    private MarcasRepository marcasRepository;

    @Override
    public List<Marcas> listAllMarcas() {
        return marcasRepository.findAll();
    }

    @Override
    public Marcas getMarcasById(Long id) {
        return marcasRepository.findById(id).orElse(null);
    }

    @Override
    public Marcas createMarcas(Marcas marcas) {
        return marcasRepository.save(marcas);
    }

    @Override
    public Marcas updateMarcas(Marcas marcas) {
        Marcas marcaNueva = getMarcasById(marcas.getId());
        if(marcaNueva != null){
            marcaNueva.setId(marcas.getId());
            marcaNueva.setRut(marcas.getRut());
            marcaNueva.setFecha(marcas.getFecha());
            marcaNueva.setHora(marcas.getHora());
            return marcasRepository.save(marcaNueva);
        }
        return null;
    }

    @Override
    public void deleteMarcas(Long id) {
        Marcas marca = getMarcasById(id);
        if(marca != null){
            marcasRepository.delete(marca);
        }
        return;
    }

    @Override
    public Marcas findMarcasByRut(String rut) {
        return marcasRepository.findByRut(rut);
    }
}
