package Teste.E_commerce.Ecommerce.Service;

import Teste.E_commerce.Ecommerce.Produto.Carrinho;
import Teste.E_commerce.Ecommerce.Produto.ItemCarrinho;
import Teste.E_commerce.Ecommerce.Produto.Produto;
import Teste.E_commerce.Ecommerce.Repository.CarrinhoRepository;
import Teste.E_commerce.Ecommerce.Repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {
    @Autowired
    private final CarrinhoRepository carrinhoRepository;
    private final ProdutoRepository produtoRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository, ProdutoRepository produtoRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.produtoRepository = produtoRepository;
    }

    public Carrinho buscarCarrinhoPorId(Long id) {
        return carrinhoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Carrinho não encontrado."));
    }

    public Carrinho criarCarrinhoPadrao() {
        Carrinho carrinho = new Carrinho();
        return carrinhoRepository.save(carrinho);
    }

    public void atualizarQuantidade(Long carrinhoId, Long itemId, int novaQuantidade) {
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId)
                .orElseThrow(() -> new EntityNotFoundException("Carrinho não encontrado"));

        ItemCarrinho item = carrinho.getItens().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado no carrinho"));

        if (novaQuantidade > 0) {
            item.setQuantidade(novaQuantidade);
        } else {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero");
        }

        carrinhoRepository.save(carrinho);
    }


    public void adicionarItemAoCarrinho(Long carrinhoId, Long produtoId, int quantidade) {
        Carrinho carrinho = buscarCarrinhoPorId(carrinhoId);
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));

        Optional<ItemCarrinho> itemExistente = carrinho.getItens().stream()
                .filter(item -> item.getProduto().getId().equals(produtoId))
                .findFirst();

        if (itemExistente.isPresent()) {
            itemExistente.get().setQuantidade(itemExistente.get().getQuantidade() + quantidade);
        } else {
            carrinho.getItens().add(new ItemCarrinho(produto, quantidade));
        }

        carrinhoRepository.save(carrinho);
    }

    public List<Carrinho> buscarTodosCarrinhos() {
        return carrinhoRepository.findAll();
    }

    public void removerItemDoCarrinho(Long carrinhoId, Long itemId) {
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId)
                .orElseThrow(() -> new EntityNotFoundException("Carrinho não encontrado"));

        carrinho.getItens().removeIf(item -> item.getId().equals(itemId));
        carrinhoRepository.save(carrinho);
    }
}




