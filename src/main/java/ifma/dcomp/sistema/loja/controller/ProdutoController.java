package ifma.dcomp.sistema.loja.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ifma.dcomp.sistema.loja.model.Categoria;
import ifma.dcomp.sistema.loja.model.DetalhesImagemProduto;
import ifma.dcomp.sistema.loja.model.Fornecedor;
import ifma.dcomp.sistema.loja.model.Origem;
import ifma.dcomp.sistema.loja.model.Produto;
import ifma.dcomp.sistema.loja.repository.Fornecedores;
import ifma.dcomp.sistema.loja.repository.filtro.ProdutoFilter;
import ifma.dcomp.sistema.loja.service.CategoriaService;
import ifma.dcomp.sistema.loja.service.ImagemProdutoUploadService;
import ifma.dcomp.sistema.loja.service.ProdutoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	private static final String FORM_PRODUTO = "form-produto";

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private Fornecedores fornecedores;
	
	@Autowired
	private CategoriaService categoriaService;
	
	
	@GetMapping("/form")
	public String form(Produto produto ) {
		return FORM_PRODUTO;
	}
	
	@ModelAttribute("fornecedores")
	public List<Fornecedor> todoFornecedores() {
		return fornecedores.findAll();
	}
	
	@ModelAttribute("todasCategorias")
	public List<Categoria> todasCategorias() {
		return categoriaService.todas();
	}
	
	@ModelAttribute("origens")
	public Origem[] todasOrigens() {
		return Origem.values();
	}
	
	@PostMapping("/salva")
	public String salva(@Validated Produto produto, Errors validacao, 
			                       RedirectAttributes redirect,
						         
			                       MultipartFile imagemDoProduto,
						           HttpServletRequest request) {
		
		if ( validacao.hasErrors()  ) {
			return FORM_PRODUTO;
		}
		
    	produto = produtoService.salva(produto );

    	if ( foiSelecionadaA(imagemDoProduto) ) {
    		
    		ImagemProdutoUploadService arquivoUpload = 
    				new ImagemProdutoUploadService(imagemDoProduto );
    		
    		arquivoUpload.salvaImagem(produto.getId(), request );
    		
    		final DetalhesImagemProduto detalhesImagem = arquivoUpload.criaDetalhesImagemLivro();
    		
    		produto.setDetalhesImagem(detalhesImagem);
    		
    		// salva o produto com imagem
    		produtoService.salva(produto);
    	}
    	
		redirect.addFlashAttribute("mensagem", "Produto [" + produto.getNome() + "] foi salvo com sucesso");
    	String rota = produto.ehNovo() ? "redirect:/produto/form" : "redirect:/produto/lista";

    	return rota;
	}


	private boolean foiSelecionadaA(MultipartFile imagem) {
		return (imagem != null ) && ( !imagem.isEmpty() );
	}
	
	
	
	
	@GetMapping("/edicao/{id}")
	public ModelAndView edicao(@PathVariable("id") Produto produto) {
	
		ModelAndView modelAndView = new ModelAndView(FORM_PRODUTO);
		modelAndView.addObject("produto", produto );
		
		return modelAndView;
	}

	@GetMapping("/lista")
	public ModelAndView listagem() {
		
		ModelAndView modelAndView = new ModelAndView("lista-produto");
		modelAndView.addObject("produtos", produtoService.todosProdutos() );
		return modelAndView;
		
	}

	
	@GetMapping("/pesquisa")
	public ModelAndView pesquisar(ProdutoFilter produtoFilter, BindingResult result ) {

		ModelAndView modelAndView = new ModelAndView("produto/pesquisa-produtos");
//		mv.addObject("produtos", produtoService.todosProdutos() );
		
		modelAndView.addObject("produtos", produtoService.filtrar(produtoFilter) );
		return modelAndView;
	}

}
