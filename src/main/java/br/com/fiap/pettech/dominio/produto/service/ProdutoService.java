package br.com.fiap.pettech.dominio.produto.service;

import br.com.fiap.pettech.dominio.produto.entity.Produto;
import br.com.fiap.pettech.dominio.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class ProdutoService {
    @Qualifier("produtoRepositoryImpl")
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

    public Optional<Produto> update(UUID id, Produto produto) {
        Optional<Produto> produtoOptional = this.findById(id);
        if (produtoOptional.isPresent()) {
            Produto produtoUpdate = repository.update(id, produto);
            return Optional.of(produtoUpdate);
        }
        return Optional.empty();
    }

    public void delete(UUID id) {
        repository.delete(id);
    }
}
