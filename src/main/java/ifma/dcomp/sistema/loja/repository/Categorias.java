package ifma.dcomp.sistema.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifma.dcomp.sistema.loja.model.Categoria;


public interface Categorias extends JpaRepository<Categoria, Integer> {
	
}
