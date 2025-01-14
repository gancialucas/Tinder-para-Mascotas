package edu.egg.tinder.controladores;

import edu.egg.tinder.entidades.Zona;
import edu.egg.tinder.errores.ErrorServicio;
import edu.egg.tinder.repositorios.ZonaRepositorio;
import edu.egg.tinder.servicios.UsuarioServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ZonaRepositorio zonaRepositorio;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
    @GetMapping("/inicio")
    public String inicio() {
        return "inicio.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {
        if (error != null) {
            model.put("error", "Nombre de usuario o clave incorrecto.");
        }
        if (logout != null) {
            model.put("logout", "Ha salido correctamente de la plataforma.");
        }
        return "login.html";
    }

    @GetMapping("/registro")
    public String registro(ModelMap model) {
        List<Zona> zonas = zonaRepositorio.findAll();
        model.put("zonas", zonas);
        return "registro.html";
    }

    @PostMapping("/registrar")
    public String registrar(ModelMap model, MultipartFile archivo, @RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2, @RequestParam String idZona) {

        try {
            usuarioServicio.registrar(archivo, nombre, apellido, mail, clave1, clave2, idZona);
        } catch (ErrorServicio ex) {
            List<Zona> zonas = zonaRepositorio.findAll();
            model.put("zonas", zonas);

            model.put("error", ex.getMessage());
            model.put("nombre", nombre);
            model.put("apellido", apellido);
            model.put("mail", mail);
            model.put("clave1", clave1);
            model.put("clave2", clave2);

            return "registro.html";
        }
        model.put("titulo", "¡Bienvenido a Tinder de Mascotas!");
        model.put("descripcion", "¡Tu usuario fue registrado con éxito!");

        return "exito.html";
    }
}
