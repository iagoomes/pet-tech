package br.com.fiap.pettech.dominio.produto.repository;

import br.com.fiap.pettech.dominio.produto.entity.Produto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepository {

    private static final Set<Produto> produtos;

    static {
        produtos = new HashSet<>();

        Produto produto1 = new Produto("Produto 1", "Descrição 1", "URL Imagem 1", new BigDecimal("19.99"));
        Produto produto2 = new Produto("Produto 2", "Descrição 2", "URL Imagem 2", new BigDecimal("29.99"));

        produtos.add(produto1);
        produtos.add(produto2);
    }

    @Override
    public Optional<Produto> findById(UUID id) {
        return produtos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public Set<Produto> findAll() {
        return produtos;
    }

    @Override
    public Produto save(Produto produto) {
        produto.setId(UUID.randomUUID());
        produtos.add(produto);
        return produto;
    }

    @Override
    public Produto update(UUID id, Produto produto) {
        Produto produtoBuscado = produtos.stream().filter(p -> p.getId().equals(id)).findFirst().get();
        produtoBuscado.setNome(produto.getNome());
        produtoBuscado.setDescricao(produto.getDescricao());
        produtoBuscado.setPreco(produto.getPreco());
        produtoBuscado.setUrlImagem(produto.getUrlImagem());
        return produtoBuscado;
    }

    @Override
    public void delete(UUID id) {
        produtos.removeIf(p -> p.getId().equals(id));
    }
}
