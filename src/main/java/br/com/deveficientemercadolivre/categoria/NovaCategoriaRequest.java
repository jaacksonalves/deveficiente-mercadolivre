package br.com.deveficientemercadolivre.categoria;

import br.com.deveficientemercadolivre.compartilhado.CampoUnico;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public record NovaCategoriaRequest(@NotBlank @CampoUnico(classeDominio = Categoria.class, campo = "nome")
                                   String nome,
                                   Long idCategoriaMae) {
    public Categoria toModel(CategoriaRepository categoriaRepository) {
        if (Objects.nonNull(idCategoriaMae)) {
            var categoriaMae = categoriaRepository.findById(idCategoriaMae)
                    .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Foi passado um id de categoria mae que não existe"));
            return new Categoria(nome, categoriaMae);
        }
        return new Categoria(nome);
    }
}
