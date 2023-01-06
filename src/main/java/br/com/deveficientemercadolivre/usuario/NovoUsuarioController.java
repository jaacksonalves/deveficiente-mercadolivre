package br.com.deveficientemercadolivre.usuario;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class NovoUsuarioController {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public NovoUsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public void novoUsuario(@RequestBody @Valid NovoUsuarioRequest request) {
        var usuario = request.toModel();
        usuarioRepository.save(usuario);
    }
}
