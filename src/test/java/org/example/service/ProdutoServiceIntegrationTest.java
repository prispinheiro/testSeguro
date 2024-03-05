package org.example.service;

import org.example.model.Categoria;
import org.example.model.Produto;
import org.example.repository.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Optional;

@SpringBootTest
public class ProdutoServiceIntegrationTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private MongoOperations mongoOperations;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    public void testBuscarProdutoPorId() {
        Produto produto = new Produto("1", "Produto 1", "VIDA", 10.0, 12.0);
        Mockito.when(produtoRepository.findById("1")).thenReturn(Optional.of(produto));

        Produto produtoRetornado = produtoService.buscarProdutoPorId("1");

        Assertions.assertEquals(produto, produtoRetornado);
    }

    @Test
    public void testAtualizarProduto() {
        Produto produtoExistente = new Produto("3", "Produto 3", "VIDA", 20.0, 24.0);
        Produto produtoAtualizado = new Produto("3", "Produto Atualizado", "AUTO", 25.0, 30.0);
        Categoria categoriaAtualizada = new Categoria();
        categoriaAtualizada.setDescricao("AUTO");
        categoriaAtualizada.setIOF(0.1);
        categoriaAtualizada.setPIS(0.05);
        categoriaAtualizada.setCOFINS(0.05);

        Mockito.when(produtoRepository.findById("3")).thenReturn(Optional.of(produtoExistente));
        Mockito.when(mongoOperations.findOne(new Query(Criteria.where("descricao").is("AUTO")), Categoria.class, "categoria")).thenReturn(categoriaAtualizada);
        Mockito.when(produtoRepository.save(produtoExistente)).thenReturn(produtoExistente);

        Produto produtoAtualizadoSalvo = produtoService.atualizarProduto("3", produtoAtualizado);

        Assertions.assertEquals("Produto Atualizado", produtoAtualizadoSalvo.getNome());
        Assertions.assertEquals("AUTO", produtoAtualizadoSalvo.getCategoria());
        Assertions.assertEquals(25.0, produtoAtualizadoSalvo.getPrecoBase());
        Assertions.assertEquals(30.0, produtoAtualizadoSalvo.getPrecoTarifado());
    }
}

