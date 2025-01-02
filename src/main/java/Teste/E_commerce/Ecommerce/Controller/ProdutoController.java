package Teste.E_commerce.Ecommerce.Controller;

import Teste.E_commerce.Ecommerce.Produto.Produto;
import Teste.E_commerce.Ecommerce.Service.CarrinhoService;
import Teste.E_commerce.Ecommerce.Service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;
    private final CarrinhoService carrinhoService;

    public ProdutoController(ProdutoService produtoService, CarrinhoService carrinhoService) {
        this.produtoService = produtoService;
        this.carrinhoService = carrinhoService;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.listarProdutos();
        if (produtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    public ResponseEntity<Produto> adicionarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.salvarProduto(produto);

        carrinhoService.adicionarItemAoCarrinho(1L, novoProduto.getId(), 1);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}


