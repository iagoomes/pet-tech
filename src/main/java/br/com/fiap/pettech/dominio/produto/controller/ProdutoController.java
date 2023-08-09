package br.com.fiap.pettech.dominio.produto.controller;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDto;
import br.com.fiap.pettech.dominio.produto.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<Page<ProdutoDto>> findAll(@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
                                                    @RequestParam(value = "tamanho", defaultValue = "10") Integer tamanho) {
        PageRequest pageRequest = PageRequest.of(pagina, tamanho);
        var produtos = service.findAll(pageRequest);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoDto> findById(@PathVariable UUID id) {
        var dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> save(@RequestBody @Valid ProdutoDto dto) {
        var produtoSeved = service.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produtoSeved.getId()).toUri();
        return ResponseEntity.created(uri).body(produtoSeved);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProdutoDto> update(@PathVariable UUID id, @RequestBody @Valid ProdutoDto dto) {
        var produtoUpdate = service.update(id, dto);
        return ResponseEntity.ok(produtoUpdate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
