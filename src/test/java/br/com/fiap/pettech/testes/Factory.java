package br.com.fiap.pettech.testes;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDto;
import br.com.fiap.pettech.dominio.produto.entity.Produto;

import java.math.BigDecimal;

public class Factory {
    public static Produto createProduto() {
        return new Produto("Iphone", "Descrição 1", "Url 1", BigDecimal.ONE);
    }

    public static ProdutoDto createProdutoDto() {
        Produto produto = createProduto();
        return new ProdutoDto(produto);
    }
}
