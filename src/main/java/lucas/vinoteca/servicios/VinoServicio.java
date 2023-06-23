package lucas.vinoteca.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jakarta.transaction.Transactional;
import lucas.vinoteca.entidades.Bodega;
import lucas.vinoteca.entidades.Cepa;
import lucas.vinoteca.entidades.Vino;
import lucas.vinoteca.excepciones.MiException;
import lucas.vinoteca.repositorios.BodegaRepository;
import lucas.vinoteca.repositorios.CepaRepository;
import lucas.vinoteca.repositorios.VinoRepository;

@Service
public class VinoServicio {

    @Autowired
    private VinoRepository vinoRepositorio;

    @Autowired
    private CepaRepository cepaRepositorio;

    @Autowired
    private BodegaRepository bodegaRepositorio;

    @Transactional
    public void crearVino(String nombre, String tipo, String idCepa, String idBodega, Integer aniada,
            Integer graduacionAlcohol, Integer precio, String descripcion) throws MiException {

        validar(nombre, tipo, idCepa, idBodega, aniada, graduacionAlcohol, precio, descripcion);

        Cepa cepa = cepaRepositorio.findById(idCepa).get();
        Bodega bodega = bodegaRepositorio.findById(idBodega).get();

        Vino vino = new Vino();
        vino.setNombre(nombre);
        vino.setTipo(tipo);
        vino.setAniada(aniada);
        vino.setGraduacionAlcohol(graduacionAlcohol);
        vino.setPrecio(precio);
        vino.setDescripcion(descripcion);
        vino.setAlta(new Date());
        vino.setBodega(bodega);
        vino.setCepa(cepa);

        vinoRepositorio.save(vino);
    }

    public List<Vino> listarVinos() {
        List<Vino> vinos = new ArrayList();
        vinos = vinoRepositorio.findAll();
        return vinos;
    }

    @Transactional
    public void modificarVino(String id, String nombre, String tipo, String idCepa,
            String idBodega, Integer aniada, Integer graduacionAlcohol, Integer precio,
            String descripcion) throws MiException {

        validar(nombre, tipo, idCepa, idBodega, aniada, graduacionAlcohol, precio, descripcion);

        Optional<Vino> respuesta = vinoRepositorio.findById(id);
        Optional<Cepa> respuestaCepa = cepaRepositorio.findById(idCepa);
        Optional<Bodega> respuestaBodega = bodegaRepositorio.findById(idBodega);

        Cepa cepa = new Cepa();
        Bodega bodega = new Bodega();

        if (respuestaCepa.isPresent()) {
            cepa = respuestaCepa.get();
        }

        if (respuestaBodega.isPresent()) {
            bodega = respuestaBodega.get();
        }

        if (respuesta.isPresent()) {
            Vino wine = respuesta.get();
            wine.setNombre(nombre);
            wine.setTipo(tipo);
            wine.setAniada(aniada);
            wine.setGraduacionAlcohol(graduacionAlcohol);
            wine.setPrecio(precio);
            wine.setDescripcion(descripcion);
            wine.setBodega(bodega);
            wine.setCepa(cepa);
            vinoRepositorio.save(wine);
        }
    }

    @Transactional
    public void actualizarPrecio(String id, double precio) {
        Optional<Vino> respuesta = vinoRepositorio.findById(id);
        if (respuesta.isPresent()) { // verifica que haya un objeto y realiza los cambios
            Vino vino = respuesta.get();
            vino.setPrecio(precio);
            vinoRepositorio.save(vino);
        }
    }

    private void validar(String nombre, String tipo, String idCepa, String idBodega, Integer aniada,
            Integer graduacionAlcohol, Integer precio, String descripcion) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nommbre no puede estar vacio");
        }
        if (tipo.isEmpty() || tipo == null) {
            throw new MiException("El Tipo no puede estar vacio");
        }
        if (idCepa.isEmpty() || idCepa == null) {
            throw new MiException("La cepa no puede ser nula o estar vacia");
        }
        if (idBodega.isEmpty() || idBodega == null) {
            throw new MiException("La bodega no puede ser nula o estar vacia");
        }
        if (aniada == null) {
            throw new MiException("La añada no puede ser nula o estar vacia");
        }
        if (graduacionAlcohol == null) {
            throw new MiException("La graduacion alcoholica no puede ser nula o estar vacia");
        }
        if (precio == null) {
            throw new MiException("El precio no puede ser nulo");
        }
        if (descripcion.isEmpty() || descripcion == null) {
            throw new MiException("La descripcion no puede ser nula o estar vacia");
        }
    }
}
