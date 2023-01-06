package br.com.deveficientemercadolivre.categoria;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class NovaCategoriaController {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public NovaCategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public void novaCategoria(@RequestBody @Valid NovaCategoriaRequest request) {
        var categoria = request.toModel(categoriaRepository);
        categoriaRepository.save(categoria);
    }

}
