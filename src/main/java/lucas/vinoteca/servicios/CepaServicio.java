package lucas.vinoteca.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jakarta.transaction.Transactional;
import lucas.vinoteca.entidades.Cepa;
import lucas.vinoteca.excepciones.MiException;
import lucas.vinoteca.repositorios.CepaRepository;

@Service
public class CepaServicio {

    @Autowired
    private CepaRepository cepaRepositorio;

    @Transactional
    public void crearCepa(String nombre) throws MiException{

        validar(nombre);
        Cepa cepa = new Cepa();
        cepa.setNombre(nombre);
        cepaRepositorio.save(cepa);
    }

    public List<Cepa> listarCepas() {
        List<Cepa> cepas = new ArrayList();
        cepas = cepaRepositorio.findAll();
        return cepas;
    }

    @Transactional
    public void modificarCepa(String id, String nombre) throws MiException{
        validar(nombre);
        Optional<Cepa> respuesta = cepaRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Cepa cepa = respuesta.get();
            cepa.setNombre(nombre);
            cepaRepositorio.save(cepa);
        }
    }

    private void validar(String nombre) throws MiException{
         if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nommbre no puede estar vacio");
        }
    }
    
}