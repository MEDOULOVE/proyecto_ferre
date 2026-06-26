package Usuario.usuario.controller;
import jakarta.validation.Valid;
import Usuario.usuario.entity.Usuario;
import Usuario.usuario.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import Usuario.usuario.dto.LoginRequest;
@CrossOrigin(origins = "*")


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Usuario> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return service.buscarUsuarioPorId(id);
    }

    @PostMapping
    public Usuario crear(@Valid @RequestBody Usuario usuario) {
        return service.guardar(usuario);
    }
    @GetMapping("/email/{email}")
    public Usuario buscarPorEmail(@PathVariable String email) {

        return service.buscarPorEmail(email);
    }
    @PostMapping("/login")
    public Usuario login(@RequestBody LoginRequest request) {

        return service.login(
                request.getEmail(),
                request.getPassword()
        );
    }


    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Long id,
                              @Valid @RequestBody Usuario usuario) {
        usuario.setId(id);
        return service.guardar(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}