package produtos.produtos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import produtos.produtos.model.ProdutoModel;
import produtos.produtos.repository.ProdutoRepository;
import produtos.produtos.service.ProdutoService;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public List<ProdutoModel> findAll() {
        return produtoRepository.findAll();
    }

    @Override
    public ProdutoModel save(ProdutoModel produtoModel) {

        return produtoRepository.save(produtoModel);
    }

    @Override
    public Optional<ProdutoModel> findById(Long produtoId) {
        return produtoRepository.findById(produtoId);
    }

    @Override
    public void delete(ProdutoModel produtoModel) {
        produtoRepository.delete(produtoModel);
    }


}
