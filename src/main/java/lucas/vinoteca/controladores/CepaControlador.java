package lucas.vinoteca.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lucas.vinoteca.excepciones.MiException;
import lucas.vinoteca.servicios.CepaServicio;

@Controller
@RequestMapping("/cepa")
public class CepaControlador {
    
    @Autowired
    private CepaServicio cepaServicio;

    @GetMapping("/registrar")
    public String resgistrar(){
        return "cepa_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre){
           try {
            cepaServicio.crearCepa(nombre);
            return "cepa_form.html";
        } catch (MiException e) {
            e.printStackTrace();
        }
           return "index.html";
    }

}
