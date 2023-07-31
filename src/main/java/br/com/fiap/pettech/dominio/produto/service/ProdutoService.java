package br.com.fiap.pettech.dominio.produto.service;

import br.com.fiap.pettech.dominio.produto.entity.Produto;
import br.com.fiap.pettech.dominio.produto.repository.ProdutoRepository;
import br.com.fiap.pettech.dominio.produto.service.exception.ControllerNotFoundException;
import br.com.fiap.pettech.dominio.produto.service.exception.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Collection<Produto> findAll() {
        return repository.findAll();
    }

    public Produto findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado"));
    }

    public Produto save(Produto produto) {
        return repository.save(produto);
    }

    public Produto update(UUID id, Produto produto) {
        try {
            Produto buscaProduto = repository.getOne(id);
            buscaProduto.setNome(produto.getNome());
            buscaProduto.setDescricao(produto.getDescricao());
            buscaProduto.setUrlImagem(produto.getUrlImagem());
            buscaProduto.setPreco(produto.getPreco());
            buscaProduto = repository.save(buscaProduto);
            return buscaProduto;
        } catch (EntityNotFoundException ex) {
            throw new ControllerNotFoundException("Produto não encontrado, id:" + id);
        }

    }

    public void delete(UUID id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException("Produto não encontrado, id:" + id);
        } catch (DataIntegrityViolationException ex) {
            throw new DatabaseException("Violação de integridade da base");
        }
    }
}
