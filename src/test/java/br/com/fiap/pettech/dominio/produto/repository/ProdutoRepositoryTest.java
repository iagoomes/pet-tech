package br.com.fiap.pettech.dominio.produto.repository;

import br.com.fiap.pettech.dominio.produto.entity.Produto;
import br.com.fiap.pettech.dominio.produto.service.exception.ControllerNotFoundException;
import br.com.fiap.pettech.testes.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;
import java.util.UUID;

@DataJpaTest
public class ProdutoRepositoryTest {
    @Autowired
    private ProdutoRepository repository;
    private UUID idExistente;
    private UUID idNaoExistente;
    private PageRequest pageRequest;
    private long countTotalProdutos;
    private String nomeAtualizado;
    private Produto produto;

    @BeforeEach
    void setUp() throws Exception {
        idExistente = UUID.fromString("677e1376-70ac-4a87-8903-a541ae439df4");
        idNaoExistente = UUID.fromString("677e1376-70ac-4a87-8903-a541ae439df0");
        pageRequest = PageRequest.of(0, 10);
        countTotalProdutos = 5L;
        nomeAtualizado = "Atualização nome do produto";
        produto = Factory.createProduto();
    }
    @Test
    public void findAllDeveRetornarListaDeObjetosCadastrados() {
        Page<Produto> produtos = repository.findAll(this.pageRequest);
        Assertions.assertEquals(produtos.getTotalElements(), countTotalProdutos);
    }

    @Test
    public void findByIdDeveRetornarObjetoCasoIdExista() {
        Optional<Produto> produtoOptional = repository.findById(this.idExistente);
        Assertions.assertTrue(produtoOptional.isPresent());
    }

    @Test
    public void findByIdDeveRetornarControllerNotFoundExceptionCasoIdNaoExista() {
        Assertions.assertThrows(ControllerNotFoundException.class, () -> repository.findById(idNaoExistente).orElseThrow(
                () -> new ControllerNotFoundException("Produto não encontrado")));
    }

    @Test
    public void saveDeveSalvarObjetoCasoIdSejaNull() {
        Produto produto = this.produto;
        produto.setId(null);
        var produtoSave = repository.save(produto);
        Assertions.assertNotNull(produtoSave.getId());
    }

    @Test
    public void saveDeveAtualizarObjetoCasoIdNaoSejaNull(){
        Produto produto = this.produto;
        produto.setId(this.idExistente);
        produto.setNome(nomeAtualizado);
        Produto produtoSave = repository.save(produto);

        Assertions.assertEquals(produtoSave.getNome(), this.nomeAtualizado);
    }

    @Test
    public void deleteDeveDeletarObjetoCasoExista() {
        repository.deleteById(this.idExistente);
        Optional<Produto> produtoOptional = repository.findById(this.idExistente);
        Assertions.assertFalse(produtoOptional.isPresent());
    }
}
