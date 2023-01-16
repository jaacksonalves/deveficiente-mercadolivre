package br.com.deveficientemercadolivre.produto;


import br.com.deveficientemercadolivre.categoria.CategoriaRepository;
import br.com.deveficientemercadolivre.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class NovoProdutoController {

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public NovoProdutoController(CategoriaRepository categoriaRepository,
                                 ProdutoRepository produtoRepository,
                                 UsuarioRepository usuarioRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public void novoProduto(@Valid @RequestBody NovoProdutoRequest request) {
        var usuario = usuarioRepository.findByEmailLogin("jackson@email.com")
                .orElseThrow(()-> new IllegalArgumentException("Usuário não encontrado"));
        var novoProduto = request.toModel(categoriaRepository, usuario);
        produtoRepository.save(novoProduto);
    }


}
