package com.example.demo.security;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
//avec cet annotation on va securise au niveau des methodes avec exple : --@PreAuthorize("hasAuthority('ADMIN')")--
//on peut mettre cet declaration de cet annotation au niveau de notre class main
@EnableMethodSecurity(prePostEnabled = true) 
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain (HttpSecurity http) throws
	Exception
	{
		http.sessionManagement( session -> 
		session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.csrf( csrf -> csrf.disable()) 
		 	.cors(cors -> cors.configurationSource(new CorsConfigurationSource() 
			 	{
				 	 @Override
				 	 public CorsConfiguration getCorsConfiguration(HttpServletRequest request) 
				 	 {
					 	CorsConfiguration cors = new CorsConfiguration();
					 	 
					 	cors.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
					 	cors.setAllowedMethods(Collections.singletonList("*"));
					 	cors.setAllowCredentials(true); //attention a cet ligne : peut etre c'est faux
					 	cors.setAllowedHeaders(Collections.singletonList("*"));
					 	cors.setExposedHeaders(Collections.singletonList("Authorization"));
					 	 return cors;
				 	 }
			 	 }
		 	))
			
			.authorizeHttpRequests( requests -> requests
					.requestMatchers("/api/all/**").hasAnyAuthority("ADMIN","USER")
					.requestMatchers(HttpMethod.GET,"/api/getbyid/**").hasAnyAuthority("ADMIN","USER")
					//.requestMatchers(HttpMethod.POST,"/api/addprod/**").hasAnyAuthority("ADMIN")
					.requestMatchers(HttpMethod.PUT,"/api/updateprod/**").hasAuthority("ADMIN")
					.requestMatchers(HttpMethod.DELETE,"/api/delprod/**").hasAuthority("ADMIN")
					.anyRequest().authenticated())
			
			.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}

