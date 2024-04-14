package produtos.produtos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import produtos.produtos.model.ProdutoModel;
import produtos.produtos.service.ProdutoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listar(){
        List<ProdutoModel> produtoModels = produtoService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(produtoModels);
    }

    @GetMapping("/{idProduto}")
    public ResponseEntity<Object> buscar(@PathVariable(value = "idProduto") Long idProduto){
        Optional<ProdutoModel> produtoModelOptional = produtoService.findById(idProduto);

        if (!produtoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não Encontrado!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(produtoModelOptional);
    }

    @PostMapping
    public ResponseEntity<ProdutoModel> adicionar(@RequestBody ProdutoModel produtoModel){
        ProdutoModel produtoModel1 = produtoService.save(produtoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoModel1);
    }

    @PutMapping("/{idProduto}")
    public ResponseEntity<Object> atualizar(@PathVariable(value = "idProduto") Long produtoId, @RequestBody ProdutoModel produtoModel){
        Optional<ProdutoModel> produtoModelOptional = produtoService.findById(produtoId);

        if(!produtoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto nao encontrado!");
        }else {
            var produtoModel1 = produtoModelOptional.get();
            produtoModel1.setNome(produtoModel.getNome());
            produtoModel1.setDescricao(produtoModel.getDescricao());
            produtoModel1.setPreco(produtoModel.getPreco());
            produtoModel1.setQuantidadeEstoque(produtoModel.getQuantidadeEstoque());
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.save(produtoModel1));
        }
    }

    @DeleteMapping("/{idProduto}")
    public ResponseEntity<Object> apagar(@PathVariable(value = "idProduto") Long produtoId){
        Optional<ProdutoModel> produtoModelOptional = produtoService.findById(produtoId);

        if (!produtoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto nao encontrado!");
        }else {
            produtoService.delete(produtoModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Produto deletado!");
        }
    }

}
