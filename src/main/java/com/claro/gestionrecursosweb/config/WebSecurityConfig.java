package com.claro.gestionrecursosweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import com.claro.gestionrecursosweb.domain.PasswordEncoder;
import com.claro.gestionrecursosweb.domain.SeguridadService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// Necesario para evitar que la seguridad se aplique a los resources
	// Como los css, imagenes y javascripts
	String[] resources = new String[] {  "/Estilos/**", "/Complementos/**", "/Scripts/**", "/img/**", "/js/**", "/layer/**" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests().antMatchers(resources).permitAll()
			.antMatchers("/").permitAll()
			//.antMatchers("/*").access("hasRole('ADMIN')")
			//.antMatchers("/*").access("hasRole('USUARIO') or hasRole('ADMIN')")
			.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/Seguridad/Ingresar")
				.loginProcessingUrl("/Seguridad/Ingresar").permitAll()
				.usernameParameter("usuario").passwordParameter("clave")
			.failureUrl("/Seguridad/Ingresar?error=true")
			.and()
			.logout()
				.logoutUrl("/Seguridad/CerrarSesion")
				.logoutSuccessUrl("/");
	}

	public PasswordEncoder passwordEncoder() {
		return new PasswordEncoder();
	}

	@Autowired
	private SeguridadService userDetailsService;
	@Autowired
    private CustomAuthenticationProvider authProvider;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }
}