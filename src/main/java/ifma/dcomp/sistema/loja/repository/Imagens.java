package ifma.dcomp.sistema.loja.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import ifma.dcomp.sistema.loja.model.Imagem;

public interface Imagens extends PagingAndSortingRepository<Imagem, Integer>{
	
	public Imagem findByNome(String nome );
	
}
