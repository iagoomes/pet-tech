package br.com.fiap.pettech.dominio.produto.dto;

import br.com.fiap.pettech.dominio.produto.entity.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public class ProdutoDto {
    private UUID id;
    @NotBlank(message = "Nome é um campo obrigatório")
    private String nome;
    private String descricao;
    private String urlImagem;
    @Positive(message = "Preço deve ser positivo")
    private BigDecimal preco;

    public ProdutoDto() {
    }

    public ProdutoDto(UUID id, String nome, String descricao, String urlImagem, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.urlImagem = urlImagem;
        this.preco = preco;
    }

    public ProdutoDto (Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.urlImagem = produto.getUrlImagem();
        this.preco = produto.getPreco();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
