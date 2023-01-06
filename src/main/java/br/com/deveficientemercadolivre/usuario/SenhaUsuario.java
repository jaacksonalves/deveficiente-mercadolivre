package br.com.deveficientemercadolivre.usuario;

import jakarta.persistence.Embeddable;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Embeddable
public class SenhaUsuario {
    private String senhaCriptografada;

    public SenhaUsuario(String senhaLimpa) {
        Assert.state(StringUtils.hasText(senhaLimpa), "senhaNormal não pode ser nulo ou vazio");
        this.senhaCriptografada = new Base64().encodeAsString(senhaLimpa.getBytes());
    }

    @Deprecated
    public SenhaUsuario() {
    }

}
