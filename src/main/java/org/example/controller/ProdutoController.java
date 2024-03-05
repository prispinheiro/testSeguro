package org.example.controller;

import org.example.model.Produto;
import org.example.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos(){
        List<Produto> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping(params = "id")
    public ResponseEntity<Produto> getProdutoById(@RequestParam(name= "id") String id){
        Produto produto = produtoService.buscarProdutoPorId(id);
        if(produto != null){
            return ResponseEntity.ok(produto);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto){
        Produto novoProduto = produtoService.criarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @PutMapping(params = "id")
    public ResponseEntity<Produto> atualizarProduto(@RequestParam(name= "id") String id, @RequestBody Produto produto){
        Produto produtoAtualizado = produtoService.atualizarProduto(id, produto);
        if(produtoAtualizado != null){
            return ResponseEntity.ok(produtoAtualizado);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
