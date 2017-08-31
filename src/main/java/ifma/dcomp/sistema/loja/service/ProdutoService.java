package ifma.dcomp.sistema.loja.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifma.dcomp.sistema.loja.model.Fornecedor;
import ifma.dcomp.sistema.loja.model.Produto;
import ifma.dcomp.sistema.loja.repository.Fornecedores;
import ifma.dcomp.sistema.loja.repository.Produtos;

@Service
public class ProdutoService {
	
	@Autowired
	private Produtos produtos;
	
	@Autowired
	private Fornecedores fornecedores;

	
	public void salva(Produto produto) {
//		String sku = produto.getSku();
//		produto.setSku( sku.toUpperCase() );
		produtos.save(produto );		
	}


	public List<Produto> todosProdutos() {
		
		return produtos.findAll();
	}

	public Produto buscaPor(Integer id) {
		return this.produtos.findOne(id );
	}


}
