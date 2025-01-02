package Teste.E_commerce.Ecommerce.Repository;

import Teste.E_commerce.Ecommerce.Produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long>  {
}
