package ifma.dcomp.sistema.loja.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ifma.dcomp.sistema.loja.model.Produto;
import ifma.dcomp.sistema.loja.repository.Produtos;
import ifma.dcomp.sistema.loja.repository.filtro.ProdutoFilter;

@Service
public class ProdutoService {
	
	@Autowired
	private Produtos produtos;
	
	@Transactional
	public Produto salva(Produto produto) {
		//executar várias ações no banco
		
		return produtos.save(produto );		
	}


	@Transactional(readOnly=true)
	public List<Produto> todosProdutos() {
		return produtos.findAll();
	}

	public Produto buscaPor(Integer id) {
		return this.produtos.findOne(id );
	}

    @Transactional(readOnly=true)
	public List<Produto> filtrar(ProdutoFilter produtoFilter) {
	    return produtos.filtrar(produtoFilter );
	}

  

}
