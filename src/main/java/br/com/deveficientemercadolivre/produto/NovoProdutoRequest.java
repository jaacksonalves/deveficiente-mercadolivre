package br.com.deveficientemercadolivre.produto;

import br.com.deveficientemercadolivre.categoria.Categoria;
import br.com.deveficientemercadolivre.categoria.CategoriaRepository;
import br.com.deveficientemercadolivre.compartilhado.ExistePorCampo;
import br.com.deveficientemercadolivre.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public record NovoProdutoRequest(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
                                 @NotNull @Positive int quantidade,
                                 @NotNull @Size(min = 3) List<@NotBlank String> caracteristicas,
                                 @NotBlank @Length(max = 1000) String descricao,
                                 @NotNull @ExistePorCampo(classeDominio = Categoria.class, campo = "id") Long categoriaId) {
    public Produto toModel(CategoriaRepository categoriaRepository, Usuario usuario) {
        var categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(()-> new ResponseStatusException(BAD_REQUEST, "Categoria não encontrada"));
        return new Produto(nome, valor, quantidade, descricao, caracteristicas, categoria, usuario);
    }
}
