package br.com.deveficientemercadolivre.categoria;

import br.com.deveficientemercadolivre.compartilhado.CampoUnico;
import br.com.deveficientemercadolivre.compartilhado.ExistePorCampo;
import jakarta.validation.constraints.NotBlank;
import org.springframework.util.Assert;

import java.util.Objects;

public record NovaCategoriaRequest(@NotBlank @CampoUnico(classeDominio = Categoria.class, campo = "nome")
                                   String nome,
                                   @ExistePorCampo(classeDominio = Categoria.class, campo = "id")
                                   Long idCategoriaMae) {
    public Categoria toModel(CategoriaRepository categoriaRepository) {
        if (Objects.isNull(idCategoriaMae)) {
            return new Categoria(nome);
        }
        var categoriaMae = categoriaRepository.findById(idCategoriaMae);
        Assert.state(categoriaMae.isPresent(), "id da categoria mãe deveria estar correto aqui");
        return new Categoria(nome, categoriaMae.get());
    }
}
