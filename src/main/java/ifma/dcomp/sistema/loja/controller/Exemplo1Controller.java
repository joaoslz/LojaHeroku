package ifma.dcomp.sistema.loja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Exemplo1Controller {
	
	
	@GetMapping("/ola")
	public String olaMundo() {
		return "ola";
	}

}
