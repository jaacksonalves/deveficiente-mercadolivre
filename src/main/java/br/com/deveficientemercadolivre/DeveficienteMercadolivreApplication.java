package br.com.deveficientemercadolivre;

import br.com.deveficientemercadolivre.usuario.NovoUsuarioRequest;
import br.com.deveficientemercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeveficienteMercadolivreApplication implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public static void main(String[] args) {
        SpringApplication.run(DeveficienteMercadolivreApplication.class, args);
    }

    @Override
    public void run(String... args) {
        var usuario = new NovoUsuarioRequest("jackson@email.com", "123456").toModel();
        usuarioRepository.save(usuario);
    }
}
