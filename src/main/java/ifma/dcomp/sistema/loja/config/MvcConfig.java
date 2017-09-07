package ifma.dcomp.sistema.loja.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
		
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			
			registry.addRedirectViewController("/", "/produto/pesquisa");
			registry.addRedirectViewController("/produto/", "/produto/pesquisa");
			registry.addRedirectViewController("/produto", "/produto/pesquisa");
		}
		
		
		
		
	}