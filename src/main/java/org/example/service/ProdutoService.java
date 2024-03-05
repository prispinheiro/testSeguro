package org.example.service;

import org.example.model.Categoria;
import org.example.model.Produto;
import org.example.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private MongoOperations mongoOperations;

    public List<Produto> listarProdutos() {
        return repository.findAll();
    }

    public Produto buscarProdutoPorId(final String id) {
        return repository.findById(id).orElseThrow();
    }

    public Produto criarProduto(final Produto produto) {
        produto.setPrecoTarifado(calculaPrecoTarifado(produto.getCategoria(), produto.getPrecoBase()));
        return repository.save(produto);
    }

    public Produto atualizarProduto(final String id, final Produto produtoAtualizado) {
        Produto produtoExistente = buscarProdutoPorId(id);
        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setCategoria(produtoAtualizado.getCategoria());
        produtoExistente.setPrecoBase(produtoAtualizado.getPrecoBase());
        produtoExistente.setPrecoTarifado(calculaPrecoTarifado(produtoAtualizado.getCategoria(), produtoAtualizado.getPrecoBase()));
        return repository.save(produtoExistente);
    }

    private double calculaPrecoTarifado(final String categoria, final double precoBase) {
        double precoTarifado = 0.0;

        Query categoriaQuery = new Query(Criteria.where("descricao").is(categoria));
        Categoria categoriaSeguro = mongoOperations.findOne(categoriaQuery, Categoria.class, "categoria");

        if (categoriaSeguro != null) {
            precoTarifado = precoBase +
                    (precoBase * categoriaSeguro.getIOF()) +
                    (precoBase * categoriaSeguro.getPIS()) +
                    (precoBase * categoriaSeguro.getCOFINS());
        }

        return precoTarifado;
    }
}
