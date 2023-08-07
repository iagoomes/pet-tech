package br.com.fiap.pettech.dominio.produto.controller;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDto;
import br.com.fiap.pettech.dominio.produto.service.ProdutoService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProdutoService Service;

    public void findByIdDeveRetornarUmProdutoDTOCasoIdExista() throws Exception {
        UUID id = UUID.fromString("677e1376-70ac-4a87-8903-a541ae439df4");
        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setNome("PC");
        produtoDto.setDescricao("PC Gamer");
        produtoDto.setUrlImagem("URL 1");
        produtoDto.setPreco(new BigDecimal("5.000"));
        produtoDto.setId(id);

        Mockito.when(Service.findById(id)).thenReturn(produtoDto);

        ResultActions result = mockMvc.perform(get("produtos/{id}", id).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }
}
