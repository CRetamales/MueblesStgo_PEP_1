package cl.MSapp.services;

import cl.MSapp.entities.Horas;
import cl.MSapp.repositories.HorasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HorasServiceImp implements HorasService {

    @Autowired
    private HorasRepository horasRepository;

    @Override
    public List<Horas> listAllHoras() {
        return horasRepository.findAll();
    }

    @Override
    public Horas getHorasById(Long id) {
        return horasRepository.findById(id).orElse(null);
    }

    @Override
    public Horas createHoras(Horas horas) {
        return horasRepository.save(horas);
    }

    @Override
    public Horas updateHoras(Horas horas){
        Horas horasNueva = getHorasById(horas.getId());
        if(horasNueva != null){
            horasNueva.setId(horas.getId());
            horasNueva.setRut(horas.getRut());
            horasNueva.setFecha(horas.getFecha());
            horasNueva.setCategoria(horas.getCategoria());
            return horasRepository.save(horasNueva);
        }
        return null;
    }

    @Override
    public void deleteHoras(Long id) {
        Horas horas = getHorasById(id);
        if(horas != null){
            horasRepository.delete(horas);
        }
        return;
    }

    @Override
    public List<Horas> findHorasByRut(String rut) {
        return horasRepository.findByRut(rut);
    }

}
