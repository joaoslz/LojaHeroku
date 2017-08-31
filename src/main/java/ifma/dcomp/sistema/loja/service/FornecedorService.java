package ifma.dcomp.sistema.loja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifma.dcomp.sistema.loja.model.Fornecedor;
import ifma.dcomp.sistema.loja.repository.Fornecedores;

@Service
public class FornecedorService {

	@Autowired
	private Fornecedores fornecedores;
	
	public Fornecedor salva(Fornecedor fornecedor) {
		return fornecedores.saveAndFlush(fornecedor );
	}

}
