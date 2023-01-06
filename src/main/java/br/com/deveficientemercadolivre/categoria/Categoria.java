package br.com.deveficientemercadolivre.categoria;

import jakarta.persistence.*;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String nome;
    @ManyToOne
    private Categoria categoriaMae;

    public Categoria(String nome) {
        Assert.state(StringUtils.hasText(nome), "nome não pode ser nulo ou vazio");
        this.nome = nome;
    }

    public Categoria(String nome, Categoria categoriaMae) {
        Assert.state(StringUtils.hasText(nome), "nome não pode ser nulo ou vazio");
        Assert.state(Objects.nonNull(categoriaMae), "categoriaMae não pode ser nulo");
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }

    @Deprecated
    public Categoria() {
    }
}
