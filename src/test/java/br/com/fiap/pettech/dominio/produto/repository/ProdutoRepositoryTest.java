package br.com.fiap.pettech.dominio.produto.repository;

import br.com.fiap.pettech.dominio.produto.entity.Produto;
import br.com.fiap.pettech.dominio.produto.service.exception.ControllerNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@DataJpaTest
public class ProdutoRepositoryTest {
    @Autowired
    private ProdutoRepository repository;

    @Test
    public void findByIdDeveRetornarObjetoCasoIdExista() {
        UUID id = UUID.fromString("677e1376-70ac-4a87-8903-a541ae439df4");
        Optional<Produto> produtoOptional = repository.findById(id);
        Assertions.assertTrue(produtoOptional.isPresent());
    }

    @Test
    public void findByIdDeveRetornarControllerNotFoundExceptionCasoIdNaoExista() {
        UUID id = UUID.fromString("677e1376-70ac-4a87-8903-a541ae439df0");
        repository.findById(id);
        Assertions.assertThrows(ControllerNotFoundException.class, () -> repository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado")));
    }

    @Test
    public void saveDeveSalvarObjetoCasoIdSejaNull() {
        Produto produto = new Produto();
        produto.setNome("PC");
        produto.setDescricao("PC Gamer");
        produto.setUrlImagem("URL 1");
        produto.setPreco(new BigDecimal("5.000"));
        produto.setId(null);

        var produtoSave = repository.save(produto);
        Assertions.assertNotNull(produtoSave.getId());
    }
}
