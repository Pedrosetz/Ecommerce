package Teste.E_commerce.Ecommerce.Controller;

import Teste.E_commerce.Ecommerce.Produto.Carrinho;
import Teste.E_commerce.Ecommerce.Service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {
    @Autowired
    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrinho> buscarCarrinho(@PathVariable Long id) {
        Carrinho carrinho = carrinhoService.buscarCarrinhoPorId(id);
        return ResponseEntity.ok(carrinho);
    }

    @PatchMapping("/{carrinhoId}/itens/{itemId}")
    public ResponseEntity<Void> atualizarQuantidade(@PathVariable Long carrinhoId,
                                                    @PathVariable Long itemId,
                                                    @RequestBody Map<String, Integer> quantidadePayload) {
        int novaQuantidade = quantidadePayload.get("quantidade");
        carrinhoService.atualizarQuantidade(carrinhoId, itemId, novaQuantidade);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/adicionar")
    public ResponseEntity<Carrinho> adicionarProdutoNoCarrinho(
            @PathVariable Long id,
            @RequestParam Long produtoId,
            @RequestParam int quantidade) {

        carrinhoService.adicionarItemAoCarrinho(id, produtoId, quantidade);
        Carrinho carrinhoAtualizado = carrinhoService.buscarCarrinhoPorId(id);
        return ResponseEntity.ok(carrinhoAtualizado);
    }

    @DeleteMapping("/{carrinhoId}/itens/{itemId}")
    public ResponseEntity<Void> removerItem(@PathVariable Long carrinhoId, @PathVariable Long itemId) {
        carrinhoService.removerItemDoCarrinho(carrinhoId, itemId);
        return ResponseEntity.noContent().build();
    }
}

