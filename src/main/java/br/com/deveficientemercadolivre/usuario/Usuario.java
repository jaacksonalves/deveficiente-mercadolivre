package br.com.deveficientemercadolivre.usuario;

import jakarta.persistence.*;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String emailLogin;
    @Embedded
    @Column(nullable = false)
    private SenhaUsuario senhaUsuario;
    @Column(nullable = false, updatable = false)
    private final LocalDateTime instanteCriacao = LocalDateTime.now();

    public Usuario(String emailLogin, SenhaUsuario senhaUsuario) {
        Assert.state(StringUtils.hasText(emailLogin), "emailLogin não pode ser nulo ou vazio");
        Assert.notNull(senhaUsuario, "senhaUsuario não pode ser nulo");
        this.emailLogin = emailLogin;
        this.senhaUsuario = senhaUsuario;
    }

    @Deprecated
    public Usuario() {
    }
}
