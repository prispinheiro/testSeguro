package org.example.service;

import org.example.model.Categoria;
import org.example.model.Produto;
import org.example.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repository;

    @Mock
    private MongoOperations mongoOperations;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBuscarProdutoPorId() {
        Produto produto = new Produto("1", "Seguro de Vida Individual ABC", "VIDA", 100.0, 120.0);

        Mockito.when(repository.findById("1")).thenReturn(Optional.of(produto));

        Produto produtoRetornado = produtoService.buscarProdutoPorId("1");

        assertEquals(produto, produtoRetornado);
    }

    @Test
    public void testCriarProduto() {
        Produto produto = new Produto("1", "Seguro de Vida Individual ABC", "VIDA", 100.0, 0.0);

        Categoria categoria = new Categoria();
        categoria.setDescricao("VIDA");
        categoria.setIOF(0.1);
        categoria.setPIS(0.05);
        categoria.setCOFINS(0.05);

        Mockito.when(mongoOperations.findOne(new Query(Criteria.where("descricao").is("VIDA")), Categoria.class, "categoria")).thenReturn(categoria);
        Mockito.when(repository.save(produto)).thenReturn(produto);

        Produto produtoCriado = produtoService.criarProduto(produto);

        assertEquals(120, produtoCriado.getPrecoTarifado());
    }
}