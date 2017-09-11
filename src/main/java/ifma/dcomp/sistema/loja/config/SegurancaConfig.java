package ifma.dcomp.sistema.loja.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SegurancaConfig extends WebSecurityConfigurerAdapter {
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		  .authorizeRequests()
		  .antMatchers("/produto/lista").hasAnyRole("ADMIN", "USER")
		     .antMatchers("/produto/form").hasAnyRole("ADMIN")
		     .antMatchers("/produto/pesquisa").hasAnyRole("ADMIN")
		     .anyRequest().authenticated()
		     .and()
		  .formLogin().loginPage("/login")
		      .permitAll();
		     
	}
	
	
	@Autowired
	public void configurarUsuariosEmMemoria(AuthenticationManagerBuilder auth ) throws Exception {
		
		auth.inMemoryAuthentication()
		    .withUser("joao").password("joao").roles("ADMIN", "USER")
		    .and()
		    .withUser("maria").password("maria").roles("USER")
		    .and()
		    .withUser("davi").password("davi").roles("USER").disabled(true)
		    .and()
		    .withUser("julia").password("julia").roles("USER").accountLocked(true);
		
	}


	
/*	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		  .authorizeRequests()
		     .antMatchers("/produto/lista").hasAnyRole("ADMIN", "USER")
		     .antMatchers("/produto/form").hasAnyRole("ADMIN")
		     .antMatchers("/produto/pesquisa").hasAnyRole("ADMIN")
		     .anyRequest().authenticated()
		     .and()
		       .formLogin().loginPage("/login")
		    
		      .permitAll();
		     
	}
	

*/
}
