package br.com.fiap.pettech.dominio.produto.service;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDto;
import br.com.fiap.pettech.dominio.produto.entity.Produto;
import br.com.fiap.pettech.dominio.produto.repository.ProdutoRepository;
import br.com.fiap.pettech.dominio.produto.service.exception.ControllerNotFoundException;
import br.com.fiap.pettech.dominio.produto.service.exception.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Page<ProdutoDto> findAll(PageRequest pagina) {
        return repository.findAll(pagina).map(ProdutoDto::new);
    }

    public ProdutoDto findById(UUID id) {
        Produto produto = repository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado"));
        return new ProdutoDto(produto);
    }

    public ProdutoDto save(ProdutoDto dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setUrlImagem(dto.getUrlImagem());
        produto.setPreco(dto.getPreco());

        var produtoSaved = repository.save(produto);
        return new ProdutoDto(produtoSaved);
    }

    public ProdutoDto update(UUID id, ProdutoDto dto) {
        try {
            Produto buscaProduto = repository.getReferenceById(id);
            buscaProduto.setNome(dto.getNome());
            buscaProduto.setDescricao(dto.getDescricao());
            buscaProduto.setUrlImagem(dto.getUrlImagem());
            buscaProduto.setPreco(dto.getPreco());
            buscaProduto = repository.save(buscaProduto);
            return new ProdutoDto(buscaProduto);
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
