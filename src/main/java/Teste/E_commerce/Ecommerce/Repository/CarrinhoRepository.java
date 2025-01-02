package Teste.E_commerce.Ecommerce.Repository;

import Teste.E_commerce.Ecommerce.Produto.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
}
