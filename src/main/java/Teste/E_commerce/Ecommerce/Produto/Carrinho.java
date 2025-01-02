package Teste.E_commerce.Ecommerce.Produto;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version // Controle de versão
    private int version;

    @ElementCollection
    @CollectionTable(name = "carrinho_itens", joinColumns = @JoinColumn(name = "carrinho_id"))
    @MapKeyJoinColumn(name = "produto_id")
    @Column(name = "quantidade")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarrinho> itens = new ArrayList<>();

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public void setItens(List<ItemCarrinho> itens) {
        this.itens = itens;
    }
}
