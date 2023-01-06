package br.com.deveficientemercadolivre.usuario;

import br.com.deveficientemercadolivre.compartilhado.CampoUnico;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record NovoUsuarioRequest(@NotBlank @Email @CampoUnico(classeDominio = Usuario.class, campo = "emailLogin") String emailLogin,
                                 @NotBlank @Length(min = 6) String senha) {
    public Usuario toModel() {
        return new Usuario(emailLogin, new SenhaUsuario(senha));
    }
}
