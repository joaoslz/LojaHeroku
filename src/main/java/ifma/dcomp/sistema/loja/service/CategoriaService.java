package ifma.dcomp.sistema.loja.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ifma.dcomp.sistema.loja.model.Categoria;
import ifma.dcomp.sistema.loja.repository.Categorias;

@Service
public class CategoriaService {
	
	@Autowired
	private Categorias categorias;
	
	
	public List<Categoria> todas() {
		return categorias.findAll();
	}


	@Transactional
	public void salva(Categoria categoria) {
		categorias.save(categoria );
		
	}

}
