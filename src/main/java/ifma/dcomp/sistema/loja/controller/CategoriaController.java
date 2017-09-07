package ifma.dcomp.sistema.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ifma.dcomp.sistema.loja.model.Categoria;
import ifma.dcomp.sistema.loja.service.CategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	
	@GetMapping
	public String categorias(Categoria categoria ) {
		return "categoria/cadastro-categoria";
	}
	
	@PostMapping
	public ModelAndView salva(Categoria categoria, RedirectAttributes redirect) {
		
		categoriaService.salva(categoria );
		redirect.addFlashAttribute("mensagem", "Categoria Salva com sucesso");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:categorias");

		
		return modelAndView;
		
	}
	
	
	@ModelAttribute("categorias")
	public Iterable<Categoria> todas() {
		return categoriaService.todas();
		
	}
	
	@GetMapping("/edicao/{id}")
	public ModelAndView edicao(@PathVariable("id") Categoria categoria ) {
	
		ModelAndView modelAndView = new ModelAndView("categoria/cadastro-categoria");
		modelAndView.addObject("categoria", categoria );
		
		return modelAndView;
	}
	
	
	
	
	
	
	
	
	
		
	

}
