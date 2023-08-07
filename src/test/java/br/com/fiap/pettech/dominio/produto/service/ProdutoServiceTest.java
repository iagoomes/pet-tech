package br.com.fiap.pettech.dominio.produto.service;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDto;
import br.com.fiap.pettech.dominio.produto.entity.Produto;
import br.com.fiap.pettech.dominio.produto.repository.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class ProdutoServiceTest {
    @InjectMocks
    private ProdutoService service;
    @Mock
    private ProdutoRepository repository;

    @Test
    public void findByIdDeveRetornarUmProdutoDtoAoBuscarPorId() {
        UUID id = UUID.fromString("677e1376-70ac-4a87-8903-a541ae439df4");
        Produto produto = new Produto();
        produto.setNome("PC");
        produto.setDescricao("PC Gamer");
        produto.setUrlImagem("URL 1");
        produto.setPreco(new BigDecimal("5.000"));
        produto.setId(null);

        Mockito.when(repository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(produto));

        ProdutoDto produtoDto = service.findById(id);
        Assertions.assertNotNull(produtoDto);
    }
}
