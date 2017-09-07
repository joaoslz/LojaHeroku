package ifma.dcomp.sistema.loja.repository.helper.produto;

import java.util.List;

import ifma.dcomp.sistema.loja.model.Produto;
import ifma.dcomp.sistema.loja.repository.filtro.ProdutoFilter;

public interface ProdutosQueries {
	
	List<Produto> filtrar(ProdutoFilter filtro);

}
