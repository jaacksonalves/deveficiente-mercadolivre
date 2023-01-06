package br.com.deveficientemercadolivre.categoria;

import br.com.deveficientemercadolivre.compartilhado.CampoUnico;
import br.com.deveficientemercadolivre.compartilhado.ExistePorCampo;
import jakarta.validation.constraints.NotBlank;

public record NovaCategoriaRequest(@NotBlank @CampoUnico(classeDominio = Categoria.class, campo = "nome")
                                   String nome,
                                   @ExistePorCampo(classeDominio = Categoria.class, campo = "id")
                                   Long idCategoriaMae) {
    public Categoria toModel(CategoriaRepository categoriaRepository) {
        // esse código depende especificamente da validação do @ExistePorCampo, pois se a pessoa enviou o ID da categoria mãe
        // inexistente, essa validação vai assegurar que não passe adiante. Enquanto se ela não enviar o id, método abaixo vai direto
        // pro orElseGet pois não vai encontrar a categoria mãe.
        return categoriaRepository
                .findById(idCategoriaMae)
                .map(categoriaMae -> new Categoria(nome, categoriaMae))
                .orElseGet(() -> new Categoria(nome));

        // outra implementação possível seria:
//        if (Objects.isNull(idCategoriaMae)) {
//            return new Categoria(nome);
//        }
//        var categoriaMae = categoriaRepository.findById(idCategoriaMae);
//        Assert.state(categoriaMae.isPresent(), "id da categoria mãe deveria estar correto aqui");
//        return new Categoria(nome, categoriaMae.get());
    }
}
