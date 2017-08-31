package ifma.dcomp.sistema.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ifma.dcomp.sistema.loja.model.Fornecedor;
import ifma.dcomp.sistema.loja.service.FornecedorService;

@Controller
@RequestMapping("/fornecedor")
public class FornecedorController {

	@Autowired
	private FornecedorService fornecedorService;

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Validated Fornecedor fornecedor, Errors validacao) {
	   
		if (validacao.hasErrors() ) {
//			return ResponseEntity.badRequest().body(validacao.getFieldErrors() );
			return ResponseEntity
					.badRequest()
					.body(validacao.getFieldError("nome").getDefaultMessage() );
				
		}
		//return ResponseEntity.badRequest().body("Erro salvando fornecedor");	
		fornecedor = fornecedorService.salva(fornecedor);
		
		return ResponseEntity.ok(fornecedor );	
		
	}
	
}
