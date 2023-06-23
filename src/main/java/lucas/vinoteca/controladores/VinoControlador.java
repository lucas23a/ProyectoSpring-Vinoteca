package lucas.vinoteca.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lucas.vinoteca.excepciones.MiException;
import lucas.vinoteca.servicios.BodegaServicio;
import lucas.vinoteca.servicios.CepaServicio;
import lucas.vinoteca.servicios.VinoServicio;

@Controller
@RequestMapping("/vino")
public class VinoControlador {
    
    @Autowired
    private VinoServicio vinoServicio;

    @Autowired
    private CepaServicio cepaServicio;

    @Autowired
    private BodegaServicio bodegaServicio;

    @GetMapping("/registrar")
    public String resgistrar() {
        return "vino_form.html";
    }

    @PostMapping("/registro")
    public String resgistro(@RequestParam String nombre, @RequestParam String tipo, @RequestParam Integer aniada,
            @RequestParam Integer graduacionAlcohol, @RequestParam Integer precio, @RequestParam String descripcion,
            @RequestParam String idCepa, @RequestParam String idBodega) {

                try {
                    vinoServicio.crearVino(nombre, tipo, idCepa, idBodega, aniada, graduacionAlcohol, precio, descripcion);
                } catch (MiException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


        return "vino_form.html";
    }

}
