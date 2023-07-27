package br.com.fiap.pettech.dominio.produto.controller;

import br.com.fiap.pettech.dominio.produto.entity.Produto;
import br.com.fiap.pettech.dominio.produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<Collection<Produto>> findAll() {
        var produtos = service.findAll();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Produto>> findById(@PathVariable UUID id) {
        var produto = service.findById(id);
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody Produto produto) {
        var produtoSeved = service.save(produto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produtoSeved.getId()).toUri();
        return ResponseEntity.created(uri).body(produtoSeved);
    }

    @PutMapping("{id}")
    public ResponseEntity<Optional<Produto>> update(@PathVariable UUID id, @RequestBody Produto produto){
        Optional<Produto> produtoUpdate = service.update(id, produto);
        return ResponseEntity.ok(produtoUpdate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
