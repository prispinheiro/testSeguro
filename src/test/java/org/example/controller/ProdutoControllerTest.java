package org.example.controller;

import org.example.model.Produto;
import org.example.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProdutoControllerTest {

    @Mock
    private ProdutoService produtoService;

    @InjectMocks
    private ProdutoController produtoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("1", "Seguro de Vida Individual ABC", "VIDA", 100.0, 12.0));
        produtos.add(new Produto("2", "Seguro de Automóvel ABC", "AUTO", 150.0, 0.0));

        Mockito.when(produtoService.listarProdutos()).thenReturn(produtos);

        ResponseEntity<List<Produto>> response = produtoController.listarProdutos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(produtos, response.getBody());
    }

    @Test
    public void testGetProdutoById() {
        Produto produto = new Produto("1", "Seguro de Automóvel ABC", "AUTO", 110.0, 12.0);

        Mockito.when(produtoService.buscarProdutoPorId("1")).thenReturn(produto);

        ResponseEntity<Produto> response = produtoController.getProdutoById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(produto, response.getBody());
    }

    @Test
    public void testGetProdutoByIdNotFound() {
        Mockito.when(produtoService.buscarProdutoPorId("999")).thenReturn(null);

        ResponseEntity<Produto> response = produtoController.getProdutoById("999");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testCriarProduto() {
        Produto produto = new Produto("1", "Seguro de Vida Individual ABC", "VIDA", 200.0, 0.0);

        Mockito.when(produtoService.criarProduto(produto)).thenReturn(produto);

        ResponseEntity<Produto> response = produtoController.criarProduto(produto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(produto, response.getBody());
    }

    @Test
    public void testTryUpdateProductNotFound() {
        Mockito.when(produtoService.atualizarProduto("999", new Produto())).thenReturn(null);

        ResponseEntity<Produto> response = produtoController.atualizarProduto("999", new Produto());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}