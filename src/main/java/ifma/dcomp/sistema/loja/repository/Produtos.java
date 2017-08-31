package ifma.dcomp.sistema.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifma.dcomp.sistema.loja.model.Produto;


@Repository
public interface Produtos extends JpaRepository<Produto, Integer> {

}
