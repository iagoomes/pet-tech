package br.com.fiap.pettech.dominio.produto.service;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDto;
import br.com.fiap.pettech.dominio.produto.entity.Produto;
import br.com.fiap.pettech.dominio.produto.repository.ProdutoRepository;
import br.com.fiap.pettech.dominio.produto.service.exception.ControllerNotFoundException;
import br.com.fiap.pettech.testes.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class ProdutoServiceTest {
    @InjectMocks
    private ProdutoService service;
    @Mock
    private ProdutoRepository repository;

    private UUID idExistente;
    private UUID idNaoExistente;
    private PageRequest pageRequest;
    private PageImpl<Produto> page;
    private ProdutoDto produtoDto;
    private Produto produto;
    private String nomeAtualizado;

    @BeforeEach
    void setUp() throws Exception {
        idExistente = UUID.fromString("677e1376-70ac-4a87-8903-a541ae439df4");
        idNaoExistente = UUID.fromString("677e1376-70ac-4a87-8903-a541ae439df0");
        pageRequest = PageRequest.of(0, 10);
        nomeAtualizado = "Atualização nome do produto";
        produto = Factory.createProduto();
        produtoDto = Factory.createProdutoDto();
        page = new PageImpl<>(List.of(produto));
        nomeAtualizado = "Produto atualizado";

        Mockito.when(repository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(produto));
        Mockito.when(repository.findAll((PageRequest) ArgumentMatchers.any())).thenReturn(page);
        Mockito.when(repository.findById(this.idNaoExistente)).thenReturn(Optional.empty());
    }

    @Test
    public void findAllDeveRetornarUmaListaDeProdutosDTO() {
        Page<ProdutoDto> produtosDto = service.findAll(this.pageRequest);
        Assertions.assertNotNull(produtosDto);
    }
    @Test
    public void findByIdDeveRetornarUmProdutoDtoAoBuscarPorId() {
        ProdutoDto produtoDto = service.findById(idExistente);
        Assertions.assertNotNull(produtoDto);
    }
    @Test
    public void findByIdDeveRetornarUmaExcecaoAoBuscarPorIdQueNaoExiste() {
        Assertions.assertThrows(ControllerNotFoundException.class, () -> service.findById(idNaoExistente));
    }
}
