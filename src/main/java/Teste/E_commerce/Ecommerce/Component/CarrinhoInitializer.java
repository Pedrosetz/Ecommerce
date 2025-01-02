package Teste.E_commerce.Ecommerce.Component;

import Teste.E_commerce.Ecommerce.Service.CarrinhoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CarrinhoInitializer implements CommandLineRunner {

    private final CarrinhoService carrinhoService;

    public CarrinhoInitializer(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (carrinhoService.buscarTodosCarrinhos().isEmpty()) {
            carrinhoService.criarCarrinhoPadrao();
            System.out.println("Carrinho inicial criado.");
        }
    }
}