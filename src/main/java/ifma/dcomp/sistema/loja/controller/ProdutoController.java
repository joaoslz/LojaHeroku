package ifma.dcomp.sistema.loja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ifma.dcomp.sistema.loja.model.Fornecedor;
import ifma.dcomp.sistema.loja.model.Produto;
import ifma.dcomp.sistema.loja.repository.Fornecedores;
import ifma.dcomp.sistema.loja.repository.Produtos;
import ifma.dcomp.sistema.loja.service.ProdutoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	private static final String FORM_PRODUTO = "form-produto";

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private Fornecedores fornecedores;
	
	@GetMapping("/form")
	public String form(Produto produto ) {
		
		return FORM_PRODUTO;
	}
	
	
	@ModelAttribute("fornecedores")
	public List<Fornecedor> todoFornecedores() {
		return fornecedores.findAll();
	}
	
	@PostMapping("/salva")
	public String salva(@Validated Produto produto, Errors validacao, RedirectAttributes redirect) {
		
		if ( validacao.hasErrors()  ) {
			return FORM_PRODUTO;
		}
		
		produtoService.salva(produto );
		
		redirect.addFlashAttribute("mensagem", "Produto [" + produto.getNome() + "] foi salvo com sucesso");
		return "redirect:/produto/form";
		
	}
	
	
	
	@GetMapping("/lista")
	public ModelAndView listagem() {
		
		ModelAndView modelAndView = new ModelAndView("lista-produto");
		
		modelAndView.addObject("produtos", produtoService.todosProdutos() );
		
		return modelAndView;
		
	}
	
/*	@GetMapping("/edicao/{id}")
	public ModelAndView edicao(@PathVariable Integer id) {
		
		Produto produto = produtoService.buscaPor(id );
		
		ModelAndView modelAndView = new ModelAndView(FORM_PRODUTO);
		modelAndView.addObject("produto", produto );
		
		return modelAndView;
		
	}
*/	
	@GetMapping("/edicao/{id}")
	public ModelAndView edicao(@PathVariable("id") Produto produto) {
	
		ModelAndView modelAndView = new ModelAndView(FORM_PRODUTO);
		modelAndView.addObject("produto", produto );
		
		return modelAndView;
		
	}
	
	
	
	
}
