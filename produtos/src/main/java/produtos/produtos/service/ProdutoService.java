package produtos.produtos.service;

import produtos.produtos.model.ProdutoModel;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {


    List<ProdutoModel> findAll();

    ProdutoModel save(ProdutoModel produtoModel);

    Optional<ProdutoModel> findById(Long produtoId);

    void delete(ProdutoModel produtoModel);
}
