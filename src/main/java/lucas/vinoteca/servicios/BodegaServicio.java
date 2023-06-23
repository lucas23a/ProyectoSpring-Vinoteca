package lucas.vinoteca.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jakarta.transaction.Transactional;
import lucas.vinoteca.entidades.Bodega;
import lucas.vinoteca.excepciones.MiException;
import lucas.vinoteca.repositorios.BodegaRepository;

@Service
public class BodegaServicio {
    @Autowired
    private BodegaRepository bodegaRepositorio;

    @Transactional
    public void crearBodega(String nombre, String region, String descripcion) throws MiException {

        validar(nombre, region, descripcion);

        Bodega bodega = new Bodega();
        bodega.setNombre(nombre);
        bodega.setRegion(region);
        bodega.setDescripcion(descripcion);
        bodegaRepositorio.save(bodega);
    }

    public List<Bodega> listarBodegas() {
        List<Bodega> bodegas = new ArrayList();
        bodegas = bodegaRepositorio.findAll();
        return bodegas;
    }

    @Transactional
    public void modificarBodega(String id, String nombre, String region, String descripcion) throws MiException {

        validar(nombre, region, descripcion);

        Optional<Bodega> respuesta = bodegaRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Bodega bodega = respuesta.get();
            bodega.setNombre(nombre);
            bodega.setRegion(region);
            bodega.setDescripcion(descripcion);
            bodegaRepositorio.save(bodega);
        }
    }

    private void validar(String nombre, String region, String descripcion) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nommbre no puede estar vacio");
        }
          if (region.isEmpty() || region == null) {
            throw new MiException("La region no puede estar vacia");
        }
          if (descripcion.isEmpty() || descripcion == null) {
            throw new MiException("La descripcion no puede estar vacia");
        }
    }
}
