package lucas.vinoteca.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lucas.vinoteca.excepciones.MiException;
import lucas.vinoteca.servicios.BodegaServicio;

@Controller
@RequestMapping("/bodega")
public class BodegaControlador {
    
@Autowired
private BodegaServicio bodegaServicio;

    @GetMapping("/registrar")
    public String registrar(){
        return "bodega_form.html";
    }

        @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String region, @RequestParam String descripcion){
           try {
           bodegaServicio.crearBodega(nombre, region, descripcion);
            return "bodega_form.html";
        } catch (MiException e) {
            e.printStackTrace();
        }
           return "index.html";
    }
}
