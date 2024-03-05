package org.example.controller;

import org.example.model.Produto;
import org.example.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProdutoService produtoService;

    @Test
    public void testListarProdutos() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/produto")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetProdutoById() throws Exception {
        Produto produto = new Produto("1", "Seguro de Vida Individual ABC", "VIDA", 100.0, 0.0);
        produtoService.criarProduto(produto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/produto")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Seguro de Vida Individual ABC"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.precoTarifado").value(103.2));
    }

    @Test
    public void testCriarProduto() throws Exception {
        Produto produto = new Produto("2", "Seguro Residencial ABC", "RESIDENCIAL", 150.0, 160.5);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/produto")
                        .content("{\"id\":\"2\",\"nome\":\"Seguro Residencial ABC\",\"categoria\":\"RESIDENCIAL\",\"precoBase\":150.0,\"precoTarifado\":160.5}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Seguro Residencial ABC"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoria").value("RESIDENCIAL"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.precoTarifado").value(160.5));
    }

    @Test
    public void testAtualizarProduto() throws Exception {
        Produto produto = new Produto("3", "Seguro Patrimonial ABC", "PATRIMONIAL", 20.0, 24.0);
        produtoService.criarProduto(produto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/produto")
                        .param("id", "3")
                        .content("{\"id\":\"3\",\"nome\":\"Seguro Patrimonial DEF\",\"categoria\":\"PATRIMONIAL\",\"precoBase\":25.0}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Seguro Patrimonial DEF"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoria").value("PATRIMONIAL"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.precoBase").value(25.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.precoTarifado").value(27.0));
    }
}
