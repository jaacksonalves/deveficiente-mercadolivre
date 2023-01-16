package br.com.deveficientemercadolivre.produto;

import br.com.deveficientemercadolivre.categoria.Categoria;
import br.com.deveficientemercadolivre.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String nome;
    @Positive
    @Column(nullable = false)
    private BigDecimal valor;
    @Positive
    @Column(nullable = false)
    private int quantidade;
    @NotBlank
    @Column(nullable = false)
    private String descricao;
    @ElementCollection
    @Column(nullable = false)
    @Size(min = 3)
    private List<@NotBlank String> caracteristicas;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    public Produto(String nome, BigDecimal valor, int quantidade, String descricao, List<String> caracteristicas,
                   Categoria categoria, Usuario usuario) {
        Assert.state(StringUtils.hasText(nome), "Nome é obrigatório");
        Assert.state(Objects.nonNull(valor), "Valor é obrigatório");
        Assert.state(valor.compareTo(BigDecimal.ZERO) > 0, "Valor deve ser maior que zero");
        Assert.state(quantidade > 0, "Quantidadde deve ser maior que zero");
        Assert.state(StringUtils.hasText(descricao), "Descrição é obrigatório");
        Assert.state(Objects.nonNull(caracteristicas), "Características é obrigatório");
        Assert.state(caracteristicas.size() >= 3, "Características deve ter no mínimo 3 características");
        Assert.state(Objects.nonNull(categoria.getId()), "Categoria é obrigatório");
        Assert.state(Objects.nonNull(usuario.getId()), "Usuário é obrigatório");

        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.caracteristicas = caracteristicas;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    @Deprecated
    protected Produto() {
    }
}
