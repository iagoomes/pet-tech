package br.com.fiap.pettech.dominio.produto.service;

import br.com.fiap.pettech.dominio.produto.entity.Produto;
import br.com.fiap.pettech.dominio.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Collection<Produto> findAll() {
        return repository.findAll();
    }

    public Optional<Produto> findById(UUID id) {
        return repository.findById(id);
    }

    public Produto save(Produto produto) {
        return repository.save(produto);
    }

    public Produto update(UUID id, Produto produto) {
        Produto buscaProduto = repository.getOne(id);
        buscaProduto.setNome(produto.getNome());
        buscaProduto.setDescricao(produto.getDescricao());
        buscaProduto.setUrlImagem(produto.getUrlImagem());
        buscaProduto.setPreco(produto.getPreco());
        buscaProduto = repository.save(buscaProduto);
        return buscaProduto;
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
