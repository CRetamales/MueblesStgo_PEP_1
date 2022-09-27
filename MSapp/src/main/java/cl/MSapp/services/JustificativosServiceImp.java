package cl.MSapp.services;

import cl.MSapp.entities.Justificativos;
import cl.MSapp.repositories.JustificativosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JustificativosServiceImp implements JustificativosService {

    @Autowired
    private JustificativosRepository justificativosRepository;

    @Override
    public List<Justificativos> listAllJustificativos() {
        return justificativosRepository.findAll();
    }

    @Override
    public Justificativos getJustificativosById(Long id) {
        return justificativosRepository.findById(id).orElse(null);
    }

    @Override
    public Justificativos createJustificativos(Justificativos justificativos) {
        return justificativosRepository.save(justificativos);
    }

    @Override
    public Justificativos updateJustificativos(Justificativos justificativos) {
        Justificativos justificativoNuevo = getJustificativosById(justificativos.getId());
        if(justificativoNuevo != null){
            justificativoNuevo.setId(justificativos.getId());
            justificativoNuevo.setRut(justificativos.getRut());
            justificativoNuevo.setFecha(justificativos.getFecha());
            justificativoNuevo.setCategoria(justificativos.getCategoria());
            return justificativosRepository.save(justificativoNuevo);
        }
        return null;
    }

    @Override
    public void deleteJustificativos(Long id) {
        Justificativos justificativo = getJustificativosById(id);
        if(justificativo != null){
            justificativosRepository.delete(justificativo);
        }
        return;
    }

    @Override
    public List<Justificativos> findJustificativosByRut(String rut) {
        return justificativosRepository.findByRut(rut);
    }
}
