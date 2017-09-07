package ifma.dcomp.sistema.loja.repository.helper.produto;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import ifma.dcomp.sistema.loja.model.Produto;
import ifma.dcomp.sistema.loja.repository.filtro.ProdutoFilter;

public class ProdutosImpl implements ProdutosQueries {
	
	@Autowired
	private EntityManager manager;

	@Override
	public List<Produto> filtrar(ProdutoFilter filtro) {
		
		Criteria criteria = manager.unwrap(Session.class ).createCriteria(Produto.class );
		
		if (filtro != null) {
			if (temSku(filtro)) {
				criteria.add(Restrictions.eq("sku", filtro.getSku() ));
			}
			
			if (temNome(filtro) ) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}

			if (fornecedorFoiSelecionado(filtro)) {
				criteria.add(Restrictions.eq("fornecedor", filtro.getFornecedor() ) );
			}

			if (filtro.getOrigem() != null) {
				criteria.add(Restrictions.eq("origem", filtro.getOrigem()) );
			}

			if (filtro.getPrecoDe() != null) {
				criteria.add(Restrictions.ge("preco", filtro.getPrecoDe()) );
			}

			if (filtro.getPrecoAte() != null) {
				criteria.add(Restrictions.le("preco", filtro.getPrecoAte()) );
			}
		}
		
		return criteria.list();
	}

	private boolean temNome(ProdutoFilter filtro) {
		return filtro.getNome() != null && !filtro.getNome().isEmpty();
	}

	private boolean temSku(ProdutoFilter filtro) {
		return filtro.getSku()!=null && !filtro.getSku().isEmpty();
	}
	
	private boolean fornecedorFoiSelecionado(ProdutoFilter filtro) {
		return filtro.getFornecedor() != null && filtro.getFornecedor().getId() != null;
	}

}
