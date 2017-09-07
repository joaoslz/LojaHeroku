package ifma.dcomp.sistema.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifma.dcomp.sistema.loja.model.Produto;
import ifma.dcomp.sistema.loja.repository.helper.produto.ProdutosQueries;


@Repository
public interface Produtos extends JpaRepository<Produto, Integer>, ProdutosQueries {

}
